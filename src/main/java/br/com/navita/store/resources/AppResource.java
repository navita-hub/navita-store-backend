package br.com.navita.store.resources;

import br.com.navita.store.model.dto.AppDTO;
import br.com.navita.store.model.dto.FeedbackAppDTO;
import br.com.navita.store.service.AppService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Path("/app")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class AppResource {

    private final AppService appService;

    @GET
    @Path("/{appId}")
    public Response listApp(@PathParam("appId") final String appId) {
        this.appService.listApp(appId);
        return Response.ok().build();
    }

    @POST
    @Path("/{appId}")
    public Response sendApp(@PathParam("appId") final String appId, final List<AppDTO> appList) {
        return Response.ok().build();
    }

    @POST
    @Path("/{appId}/uninstall")
    public Response uninstallApp(@PathParam("appId") final String appId, final List<AppDTO> appList) {
        return Response.ok().build();
    }

    @POST
    @Path("/{appId}/feedback")
    public Response feedbackApp(@PathParam("appId") final String appId, final FeedbackAppDTO feedbackAppDTO) {
        return Response.ok().build();
    }

}
