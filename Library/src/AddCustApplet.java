import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddCustApplet extends JApplet {

	JTextField t1, t2, t3, t4, t5, t6;
	JTextArea textArea;
	JButton registerButton, resetButton, cancelButton;
	JLabel l1, l2, l3, l4, l5, l6, l7;
	JPanel panel;
	FileReader rd1;
	JTextField read1;
	FileWriter wr1;
	String year = "20";
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public AddCustApplet() {
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionBorder);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception de) {
		}
		
		l1 = new JLabel("Book Name");
		l1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l1.setBounds(10, 23, 72, 14);
		panel.add(l1);
		
		t1 = new JTextField();
		t1.setBounds(119, 21, 133, 20);
		panel.add(t1);
		t1.setColumns(10);
		
		l2 = new JLabel("Book Author");
		l2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l2.setBounds(10, 64, 72, 14);
		panel.add(l2);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(119, 62, 133, 20);
		panel.add(t2);
		
		l3 = new JLabel("Book Publication");
		l3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l3.setBounds(10, 102, 99, 14);
		panel.add(l3);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(119, 100, 133, 20);
		panel.add(t3);
		
		l4 = new JLabel("Book Issue Date");
		l4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l4.setBounds(10, 144, 99, 14);
		panel.add(l4);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(119, 142, 133, 20);
		panel.add(t4);
		
		l5 = new JLabel("Book Details");
		l5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l5.setBounds(10, 188, 72, 14);
		panel.add(l5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(119, 186, 133, 56);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(10, 275, 90, 25);
		panel.add(registerButton);

		resetButton = new JButton("Reset");
		resetButton.setBounds(110, 275, 90, 25);
		panel.add(resetButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(210, 275, 90, 25);
		panel.add(cancelButton);
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					rd1 = new FileReader("Database/pointer.mmm");
					read1 = new JTextField();
					read1.read(rd1, null);
					int count2 = Integer.parseInt(read1.getText());
					rd1.close();

					wr1 = new FileWriter("Database/" + count2 + ".name");
					wr1.write(t1.getText() + "");
					wr1.close();

					wr1 = new FileWriter("Database/" + count2 + ".author");
					wr1.write(t2.getText() + "");
					wr1.close();

					wr1 = new FileWriter("Database/" + count2 + ".publication");
					wr1.write(t3.getText() + "");
					wr1.close();

					wr1 = new FileWriter("Database/" + count2 + ".issue");
					wr1.write(t4.getText() + "");
					wr1.close();

					wr1 = new FileWriter("Database/" + count2 + ".return");
					wr1.write("00/00/00");
					wr1.close();

					wr1 = new FileWriter("Database/" + count2 + ".detail");
					wr1.write(textField_4.getText() + "");
					wr1.close();

					wr1 = new FileWriter("Database/" + count2 + ".id");
					wr1.write("0000");
					wr1.close();

					count2 = count2 + 1;

					wr1 = new FileWriter("Database/pointer.mmm");
					wr1.write(count2 + "");
					wr1.close();

					setVisible(false);
				} catch (Exception gr) {
				}
			}
		});

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t6.setText("");
				t4.setText("");
				t2.setText("");
				textArea.setText("");
				t5.setText("");
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibraryApplet library = new LibraryApplet();
				library.init();
				library.start();
				panel.setVisible(false);
				setLayout(new BorderLayout(800, 600));
				add("Center", library);
			}
		});

	}

}
