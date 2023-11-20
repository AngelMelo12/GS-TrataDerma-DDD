package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.EnderecoPaciente;
import br.com.fiap.trataderma.domain.service.impl.EnderecoPacienteService;

import java.util.Objects;

public record EnderecoPacienteDTO(
    Long id,
    Long numero,
    String cep,
    String pontoReferencia

) {

    private static EnderecoPacienteService service = new EnderecoPacienteService();

    public static EnderecoPaciente of(EnderecoPacienteDTO dto){

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return service.findById( dto.id );

        EnderecoPaciente enderecoPaciente = new EnderecoPaciente();
        enderecoPaciente.setId(null);
        enderecoPaciente.setNumero(dto.numero);
        enderecoPaciente.setCep(dto.cep);
        enderecoPaciente.setPontoReferencia(dto.pontoReferencia);

        return enderecoPaciente;
    }

    public static EnderecoPacienteDTO of(EnderecoPaciente entity) {
        return new EnderecoPacienteDTO(entity.getId(), entity.getNumero(), entity.getCep(), entity.getPontoReferencia());
    }
}
