package com.entra.core.basqueteapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "jogo_basquete")
public class JogoBasquete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int placar;
    private Integer minimoTemporada;
    private Integer maximoTemporada;
    private Integer quebraRecordeMinimo;
    private Integer quebraRecordeMaximo;
    private LocalDateTime dataCriacao;

    public JogoBasquete() {
    }

    public JogoBasquete(Integer placar) {
        this.placar = placar;
        this.minimoTemporada = placar;
        this.maximoTemporada = placar;
        this.quebraRecordeMaximo = 0;
        this.quebraRecordeMinimo = 0;
        this.dataCriacao = LocalDateTime.now();
    }

    public JogoBasquete(Integer id, int placar) {
        this.id = id;
        this.placar = placar;
    }

    public Integer getId() {
        return id;
    }

    public int getPlacar() {
        return placar;
    }

    public void setPlacar(int placar) {
        this.placar = placar;
    }

    public Integer getMinimoTemporada() {
        return minimoTemporada;
    }

    public void setMinimoTemporada(Integer minimoTemporada) {
        this.minimoTemporada = minimoTemporada;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
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

    public void setQuebraRecordeMaximo(Integer quebraRecordeMaximo) {
        this.quebraRecordeMaximo = quebraRecordeMaximo;
    }
}
