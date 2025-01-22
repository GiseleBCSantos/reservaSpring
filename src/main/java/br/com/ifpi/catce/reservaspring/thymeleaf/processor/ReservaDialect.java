package br.com.ifpi.catce.reservaspring.thymeleaf.processor;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

public class ReservaDialect extends AbstractProcessorDialect {

    public ReservaDialect() {
        super("ReservaSpring", "reserva", StandardDialect.PROCESSOR_PRECEDENCE);
    }


    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processadores = new HashSet<>();
        processadores.add(new OrderElementTagProcessor(dialectPrefix));
        processadores.add(new PaginationElementTagProcessor(dialectPrefix));
        return processadores;
    }
}
