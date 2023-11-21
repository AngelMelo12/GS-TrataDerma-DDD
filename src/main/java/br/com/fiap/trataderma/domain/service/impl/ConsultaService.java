package br.com.fiap.trataderma.domain.service.impl;

import br.com.fiap.trataderma.domain.entity.Autentica;
import br.com.fiap.trataderma.domain.entity.Consulta;
import br.com.fiap.trataderma.domain.repository.impl.ConsultaRepository;
import br.com.fiap.trataderma.domain.service.Service;

import java.util.List;
import java.util.Objects;

public class ConsultaService implements Service<Consulta, Long> {

    ConsultaRepository repository = ConsultaRepository.build();

    @Override
    public List<Consulta> findAll() {
        return repository.findAll();
    }

    @Override
    public Consulta findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Consulta persist(Consulta consulta) {

        if (!Objects.nonNull(consulta)){
            System.err.println("Dados inválidos");
            return new Consulta();
        }
        return repository.persist(consulta);
    }
}
