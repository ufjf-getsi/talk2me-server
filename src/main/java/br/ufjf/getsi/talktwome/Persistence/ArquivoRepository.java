package br.ufjf.getsi.talktwome.Persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufjf.getsi.talktwome.Models.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

    
}