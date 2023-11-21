package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.UnidadeHospitalar;
import br.com.fiap.trataderma.domain.service.impl.UnidadeHospitalarService;

import java.time.LocalDate;
import java.util.Objects;

public record UnidadeHospitalarDTO(

        Long id,
        String razaoSocial,
        Long numero,
        Long cep,
        LocalDate dataCadastro

) {

    private static UnidadeHospitalarService service = new UnidadeHospitalarService();

    public static UnidadeHospitalar of(UnidadeHospitalarDTO dto) {

        if (Objects.isNull( dto )) return null;
        if (Objects.nonNull( dto.id )) return service.findById( dto.id );

        UnidadeHospitalar unidadeHospitalar = new UnidadeHospitalar();
        unidadeHospitalar.setId(null);
        unidadeHospitalar.setRazaoSocial(dto.razaoSocial);
        unidadeHospitalar.setNumero(dto.numero);
        unidadeHospitalar.setCep(dto.cep);
        unidadeHospitalar.setDataCadastro(dto.dataCadastro);


        return unidadeHospitalar;
    }

    public static UnidadeHospitalarDTO of(UnidadeHospitalar entity) {
        return new UnidadeHospitalarDTO( entity.getId(), entity.getRazaoSocial(), entity.getNumero(), entity.getCep(), entity.getDataCadastro());
    }

}


