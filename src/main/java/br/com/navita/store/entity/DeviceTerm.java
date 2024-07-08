package br.com.navita.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "device_term")
@Accessors(chain = true)
@Data
@NoArgsConstructor
public class DeviceTerm {

    @EmbeddedId
    public DeviceTermId id;

    @ManyToOne
    @JoinColumn(name = "device_id", insertable=false, updatable=false)
    private Device device;

    @ManyToOne
    @JoinColumn(name = "term_id", insertable=false, updatable=false)
    private Term term;

    @Column(name = "accept_at", nullable = false)
    private LocalDateTime acceptAt;

    public DeviceTerm(final Device device, final Term term) {
        this.id = new DeviceTermId(device.getId(), term.getId());
        this.acceptAt = LocalDateTime.now();
    }

}


