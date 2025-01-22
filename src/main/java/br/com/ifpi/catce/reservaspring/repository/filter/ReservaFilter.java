package br.com.ifpi.catce.reservaspring.repository.filter;

import br.com.ifpi.catce.reservaspring.model.Equipamento;
import br.com.ifpi.catce.reservaspring.model.Funcionario;
import br.com.ifpi.catce.reservaspring.model.Espaco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaFilter {

    private Funcionario funcionario;
    private Espaco espaco;
    private Equipamento equipamento;
}
