package br.com.navita.store.service.queue;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class AppQueue {

    @Outgoing("app")
    public Object listApp() {
        return null;
    }


}
