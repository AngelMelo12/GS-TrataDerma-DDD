package br.com.fiap.trataderma.domain.resource;

import br.com.fiap.trataderma.domain.dto.QuadroClinicoDTO;
import br.com.fiap.trataderma.domain.entity.QuadroClinico;
import br.com.fiap.trataderma.domain.service.impl.QuadroClinicoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/quadroClinico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuadroClinicoResource implements Resource<QuadroClinicoDTO, Long>{

    @Context
    UriInfo uriInfo;

    private final QuadroClinicoService service = new QuadroClinicoService();

    @GET
    @Override
    public Response findAll() {
        List<QuadroClinico> all = service.findAll();
        return Response.ok(all.stream().map(QuadroClinicoDTO::of).toList()).build();
    }
    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        QuadroClinico entity = service.findById(id);
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(QuadroClinicoDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(QuadroClinicoDTO quadroClinicodto) {

        QuadroClinico persisted = service.persist(QuadroClinicoDTO.of(quadroClinicodto));

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(QuadroClinicoDTO.of(persisted)).build();
    }
}
