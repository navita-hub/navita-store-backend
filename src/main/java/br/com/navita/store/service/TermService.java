package br.com.navita.store.service;

import br.com.navita.store.entity.Device;
import br.com.navita.store.entity.DeviceTerm;
import br.com.navita.store.entity.Term;
import br.com.navita.store.exceptions.ApplicationException;
import br.com.navita.store.model.dto.TermDTO;
import br.com.navita.store.repository.TermRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor
public class TermService {

    private final DeviceService deviceService;

    private final DeviceTermService deviceTermService;

    private final TermRepository termRepository;

    public TermDTO getTerm(final String appId) {
        final Optional<DeviceTerm> deviceTermOptional = this.deviceTermService.getLastDeviceTerm(appId);

        final Term term;
        if (deviceTermOptional.isPresent()) {
            term = deviceTermOptional.get().getTerm();
        } else {
            term = termRepository.findLastTerm().orElseThrow(ApplicationException::new);
        }

        return TermDTO.of(term);
    }

    @Transactional
    public TermDTO createTerm(final TermDTO termDTO) {
        final Term term = new Term().setTerm(termDTO.getTerm());
        termRepository.persist(term);
        termDTO.setId(term.getId());
        return termDTO;
    }

    @Transactional
    public void updateTerm(final TermDTO termDTO) {
        final Term term = termRepository.findById(termDTO.getId());
        term.setTerm(termDTO.getTerm());
        termRepository.persist(term);
    }

    @Transactional
    public void deleteTerm(final Long id) {
        final boolean existsTermLinked = this.deviceTermService.existsTermLinkedToDevice(id);

        if (existsTermLinked) {
            throw new ApplicationException("Exists term linked to device");
        }

        termRepository.deleteById(id);
    }

    @Transactional
    public void termsAcceptedAt(final String appId, final Long termId) {
        final Device device = deviceService.getDeviceByAppId(appId);
        final Optional<Term> termOptional = termRepository.findByIdOptional(termId);
        termOptional.orElseThrow(() -> new ApplicationException("Term not found"));
        deviceTermService.createLinkDeviceTerm(new DeviceTerm(device, termOptional.get()));
    }

}
