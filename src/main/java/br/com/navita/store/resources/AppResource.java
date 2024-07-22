package br.com.navita.store.resources;

import br.com.navita.store.model.dto.AppDTO;
import br.com.navita.store.model.dto.FeedbackAppDTO;
import br.com.navita.store.model.dto.FeedbackDTO;
import br.com.navita.store.service.AppService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/app")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@Tag(name = "App Resource")
public class AppResource {

    private final AppService appService;

    @GET
    @Path("/{app-id}")
    @Operation(summary = "Solicitar listagem de aplicativos")
    public Response listApp(@PathParam("app-id") final String appId) {
        this.appService.listApp(appId);
        return Response.ok().build();
    }

    @POST
    @Path("/{app-id}/feedback")
    @Operation(summary = "Recebe notificação de feedback")
    public Response feedbackApp(@PathParam("app-id") final String appId, final FeedbackDTO feedbackDTO) {
        this.appService.feedback(appId, feedbackDTO);
        return Response.ok().build();
    }

}
