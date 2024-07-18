package br.com.navita.store.model;

import com.google.firebase.messaging.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor(staticName = "of")
public class FirebaseCommand {

    private String token;
    private Map<String, String> data;

    public Message toMessage() {
        return Message.builder()
                    .putAllData(Objects.requireNonNullElse(this.data, new HashMap<>()))
                    .setToken(token)
                    .build();
    }
}
