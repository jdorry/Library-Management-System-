import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddCustApplet extends JApplet {

	JTextField t1, t2, t3, t4, t5, t6, t21, t51, t52, user, pass;
	JTextArea textArea;
	JButton registerButton, resetButton, cancelButton;
	JLabel l1, l2, l3, l4, l5, l6, l7, l21, l51, l52, userLabel, passLabel;
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
		
		Date d = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int mon = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		mon = mon + 1;
		int y = cal.get(Calendar.YEAR);
		
		String str = y + "";
		int len = str.length();

		for (int i = 2; i < len; i++) {

			if (i > 0) {
				year = year + str.charAt(i);
			}
		}
		String issue = mon + "/" + date + "/" + year;
		
		l1 = new JLabel("Customer ID");
		l1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l1.setBounds(10, 23, 72, 14);
		panel.add(l1);
		
		t1 = new JTextField();
		t1.setBounds(119, 21, 133, 20);
		panel.add(t1);
		t1.setColumns(10);
		
		l2 = new JLabel("First Name");
		l2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l2.setBounds(10, 48, 72, 20);
		panel.add(l2);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(119, 48, 133, 20);
		panel.add(t2);
		
		userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userLabel.setBounds(270, 48, 72, 20);
		panel.add(userLabel);
		
		user = new JTextField();
		user.setColumns(10);
		user.setBounds(350, 48, 133, 20);
		panel.add(user);
		
		passLabel = new JLabel("password");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passLabel.setBounds(270, 75, 72, 20);
		panel.add(passLabel);
		
		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(350, 75, 133, 20);
		panel.add(pass);
		
		l21 = new JLabel("Last Name");
		l21.setFont(new Font("Tahoma", Font.PLAIN,12));
		l21.setBounds(10, 75, 72, 20);
		panel.add(l21);
		
		t21 = new JTextField();
		t21.setColumns(10);
		t21.setBounds(119, 75, 133, 20);
		panel.add(t21);
		
		l3 = new JLabel("Registation Date");
		l3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l3.setBounds(10, 103, 99, 14);
		panel.add(l3);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(119, 103, 133, 20);
		t3.setText(issue);
		panel.add(t3);
		
		l4 = new JLabel("Book Name");
		l4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l4.setBounds(10, 132, 99, 14);
		panel.add(l4);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(119, 132, 133, 20);
		panel.add(t4);
		
		l51 = new JLabel("Date Of Purchase");
		l51.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l51.setBounds(10, 160, 99, 14);
		panel.add(l51);
		
		t51 = new JTextField();
		t51.setColumns(10);
		t51.setBounds(119, 160, 133, 20);
		panel.add(t51);
		
		l52 = new JLabel("Date Of Return");
		l52.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l52.setBounds(10, 190, 99, 14);
		panel.add(l52);
		
		t52 = new JTextField();
		t52.setColumns(10);
		t52.setBounds(119, 190, 133, 20);
		panel.add(t52);
		
		l5 = new JLabel("Details");
		l5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l5.setBounds(10, 223, 72, 14);
		panel.add(l5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(119, 223, 133, 56);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		registerButton = new JButton("Register");
		registerButton.setBounds(10, 300, 90, 25);
		panel.add(registerButton);

		resetButton = new JButton("Reset");
		resetButton.setBounds(110, 300, 90, 25);
		panel.add(resetButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(210, 300, 90, 25);
		panel.add(cancelButton);
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
				} catch (Exception gr) {
				}
			}
		});

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t4.setText("");
				t2.setText("");
				textField_4.setText("");
				user.setText("");
				pass.setText("");
				t21.setText("");
				t51.setText("");
				t52.setText("");
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
