package br.com.navita.store.service.queue;

import br.com.navita.store.model.dto.ConsultAppDTO;
import br.com.navita.store.model.dto.FeedbackAppDTO;
import br.com.navita.store.service.AppService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.rabbitmq.IncomingRabbitMQMessage;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
@RequiredArgsConstructor
public class AppQueue {

    @Channel("app")
    Emitter<ConsultAppDTO> appEmitter;

    @Channel("feedback")
    Emitter<FeedbackAppDTO> feedbackEmitter;

    private final AppService appService;

    public void listApp(final ConsultAppDTO consultAppDTO) {
        this.appEmitter.send(consultAppDTO);
    }

    public void feedback(final FeedbackAppDTO feedbackAppDTO) {
        this.feedbackEmitter.send(feedbackAppDTO);
    }

    @Incoming("applist")
    @Blocking(ordered = false, value = "rabbitmq-thread-pool")
    public CompletionStage<Void> appList(final Message<JsonObject> message) {
        final IncomingRabbitMQMessage<?> unwrap = message.unwrap(IncomingRabbitMQMessage.class);
        final ConsultAppDTO consultAppDTO = new Gson().fromJson(unwrap.getRabbitMQMessage().body().toString(), ConsultAppDTO.class);
        this.appService.sendAppListToDevice(consultAppDTO);
        return message.ack();
    }

    @Incoming("appdelete")
    @Blocking(ordered = false, value = "rabbitmq-thread-pool")
    public CompletionStage<Void> appDelete(final Message<JsonObject> message) {
        final IncomingRabbitMQMessage<?> unwrap = message.unwrap(IncomingRabbitMQMessage.class);
        final ConsultAppDTO consultAppDTO = new Gson().fromJson(unwrap.getRabbitMQMessage().body().toString(), ConsultAppDTO.class);
        this.appService.sendDeleteAppToDevice(consultAppDTO);
        return message.ack();
    }

    @Incoming("appsync")
    @Blocking(ordered = false, value = "rabbitmq-thread-pool")
    public CompletionStage<Void> appSync(final Message<JsonObject> message) {
        final IncomingRabbitMQMessage<?> unwrap = message.unwrap(IncomingRabbitMQMessage.class);
        final ConsultAppDTO consultAppDTO = new Gson().fromJson(unwrap.getRabbitMQMessage().body().toString(), ConsultAppDTO.class);
        this.appService.sendSyncAppsToDevice(consultAppDTO);
        return message.ack();
    }

}
