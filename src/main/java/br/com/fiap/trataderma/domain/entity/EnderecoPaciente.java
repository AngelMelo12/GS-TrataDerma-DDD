package br.com.fiap.trataderma.domain.entity;

public class EnderecoPaciente {

    private Long id;
    private String cep;
    private Long numero;
    private String pontoReferencia;

    private Paciente paciente;

    public EnderecoPaciente() {
    }

    public EnderecoPaciente(Long id, String cep, Long numero, String pontoReferencia, Paciente paciente) {
        this.id = id;
        this.cep = cep;
        this.numero = numero;
        this.pontoReferencia = pontoReferencia;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "EnderecoPaciente{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", numero=" + numero +
                ", pontoReferencia='" + pontoReferencia + '\'' +
                ", paciente=" + paciente +
                '}';
    }
}
