package br.com.fiap.trataderma.domain.resource;

import br.com.fiap.trataderma.domain.dto.PacienteDTO;
import br.com.fiap.trataderma.domain.entity.Paciente;
import br.com.fiap.trataderma.domain.service.impl.PacienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/paciente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource implements Resource<PacienteDTO, Long>{

    @Context
    UriInfo uriInfo;

    private final PacienteService service = new PacienteService();

    @GET
    @Override
    public Response findAll() {
        List<Paciente> all = service.findAll();
        return Response.ok(all.stream().map(PacienteDTO::of).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        Paciente entity = service.findById(id);
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(PacienteDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(PacienteDTO pacientedto) {

        Paciente persisted = service.persist(PacienteDTO.of(pacientedto));

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(PacienteDTO.of(persisted)).build();
    }

}
