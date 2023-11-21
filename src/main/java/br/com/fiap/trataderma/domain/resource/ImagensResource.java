package br.com.fiap.trataderma.domain.resource;

import br.com.fiap.trataderma.domain.dto.ImagensDTO;
import br.com.fiap.trataderma.domain.entity.Imagens;
import br.com.fiap.trataderma.domain.service.impl.ImagensService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Path("/imagens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImagensResource implements Resource<ImagensDTO, Long> {

    @Context
    UriInfo uriInfo;

    private final ImagensService service = new ImagensService();

    @GET
    @Override
    public Response findAll() {
        List<Imagens> all = service.findAll();
        return Response.ok(all.stream().map(ImagensDTO::of).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        Imagens entity = service.findById(id);
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(ImagensDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(ImagensDTO imagensDTO) {

        Imagens persisted = service.persist(ImagensDTO.of(imagensDTO));

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(ImagensDTO.of(persisted)).build();
    }
}
