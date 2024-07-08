package br.com.navita.store.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceDTO {

    private Long id;
    private String appId;
    private String firebaseToken;

}
