package br.ufjf.getsi.talktwome.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.getsi.talktwome.Models.Jogador;
import br.ufjf.getsi.talktwome.Models.Partida;
import br.ufjf.getsi.talktwome.Persistence.JogadorRepository;
import br.ufjf.getsi.talktwome.Persistence.PartidaRepository;
import br.ufjf.getsi.talktwome.Util.Md5;

@Controller
public class PartidaController {

    @Autowired
    private PartidaRepository repositoryPartida;
    @Autowired
    private JogadorRepository repositoryJogador;

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
            jogador.setId(i + 1L);
            partida.getJogadores().add(jogador);
        }
        mv.addObject("id", partida.getId());
        mv.addObject("jogadores", partida.getJogadores());
        mv.setViewName("configurar-jogadores");
        return mv;
    }

    @RequestMapping(value = {"/configurar-jogadores"}, method = RequestMethod.POST)
    public ModelAndView PostCriarJogadores(@RequestParam(value = "id", required = true) Long id, 
    @RequestParam(value = "jogadores") String[] jogadores)
    {
        ModelAndView mv = new ModelAndView();       
        Partida partida = repositoryPartida.getOne(id); 
        for (int i = 0; i < jogadores.length; i++)
        {
            Jogador jogador = new Jogador();
            jogador.setNome(jogadores[i]);
            jogador.setIdPartida(id);
            jogador.setPartida(partida);
            partida.getJogadores().add(jogador);
            repositoryJogador.save(jogador);
        }
        repositoryPartida.save(partida);
        mv.addObject("game", id);
        mv.setViewName("redirect:partida-gerada");
        return mv;
    }

    @RequestMapping(value = {"/partida-gerada"}, method = RequestMethod.GET)
    public ModelAndView GetPartidaGerada(@RequestParam(value = "game", required = true) Long id)
    {
        ModelAndView mv = new ModelAndView();
        Partida partida = repositoryPartida.getOne(id);
        mv.addObject("partida", partida);
        mv.addObject("jogadores", partida.getJogadores());
        mv.setViewName("partida-gerada");
        return mv;
    }

}