package pl.tscript3r.mpp.forms;

import pl.tscript3r.mpp.exceptions.PudoNotFoundException;
import pl.tscript3r.mpp.services.PudoMatcherService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import java.awt.Button;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import java.awt.TextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class MainForm {

	private JFrame mainJform;
	private JTextField plzTextField;
	private JTextField textField_1;
	private JTextField pudoTextField;
	private JTextField trackingTextField;
	private JTextField dateTextField;
	private JTextField textField_4;
	private final PudoMatcherService pudoMatcherService;


	/**
	 * Create the application.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public MainForm(PudoMatcherService pudoMatcherService) throws KeyManagementException, NoSuchAlgorithmException {
		this.pudoMatcherService = pudoMatcherService;
		initialize();
	}

	public static String getStackTrace(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

	public JFrame getMainJform() {
		return mainJform;
	}

	/**
	 * Initialise the contents of the frame.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private void initialize() {
		mainJform = new JFrame();
		mainJform.setTitle("MPP");
		mainJform.setBounds(100, 100, 283, 317);
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

		plzTextField = new JTextField();
		plzTextField.setBounds(10, 25, 256, 20);
		panel.add(plzTextField);
		plzTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("PLZ:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("Find PUDO");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 48, 255, 23);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(pudoMatcherService
							.getPudoByPlz(Integer.valueOf(plzTextField.getText()))
							.orElseThrow(() -> new PudoNotFoundException(Integer.valueOf(plzTextField.getText()))));
				} catch (PudoNotFoundException e1) {
					e1.printStackTrace();
				}
			}

		});
		panel.add(btnNewButton);

		textField_1 = new JTextField();
		textField_1.setBounds(10, 76, 211, 23);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton_1 = new JButton("❐");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnNewButton_1.setBounds(224, 76, 41, 22);
		panel.add(btnNewButton_1);
		
		JLabel lblPudo = new JLabel("PUDO:");
		lblPudo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPudo.setBounds(10, 120, 46, 14);
		panel.add(lblPudo);
		
		pudoTextField = new JTextField();
		pudoTextField.setColumns(10);
		pudoTextField.setBounds(10, 134, 256, 20);
		panel.add(pudoTextField);
		
		JLabel lblTrackingNumber = new JLabel("Tracking number:");
		lblTrackingNumber.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTrackingNumber.setBounds(10, 157, 107, 12);
		panel.add(lblTrackingNumber);
		
		trackingTextField = new JTextField();
		trackingTextField.setColumns(10);
		trackingTextField.setBounds(10, 170, 256, 20);
		panel.add(trackingTextField);
		
		JLabel lblExpectedDeliveryDate = new JLabel("Expected delivery date:");
		lblExpectedDeliveryDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblExpectedDeliveryDate.setBounds(10, 185, 211, 28);
		panel.add(lblExpectedDeliveryDate);
		
		dateTextField = new JTextField();
		dateTextField.setColumns(10);
		dateTextField.setBounds(10, 205, 256, 20);
		panel.add(dateTextField);
		
		JButton btnGenerateMemo = new JButton("Generate Memo");
		btnGenerateMemo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGenerateMemo.setBounds(10, 229, 255, 23);
		panel.add(btnGenerateMemo);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 256, 211, 23);
		panel.add(textField_4);
		
		JButton button = new JButton("❐");
		button.setFont(new Font("Tahoma", Font.PLAIN, 7));
		button.setBounds(224, 256, 41, 22);
		panel.add(button);

	}
}
