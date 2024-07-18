package br.com.navita.store.resources;

import br.com.navita.store.model.dto.TermDTO;
import br.com.navita.store.service.TermService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/term")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@RequestScoped
@Tag(name = "Term Resource")
public class TermResource {

    private final TermService termService;

    @GET
    @Path("/{appId}")
    @Operation(summary = "Buscar termo de uso", description = "Busca o termo assinado, caso não tenha feito, retorna o termo mais atualizado")
    public Response getTerm(@PathParam("appId") final String appId) {
        return Response.ok(this.termService.getTerm(appId)).build();
    }

    @POST
    @Operation(summary = "Criar termo de uso", description = "Adiciona novo termo a ser apresentado")
    public Response createTerm(final TermDTO termDTO) {
        return Response.ok(this.termService.createTerm(termDTO)).build();
    }

    @PUT
    @Operation(summary = "Atualizar termo de uso", description = "Atualiza um termo existente")
    public Response updateTerm(final TermDTO termDTO) {
        this.termService.updateTerm(termDTO);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletar termo de uso", description = "Deleta um termo existente")
    public Response deleteTerm(@PathParam("id") final Long id) {
        this.termService.deleteTerm(id);
        return Response.ok().build();
    }

    @POST
    @Path("/{appId}/{termId}")
    @Operation(summary = "Assinar termo", description = "Cria o vínculo de assinatura do termo efetuado pelo cliente")
    public Response termsAcceptedAt(@PathParam("appId") final String appId, @PathParam("termId") final Long termId) {
        this.termService.termsAcceptedAt(appId, termId);
        return Response.ok().build();
    }

}
