package br.com.fiap.trataderma.domain.entity;

public class TelefonePaciente {

    private Long id;
    private Long numeroDdi;
    private Long numeroDdd;
    private Long numeroTelefone;
    private String tipoTelefone;

    public TelefonePaciente() {
    }

    public TelefonePaciente(Long id, Long numeroDdi, Long numeroDdd, Long numeroTelefone, String tipoTelefone) {
        this.id = id;
        this.numeroDdi = numeroDdi;
        this.numeroDdd = numeroDdd;
        this.numeroTelefone = numeroTelefone;
        this.tipoTelefone = tipoTelefone;
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

    @Override
    public String toString() {
        return "TelefonePaciente{" +
                "id=" + id +
                ", numeroDdi=" + numeroDdi +
                ", numeroDdd=" + numeroDdd +
                ", numeroTelefone=" + numeroTelefone +
                ", tipoTelefone='" + tipoTelefone + '\'' +
                '}';
    }
}


