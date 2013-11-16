import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Login extends JApplet {
	private JTextField textField;
	private JPasswordField passwordField;
	private JPanel panel;

	/**
	 * Create the applet.
	 */
	
	public Login() {
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(97, 62, 80, 14);
		panel.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(175, 59, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(97, 113, 80, 14);
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 110, 86, 20);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(97, 165, 89, 23);
		panel.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(196, 165, 89, 23);
		panel.add(btnCancel);
		
		final JLabel lblPleaseCheckUsername = new JLabel("Please Check Username and Password");
		lblPleaseCheckUsername.setBounds(97, 210, 276, 14);
		lblPleaseCheckUsername.setVisible(false);
		lblPleaseCheckUsername.setForeground(getBackground());
		panel.add(lblPleaseCheckUsername);
		panel.setVisible(true);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("admin")
						&& passwordField.getText().equals("admin")) {
					 //Library lb = new Library();
					// splash1 s=new splash1();
					 //setVisible(false);
					 //lb.setVisible(true);
					// lb.setSize(800, 600);
						 
					LibraryApplet library = new LibraryApplet();
					library.init();
					library.start();
					panel.setVisible(false);
					setLayout(new BorderLayout(800, 600));
					add("Center", library);
	 

				} else {
					lblPleaseCheckUsername.setVisible(true);
					lblPleaseCheckUsername.setForeground(Color.red);
				}

			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.exit(0);
				} catch (Exception ftr) {
				}
			}
		});

	}
	
	
}
