package br.com.fiap.trataderma.domain.entity;

import java.time.LocalDateTime;

public class Consulta {

    private Long id;
    private LocalDateTime consulta;
    private String telefoneCentral;
    Paciente paciente;
    UnidadeHospitalar unidadeHospitalar;

    public Consulta() {
    }

    public Consulta(Long id, LocalDateTime consulta, String telefoneCentral, Paciente paciente, UnidadeHospitalar unidadeHospitalar) {
        this.id = id;
        this.consulta = consulta;
        this.telefoneCentral = telefoneCentral;
        this.paciente = paciente;
        this.unidadeHospitalar = unidadeHospitalar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getConsulta() {
        return consulta;
    }

    public void setConsulta(LocalDateTime consulta) {
        this.consulta = consulta;
    }

    public String getTelefoneCentral() {
        return telefoneCentral;
    }

    public void setTelefoneCentral(String telefoneCentral) {
        telefoneCentral = telefoneCentral;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public UnidadeHospitalar getUnidadeHospitalar() {
        return unidadeHospitalar;
    }

    public void setUnidadeHospitalar(UnidadeHospitalar unidadeHospitalar) {
        this.unidadeHospitalar = unidadeHospitalar;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", consulta=" + consulta +
                ", telefoneCentral=" + telefoneCentral +
                ", paciente=" + paciente +
                ", unidadeHospitalar=" + unidadeHospitalar +
                '}';
    }
}

