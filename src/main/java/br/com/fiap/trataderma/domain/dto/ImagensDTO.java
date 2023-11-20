package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.Imagens;
import br.com.fiap.trataderma.domain.service.impl.ImagensService;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Objects;

public record ImagensDTO(
        Long id,
        LocalDate envio,
        Blob fotos

) {
    private static ImagensService service = new ImagensService();

    public static Imagens of(ImagensDTO dto){

        if(Objects.isNull(dto)) return null;
        if(Objects.nonNull(dto.id)) return service.findById(dto.id);

        Imagens imagens = new Imagens();
        imagens.setId(null);
        imagens.setEnvio(dto.envio);
        imagens.setFotos(dto.fotos);

        return imagens;
    }

        public static ImagensDTO of(Imagens entity){
            return new ImagensDTO(entity.getId(), entity.getEnvio(), entity.getFotos());
        }

}
