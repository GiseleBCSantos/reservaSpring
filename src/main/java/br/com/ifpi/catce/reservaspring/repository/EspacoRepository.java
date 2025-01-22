package br.com.ifpi.catce.reservaspring.repository;

import br.com.ifpi.catce.reservaspring.model.Espaco;
import br.com.ifpi.catce.reservaspring.repository.helper.espaco.EspacoQueries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspacoRepository extends JpaRepository<Espaco, Long>, EspacoQueries {

    public Optional<Espaco> findByDescricaoIgnoreCase(String descricao);
}
