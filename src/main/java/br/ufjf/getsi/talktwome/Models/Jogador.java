package br.ufjf.getsi.talktwome.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private Long idPartida;
    
    @OneToMany(mappedBy = "jogador")
    private Set<Arquivo> arquivos;

    @ManyToOne
    private Partida partida;

    public Jogador() {
    }

    public Jogador(Long id, String nome) {
        this.id = id;
        this.nome = nome;
        this.arquivos = new HashSet<>();
    }

    public Jogador(Long id, String nome, Set<Arquivo> arquivos, Partida partida, Long idPartida) {
        this.id = id;
        this.nome = nome;
        this.arquivos = arquivos;
        this.partida = partida;
        this.idPartida = idPartida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(Set<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Long idPartida) {
        this.idPartida = idPartida;
    }

}