package br.ufjf.getsi.talktwome.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.getsi.talktwome.DTO.JogadorDTO;
import br.ufjf.getsi.talktwome.DTO.PartidaDTO;
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

    @RequestMapping(value = {"/listar-partidas"}, method = RequestMethod.GET)
    public ModelAndView GetListarJogos()
    {
        ModelAndView mv = new ModelAndView();
        List<Partida> partidas = repositoryPartida.findAll();
        mv.addObject("partidas", partidas);
        mv.setViewName("listar-partidas");
        return mv;
    }

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
        String []nomeJogadores = partida.getJogadoresNome().split(",");
        partida.setNumJogadores(nomeJogadores.length);
        repositoryPartida.save(partida);

        for (int i = 0; i < nomeJogadores.length; i++)
        {
            Jogador jogador = new Jogador();
            jogador.setNome(nomeJogadores[i]);
            jogador.setIdPartida(partida.getId());
            jogador.setPartida(partida);
            partida.getJogadores().add(jogador);
            repositoryJogador.save(jogador);
        }
        repositoryPartida.save(partida);
        mv.addObject("game", partida.getId());
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

    @RequestMapping(value = {"/info-partida"}, method = RequestMethod.GET)
    @ResponseBody
    public PartidaDTO GetPartidaAsJson(@RequestParam(value = "game", required = true) Long id)
    {
        Partida partida = repositoryPartida.getOne(id);
        PartidaDTO partidaRetorno = new PartidaDTO();
        partidaRetorno.setId(Long.toString(partida.getId()));
        partidaRetorno.setNumJogadores(partida.getNumJogadores());
        partidaRetorno.setPalavras(partida.getPalavras());
        partidaRetorno.setTitulo(partida.getTitulo());
        partidaRetorno.setTurnos(partida.getTurnos());
        for (Jogador var : partida.getJogadores()) {
            JogadorDTO jogadorDTO = new JogadorDTO(var.getId(), var.getNome());
            partidaRetorno.getJogadores().add(jogadorDTO);
        }
        return partidaRetorno;
    }

    @RequestMapping(value = {"/ver-detalhes-senha/{id}"}, method = RequestMethod.GET)
    public ModelAndView GetDetalhes(@PathVariable(value = "id", required = true) Long id)
    {
        ModelAndView mv = new ModelAndView();
        Partida partida = new Partida();
        partida.setId(id);
        mv.addObject("partida", partida);
        mv.setViewName("ver-detalhes-senha");
        return mv;
    }

    @RequestMapping(value = {"/ver-detalhes-senha"}, method = RequestMethod.POST)
    public ModelAndView PostDetalhes(@RequestParam(value = "id", required = true) Long id, Partida partida)
    {
        ModelAndView mv = new ModelAndView();
        Partida partidaFinal = repositoryPartida.getOne(id);
        Md5 md5 = new Md5();
        if (partidaFinal.getSenha().equals(md5.encriptografar(partida.getSenha())))
        {
            mv.addObject("partida", partidaFinal);
            mv.addObject("jogadores", partidaFinal.getJogadores());
            mv.setViewName("ver-detalhes");
            return mv;
        }
        else
        {
            mv.setViewName("redirect:partida-gerada");
        }
        return mv;
    }


}