package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.Autentica;
import br.com.fiap.trataderma.domain.service.impl.AutenticaService;

import java.time.LocalDate;
import java.util.Objects;

public record AutenticaDTO(

         Long id,
         String login,
         String senha,
         String status
) {

    public static AutenticaService autenticaService = new AutenticaService();

    public static Autentica of(AutenticaDTO dto){

        if(Objects.isNull(dto)) return null;

        if(Objects.nonNull(dto.id)) return autenticaService.findById(dto.id);

        Autentica autentica = new Autentica();
        autentica.setId(null);
        autentica.setLogin(dto.login);
        autentica.setSenha(dto.senha);
        autentica.setStatus(dto.status);

        return autentica;
    }

    public static AutenticaDTO of(Autentica entity) {
        return new AutenticaDTO(entity.getId(), entity.getLogin(), entity.getSenha(), entity.getStatus());
    }

}
