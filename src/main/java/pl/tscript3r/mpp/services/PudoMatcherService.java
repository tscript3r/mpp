package pl.tscript3r.mpp.services;

import pl.tscript3r.mpp.domain.Pudo;

import java.util.Optional;

public interface PudoMatcherService {
    void addPudo(Pudo pudo);

    Optional<Pudo> getPudoByZipCode(Integer plz);
}
