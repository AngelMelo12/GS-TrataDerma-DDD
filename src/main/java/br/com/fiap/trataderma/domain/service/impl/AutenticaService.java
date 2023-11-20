package br.com.fiap.trataderma.domain.service.impl;

import br.com.fiap.trataderma.domain.entity.Autentica;
import br.com.fiap.trataderma.domain.repository.impl.AutenticaRepository;
import br.com.fiap.trataderma.domain.service.Service;

import java.util.List;
import java.util.Objects;

public class AutenticaService implements Service<Autentica, Long> {

    AutenticaRepository repository = AutenticaRepository.build();

    @Override
    public List<Autentica> findAll() {
        return repository.findAll();
    }

    @Override
    public Autentica findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Autentica persist(Autentica autentica) {

        if (Objects.nonNull(autentica)){
            System.err.println("Dados inválidos");
            return new Autentica();
        }
        return repository.persist(autentica);
    }
}
