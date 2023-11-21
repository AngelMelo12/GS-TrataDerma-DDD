package br.com.fiap.trataderma.domain.service.impl;

import br.com.fiap.trataderma.domain.entity.TelefonePaciente;
import br.com.fiap.trataderma.domain.entity.UnidadeHospitalar;
import br.com.fiap.trataderma.domain.repository.impl.TelefonePacienteRepository;
import br.com.fiap.trataderma.domain.repository.impl.UnidadeHospitalarRepository;
import br.com.fiap.trataderma.domain.service.Service;

import java.util.List;
import java.util.Objects;

public class UnidadeHospitalarService implements Service<UnidadeHospitalar, Long> {

    UnidadeHospitalarRepository repository = UnidadeHospitalarRepository.build();

    @Override
    public List<UnidadeHospitalar> findAll() {

        return repository.findAll();
    }

    @Override
    public UnidadeHospitalar findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public UnidadeHospitalar persist(UnidadeHospitalar unidadeHospitalar) {
            if (!Objects.nonNull(unidadeHospitalar)){
                System.err.println("Dados inválidos");
                return new UnidadeHospitalar();
            }
            return repository.persist(unidadeHospitalar);
    }
}
