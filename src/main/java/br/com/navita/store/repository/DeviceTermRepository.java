package br.com.navita.store.repository;

import br.com.navita.store.entity.DeviceTerm;
import br.com.navita.store.entity.DeviceTermId;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.Optional;

@ApplicationScoped
public class DeviceTermRepository implements PanacheRepositoryBase<DeviceTerm, DeviceTermId> {

    public Optional<DeviceTerm> findLastTermByAppId(final String appId) {
        return find("device.appId = :appId order by id desc",
                Parameters.with("appId", appId)).firstResultOptional();
    }

    public boolean existsTermLinkedToDevice(final Long termId) {
        return count("term.id = :termId", Parameters.with("termId", termId)) > BigDecimal.ZERO.intValue();
    }
}
