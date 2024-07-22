package br.com.navita.store.service;

import br.com.navita.store.entity.Device;
import br.com.navita.store.exceptions.ApplicationException;
import br.com.navita.store.model.dto.DeviceDTO;
import br.com.navita.store.repository.DeviceRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Transactional
    public Device getDeviceByAppId(final String appId) {
        final Optional<Device> deviceOptional = deviceRepository.findByAppId(appId);
        return deviceOptional.orElseThrow(() -> new ApplicationException("Device not found"));
    }

    @Transactional
    public void createDevice(final DeviceDTO deviceDTO) {
        deviceRepository.persist(new Device(deviceDTO));
    }

    @Transactional
    public void updateToken(final DeviceDTO deviceDTO) {
        final Optional<Device> deviceOptional = deviceRepository.findByAppId(deviceDTO.getAppId());
        final Device device = deviceOptional.orElseThrow(() -> new ApplicationException("Device not found"));
        device.setFirebaseToken(deviceDTO.getFirebaseToken());
        device.setUpdatedAt(LocalDateTime.now());
        deviceRepository.persist(device);
    }
}
