package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.Paciente;
import br.com.fiap.trataderma.domain.service.impl.PacienteService;

import java.time.LocalDate;
import java.util.Objects;

public record PacienteDTO(

        Long id,
        String nome,
        String cpf,
        String rg,
        LocalDate dataNascimento,
        String sexo,
        String grupoSanguineo
) {

    private static PacienteService service = new PacienteService();
    public static Paciente of(PacienteDTO dto){

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return service.findById( dto.id );

        Paciente paciente = new Paciente();
        paciente.setId(null);
        paciente.setNome(dto.nome);
        paciente.setCpf(dto.cpf);
        paciente.setRg(dto.rg);
        paciente.setDataNascimento(dto.dataNascimento());
        paciente.setSexo(dto.sexo);
        paciente.setGrupoSanguineo(dto.grupoSanguineo);

        return paciente;
    }

    public static PacienteDTO of(Paciente entity){
        return new PacienteDTO(entity.getId(), entity.getNome(), entity.getCpf(), entity.getRg(), entity.getDataNascimento(), entity.getSexo(), entity.getGrupoSanguineo());
    }
}
