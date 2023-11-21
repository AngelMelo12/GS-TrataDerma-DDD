package br.com.fiap.trataderma.domain.resource;

import br.com.fiap.trataderma.domain.dto.EnderecoPacienteDTO;
import br.com.fiap.trataderma.domain.entity.EnderecoPaciente;
import br.com.fiap.trataderma.domain.service.impl.EnderecoPacienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;


@Path("/enderecoPaciente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoPacienteResource implements Resource<EnderecoPacienteDTO, Long>{

    @Context
    UriInfo uriInfo;

    private final EnderecoPacienteService service = new EnderecoPacienteService();

    @GET
    @Override
    public Response findAll() {
        List<EnderecoPaciente> all = service.findAll();
        return Response.ok(all.stream().map(EnderecoPacienteDTO::of).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        EnderecoPaciente entity = service.findById(id);
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(EnderecoPacienteDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(EnderecoPacienteDTO enderecoPacienteDTO) {

        EnderecoPaciente persisted = service.persist(EnderecoPacienteDTO.of(enderecoPacienteDTO));

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(EnderecoPacienteDTO.of(persisted)).build();
    }
}
