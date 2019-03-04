package pl.tscript3r.mpp;

import pl.tscript3r.mpp.forms.MainForm;
import pl.tscript3r.mpp.services.PudoLoaderService;
import pl.tscript3r.mpp.services.PudoLoaderServiceImpl;
import pl.tscript3r.mpp.services.PudoMatcherService;
import pl.tscript3r.mpp.services.PudoMatcherServiceImpl;

import java.awt.*;
import java.io.IOException;

public class Main 
{
    public static void main(String[] args) throws IOException {

        PudoLoaderService pudoLoaderService = new PudoLoaderServiceImpl();
        PudoMatcherService pudoMatcherService = new PudoMatcherServiceImpl();

        pudoLoaderService.loadPudos(pudoMatcherService);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainForm window = new MainForm(pudoMatcherService);
                    window.getMainJform().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
