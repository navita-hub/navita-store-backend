package br.com.navita.store.service;

import br.com.navita.store.entity.Device;
import br.com.navita.store.model.FirebaseCommand;
import br.com.navita.store.model.RequestType;
import br.com.navita.store.model.dto.ConsultAppDTO;
import br.com.navita.store.model.dto.FeedbackAppDTO;
import br.com.navita.store.model.dto.FeedbackDTO;
import br.com.navita.store.service.queue.AppQueue;
import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.function.Supplier;

@ApplicationScoped
@RequiredArgsConstructor
public class AppService {

    public static final String STORE = "STORE";
    public static final String REQUEST_TYPE = "REQUEST_TYPE";
    public static final String DATA = "DATA";

    private final AppQueue appQueue;
    private final DeviceService deviceService;
    private final FirebaseService firebaseService;

    public void listApp(final String appId) {
        this.appQueue.listApp(ConsultAppDTO.builder().appId(appId).origin(STORE).build());
    }

    public void feedback(final String appId, final FeedbackDTO feedbackDTO) {
        this.appQueue.feedback(FeedbackAppDTO.builder()
                .appId(appId)
                .uuid(feedbackDTO.getUuid())
                .message(feedbackDTO.getMessage())
                .build());
    }

    @Transactional
    public void sendAppListToDevice(final ConsultAppDTO consultAppDTO) {
        this.generalSend(consultAppDTO.getAppId(), () -> {
            final String listApp = new Gson().toJson(consultAppDTO.getListApps());
            return Map.of(REQUEST_TYPE, RequestType.APP_LIST.name(), DATA, listApp);
        });
    }

    @Transactional
    public void sendDeleteAppToDevice(final ConsultAppDTO consultAppDTO) {
        this.generalSend(consultAppDTO.getAppId(), () -> {
            final String listApp = new Gson().toJson(consultAppDTO.getListApps());
            return Map.of(REQUEST_TYPE, RequestType.DELETE_APP.name(), DATA, listApp);
        });
    }

    @Transactional
    public void sendSyncAppsToDevice(final ConsultAppDTO consultAppDTO) {
        this.generalSend(consultAppDTO.getAppId(), () -> Map.of(REQUEST_TYPE, RequestType.SYNC_APP_LIST.name()));
    }

    private void generalSend(final String appId, final Supplier<Map<String,String>> supplierData) {
        final Device device = this.deviceService.getDeviceByAppId(appId);
        this.firebaseService.sendCommand(FirebaseCommand.of(device.getFirebaseToken(), supplierData.get()));
    }

}
