package br.com.navita.store.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ConsultAppDTO {

    private String origin;
    private String appId;
    private List<AppDTO> listApps;

}
