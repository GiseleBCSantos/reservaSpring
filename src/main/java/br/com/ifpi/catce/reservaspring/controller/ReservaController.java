package br.com.ifpi.catce.reservaspring.controller;

import br.com.ifpi.catce.reservaspring.controller.page.PageWrapper;
import br.com.ifpi.catce.reservaspring.model.Funcionario;
import br.com.ifpi.catce.reservaspring.model.Reserva;
import br.com.ifpi.catce.reservaspring.repository.EquipamentoRepository;
import br.com.ifpi.catce.reservaspring.repository.EspacoRepository;
import br.com.ifpi.catce.reservaspring.repository.FuncionarioRepository;
import br.com.ifpi.catce.reservaspring.repository.ReservaRepository;
import br.com.ifpi.catce.reservaspring.repository.filter.ReservaFilter;
import br.com.ifpi.catce.reservaspring.service.EquipamentoService;
import br.com.ifpi.catce.reservaspring.service.EspacoService;
import br.com.ifpi.catce.reservaspring.service.FuncionarioService;
import br.com.ifpi.catce.reservaspring.service.ReservaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private EquipamentoService equipamentoService;

    @Autowired
    private EspacoService espacoService;

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("/listar")
    public ModelAndView listar(ReservaFilter filtro, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("reserva/listarReserva");

        PageWrapper<Reserva> pageWrapper = new PageWrapper<>(reservaRepository.filtrar(filtro, pageable), request);

        mv.addObject("pagina", pageWrapper);
        mv.addObject("funcionarios", funcionarioService.listar());
        mv.addObject("equipamentos", equipamentoService.listar());
        mv.addObject("espacos", espacoService.listar());
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView cadastro(Reserva reserva) {
        ModelAndView mv = new ModelAndView("reserva/cadastroReserva");
        mv.addObject("funcionarios", funcionarioService.listar());
        mv.addObject("equipamentos", equipamentoService.listar());
        mv.addObject("espacos", espacoService.listar());
        mv.addObject("reservas", reservaService.listar());
        mv.addObject("reserva", reserva);
        return mv;
    }

    @PostMapping("/novo")
    public ModelAndView cadastroSubmit(@Valid Reserva reserva, BindingResult bindingResult, RedirectAttributes attributes) {
        System.out.println(reserva);
        if (bindingResult.hasErrors()) {
            return cadastro(reserva);
        }

        if (reserva.getEquipamento() == null && reserva.getEspaco() == null){
            return cadastro(reserva);
        }

        reservaService.salvar(reserva);
        attributes.addFlashAttribute("mensagem", "Reserva Cadastrado com sucesso!");
        return new ModelAndView("redirect:/reservas/listar");
    }

}
