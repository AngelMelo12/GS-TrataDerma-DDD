package br.com.fiap.trataderma.domain.entity;

public class QuadroClinico {

    private Long id;
    private String descricaoAlergias;

    Paciente paciente;

    public QuadroClinico() {
    }

    public QuadroClinico(Long id, String descricaoAlergias, Paciente paciente) {
        this.id = id;
        this.descricaoAlergias = descricaoAlergias;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoAlergias() {
        return descricaoAlergias;
    }

    public void setDescricaoAlergias(String descricaoAlergias) {
        this.descricaoAlergias = descricaoAlergias;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "QuadroClinico{" +
                "id=" + id +
                ", descricaoAlergias='" + descricaoAlergias + '\'' +
                ", paciente=" + paciente +
                '}';
    }
}


