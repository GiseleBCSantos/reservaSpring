package br.com.ifpi.catce.reservaspring.controller;

import br.com.ifpi.catce.reservaspring.controller.page.PageWrapper;
import br.com.ifpi.catce.reservaspring.model.Espaco;
import br.com.ifpi.catce.reservaspring.repository.EspacoRepository;
import br.com.ifpi.catce.reservaspring.repository.filter.EspacoFilter;
import br.com.ifpi.catce.reservaspring.service.EspacoService;
import br.com.ifpi.catce.reservaspring.service.exceptions.DescricaoJaCadastrada;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/espacos")
public class EspacoController {

    @Autowired
    EspacoService espacoService;

    @Autowired
    EspacoRepository espacos;

    @GetMapping("/listar")
    public ModelAndView listar(EspacoFilter espacoFilter, BindingResult result, @PageableDefault(size = 1) Pageable pageable, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("espaco/listarEspaco");

        PageWrapper<Espaco> pageWrapper = new PageWrapper<>(espacos.filtrar(espacoFilter, pageable), request);
        mv.addObject("pagina", pageWrapper);
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView cadastro(Espaco espaco) {
        ModelAndView mv = new ModelAndView("espaco/cadastroEspaco");
        mv.addObject("espaco", espaco);
        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView cadastroSubmit(@Valid Espaco espaco, BindingResult result, RedirectAttributes attributes) {
        System.out.println(espaco);
        if (result.hasErrors()) {
            System.out.println("erro");
            return cadastro(espaco);
        }
        try{
            espacoService.salvar(espaco);
        } catch (DescricaoJaCadastrada e){
            result.rejectValue("descricao", e.getMessage(), e.getMessage());
            return cadastro(espaco);
        }
        attributes.addFlashAttribute("mensagem", "Espaco adicionado com sucesso");
        return new ModelAndView("redirect:/espacos/listar");
    }
}
