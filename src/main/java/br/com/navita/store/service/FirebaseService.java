package br.com.navita.store.service;

import br.com.navita.store.exceptions.ApplicationException;
import br.com.navita.store.model.FirebaseCommand;
import br.com.navita.store.model.dto.AppDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@RequiredArgsConstructor
public class FirebaseService {

    private final FirebaseExecutor firebaseExecutor;

    public void sendCommand(final List<AppDTO> list, final String token){
        final ObjectMapper mapper = new ObjectMapper();
        list.stream()
                .map(appDTO -> {
                    try {
                        final Map data = mapper.readValue(mapper.writeValueAsString(appDTO), HashMap.class);
                        return new FirebaseCommand(token, appDTO.getCommandUUID(), data);
                    } catch (Exception e) {
                        throw new ApplicationException(e);
                    }
                })
                .forEach(this.firebaseExecutor::sendCommand);
    }

    // "REQUEST_TYPE":"SYNC_APP_LIST"

    // "REQUEST_TYPE":"DELETE_APP"

    // "REQUEST_TYPE":"APP_ID" - "APP_ID": "fsafdsfds  fdsf fsdf"


    // "REQUEST_TYPE":"APP_LIST"
    // "DATA":"{}"

}
