package br.com.navita.store.model.dto;

import br.com.navita.store.entity.Term;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TermDTO {

    private Long id;
    private String term;
    private Boolean accept;

    private TermDTO(Long id, String term) {
        this.id = id;
        this.term = term;
    }

    private TermDTO(Long id, String term, boolean accept) {
        this.id = id;
        this.term = term;
        this.accept = accept;
    }

    public static TermDTO of(Term term) {
        return new TermDTO(term.getId(), term.getTerm());
    }
}
