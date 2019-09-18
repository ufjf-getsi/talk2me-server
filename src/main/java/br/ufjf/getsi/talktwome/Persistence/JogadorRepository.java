package br.ufjf.getsi.talktwome.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.getsi.talktwome.Models.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long>{

    
}