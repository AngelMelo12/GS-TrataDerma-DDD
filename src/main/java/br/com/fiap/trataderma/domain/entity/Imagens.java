package br.com.fiap.trataderma.domain.entity;

import java.sql.Blob;
import java.time.LocalDate;

public class Imagens {

    private Long id;
    private LocalDate envio;
    private Blob fotos;

    public Imagens() {
    }

    public Imagens(Long id, LocalDate envio, Blob fotos) {
        this.id = id;
        this.envio = envio;
        this.fotos = fotos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getEnvio() {
        return envio;
    }

    public void setEnvio(LocalDate envio) {
        this.envio = envio;
    }

    public Blob getFotos() {
        return fotos;
    }

    public void setFotos(Blob fotos) {
        this.fotos = fotos;
    }

    @Override
    public String toString() {
        return "Imagens{" +
                "id=" + id +
                ", envio=" + envio +
                ", fotos=" + fotos +
                '}';
    }
}


