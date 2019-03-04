package pl.tscript3r.mpp.services;

import java.io.IOException;

public interface PudoLoaderService {
    void loadPudos(PudoMatcherService pudoMatcherService) throws IOException;
}
