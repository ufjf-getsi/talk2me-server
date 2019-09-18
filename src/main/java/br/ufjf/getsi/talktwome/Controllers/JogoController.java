package br.ufjf.getsi.talktwome.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.getsi.talktwome.Models.Jogo;

@Controller
public class JogoController {

    @RequestMapping(value = {"/configurar-jogo"}, method = RequestMethod.GET)
    public ModelAndView GetCriarJogo()
    {
        ModelAndView mv = new ModelAndView();
        Jogo jogo = new Jogo();
        mv.addObject("jogo", jogo);
        mv.setViewName("configurar-jogo");
        return mv;
    }
    
    @RequestMapping(value = {"/configurar-jogo"}, method = RequestMethod.POST)
    public ModelAndView PostCriarJogo(Jogo jogo)
    {
        ModelAndView mv = new ModelAndView();
        return mv;
    }
}