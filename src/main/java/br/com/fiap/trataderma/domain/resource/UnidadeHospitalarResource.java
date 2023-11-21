package br.com.fiap.trataderma.domain.resource;

import br.com.fiap.trataderma.domain.dto.UnidadeHospitalarDTO;
import br.com.fiap.trataderma.domain.entity.UnidadeHospitalar;
import br.com.fiap.trataderma.domain.service.impl.UnidadeHospitalarService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/unidadeHospitalar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UnidadeHospitalarResource implements Resource<UnidadeHospitalarDTO, Long>{

    @Context
    UriInfo uriInfo;

    private final UnidadeHospitalarService service = new UnidadeHospitalarService();

    @GET
    @Override
    public Response findAll() {
        List<UnidadeHospitalar> all = service.findAll();
        return Response.ok(all.stream().map(UnidadeHospitalarDTO::of).toList()).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id")Long id) {
        UnidadeHospitalar entity = service.findById(id);
        if (Objects.isNull(entity)) return Response.status(404).build();
        return Response.ok(UnidadeHospitalarDTO.of(entity)).build();
    }

    @POST
    @Override
    public Response persist(UnidadeHospitalarDTO unidadeHospitalardto) {
        UnidadeHospitalar persisted = service.persist(UnidadeHospitalarDTO.of(unidadeHospitalardto));

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(String.valueOf(persisted.getId())).build();

        return Response.created(uri).entity(UnidadeHospitalarDTO.of(persisted)).build();
    }
}

