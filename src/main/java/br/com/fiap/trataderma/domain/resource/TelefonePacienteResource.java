package br.com.fiap.trataderma.domain.resource;

import br.com.fiap.trataderma.domain.dto.TelefonePacienteDTO;
import br.com.fiap.trataderma.domain.entity.TelefonePaciente;
import br.com.fiap.trataderma.domain.service.impl.TelefonePacienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/telefonePaciente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefonePacienteResource implements Resource<TelefonePacienteDTO, Long> {
    
    @Context
    UriInfo uriInfo;

    private final TelefonePacienteService service = new TelefonePacienteService();

    @GET
    @Override
    public Response findAll() {
        List<TelefonePaciente> all = service.findAll();
        return Response.ok(all.stream().map(TelefonePacienteDTO::of).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        TelefonePaciente entity = service.findById(id);
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(TelefonePacienteDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(TelefonePacienteDTO telefonePacientedto) {

        TelefonePaciente persisted = service.persist(TelefonePacienteDTO.of(telefonePacientedto));

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(TelefonePacienteDTO.of(persisted)).build();
    }
}
