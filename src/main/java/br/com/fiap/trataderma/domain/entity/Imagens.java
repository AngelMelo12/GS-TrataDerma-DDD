package br.com.fiap.trataderma.domain.entity;

import br.com.fiap.trataderma.domain.dto.PacienteDTO;

import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDate;

public class Imagens {

    private Long id;
    private String linkUrl;
    private LocalDate dataEnvio;
    Paciente paciente;

    public Imagens() {
    }

    public Imagens(Long id, String linkUrl, LocalDate dataEnvio, Paciente paciente) {
        this.id = id;
        this.linkUrl = linkUrl;
        this.dataEnvio = dataEnvio;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Imagens{" +
                "id=" + id +
                ", linkUrl='" + linkUrl + '\'' +
                ", dataEnvio=" + dataEnvio +
                ", paciente=" + paciente +
                '}';
    }
}


