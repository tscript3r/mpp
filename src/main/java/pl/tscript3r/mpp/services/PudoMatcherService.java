package pl.tscript3r.mpp.services;

import pl.tscript3r.mpp.domain.Pudo;
import pl.tscript3r.mpp.exceptions.PudoNotFoundException;

import java.util.Optional;

public interface PudoMatcherService {
    void addPudo(Pudo pudo);
    Optional<Pudo> getPudoByPlz(Integer plz) throws PudoNotFoundException;
}
