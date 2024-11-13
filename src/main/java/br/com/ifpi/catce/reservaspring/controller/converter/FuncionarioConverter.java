package br.com.ifpi.catce.reservaspring.controller.converter;

import br.com.ifpi.catce.reservaspring.model.Funcionario;
import ch.qos.logback.core.util.StringUtil;
import org.springframework.core.convert.converter.Converter;

public class FuncionarioConverter implements Converter<String, Funcionario> {

    @Override
    public Funcionario convert(String id) {
        if (!StringUtil.isNullOrEmpty(id)){
            Funcionario funcionario = new Funcionario();
            funcionario.setId(Long.valueOf(id));
            return funcionario;
        }
        return null;
    }
}
