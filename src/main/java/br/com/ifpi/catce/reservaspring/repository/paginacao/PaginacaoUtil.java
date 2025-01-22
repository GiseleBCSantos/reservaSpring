package br.com.ifpi.catce.reservaspring.repository.paginacao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaginacaoUtil {

    public TypedQuery prepararPaginacao(Pageable pageable, CriteriaQuery criteria, List predicates, CriteriaBuilder builder, Root root, EntityManager manager) {
        Sort sort = pageable.getSort();
        if (sort != null && sort.iterator().hasNext()) {
            Sort.Order order = sort.iterator().next();
            String field = order.getProperty();
            criteria.orderBy(order.isAscending() ? builder.asc(root.get(field)) : builder.desc(root.get(field)));
        }

        TypedQuery query = manager.createQuery(criteria);

        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistro);
        query.setMaxResults(totalRegistrosPorPagina);

        return query;
    }
}