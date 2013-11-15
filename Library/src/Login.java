import javax.swing.JApplet;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Login extends JApplet {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the applet.
	 */
	public Login() {
		
		JInternalFrame internalFrame = new JInternalFrame("Login");
		internalFrame.getContentPane().setBackground(Color.GRAY);
		getContentPane().add(internalFrame, BorderLayout.CENTER);
		internalFrame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(97, 62, 80, 14);
		internalFrame.getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(175, 59, 86, 20);
		internalFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(97, 113, 80, 14);
		internalFrame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 110, 86, 20);
		internalFrame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(97, 165, 89, 23);
		internalFrame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(196, 165, 89, 23);
		internalFrame.getContentPane().add(btnCancel);
		
		final JLabel lblPleaseCheckUsername = new JLabel("Please Check Username and Password");
		lblPleaseCheckUsername.setBounds(97, 210, 276, 14);
		lblPleaseCheckUsername.setVisible(false);
		lblPleaseCheckUsername.setForeground(getBackground());
		internalFrame.getContentPane().add(lblPleaseCheckUsername);
		internalFrame.setVisible(true);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("admin")
						&& passwordField.getText().equals("admin")) {
					

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
