package br.com.fiap.trataderma.domain.entity;

import java.time.LocalDate;

public class Autentica {

    private Long id;
    private String login;
    private String senha;
    private String status;

    public Autentica() {
    }

    public Autentica(Long id, String login, String senha, String status) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Autentica{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
