package br.com.navita.store.service;

import br.com.navita.store.entity.Device;
import br.com.navita.store.exceptions.ApplicationException;
import br.com.navita.store.model.dto.AppDTO;
import br.com.navita.store.service.restclient.AMAPIClient;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class AppService {

    private final FirebaseService firebaseService;
    private final AMAPIClient amapiClient;
    private final DeviceService deviceService;

    public void listApp(final String appId) {
        final Device device = this.deviceService.getDeviceByAppId(appId);

        if (StringUtils.isBlank(device.getFirebaseToken())) {
            throw new ApplicationException("Invalid firebase token");
        }

        final List<AppDTO> appList = this.amapiClient.listApps(appId);
        this.firebaseService.sendCommand(appList, device.getFirebaseToken());
    }

}
