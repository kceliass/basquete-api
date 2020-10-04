package com.entra.core.basqueteapi.service;

import com.entra.core.basqueteapi.dto.JogoBasqueteDTO;
import com.entra.core.basqueteapi.exception.NotFoundException;
import com.entra.core.basqueteapi.model.JogoBasquete;
import com.entra.core.basqueteapi.repository.IJogoBasqueteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class JogoBasqueteServiceTest {

    @Mock
    private IJogoBasqueteRepository iJogoBasqueteRepository;
    @InjectMocks
    private JogoBasqueteService jogoBasqueteService;

    @Test
    void incluir() {
        JogoBasqueteDTO jogo = new JogoBasqueteDTO(null, 12, null, null, null, null, null);

        when(iJogoBasqueteRepository.save(any(JogoBasquete.class))).thenReturn(new JogoBasquete(12));

        JogoBasquete retorno = jogoBasqueteService.incluir(jogo);

        assertEquals(12, retorno.getPlacar());
        assertEquals(12, retorno.getMaximoTemporada());
        assertEquals(12, retorno.getMaximoTemporada());
        assertEquals(0, retorno.getQuebraRecordeMaximo());
        assertEquals(0, retorno.getQuebraRecordeMinimo());
    }

    @Test
    void listarJogos() {
        List<JogoBasquete> listaJogos = new ArrayList<>(Arrays.asList(
                new JogoBasquete(12),
                new JogoBasquete(15),
                new JogoBasquete(13)
        ));

        when(iJogoBasqueteRepository.findAll()).thenReturn(listaJogos);

        List<JogoBasquete> listagem = jogoBasqueteService.listarJogos();

        assertThat(listagem).isNotEmpty();
        assertEquals(3, listagem.size());
        assertEquals(12, listagem.get(0).getPlacar());
        assertEquals(15, listagem.get(1).getPlacar());
        assertEquals(13, listagem.get(2).getPlacar());
    }

    @Test
    void listaUm() throws NotFoundException {
        JogoBasquete jogo = new JogoBasquete(1,12);

        when(iJogoBasqueteRepository.findById(anyInt())).thenReturn(Optional.of(jogo));

        JogoBasquete jogoBuscado = jogoBasqueteService.listaUm(1);

        assertNotNull(jogoBuscado);
        assertEquals(12, jogoBuscado.getPlacar());
        assertEquals(1, jogoBuscado.getId());
    }
}