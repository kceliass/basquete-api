package com.entra.core.basqueteapi.dto;

import com.entra.core.basqueteapi.model.JogoBasquete;

import java.time.LocalDateTime;

public class JogoBasqueteDTO {

    private Integer id;
    private Integer placar;
    private Integer minimoTemporada;
    private Integer maximoTemporada;
    private Integer quebraRecordeMinimo;
    private Integer quebraRecordeMaximo;
    private LocalDateTime dataCriacao;

    public JogoBasqueteDTO() {
    }

    public JogoBasqueteDTO(Integer id, Integer placar, Integer minimoTemporada, Integer maximoTemporada, Integer quebraRecordeMinimo, Integer quebraRecordeMaximo, LocalDateTime dataCriacao) {
        this.id = id;
        this.placar = placar;
        this.minimoTemporada = minimoTemporada;
        this.maximoTemporada = maximoTemporada;
        this.quebraRecordeMinimo = quebraRecordeMinimo;
        this.quebraRecordeMaximo = quebraRecordeMaximo;
        this.dataCriacao = dataCriacao;
    }

    public static JogoBasqueteDTO of(JogoBasquete jogoBasquete) {
        return new JogoBasqueteDTO(
                jogoBasquete.getId(),
                jogoBasquete.getPlacar(),
                jogoBasquete.getMinimoTemporada(),
                jogoBasquete.getMaximoTemporada(),
                jogoBasquete.getQuebraRecordeMinimo(),
                jogoBasquete.getQuebraRecordeMaximo(),
                jogoBasquete.getDataCriacao()
        );
    }

    public Integer getId() {
        return id;
    }

    public Integer getPlacar() {
        return placar;
    }

    public void setPlacar(Integer placar) {
        this.placar = placar;
    }

    public Integer getMinimoTemporada() {
        return minimoTemporada;
    }

    public void setMinimoTemporada(Integer minimoTemporada) {
        this.minimoTemporada = minimoTemporada;
    }

    public Integer getMaximoTemporada() {
        return maximoTemporada;
    }

    public void setMaximoTemporada(Integer maximoTemporada) {
        this.maximoTemporada = maximoTemporada;
    }

    public Integer getQuebraRecordeMinimo() {
        return quebraRecordeMinimo;
    }

    public void setQuebraRecordeMinimo(Integer quebraRecordeMinimo) {
        this.quebraRecordeMinimo = quebraRecordeMinimo;
    }

    public Integer getQuebraRecordeMaximo() {
        return quebraRecordeMaximo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setQuebraRecordeMaximo(Integer quebraRecordeMaximo) {
        this.quebraRecordeMaximo = quebraRecordeMaximo;
    }
}
