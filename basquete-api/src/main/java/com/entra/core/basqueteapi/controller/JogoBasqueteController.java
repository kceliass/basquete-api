package com.entra.core.basqueteapi.controller;

import com.entra.core.basqueteapi.dto.JogoBasqueteDTO;
import com.entra.core.basqueteapi.exception.NotFoundException;
import com.entra.core.basqueteapi.service.JogoBasqueteService;
import com.entra.core.basqueteapi.util.DTOUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class JogoBasqueteController {

    private final JogoBasqueteService jogoBasqueteService;

    public JogoBasqueteController(JogoBasqueteService jogoBasqueteService) {
        this.jogoBasqueteService = jogoBasqueteService;
    }

    @PostMapping("/jogo")
    public JogoBasqueteDTO incluir(@RequestBody JogoBasqueteDTO jogoBasqueteDTO) throws NotFoundException {
        return JogoBasqueteDTO.of(this.jogoBasqueteService.incluir(jogoBasqueteDTO));
    }

    @GetMapping
    public List<JogoBasqueteDTO> listarJogos() {
        return DTOUtil.parseToList(this.jogoBasqueteService.listarJogos());
    }

    @GetMapping("/{id}")
    public JogoBasqueteDTO listaUm(@PathVariable("id") Integer id) throws NotFoundException {
        return JogoBasqueteDTO.of(this.jogoBasqueteService.listaUm(id));
    }

    @DeleteMapping("/{id}")
    public String deleteJogoPorId(@PathVariable("id") Integer id) {
        return this.jogoBasqueteService.deleteJogoPorId(id);
    }
}
