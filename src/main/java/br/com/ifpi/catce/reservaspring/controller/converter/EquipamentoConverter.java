package br.com.ifpi.catce.reservaspring.controller.converter;

import br.com.ifpi.catce.reservaspring.model.Equipamento;
import ch.qos.logback.core.util.StringUtil;
import org.springframework.core.convert.converter.Converter;

public class EquipamentoConverter implements Converter<String, Equipamento> {

    @Override
    public Equipamento convert(String id) {
        if (!StringUtil.isNullOrEmpty(id)){
            Equipamento equipamento = new Equipamento();
            equipamento.setId(Long.parseLong(id));
            return equipamento;
        }
        return null;
    }
}
