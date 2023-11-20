package br.com.fiap.trataderma.domain.resource;

import br.com.fiap.trataderma.domain.dto.ConsultaDTO;
import br.com.fiap.trataderma.domain.entity.Consulta;
import br.com.fiap.trataderma.domain.service.impl.ConsultaService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Objects;

public class ConsultaResource implements Resource<ConsultaDTO, Long> {

    @Context
    UriInfo uriInfo;

    private final ConsultaService service = new ConsultaService();

    @GET
    @Override
    public Response findAll() {
        List<Consulta> all = service.findAll();
        return Response.ok(all.stream().map(ConsultaDTO::of).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        Consulta entity = service.findById(id);
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(ConsultaDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(ConsultaDTO consultaDTO) {

        Consulta persisted = service.persist(ConsultaDTO.of(consultaDTO));

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(ConsultaDTO.of(persisted)).build();

    }
}
