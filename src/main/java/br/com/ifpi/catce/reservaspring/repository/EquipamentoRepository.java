package br.com.ifpi.catce.reservaspring.repository;

import br.com.ifpi.catce.reservaspring.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    public Optional<Equipamento> findByDescricaoIgnoreCase(String nome);

}
