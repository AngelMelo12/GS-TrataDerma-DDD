package br.com.fiap.trataderma.domain.repository.impl;

import br.com.fiap.trataderma.domain.entity.Paciente;
import br.com.fiap.trataderma.domain.repository.Repository;

import java.util.List;

public class PacienteRepository implements Repository<Paciente, Long> {
    public static PacienteRepository build() {
        return new PacienteRepository();
    }

    @Override
    public List<Paciente> findAll() {
        return null;
    }

    @Override
    public Paciente findById(Long id) {
        return null;
    }

    @Override
    public Paciente persist(Paciente paciente) {
        return null;
    }
}
