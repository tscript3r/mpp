package pl.tscript3r.mpp.forms;

import pl.tscript3r.mpp.domain.Pudo;
import pl.tscript3r.mpp.exceptions.PudoNotFoundException;
import pl.tscript3r.mpp.services.PudoMatcherService;
import pl.tscript3r.mpp.utils.Logger;
import pl.tscript3r.mpp.utils.SystemClipboard;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

import static pl.tscript3r.mpp.utils.Logger.setTextArea;

public class MainForm {

    private static final String MEMO_TEMPLATE = "Shipping to PUDO: %s / Tracking: %s  / ETA: %s  EOB";
    private JFrame mainJform;
    private JTextField zipTextField;
    private JTextField pudoTextField;
    private JTextField trackingTextField;
    private JTextField dateTextField;
    private JTextField textField_4;
    private final PudoMatcherService pudoMatcherService;


    /**
     * Create the application.
     */
    public MainForm(PudoMatcherService pudoMatcherService) {
        this.pudoMatcherService = pudoMatcherService;
        initialize();
    }

    public JFrame getMainJform() {
        return mainJform;
    }

    /**
     * Initialise the contents of the frame.
     *
     */
    private void initialize() {
        mainJform = new JFrame();
        mainJform.setTitle("MPP");
        mainJform.setBounds(100, 100, 551, 300);
        mainJform.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJform.getContentPane().setLayout(new BorderLayout(0, 0));
        mainJform.setResizable(false);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - mainJform.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - mainJform.getHeight()) / 2);

        mainJform.setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setBounds(10, 0, 319, 177);
        panel.setBackground(UIManager.getColor("Button.highlight"));
        mainJform.getContentPane().add(panel);
        panel.setLayout(null);

        zipTextField = new JTextField();
        zipTextField.setBounds(10, 44, 256, 20);
        panel.add(zipTextField);
        zipTextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Zip code:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel.setBounds(10, 30, 46, 14);
        panel.add(lblNewLabel);

        JButton btnNewButton = new JButton("Find PUDO");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnNewButton.setBounds(10, 67, 255, 23);
        btnNewButton.addActionListener((actionListener) -> {

            try {
                Pudo foundPudo = pudoMatcherService
                        .getPudoByZipCode(Integer.valueOf(zipTextField.getText()))
                        .orElseThrow(() -> new PudoNotFoundException(Integer.valueOf(zipTextField.getText())));
                Logger.print("Found: ", foundPudo.toString());
                pudoTextField.setText(foundPudo.getCode());
            } catch (PudoNotFoundException e1) {
                Logger.print("Could not find PUDO for PLZ: ", zipTextField.getText());
            } catch (NumberFormatException e1) {
                Logger.print("Wrong zip code value: ", zipTextField.getText());
            }

        });
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("❐");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 7));
        btnNewButton_1.setBounds(240, 115, 26, 19);
        btnNewButton_1.addActionListener((actionListener) -> {

            SystemClipboard.copy(pudoTextField.getText());
            Logger.print("Copied PUDO code");

        });
        panel.add(btnNewButton_1);

        JLabel lblPudo = new JLabel("PUDO:");
        lblPudo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblPudo.setBounds(10, 101, 46, 12);
        panel.add(lblPudo);

        pudoTextField = new JTextField();
        pudoTextField.setColumns(10);
        pudoTextField.setBounds(10, 115, 228, 20);
        panel.add(pudoTextField);

        JLabel lblTrackingNumber = new JLabel("Tracking number:");
        lblTrackingNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblTrackingNumber.setBounds(10, 140, 107, 12);
        panel.add(lblTrackingNumber);

        trackingTextField = new JTextField();
        trackingTextField.setColumns(10);
        trackingTextField.setBounds(10, 153, 256, 20);
        panel.add(trackingTextField);

        JLabel lblExpectedDeliveryDate = new JLabel("ETA:");
        lblExpectedDeliveryDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblExpectedDeliveryDate.setBounds(10, 178, 211, 14);
        panel.add(lblExpectedDeliveryDate);

        dateTextField = new JTextField();
        dateTextField.setColumns(10);
        dateTextField.setBounds(10, 191, 256, 20);
        panel.add(dateTextField);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(10, 241, 228, 20);
        panel.add(textField_4);

        JButton btnGenerateMemo = new JButton("Generate Memo");
        btnGenerateMemo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnGenerateMemo.setBounds(10, 215, 255, 23);
        btnGenerateMemo.addActionListener((actionListener) -> {
            if (pudoTextField.getText().isEmpty())
                Logger.print("Warning: PUDO field empty");

            if (trackingTextField.getText().isEmpty())
                Logger.print("Warning: Tracking field empty");

            if (dateTextField.getText().isEmpty())
                Logger.print("Warning: ETA field empty");

            textField_4.setText(String.format(MEMO_TEMPLATE,
                    pudoTextField.getText(), trackingTextField.getText(), dateTextField.getText()));

            Logger.print("Memo template generated");

        });
        panel.add(btnGenerateMemo);

        JButton button = new JButton("❐");
        button.setFont(new Font("Tahoma", Font.PLAIN, 7));
        button.setBounds(240, 241, 26, 19);
        button.addActionListener((actionListener) -> {

            SystemClipboard.copy(textField_4.getText());
            Logger.print("Copied memo template");

        });
        panel.add(button);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 96, 256, 2);
        panel.add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setOrientation(SwingConstants.VERTICAL);
        separator_1.setBounds(272, 41, 7, 220);
        panel.add(separator_1);

        JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(280, 42, 256, 219);
        panel.add(scroll);

        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        scroll.setViewportView(textArea);
        textArea.setEditable(false);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
        textArea.updateUI();
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        setTextArea(textArea);
        
        JLabel lblOnlyForClient = new JLabel("ONLY FOR CLIENT 771 / 772 / 773 / 775");
        lblOnlyForClient.setForeground(Color.RED);
        lblOnlyForClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblOnlyForClient.setBounds(138, 11, 304, 14);
        panel.add(lblOnlyForClient);
    }
}
