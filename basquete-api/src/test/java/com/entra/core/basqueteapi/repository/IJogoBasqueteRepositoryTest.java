package com.entra.core.basqueteapi.repository;

import com.entra.core.basqueteapi.model.JogoBasquete;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class IJogoBasqueteRepositoryTest {

    @Mock
    private IJogoBasqueteRepository iJogoBasqueteRepository;

    @Test
    void findFirstByOrderByDataCriacaoDesc() {
        JogoBasquete jogoBasquete = new JogoBasquete(1, 12);

        when(iJogoBasqueteRepository.findFirstByOrderByDataCriacaoDesc()).thenReturn(Optional.of(jogoBasquete));

        Optional<JogoBasquete> jogoBuscadoOptional = iJogoBasqueteRepository.findFirstByOrderByDataCriacaoDesc();

        Assertions.assertThat(jogoBuscadoOptional).isNotEmpty();

        JogoBasquete jogoBuscado = jogoBuscadoOptional.get();

        assertEquals(12, jogoBuscado.getPlacar());
        assertEquals(1, jogoBuscado.getId());

    }
}