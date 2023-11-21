package br.com.fiap.trataderma.domain.service.impl;

import br.com.fiap.trataderma.domain.entity.Paciente;
import br.com.fiap.trataderma.domain.entity.QuadroClinico;
import br.com.fiap.trataderma.domain.repository.impl.QuadroClinicoRepository;
import br.com.fiap.trataderma.domain.service.Service;

import java.util.List;
import java.util.Objects;

public class QuadroClinicoService implements Service<QuadroClinico, Long> {

    QuadroClinicoRepository repository = QuadroClinicoRepository.build();

    @Override
    public List<QuadroClinico> findAll() {
        return repository.findAll();
    }

    @Override
    public QuadroClinico findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public QuadroClinico persist(QuadroClinico quadroClinico) {
        if (!Objects.nonNull(quadroClinico)){
            System.err.println("Dados inválidos");
            return new QuadroClinico();
        }
        return repository.persist(quadroClinico);
    }
}
