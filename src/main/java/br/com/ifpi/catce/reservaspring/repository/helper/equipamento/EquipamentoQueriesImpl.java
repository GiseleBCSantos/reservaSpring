package br.com.ifpi.catce.reservaspring.repository.helper.equipamento;

import br.com.ifpi.catce.reservaspring.model.Equipamento;
import br.com.ifpi.catce.reservaspring.repository.filter.EquipamentoFilter;
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

public class EquipamentoQueriesImpl implements EquipamentoQueries {

    @PersistenceContext
    private EntityManager manager;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PaginacaoUtil paginacaoUtil;


    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Page<Equipamento> filtrar(EquipamentoFilter filtro, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Equipamento> criteria = builder.createQuery(Equipamento.class);
        Root<Equipamento> root = criteria.from(Equipamento.class);
        List<Predicate> predicates = new ArrayList<>();



        adicionarFiltro(filtro, builder, predicates, root);

        criteria.where(predicates.toArray(new Predicate[0]));


        TypedQuery<Equipamento> query = paginacaoUtil.prepararPaginacao(pageable, criteria, predicates, builder, root, manager);

        return new PageImpl<>(query.getResultList(), pageable, total(filtro));
    }

    private Long total(EquipamentoFilter filtro) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Equipamento> root = criteria.from(Equipamento.class);
        List<Predicate> predicates = new ArrayList<>();

        adicionarFiltro(filtro, builder, predicates, root);
        criteria.where(predicates.toArray(new Predicate[0]));
        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarFiltro(EquipamentoFilter filtro, CriteriaBuilder builder, List<Predicate> predicates, Root<Equipamento> root) {
        if (filtro != null) {
            if (!StringUtils.isBlank(filtro.getDescricao())) {
                predicates.add(
                        builder.like(builder.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%")
                );
            }
            if (filtro.getQuantidadeDisponivel() != null) {
                predicates.add(builder.ge(root.get("quantidadeDisponivel"), filtro.getQuantidadeDisponivel()));
            }
            if (filtro.getQuantidadeTotal() != null) {
                predicates.add(builder.ge(root.get("quantidadeTotal"), filtro.getQuantidadeTotal()));
            }
        }
    }
}
