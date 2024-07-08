package br.com.navita.store.exceptions;

import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

@Slf4j
public class RestClientExceptionMapper implements ResponseExceptionMapper<ApplicationException> {

    @Override
    public ApplicationException toThrowable(Response response) {
        log.error(response.toString());
        return new ApplicationException("Erro na comunicação com o serviço externo");
    }

}
