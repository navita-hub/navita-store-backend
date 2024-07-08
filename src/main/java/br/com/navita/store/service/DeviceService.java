package br.com.navita.store.service;

import br.com.navita.store.entity.Device;
import br.com.navita.store.exceptions.ApplicationException;
import br.com.navita.store.model.DeviceDTO;
import br.com.navita.store.repository.DeviceRepository;
import br.com.navita.store.repository.DeviceTermRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public Device getDeviceByAppId(final String appId) {
        final Optional<Device> deviceOptional = deviceRepository.findByAppId(appId);
        return deviceOptional.orElseThrow(() -> new ApplicationException("Device not found"));
    }

    public void createDevice(final DeviceDTO deviceDTO) {
        deviceRepository.persist(new Device(deviceDTO));
    }

    public void updateToken(final DeviceDTO deviceDTO) {
        final Optional<Device> deviceOptional = deviceRepository.findByAppId(deviceDTO.getAppId());
        final Device device = deviceOptional.orElseThrow(() -> new ApplicationException("Device not found"));
        device.setFirebaseToken(deviceDTO.getFirebaseToken());
        device.setUpdatedAt(LocalDateTime.now());
        deviceRepository.persist(device);
    }
}
