package br.ufjf.getsi.talktwome.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArquivoDTO {

    @JsonProperty
    private String palavra;
    @JsonProperty
    private Long idJogador;
    @JsonProperty
    private Long idPartida;
    @JsonProperty
    private Integer rodada;

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getRodada() {
        return rodada;
    }

    public void setRodada(Integer rodada) {
        this.rodada = rodada;
    }

    public ArquivoDTO(String palavra, Long idJogador, Long idPartida, Integer rodada) {
        this.palavra = palavra;
        this.idJogador = idJogador;
        this.idPartida = idPartida;
        this.rodada = rodada;
    }

    public ArquivoDTO() {
    }

    
}