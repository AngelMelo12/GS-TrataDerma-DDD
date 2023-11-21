package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.QuadroClinico;
import br.com.fiap.trataderma.domain.service.impl.PacienteService;
import br.com.fiap.trataderma.domain.service.impl.QuadroClinicoService;

import java.util.Objects;

public record QuadroClinicoDTO(
        Long id,
        String descricaoAlergias,
        PacienteDTO paciente
) {
    private static QuadroClinicoService quadroClinicoService = new QuadroClinicoService();
    private static PacienteService pacienteService = new PacienteService();

    public static QuadroClinico of(QuadroClinicoDTO dto) {

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return quadroClinicoService.findById( dto.id );

        QuadroClinico quadroClinico = new QuadroClinico();
        quadroClinico.setId(null);
        quadroClinico.setDescricaoAlergias(dto.descricaoAlergias);
        quadroClinico.setPaciente(pacienteService.findById(dto.paciente.id()));

        return quadroClinico;
}

    public static QuadroClinicoDTO of(QuadroClinico entity) {
        return new QuadroClinicoDTO( entity.getId(), entity.getDescricaoAlergias(), PacienteDTO.of(entity.getPaciente()));
    }
}