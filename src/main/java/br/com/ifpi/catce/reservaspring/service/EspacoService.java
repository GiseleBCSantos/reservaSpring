package br.com.ifpi.catce.reservaspring.service;

import br.com.ifpi.catce.reservaspring.model.Espaco;
import br.com.ifpi.catce.reservaspring.repository.EspacoRepository;
import br.com.ifpi.catce.reservaspring.service.exceptions.DescricaoJaCadastrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EspacoService {

    @Autowired
    private EspacoRepository espacoRepository;

    @Transactional
    public Espaco salvar(Espaco espaco) {
        Optional<Espaco> optional = espacoRepository.findByDescricaoIgnoreCase(espaco.getDescricao());
        if (optional.isPresent()) {
            throw new DescricaoJaCadastrada("Descrição ja cadastrada");
        }
        return espacoRepository.save(espaco);
    };

    @Transactional
    public List<Espaco> listar() {
        return espacoRepository.findAll();
    }
}
