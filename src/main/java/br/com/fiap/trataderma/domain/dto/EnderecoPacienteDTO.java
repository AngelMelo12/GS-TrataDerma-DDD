package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.EnderecoPaciente;
import br.com.fiap.trataderma.domain.entity.Paciente;
import br.com.fiap.trataderma.domain.service.impl.EnderecoPacienteService;
import br.com.fiap.trataderma.domain.service.impl.PacienteService;

import java.util.Objects;

public record EnderecoPacienteDTO(
    Long id,
    String cep,
    Long numero,
    String pontoReferencia,
    PacienteDTO paciente

) {

    private static EnderecoPacienteService service = new EnderecoPacienteService();

    public static PacienteService pacienteService = new PacienteService();

    public static EnderecoPaciente of(EnderecoPacienteDTO dto){

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return service.findById( dto.id );

        EnderecoPaciente enderecoPaciente = new EnderecoPaciente();
        enderecoPaciente.setId(null);
        enderecoPaciente.setCep(dto.cep);
        enderecoPaciente.setNumero(dto.numero);
        enderecoPaciente.setPontoReferencia(dto.pontoReferencia);
        enderecoPaciente.setPaciente(pacienteService.findById(dto.paciente.id()));

        return enderecoPaciente;
    }

    public static EnderecoPacienteDTO of(EnderecoPaciente entity) {
        return new EnderecoPacienteDTO(entity.getId(), entity.getCep(), entity.getNumero(), entity.getPontoReferencia(), PacienteDTO.of(entity.getPaciente()));
    }
}
