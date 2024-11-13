package br.com.ifpi.catce.reservaspring.service;

import br.com.ifpi.catce.reservaspring.model.Equipamento;
import br.com.ifpi.catce.reservaspring.repository.EquipamentoRepository;
import br.com.ifpi.catce.reservaspring.service.exceptions.DescricaoJaCadastrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoService {
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Transactional
    public Equipamento salvar(Equipamento equipamento) {
        Optional<Equipamento> equipamentoOptional = equipamentoRepository.findByDescricaoIgnoreCase(equipamento.getDescricao());
        if(equipamentoOptional.isPresent()) {
            throw new DescricaoJaCadastrada("Descrição já cadastrada");
        }
        return equipamentoRepository.save(equipamento);
    }

    @Transactional
    public List<Equipamento> listar(){
        return equipamentoRepository.findAll();
    }

}
