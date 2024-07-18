package br.com.navita.store.service;

import br.com.navita.store.model.FirebaseCommand;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class FirebaseService {

    private final FirebaseExecutor firebaseExecutor;

    public void sendCommand(final FirebaseCommand firebaseCommand) {
        this.firebaseExecutor.sendCommand(firebaseCommand);
    }

}
