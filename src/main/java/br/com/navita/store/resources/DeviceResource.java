package br.com.navita.store.resources;

import br.com.navita.store.model.AppDTO;
import br.com.navita.store.model.DeviceDTO;
import br.com.navita.store.service.DeviceService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.annotations.Body;

import java.util.List;

@Path("/device")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class DeviceResource {

    private final DeviceService deviceService;

    @POST
    public Response createDevice(final DeviceDTO deviceDTO) {
        deviceService.createDevice(deviceDTO);
        return Response.ok().build();
    }

    @PUT
    public Response updateToken(final DeviceDTO deviceDTO) {
        deviceService.updateToken(deviceDTO);
        return Response.ok().build();
    }

}
