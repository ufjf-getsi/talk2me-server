package br.ufjf.getsi.talktwome.DTO;

public class JogadorDTO {
    private Long id;
    private String nome;

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

    public JogadorDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public JogadorDTO() {
    }

    
}