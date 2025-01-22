package br.com.ifpi.catce.reservaspring.repository.helper.funcionario;

import br.com.ifpi.catce.reservaspring.model.Espaco;
import br.com.ifpi.catce.reservaspring.model.Funcionario;
import br.com.ifpi.catce.reservaspring.repository.filter.EspacoFilter;
import br.com.ifpi.catce.reservaspring.repository.filter.FuncionarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioQueries {

    public Page<Funcionario> filtrar (FuncionarioFilter filtro, Pageable pageable);
}
