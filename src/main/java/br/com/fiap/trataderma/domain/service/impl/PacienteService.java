package br.com.fiap.trataderma.domain.service.impl;

import br.com.fiap.trataderma.domain.entity.Imagens;
import br.com.fiap.trataderma.domain.entity.Paciente;
import br.com.fiap.trataderma.domain.repository.impl.PacienteRepository;
import br.com.fiap.trataderma.domain.service.Service;

import java.util.List;
import java.util.Objects;

public class PacienteService implements Service<Paciente, Long> {

    PacienteRepository repository = PacienteRepository.build();

    @Override
    public List<Paciente> findAll() {
        return repository.findAll();
    }

    @Override
    public Paciente findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Paciente persist(Paciente paciente) {

        if (!Objects.nonNull(paciente)){
            System.err.println("Dados inválidos");
            return new Paciente();
        }
        return repository.persist(paciente);
    }
}
