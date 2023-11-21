package br.com.fiap.trataderma.domain.service.impl;

import br.com.fiap.trataderma.domain.entity.Consulta;
import br.com.fiap.trataderma.domain.entity.EnderecoPaciente;
import br.com.fiap.trataderma.domain.repository.impl.EnderecoPacienteRepository;
import br.com.fiap.trataderma.domain.service.Service;

import java.util.List;
import java.util.Objects;

public class EnderecoPacienteService implements Service<EnderecoPaciente, Long> {

    EnderecoPacienteRepository repository = EnderecoPacienteRepository.build();

    @Override
    public List<EnderecoPaciente> findAll() {
        return repository.findAll();
    }

    @Override
    public EnderecoPaciente findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public EnderecoPaciente persist(EnderecoPaciente enderecoPaciente) {
        if (!Objects.nonNull(enderecoPaciente)) {
            System.err.println("Dados inválidos");
            return new EnderecoPaciente();
        }
        return repository.persist(enderecoPaciente);
    }
}
