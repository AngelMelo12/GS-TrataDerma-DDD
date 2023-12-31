package br.com.fiap.trataderma.domain.entity;

public class TelefonePaciente {

    private Long id;
    private Long numeroDdi;
    private Long numeroDdd;
    private Long numeroTelefone;
    private String tipoTelefone;

    private Paciente paciente;

    public TelefonePaciente() {
    }

    public TelefonePaciente(Long id, Long numeroDdi, Long numeroDdd, Long numeroTelefone, String tipoTelefone, Paciente paciente) {
        this.id = id;
        this.numeroDdi = numeroDdi;
        this.numeroDdd = numeroDdd;
        this.numeroTelefone = numeroTelefone;
        this.tipoTelefone = tipoTelefone;
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroDdi() {
        return numeroDdi;
    }

    public void setNumeroDdi(Long numeroDdi) {
        this.numeroDdi = numeroDdi;
    }

    public Long getNumeroDdd() {
        return numeroDdd;
    }

    public void setNumeroDdd(Long numeroDdd) {
        this.numeroDdd = numeroDdd;
    }

    public Long getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(Long numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "TelefonePaciente{" +
                "id=" + id +
                ", numeroDdi=" + numeroDdi +
                ", numeroDdd=" + numeroDdd +
                ", numeroTelefone=" + numeroTelefone +
                ", tipoTelefone='" + tipoTelefone + '\'' +
                ", paciente=" + paciente +
                '}';
    }
}


