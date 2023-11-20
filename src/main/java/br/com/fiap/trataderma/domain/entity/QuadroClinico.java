package br.com.fiap.trataderma.domain.entity;

public class QuadroClinico {

    private Long id;
    private String descricaoAlergias;

    public QuadroClinico() {
    }

    public QuadroClinico(Long id, String descricaoAlergias) {
        this.id = id;
        this.descricaoAlergias = descricaoAlergias;
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

    @Override
    public String toString() {
        return "QuadroClinico{" +
                "id=" + id +
                ", descricaoAlergias='" + descricaoAlergias + '\'' +
                '}';
    }
}


