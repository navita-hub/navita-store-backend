package br.com.navita.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Entity
@Table(name = "device")
@Data
@Accessors(chain = true)
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

}


