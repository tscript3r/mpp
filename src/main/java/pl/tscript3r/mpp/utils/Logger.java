package pl.tscript3r.mpp.utils;

import javax.swing.*;

public class Logger {

    private static JTextArea taOutput;
    private static final String PREFIX = "> ";

    public static void setTextArea(JTextArea jTextArea) {
        taOutput = jTextArea;
    }

    public static void print(String... message) {
        StringBuilder output = new StringBuilder(PREFIX);
        for (String msg : message)
            output.append(msg);
        if (taOutput != null) {
            if (taOutput.getText().length() > 1)
                taOutput.append("\r\n");
            taOutput.append(output.toString());
        } else
            System.out.println(output.toString());
    }
}