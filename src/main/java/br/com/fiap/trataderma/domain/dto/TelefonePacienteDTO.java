package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.TelefonePaciente;
import br.com.fiap.trataderma.domain.service.impl.PacienteService;
import br.com.fiap.trataderma.domain.service.impl.TelefonePacienteService;

import java.util.Objects;

public record TelefonePacienteDTO(

        Long id,
        Long numeroDdi,
        Long numeroDdd,
        Long numeroTelefone,
        String tipoTelefone,

        PacienteDTO paciente
) {

    private static TelefonePacienteService service = new TelefonePacienteService();

    public static PacienteService pacienteService = new PacienteService();

    public static TelefonePaciente of(TelefonePacienteDTO dto) {

        if (Objects.isNull( dto )) return null;
        if (Objects.nonNull( dto.id )) return service.findById( dto.id );

        TelefonePaciente telefonePaciente = new TelefonePaciente();
        telefonePaciente.setId(null);
        telefonePaciente.setNumeroDdi(dto.numeroDdi);
        telefonePaciente.setNumeroDdd(dto.numeroDdd);
        telefonePaciente.setNumeroTelefone(dto.numeroTelefone);
        telefonePaciente.setTipoTelefone(dto.tipoTelefone);
        telefonePaciente.setPaciente(pacienteService.findById(dto.paciente.id()));

        return telefonePaciente;
    }

    public static TelefonePacienteDTO of(TelefonePaciente entity) {
        return new TelefonePacienteDTO( entity.getId(), entity.getNumeroDdi(), entity.getNumeroDdd(), entity.getNumeroTelefone(), entity.getTipoTelefone(), PacienteDTO.of(entity.getPaciente()));
    }
}
