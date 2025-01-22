package br.com.ifpi.catce.reservaspring.repository.helper.reserva;

import br.com.ifpi.catce.reservaspring.model.Espaco;
import br.com.ifpi.catce.reservaspring.model.Funcionario;
import br.com.ifpi.catce.reservaspring.model.Reserva;
import br.com.ifpi.catce.reservaspring.repository.filter.FuncionarioFilter;
import br.com.ifpi.catce.reservaspring.repository.filter.ReservaFilter;
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

public class ReservaQueriesImpl implements ReservaQueries {

    @PersistenceContext
    private EntityManager manager;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PaginacaoUtil paginacaoUtil;


    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Page<Reserva> filtrar(ReservaFilter filtro, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Reserva> criteria = builder.createQuery(Reserva.class);
        Root<Reserva> root = criteria.from(Reserva.class);
        List<Predicate> predicates = new ArrayList<>();

        adicionarFiltro(filtro, builder, predicates, root);

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Reserva> query = paginacaoUtil.prepararPaginacao(pageable, criteria, predicates, builder, root, manager);

        return new PageImpl<>(query.getResultList(), pageable, total(filtro));
    }

    private Long total(ReservaFilter filtro) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Reserva> root = criteria.from(Reserva.class);
        List<Predicate> predicates = new ArrayList<>();

        adicionarFiltro(filtro, builder, predicates, root);
        criteria.where(predicates.toArray(new Predicate[0]));
        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarFiltro(ReservaFilter filtro, CriteriaBuilder builder, List<Predicate> predicates, Root<Reserva> root) {
        if (filtro != null) {
            if (filtro.getFuncionario() != null && !StringUtils.isBlank(String.valueOf(filtro.getFuncionario()))) {
                predicates.add(
                        builder.equal(root.get("funcionario"), filtro.getFuncionario())
                );
            }

            if (filtro.getEquipamento() != null && !StringUtils.isBlank(String.valueOf(filtro.getEquipamento()))) {
                predicates.add(
                        builder.equal(root.get("equipamento"), filtro.getEquipamento())
                );
            }

            if (filtro.getEspaco() != null && !StringUtils.isBlank(String.valueOf(filtro.getEspaco()))) {
                predicates.add(
                        builder.equal(root.get("espaco"), filtro.getEspaco())
                );
            }
        }
    }
}
