package br.com.fiap.trataderma.domain.service.impl;

import br.com.fiap.trataderma.domain.entity.Bicicleta;
import br.com.fiap.trataderma.domain.repository.impl.BicicletaRepository;
import br.com.fiap.trataderma.domain.service.Service;

import java.util.List;

public class BicicletaService implements Service<Bicicleta, Long> {

    private final BicicletaRepository repository = BicicletaRepository.buildBicicleta();

    @Override
    public List<Bicicleta> findAll() {
        return repository.findAll();
    }

    @Override
    public Bicicleta findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Bicicleta persist(Bicicleta bicicleta) {
        return repository.persist(bicicleta);
    }
}
