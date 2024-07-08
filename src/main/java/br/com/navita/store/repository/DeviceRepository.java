package br.com.navita.store.repository;

import br.com.navita.store.entity.Device;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
public class DeviceRepository implements PanacheRepositoryBase<Device, Long> {

    public Optional<Device> findByAppId(final String appId) {
        return find("appId = :appId", Parameters.with("appId", appId)).singleResultOptional();
    }
}
