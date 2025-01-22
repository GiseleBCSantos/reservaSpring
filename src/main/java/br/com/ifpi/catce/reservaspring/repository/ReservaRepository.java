package br.com.ifpi.catce.reservaspring.repository;

import br.com.ifpi.catce.reservaspring.model.Reserva;
import br.com.ifpi.catce.reservaspring.repository.helper.reserva.ReservaQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long>, ReservaQueries {
}
