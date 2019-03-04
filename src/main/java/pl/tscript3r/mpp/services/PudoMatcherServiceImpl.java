package pl.tscript3r.mpp.services;

import pl.tscript3r.mpp.domain.Pudo;
import pl.tscript3r.mpp.utils.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PudoMatcherServiceImpl implements PudoMatcherService {

    private final List<Pudo> pudos = new ArrayList<>();

    public void addPudo(Pudo pudo) {
        pudos.add(pudo);
    }

    public Optional<Pudo> getPudoByZipCode(Integer zipCode) {
        Logger.print("Searching for: ", zipCode.toString(), " [ZIP]");
        return pudos.stream()
                .filter(pudo -> pudo.match(zipCode))
                .min(Comparator.comparingInt(Pudo::totalRange));
    }

}
