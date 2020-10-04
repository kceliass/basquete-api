package com.entra.core.basqueteapi.service;

import com.entra.core.basqueteapi.dto.JogoBasqueteDTO;
import com.entra.core.basqueteapi.exception.NotFoundException;
import com.entra.core.basqueteapi.model.JogoBasquete;
import com.entra.core.basqueteapi.repository.IJogoBasqueteRepository;
import com.entra.core.basqueteapi.util.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.entra.core.basqueteapi.util.ValidationUtils.validateInteger;

@Service
public class JogoBasqueteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogoBasqueteService.class);

    private final IJogoBasqueteRepository IJogoBasqueteRepository;

    public JogoBasqueteService(IJogoBasqueteRepository IJogoBasqueteRepository) {
        this.IJogoBasqueteRepository = IJogoBasqueteRepository;
    }

    public JogoBasquete incluir(JogoBasqueteDTO jogoBasqueteDTO) {
        this.validate(jogoBasqueteDTO);

        JogoBasquete jogoBasquete = new JogoBasquete(
                jogoBasqueteDTO.getPlacar()
        );

        this.validarQuebrasDeRecorde(jogoBasquete);

        return this.IJogoBasqueteRepository.save(jogoBasquete);
    }

    private void validarQuebrasDeRecorde(JogoBasquete jogoBasquete) {
        try {
            JogoBasquete ultimoJogo = this.listaUltimoPorDataCriacao();

            int placar = jogoBasquete.getPlacar();

            if (placar > ultimoJogo.getMaximoTemporada()) {
                jogoBasquete.setMaximoTemporada(placar);
                jogoBasquete.setMinimoTemporada(ultimoJogo.getMinimoTemporada());
                jogoBasquete.setQuebraRecordeMaximo(ultimoJogo.getQuebraRecordeMaximo() + 1);
                jogoBasquete.setQuebraRecordeMinimo(ultimoJogo.getQuebraRecordeMinimo());
            }

            if (placar < ultimoJogo.getMinimoTemporada()) {
                jogoBasquete.setMinimoTemporada(placar);
                jogoBasquete.setMaximoTemporada(ultimoJogo.getMaximoTemporada());
                jogoBasquete.setQuebraRecordeMinimo(ultimoJogo.getQuebraRecordeMinimo() + 1);
                jogoBasquete.setQuebraRecordeMaximo(ultimoJogo.getQuebraRecordeMaximo());
            }
        } catch (Exception e) {
            LOGGER.error("Erro ao validar quebras de recorde {}", e.getCause());
            return;
        }
    }

    private void validate(JogoBasqueteDTO jogoBasqueteDTO) {
        validateInteger(jogoBasqueteDTO.getPlacar(), "Placar nao pode ser nulo");
    }

    public List<JogoBasquete> listarJogos() {
        return this.IJogoBasqueteRepository.findAll();
    }

    public JogoBasquete listaUm(Integer id) throws NotFoundException {
        ValidationUtils.validateInteger(id, "Id nao pode ser nulo");

        return this.IJogoBasqueteRepository.findById(id).orElseThrow(() -> new NotFoundException("Jogo Basquete não encontrado com esse Id. Id: " + id));
    }

    public String deleteJogoPorId(Integer id) {
        ValidationUtils.validateInteger(id, "Id nao pode ser nulo");

        this.IJogoBasqueteRepository.deleteById(id);

        return "Jogo deletado com sucesso!";
    }

    private JogoBasquete listaUltimoPorDataCriacao() throws NotFoundException {
        return this.IJogoBasqueteRepository.findFirstByOrderByDataCriacaoDesc().orElseThrow(() -> new NotFoundException("Não há registros de jogos"));
    }
}
