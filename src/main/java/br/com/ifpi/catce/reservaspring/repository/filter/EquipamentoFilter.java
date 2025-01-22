package br.com.ifpi.catce.reservaspring.repository.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoFilter {

    private String descricao;
    private Integer quantidadeDisponivel;
    private Integer quantidadeTotal;
}
