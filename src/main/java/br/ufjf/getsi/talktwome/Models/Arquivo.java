package br.ufjf.getsi.talktwome.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Arquivo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String palavra;
    private String urlDeUpload;
    private Integer corretudeDaLeitura;
    private Long idJogador;
    private Long idPartida;

    @ManyToOne
    private Jogador jogador;
    
    @ManyToOne
    private Partida partida;

    public Arquivo() {
    }

    public Arquivo(Long id, String palavra, String urlDeUpload, Integer corretudeDaLeitura) {
        this.id = id;
        this.palavra = palavra;
        this.urlDeUpload = urlDeUpload;
        this.corretudeDaLeitura = corretudeDaLeitura;
    }

    public Arquivo(Long id, String palavra, String urlDeUpload, Integer corretudeDaLeitura, Jogador jogador,
            Long idJogador, Partida partida, Long idPartida) {
        this.id = id;
        this.palavra = palavra;
        this.urlDeUpload = urlDeUpload;
        this.corretudeDaLeitura = corretudeDaLeitura;
        this.jogador = jogador;
        this.idJogador = idJogador;
        this.partida = partida;
        this.idPartida = idPartida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getUrlDeUpload() {
        return urlDeUpload;
    }

    public void setUrlDeUpload(String urlDeUpload) {
        this.urlDeUpload = urlDeUpload;
    }

    public Integer getCorretudeDaLeitura() {
        return corretudeDaLeitura;
    }

    public void setCorretudeDaLeitura(Integer corretudeDaLeitura) {
        this.corretudeDaLeitura = corretudeDaLeitura;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
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