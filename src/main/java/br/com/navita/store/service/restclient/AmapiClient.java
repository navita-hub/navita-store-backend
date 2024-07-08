package br.com.navita.store.service.restclient;

import br.com.navita.store.exceptions.RestClientExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;

@RegisterRestClient(configKey = "emm-amapi-api")
@RegisterProvider(RestClientExceptionMapper.class)
@RegisterProvider(ResteasyJackson2Provider.class)
public interface AmapiClient {

    @GET
    @Path("/app/list/{appId}")
    Response listApps(@PathParam("appId") String appId);

    @POST
    @Path("/feedback/{appId}")
    Response feedbackCommand(@PathParam("appId") String appId);



}
