package br.com.navita.store.resources;

import br.com.navita.store.model.TermDTO;
import br.com.navita.store.service.TermService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/term")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@RequestScoped
public class TermResource {

    private final TermService termService;

    @GET
    @Path("/{appId}")
    public Response getTerm(@PathParam("appId") final String appId) {
        return Response.ok(this.termService.getTerm(appId)).build();
    }

    @POST
    public Response createTerm(final TermDTO termDTO) {
        return Response.ok(this.termService.createTerm(termDTO)).build();
    }

    @PUT
    public Response updateTerm(final TermDTO termDTO) {
        this.termService.updateTerm(termDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTerm(@PathParam("id") final Long id) {
        this.termService.deleteTerm(id);
        return Response.ok().build();
    }

    @POST
    @Path("/{appId}/{termId}")
    public Response termsAcceptedAt(@PathParam("appId") final String appId, @PathParam("termId") final Long termId) {
        this.termService.termsAcceptedAt(appId, termId);
        return Response.ok().build();
    }

}
