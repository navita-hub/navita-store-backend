package br.com.navita.store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

@Data
@AllArgsConstructor
public class FeedbackDTO {

    private String uuid;
    private String message;

}
