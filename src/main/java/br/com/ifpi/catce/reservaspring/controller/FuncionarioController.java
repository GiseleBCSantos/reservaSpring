package br.com.ifpi.catce.reservaspring.controller;

import br.com.ifpi.catce.reservaspring.controller.page.PageWrapper;
import br.com.ifpi.catce.reservaspring.model.Funcionario;
import br.com.ifpi.catce.reservaspring.repository.FuncionarioRepository;
import br.com.ifpi.catce.reservaspring.repository.filter.FuncionarioFilter;
import br.com.ifpi.catce.reservaspring.service.FuncionarioService;
import br.com.ifpi.catce.reservaspring.service.exceptions.EmailJaCadastrado;
import br.com.ifpi.catce.reservaspring.service.exceptions.NomeJaCadastrado;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService cadastroFuncionarioService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/listar")
    public ModelAndView listar(FuncionarioFilter filter, BindingResult bindingResult, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("funcionario/listarFuncionario");

        PageWrapper<Funcionario> pageWrapper = new PageWrapper<>(funcionarioRepository.filtrar(filter, pageable), request);

        mv.addObject("pagina", pageWrapper);
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView cadastro(Funcionario funcionario) {
        ModelAndView mv = new ModelAndView("funcionario/cadastroFuncionario");
        mv.addObject("funcionario", funcionario);
        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView cadastroSubmit(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/funcionarios/listar");
        if (result.hasErrors()) {
            return cadastro(funcionario);
        }

        try{
            cadastroFuncionarioService.salvar(funcionario);
        } catch (NomeJaCadastrado e){
            result.rejectValue("nome", e.getMessage(), e.getMessage());
            return cadastro(funcionario);
        } catch (EmailJaCadastrado e){
            result.rejectValue("email", e.getMessage(), e.getMessage());
            return cadastro(funcionario);
        }
        attributes.addFlashAttribute("mensagem", "Funcionario cadastrado com sucesso!");
        return mv;
    }
}
