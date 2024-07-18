package br.com.navita.store.service;

import br.com.navita.store.exceptions.FirebaseException;
import br.com.navita.store.model.FirebaseCommand;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.io.InputStream;

@ApplicationScoped
@RequiredArgsConstructor
public class FirebaseExecutor {

    @ConfigProperty(name = "fcm.firebase.private.key.resource")
    String configPath;

    @ConfigProperty(name = "fcm.firebase.database.url")
    String databaseUrl;

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseExecutor() {
        FirebaseOptions options;
        if (FirebaseApp.getApps().isEmpty()) {
            try {
                final GoogleCredentials googleCredentials = GoogleCredentials.fromStream(getResourceAsStream(configPath));
                options = FirebaseOptions.builder()
                        .setCredentials(googleCredentials)
                        .setDatabaseUrl(databaseUrl)
                        .build();
            } catch (IOException e) {
                throw new FirebaseException(e);
            }
            FirebaseApp.initializeApp(options);
        }
        this.firebaseMessaging = FirebaseMessaging.getInstance();
    }

    public void sendCommand(final FirebaseCommand firebaseCommand) {
        final Message message = firebaseCommand.toMessage();
        try {
            this.firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            throw new FirebaseException(e);
        }
    }

    private static InputStream getResourceAsStream(String resourceName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
    }
}
