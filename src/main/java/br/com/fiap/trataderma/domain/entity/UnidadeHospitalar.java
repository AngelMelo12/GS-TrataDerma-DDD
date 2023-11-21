package br.com.fiap.trataderma.domain.entity;

import java.time.LocalDate;

public class UnidadeHospitalar {

    private Long id;
    private String razaoSocial;
    private Long numero;
    private Long cep;
    private LocalDate dataCadastro;

    public UnidadeHospitalar() {
    }

    public UnidadeHospitalar(Long id, String razaoSocial, Long numero, Long cep, LocalDate dataCadastro) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.numero = numero;
        this.cep = cep;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "UnidadeHospitalar{" +
                "id=" + id +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", numero=" + numero +
                ", cep=" + cep +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}

