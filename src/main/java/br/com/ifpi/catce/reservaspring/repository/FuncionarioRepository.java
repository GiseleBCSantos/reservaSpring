package br.com.ifpi.catce.reservaspring.repository;

import br.com.ifpi.catce.reservaspring.model.Funcionario;
import br.com.ifpi.catce.reservaspring.repository.helper.funcionario.FuncionarioQueries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>, FuncionarioQueries {

    Optional<Funcionario> findByNomeIgnoreCase(String nome);

    Optional<Funcionario> findByEmailIgnoreCase(String email);
}
