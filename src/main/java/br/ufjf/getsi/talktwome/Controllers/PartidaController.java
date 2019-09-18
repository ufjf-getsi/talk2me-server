package br.ufjf.getsi.talktwome.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.getsi.talktwome.Models.Jogador;
import br.ufjf.getsi.talktwome.Models.Partida;
import br.ufjf.getsi.talktwome.Persistence.PartidaRepository;
import br.ufjf.getsi.talktwome.Util.Md5;

@Controller
public class PartidaController {

    @Autowired
    private PartidaRepository repositoryPartida;

    @RequestMapping(value = {"/configurar-partida"}, method = RequestMethod.GET)
    public ModelAndView GetCriarJogo()
    {
        ModelAndView mv = new ModelAndView();
        Partida partida = new Partida();
        mv.addObject("partida", partida);
        mv.setViewName("configurar-partida");
        return mv;
    }
    
    @RequestMapping(value = {"/configurar-partida"}, method = RequestMethod.POST)
    public ModelAndView PostCriarJogo(Partida partida)
    {
        ModelAndView mv = new ModelAndView();
        Md5 md5 = new Md5();
        String senha = md5.encriptografar(partida.getSenha()); 
        partida.setSenha(senha);
        repositoryPartida.save(partida);

        for (int i = 0; i < partida.getNumJogadores(); i++)
        {
            Jogador jogador = new Jogador();
            partida.getJogadores().add(jogador);
        }

        mv.addObject("partida", partida);
        mv.setViewName("configurar-jogadores");
        return mv;
    }

    @RequestMapping(value = {"/configurar-jogadores"}, method = RequestMethod.POST)
    public ModelAndView GetCriarJogadores(Long id, String senha)
    {
        ModelAndView mv = new ModelAndView();
        return mv;
    }
}