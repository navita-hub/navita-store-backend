package br.com.navita.store.model.dto;

import lombok.Builder;

@Builder
public class FeedbackAppDTO {

    private String appId;
    private String uuid;
    private String message;

}
