package br.com.navita.store.repository;

import br.com.navita.store.entity.Term;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class TermRepository implements PanacheRepositoryBase<Term, Long> {

    public Optional<Term> findLastTerm() {
        return find("order by id desc").firstResultOptional();
    }
}
