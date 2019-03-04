package pl.tscript3r.mpp;

import pl.tscript3r.mpp.forms.MainForm;
import pl.tscript3r.mpp.services.PudoLoaderService;
import pl.tscript3r.mpp.services.PudoLoaderServiceImpl;
import pl.tscript3r.mpp.services.PudoMatcherService;
import pl.tscript3r.mpp.services.PudoMatcherServiceImpl;
import pl.tscript3r.mpp.utils.Logger;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        final PudoLoaderService pudoLoaderService = new PudoLoaderServiceImpl();
        final PudoMatcherService pudoMatcherService = new PudoMatcherServiceImpl();
        EventQueue.invokeLater(() -> {

            try {
                MainForm window = new MainForm(pudoMatcherService);
                window.getMainJform().setVisible(true);
                pudoLoaderService.loadPudos(pudoMatcherService);
            } catch (Exception e) {
                Logger.print("Fail: ", e.getMessage());
                e.printStackTrace();
            }

        });
    }
}
