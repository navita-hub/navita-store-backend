package br.com.navita.store.repository;

import br.com.navita.store.entity.Device;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class DeviceRepository implements PanacheRepositoryBase<Device, UUID> {

}
