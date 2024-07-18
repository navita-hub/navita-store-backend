package br.com.navita.store.resources;

import br.com.navita.store.model.dto.DeviceDTO;
import br.com.navita.store.service.DeviceService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/device")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@Tag(name = "Device Resource API")
public class DeviceResource {

    private final DeviceService deviceService;

    @POST
    @Operation(summary = "Criar Device", description = "Cria o vínculo entre appId e token do firebase")
    public Response createDevice(final DeviceDTO deviceDTO) {
        deviceService.createDevice(deviceDTO);
        return Response.ok().build();
    }

    @PUT
    @Operation(summary = "Atualizar Device", description = "Atualiza o token do firebase")
    public Response updateToken(final DeviceDTO deviceDTO) {
        deviceService.updateToken(deviceDTO);
        return Response.ok().build();
    }

}
