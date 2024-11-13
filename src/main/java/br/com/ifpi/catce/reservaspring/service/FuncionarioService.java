package br.com.ifpi.catce.reservaspring.service;

import br.com.ifpi.catce.reservaspring.model.Funcionario;
import br.com.ifpi.catce.reservaspring.repository.FuncionarioRepository;
import br.com.ifpi.catce.reservaspring.service.exceptions.EmailJaCadastrado;
import br.com.ifpi.catce.reservaspring.service.exceptions.NomeJaCadastrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        Optional<Funcionario> funcionarioNomeOptional = funcionarioRepository.findByNomeIgnoreCase(funcionario.getNome());
        Optional<Funcionario> funcionarioEmailOptional = funcionarioRepository.findByEmailIgnoreCase(funcionario.getEmail());
        if (funcionarioNomeOptional.isPresent()){
            throw new NomeJaCadastrado("Nome já cadastrado");
        }

        if (funcionarioEmailOptional.isPresent()){
            throw new EmailJaCadastrado("Email já cadastrado");
        }

        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }
}
