package br.com.ifpi.catce.reservaspring.repository.helper.equipamento;

import br.com.ifpi.catce.reservaspring.model.Equipamento;
import br.com.ifpi.catce.reservaspring.repository.filter.EquipamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipamentoQueries {

    public Page<Equipamento> filtrar (EquipamentoFilter filtro, Pageable pageable);
}
