package br.com.fiap.trataderma.domain.service.impl;

import br.com.fiap.trataderma.domain.entity.Consulta;
import br.com.fiap.trataderma.domain.entity.Imagens;
import br.com.fiap.trataderma.domain.repository.impl.ImagensRepository;
import br.com.fiap.trataderma.domain.service.Service;

import java.util.List;
import java.util.Objects;

public class ImagensService implements Service<Imagens, Long> {

    ImagensRepository repository = ImagensRepository.build();

    @Override
    public List<Imagens> findAll() {
        return repository.findAll();
    }

    @Override
    public Imagens findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Imagens persist(Imagens imagens) {

        if (!Objects.nonNull(imagens)){
            System.err.println("Dados inválidos");
            return new Imagens();
        }
        return repository.persist(imagens);
    }
}
