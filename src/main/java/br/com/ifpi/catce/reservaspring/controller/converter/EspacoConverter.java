package br.com.ifpi.catce.reservaspring.controller.converter;

import br.com.ifpi.catce.reservaspring.model.Espaco;
import ch.qos.logback.core.util.StringUtil;
import org.springframework.core.convert.converter.Converter;

public class EspacoConverter implements Converter<String, Espaco> {

    @Override
    public Espaco convert(String id) {
        if (!StringUtil.isNullOrEmpty(id)){
            Espaco espaco = new Espaco();
            espaco.setId(Long.valueOf(id));
            return espaco;
        }
        return null;
    }
}
