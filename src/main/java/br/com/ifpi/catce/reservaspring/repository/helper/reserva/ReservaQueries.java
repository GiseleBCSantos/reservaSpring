package br.com.ifpi.catce.reservaspring.repository.helper.reserva;

import br.com.ifpi.catce.reservaspring.model.Funcionario;
import br.com.ifpi.catce.reservaspring.model.Reserva;
import br.com.ifpi.catce.reservaspring.repository.filter.FuncionarioFilter;
import br.com.ifpi.catce.reservaspring.repository.filter.ReservaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservaQueries {

    public Page<Reserva> filtrar (ReservaFilter filtro, Pageable pageable);
}
