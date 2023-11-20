package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.QuadroClinico;
import br.com.fiap.trataderma.domain.service.impl.QuadroClinicoService;

import java.util.Objects;

public record QuadroClinicoDTO(
        Long id,
        String descricaoAlergias
) {
    private static QuadroClinicoService service = new QuadroClinicoService();

    public static QuadroClinico of(QuadroClinicoDTO dto) {

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return service.findById( dto.id );

        QuadroClinico quadroClinico = new QuadroClinico();
        quadroClinico.setId(null);
        quadroClinico.setDescricaoAlergias(dto.descricaoAlergias);

        return quadroClinico;
}

    public static QuadroClinicoDTO of(QuadroClinico entity) {
        return new QuadroClinicoDTO( entity.getId(), entity.getDescricaoAlergias());
    }
}