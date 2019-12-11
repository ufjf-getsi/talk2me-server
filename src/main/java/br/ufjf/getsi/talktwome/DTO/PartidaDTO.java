package br.ufjf.getsi.talktwome.DTO;

import java.util.HashSet;
import java.util.Set;

public class PartidaDTO {
    private String id;
    private String titulo;
    private Integer turnos;
    private Integer numJogadores;
    private String palavras;    
    private Set<JogadorDTO> jogadores;

    public PartidaDTO() {
        this.jogadores = new HashSet<>();
    }

    public PartidaDTO(String id, String titulo, Integer turnos, Integer numJogadores, String palavras,
            Set<JogadorDTO> jogadores) {
        this.id = id;
        this.titulo = titulo;
        this.turnos = turnos;
        this.numJogadores = numJogadores;
        this.palavras = palavras;
        this.jogadores = jogadores;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTurnos() {
        return turnos;
    }

    public void setTurnos(Integer turnos) {
        this.turnos = turnos;
    }

    public Integer getNumJogadores() {
        return numJogadores;
    }

    public void setNumJogadores(Integer numJogadores) {
        this.numJogadores = numJogadores;
    }

    public String getPalavras() {
        return palavras;
    }

    public void setPalavras(String palavras) {
        this.palavras = palavras;
    }

    public Set<JogadorDTO> getJogadores() {
        return jogadores;
    }

    public void setJogadores(Set<JogadorDTO> jogadores) {
        this.jogadores = jogadores;
    }

    
}