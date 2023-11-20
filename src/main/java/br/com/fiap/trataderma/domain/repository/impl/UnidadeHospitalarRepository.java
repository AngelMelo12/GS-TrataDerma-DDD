package br.com.fiap.trataderma.domain.repository.impl;

import br.com.fiap.trataderma.domain.entity.UnidadeHospitalar;
import br.com.fiap.trataderma.domain.repository.Repository;

import java.util.List;

public class UnidadeHospitalarRepository implements Repository<UnidadeHospitalar, Long> {
    public static UnidadeHospitalarRepository build() {
        return new UnidadeHospitalarRepository();
    }

    @Override
    public List<UnidadeHospitalar> findAll() {
        return null;
    }

    @Override
    public UnidadeHospitalar findById(Long id) {
        return null;
    }

    @Override
    public UnidadeHospitalar persist(UnidadeHospitalar unidadeHospitalar) {
        return null;
    }
}
