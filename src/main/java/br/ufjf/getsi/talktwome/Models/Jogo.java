package br.ufjf.getsi.talktwome.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    private Integer turnos;
    private Integer numJogadores;
    private String senha;
    private String palavras;
    
    @OneToMany(mappedBy = "jogo")
    private Set<Jogador> jogadores;

    @OneToMany(mappedBy = "jogo")
    private Set<Arquivo> arquivos;

    public Jogo() {
    }

    public Jogo(Long id, String titulo, Integer turnos, Integer numJogadores, String senha, String palavras) {
        this.id = id;
        this.titulo = titulo;
        this.turnos = turnos;
        this.numJogadores = numJogadores;
        this.senha = senha;
        this.palavras = palavras;
        this.jogadores = new HashSet<>();
        this.arquivos = new HashSet<>();
    }

    public Jogo(Long id, String titulo, Integer turnos, Integer numJogadores, String senha, String palavras,
       Set<Jogador> jogadores, Set<Arquivo> arquivos) {        
        this.id = id;
        this.titulo = titulo;
        this.turnos = turnos;
        this.numJogadores = numJogadores;
        this.senha = senha;
        this.palavras = palavras;
        this.jogadores = jogadores;
        this.arquivos = arquivos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPalavras() {
        return palavras;
    }

    public void setPalavras(String palavras) {
        this.palavras = palavras;
    }

    public Set<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(Set<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Set<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(Set<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }
    
}