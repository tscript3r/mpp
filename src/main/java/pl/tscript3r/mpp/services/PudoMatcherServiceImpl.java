package pl.tscript3r.mpp.services;

import pl.tscript3r.mpp.domain.Pudo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PudoMatcherServiceImpl implements PudoMatcherService {

    private List<Pudo> pudos = new ArrayList<>();

    public void addPudo(Pudo pudo) {
        System.out.println("Added PUDO " + pudo);
        pudos.add(pudo);
    }

    public Optional<Pudo> getPudoByPlz(Integer plz) {
        System.out.println("Searching for: " + plz);
        return pudos.stream()
                .filter(pudo -> pudo.match(plz))
                .sorted(Comparator.comparingInt(Pudo::totalRange))
                .findFirst();
    }

}
