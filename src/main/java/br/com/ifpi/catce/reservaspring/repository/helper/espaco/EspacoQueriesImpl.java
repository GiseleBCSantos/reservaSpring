package br.com.ifpi.catce.reservaspring.repository.helper.espaco;

import br.com.ifpi.catce.reservaspring.model.Equipamento;
import br.com.ifpi.catce.reservaspring.model.Espaco;
import br.com.ifpi.catce.reservaspring.repository.filter.EquipamentoFilter;
import br.com.ifpi.catce.reservaspring.repository.filter.EspacoFilter;
import br.com.ifpi.catce.reservaspring.repository.paginacao.PaginacaoUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class EspacoQueriesImpl implements EspacoQueries {

    @PersistenceContext
    private EntityManager manager;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PaginacaoUtil paginacaoUtil;


    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Page<Espaco> filtrar(EspacoFilter filtro, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Espaco> criteria = builder.createQuery(Espaco.class);
        Root<Espaco> root = criteria.from(Espaco.class);
        List<Predicate> predicates = new ArrayList<>();



        adicionarFiltro(filtro, builder, predicates, root);

        criteria.where(predicates.toArray(new Predicate[0]));


        TypedQuery<Espaco> query = paginacaoUtil.prepararPaginacao(pageable, criteria, predicates, builder, root, manager);

        return new PageImpl<>(query.getResultList(), pageable, total(filtro));
    }

    private Long total(EspacoFilter filtro) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Espaco> root = criteria.from(Espaco.class);
        List<Predicate> predicates = new ArrayList<>();

        adicionarFiltro(filtro, builder, predicates, root);
        criteria.where(predicates.toArray(new Predicate[0]));
        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarFiltro(EspacoFilter filtro, CriteriaBuilder builder, List<Predicate> predicates, Root<Espaco> root) {
        if (filtro != null) {
            if (!StringUtils.isBlank(filtro.getDescricao())) {
                predicates.add(
                        builder.like(builder.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%")
                );
            }
        }
    }
}
