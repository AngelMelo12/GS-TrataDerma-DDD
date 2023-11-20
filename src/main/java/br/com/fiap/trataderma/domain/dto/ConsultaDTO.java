package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.Consulta;
import br.com.fiap.trataderma.domain.entity.Paciente;
import br.com.fiap.trataderma.domain.entity.UnidadeHospitalar;
import br.com.fiap.trataderma.domain.service.impl.ConsultaService;
import br.com.fiap.trataderma.domain.service.impl.PacienteService;
import br.com.fiap.trataderma.domain.service.impl.UnidadeHospitalarService;

import java.time.LocalDateTime;
import java.util.Objects;

public record ConsultaDTO(

        Long id,
        LocalDateTime consulta,
        String telefoneCentral,
        PacienteDTO paciente,
        UnidadeHospitalarDTO unidadeHospitalar

) {

    public static ConsultaService consultaService = new ConsultaService();
    public static PacienteService pacienteService = new PacienteService();
    public static UnidadeHospitalarService unidadeHospitalarService = new UnidadeHospitalarService();

    public static Consulta of(ConsultaDTO dto) {

        if (Objects.isNull( dto )) return null;

        if (Objects.nonNull( dto.id )) return consultaService.findById( dto.id );

        Consulta consulta = new Consulta();
        consulta.setId(null);
        consulta.setConsulta(dto.consulta);
        consulta.setTelefoneCentral(dto.telefoneCentral);
        consulta.setPaciente(pacienteService.findById(dto.paciente.id()));
        consulta.setUnidadeHospitalar(unidadeHospitalarService.findById(dto.unidadeHospitalar.id()));

        return consulta;
    }

    public static ConsultaDTO of(Consulta entity) {
        return new ConsultaDTO(entity.getId(), entity.getConsulta(), entity.getTelefoneCentral(), PacienteDTO.of(entity.getPaciente()), UnidadeHospitalarDTO.of(entity.getUnidadeHospitalar()));
    }

}
