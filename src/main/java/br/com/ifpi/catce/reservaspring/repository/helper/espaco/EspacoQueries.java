package br.com.ifpi.catce.reservaspring.repository.helper.espaco;

import br.com.ifpi.catce.reservaspring.model.Equipamento;
import br.com.ifpi.catce.reservaspring.model.Espaco;
import br.com.ifpi.catce.reservaspring.repository.filter.EquipamentoFilter;
import br.com.ifpi.catce.reservaspring.repository.filter.EspacoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EspacoQueries {

    public Page<Espaco> filtrar (EspacoFilter filtro, Pageable pageable);
}
