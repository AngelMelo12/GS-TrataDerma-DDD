package br.com.fiap.trataderma.domain.entity;

import java.time.LocalDate;

public class Paciente {

    private Long id;
    private String nome;
    private Long cpf;
    private String rg;
    private LocalDate dataNascimento;
    private String sexo;
    private String grupoSanguineo;

    Autentica autentica;

    public Paciente() {
    }

    public Paciente(Long id, String nome, Long cpf, String rg, LocalDate dataNascimento, String sexo, String grupoSanguineo, Autentica autentica) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.grupoSanguineo = grupoSanguineo;
        this.autentica = autentica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public Autentica getAutentica() {
        return autentica;
    }

    public void setAutentica(Autentica autentica) {
        this.autentica = autentica;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf=" + cpf +
                ", rg='" + rg + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo='" + sexo + '\'' +
                ", grupoSanguineo='" + grupoSanguineo + '\'' +
                ", autentica=" + autentica +
                '}';
    }
}


