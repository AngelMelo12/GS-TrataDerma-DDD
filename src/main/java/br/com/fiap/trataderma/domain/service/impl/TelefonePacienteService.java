package br.com.fiap.trataderma.domain.service.impl;

import br.com.fiap.trataderma.domain.entity.TelefonePaciente;
import br.com.fiap.trataderma.domain.repository.impl.TelefonePacienteRepository;
import br.com.fiap.trataderma.domain.service.Service;

import java.util.List;
import java.util.Objects;

public class TelefonePacienteService implements Service<TelefonePaciente, Long> {
    TelefonePacienteRepository repository = TelefonePacienteRepository.build();

    @Override
    public List<TelefonePaciente> findAll() {
        return repository.findAll();
    }

    @Override
    public TelefonePaciente findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public TelefonePaciente persist(TelefonePaciente telefonePaciente) {
        if (!Objects.nonNull(telefonePaciente)){
            System.err.println("Dados inválidos");
            return new TelefonePaciente();
        }
        return repository.persist(telefonePaciente);
    }
}
