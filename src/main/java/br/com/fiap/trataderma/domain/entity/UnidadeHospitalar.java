package br.com.fiap.trataderma.domain.entity;

import java.time.LocalDate;

public class UnidadeHospitalar {

    private Long id;
    private String RazaoSocial;
    private Long numero;
    private LocalDate dataCadastro;
    private String cep;

    public UnidadeHospitalar() {
    }

    public UnidadeHospitalar(Long id, String razaoSocial, Long numero, LocalDate dataCadastro, String cep) {
        this.id = id;
        RazaoSocial = razaoSocial;
        this.numero = numero;
        this.dataCadastro = dataCadastro;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        RazaoSocial = razaoSocial;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "UnidadeHospitalar{" +
                "id=" + id +
                ", RazaoSocial='" + RazaoSocial + '\'' +
                ", numero=" + numero +
                ", dataCadastro=" + dataCadastro +
                ", cep='" + cep + '\'' +
                '}';
    }
}
