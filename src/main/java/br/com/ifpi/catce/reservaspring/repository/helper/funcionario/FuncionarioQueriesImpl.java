package br.com.ifpi.catce.reservaspring.repository.helper.funcionario;

import br.com.ifpi.catce.reservaspring.model.Espaco;
import br.com.ifpi.catce.reservaspring.model.Funcionario;
import br.com.ifpi.catce.reservaspring.repository.filter.EspacoFilter;
import br.com.ifpi.catce.reservaspring.repository.filter.FuncionarioFilter;
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

public class FuncionarioQueriesImpl implements FuncionarioQueries {

    @PersistenceContext
    private EntityManager manager;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PaginacaoUtil paginacaoUtil;


    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Page<Funcionario> filtrar(FuncionarioFilter filtro, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Funcionario> criteria = builder.createQuery(Funcionario.class);
        Root<Funcionario> root = criteria.from(Funcionario.class);
        List<Predicate> predicates = new ArrayList<>();



        adicionarFiltro(filtro, builder, predicates, root);

        criteria.where(predicates.toArray(new Predicate[0]));


        TypedQuery<Funcionario> query = paginacaoUtil.prepararPaginacao(pageable, criteria, predicates, builder, root, manager);

        return new PageImpl<>(query.getResultList(), pageable, total(filtro));
    }

    private Long total(FuncionarioFilter filtro) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Funcionario> root = criteria.from(Funcionario.class);
        List<Predicate> predicates = new ArrayList<>();

        adicionarFiltro(filtro, builder, predicates, root);
        criteria.where(predicates.toArray(new Predicate[0]));
        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarFiltro(FuncionarioFilter filtro, CriteriaBuilder builder, List<Predicate> predicates, Root<Funcionario> root) {
        if (filtro != null) {
            if (!StringUtils.isBlank(filtro.getNome())) {
                predicates.add(
                        builder.like(builder.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%")
                );
            }
            if (!StringUtils.isBlank(filtro.getEmail())) {
                predicates.add(builder.like(builder.lower(root.get("email")), "%" + filtro.getEmail().toLowerCase() + "%"));
            }
        }
    }
}
