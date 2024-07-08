package br.com.navita.store.service;

import br.com.navita.store.entity.DeviceTerm;
import br.com.navita.store.repository.DeviceTermRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class DeviceTermService {

    private final DeviceTermRepository deviceTermRepository;

    public Optional<DeviceTerm> getLastDeviceTerm(final String appId) {
        return this.deviceTermRepository.findLastTermByAppId(appId);
    }

    public boolean existsTermLinkedToDevice(final Long id) {
        return this.deviceTermRepository.existsTermLinkedToDevice(id);
    }

    public void createLinkDeviceTerm(final DeviceTerm deviceTerm) {
        this.deviceTermRepository.persist(deviceTerm);
    }
}
