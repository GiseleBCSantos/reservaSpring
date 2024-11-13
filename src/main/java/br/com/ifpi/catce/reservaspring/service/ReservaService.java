package br.com.ifpi.catce.reservaspring.service;

import br.com.ifpi.catce.reservaspring.model.Reserva;
import br.com.ifpi.catce.reservaspring.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional
    public Reserva salvar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Transactional
    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }
}
