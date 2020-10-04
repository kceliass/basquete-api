$(function () {
  // quando o documento estiver pronto/carregado
  var url = "http://localhost:8080/api"

  // função para exibir jogos na tabela
  function exibir_jogos() {
    $.ajax({
      url: url + "/jogos",
      method: "GET",
      dataType: "json", // os dados são recebidos no formato json
      success: listar, // chama a função listar para processar o resultado
      error: function () {
        alert("erro ao ler dados, verifique o backend");
      },
    });
    function listar(jogos) {
      // esvaziar o corpo da tabela
      var linhas = "";
      // $('#corpoTabelaJogos').empty();
      // tornar a tabela visível
      mostrar_conteudo("tabelaJogos");
      // percorrer a lista de jogos retornados;
      for (var jogo of jogos) {
        //i vale a posição no vetor
        lin = `<tr>
                  <td>${jogo.id}</td>
                  <td>${jogo.placar}</td>
                  <td>${jogo.minimoTemporada}</td>
                  <td>${jogo.maximoTemporada}</td>
                  <td>${jogo.quebraRecordeMinimo}</td>
                  <td>${jogo.quebraRecordeMaximo}</td>
                </tr>`;
        linhas += lin;
      }
      $("#corpoTabelaJogos").html(linhas);
    }
  }

  // função que mostra um conteúdo e esconde os outros
  function mostrar_conteudo(identificador) {
    // esconde todos os conteúdos
    $("#tabelaJogos").addClass("invisible");
    $("#conteudoInicial").addClass("invisible");
    // torna o conteúdo escolhido visível
    $(`#${identificador}`).removeClass("invisible");
  }

  // código para mapear o click do link Listar
  $(document).on("click", "#linkListarJogos", function () {
    exibir_jogos();
  });

  // código para mapear click do link Inicio
  $(document).on("click", "#linkInicio", function () {
    mostrar_conteudo("conteudoInicial");
  });

  // código para mapear click do botão incluir jogo
  $(document).on("click", "#btIncluirJogo", function () {
    //pegar dados da tela
    jogo = $("#campoJogo").val();
    placar = $("#campoPlacar").val();
    minimoTemporada = $("#campoMinimoTemporada").val();
    maximoTemporada = $("#campoMaximoTemporada").val();
    material = $("#campoMaterial").val();
    // preparar dados no formato json
    var dados = JSON.stringify({
      jogo: jogo,
      placar: placar,
      minimoTemporada: minimoTemporada,
      maximoTemporada: maximoTemporada,
    });
    // fazer requisição para o back-end
    $.ajax({
      url: url + "/jogos/jogo",
      type: "POST",
      dataType: "json", // os dados são recebidos no formato json
      contentType: "application/json", // tipo dos dados enviados
      data: dados, // estes são os dados enviados
      success: jogoIncluido, // chama a função listar para processar o resultado
      error: erroAoIncluir,
    });
    function jogoIncluido(retorno) {
      console.log(retorno)
      // a operação deu certo?
      // informar resultado de sucesso
      alert("Jogo incluída com sucesso!");
      // limpar os campos
      $("#campoJogo").val();
      $("#campoPlacar").val();
      $("#campoMinimoTemporada").val();
      $("#campoMaximoTemporada").val();
    }
    function erroAoIncluir(retorno) {
      var error = JSON.parse(retorno.responseText);

      alert(`ERRO:  ${error.message}`);
    }
  });

  // código a ser executado quando a janela de inclusão de pessoas for fechada
  $("#modalIncluirJogo").on("hide.bs.modal", function (e) {
    // se a página de listagem não estiver invisível
    if (!$("#tabelaJogo").hasClass("invisible")) {
      // atualizar a página de listagem
      exibir_jogos();
    }
  });

  // a função abaixo é executada quando a página abre
  mostrar_conteudo("conteudoInicial");
});
