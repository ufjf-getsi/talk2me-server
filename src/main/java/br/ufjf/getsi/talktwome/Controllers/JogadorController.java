package br.ufjf.getsi.talktwome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.getsi.talktwome.Models.Jogador;
import br.ufjf.getsi.talktwome.Persistence.JogadorRepository;

@Controller
public class JogadorController {

    @Autowired
    private JogadorRepository repositoryJogador;

    @RequestMapping(value = {"/ver-detalhes-jogador/{id}"}, method = RequestMethod.GET)
    public ModelAndView GetDetalhes(@PathVariable(value = "id", required = true) Long id)
    {
        ModelAndView mv = new ModelAndView();
        Jogador jogador = repositoryJogador.getOne(id);
        mv.addObject("arquivos", jogador.getArquivos());
        mv.addObject("jogador", jogador);
        mv.setViewName("ver-detalhes-jogador");
        return mv;
    }

    
}