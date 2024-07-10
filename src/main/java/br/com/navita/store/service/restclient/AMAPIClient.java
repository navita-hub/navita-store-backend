package br.com.navita.store.service.restclient;

import br.com.navita.store.exceptions.RestClientExceptionMapper;
import br.com.navita.store.model.dto.AppDTO;
import br.com.navita.store.model.dto.FeedbackAppDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;

import java.util.List;

@RegisterRestClient(configKey = "emm-amapi-api")
@RegisterProvider(RestClientExceptionMapper.class)
@RegisterProvider(ResteasyJackson2Provider.class)
public interface AMAPIClient {

    @GET
    @Path("/app/{appId}/list")
    List<AppDTO> listApps(@PathParam("appId") final String appId);

    @POST
    @Path("/feedback/{appId}")
    Response feedbackCommand(@PathParam("appId") final String appId, final FeedbackAppDTO feedbackAppDTO);

}
