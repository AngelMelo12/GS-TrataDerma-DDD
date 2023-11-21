package br.com.fiap.trataderma.domain.dto;

import br.com.fiap.trataderma.domain.entity.Imagens;
import br.com.fiap.trataderma.domain.service.impl.ImagensService;
import br.com.fiap.trataderma.domain.service.impl.PacienteService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public record ImagensDTO(
        Long id,
        String linkUrl,
        LocalDate dataEnvio,
        PacienteDTO paciente

) {
    private static ImagensService imagensService = new ImagensService();
    public static PacienteService pacienteService = new PacienteService();

    public static Imagens of(ImagensDTO dto){

        if(Objects.isNull(dto)) return null;
        if(Objects.nonNull(dto.id)) return imagensService.findById(dto.id);

        Imagens imagens = new Imagens();
        imagens.setId(null);
        imagens.setLinkUrl(dto.linkUrl);
        imagens.setDataEnvio(dto.dataEnvio);
        imagens.setPaciente(pacienteService.findById(dto.paciente.id()));

        return imagens;
    }

        public static ImagensDTO of(Imagens entity){
            return new ImagensDTO(entity.getId(), entity.getLinkUrl(), entity.getDataEnvio(), PacienteDTO.of(entity.getPaciente()));
        }

}
