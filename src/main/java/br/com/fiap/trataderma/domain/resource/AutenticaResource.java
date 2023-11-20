package br.com.fiap.trataderma.domain.resource;

import br.com.fiap.trataderma.domain.dto.AutenticaDTO;
import br.com.fiap.trataderma.domain.entity.Autentica;
import br.com.fiap.trataderma.domain.service.impl.AutenticaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/autentica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutenticaResource implements Resource<AutenticaDTO, Long>{

    @Context
    UriInfo uriInfo;

    private final AutenticaService service = new AutenticaService();


    @GET
    @Override
    public Response findAll() {
        List<Autentica> all = service.findAll();
        return Response.ok(all.stream().map(AutenticaDTO::of).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        Autentica entity = service.findById(id);
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(AutenticaDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(AutenticaDTO autenticaDTO) {

        Autentica persisted = service.persist(AutenticaDTO.of(autenticaDTO));

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(AutenticaDTO.of(persisted)).build();
    }
}
