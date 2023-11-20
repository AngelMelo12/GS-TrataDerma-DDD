package br.com.fiap.trataderma.domain.entity;

public class EnderecoPaciente {

    private Long id;
    private Long numero;
    private String cep;
    private String pontoReferencia;

    public EnderecoPaciente() {
    }

    public EnderecoPaciente(Long id, Long numero, String cep, String pontoReferencia) {
        this.id = id;
        this.numero = numero;
        this.cep = cep;
        this.pontoReferencia = pontoReferencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    @Override
    public String toString() {
        return "EnderecoPaciente{" +
                "id=" + id +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                ", pontoReferencia='" + pontoReferencia + '\'' +
                '}';
    }
}
