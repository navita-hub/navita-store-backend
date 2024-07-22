package br.com.navita.store.entity;

import br.com.navita.store.model.dto.DeviceDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Table(name = "device")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "app_id", nullable = false)
    private String appId;

    @Column(name = "firebase_token")
    private String firebaseToken;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Device(DeviceDTO deviceDTO) {
        this.appId = deviceDTO.getAppId();
        this.firebaseToken = deviceDTO.getFirebaseToken();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}


