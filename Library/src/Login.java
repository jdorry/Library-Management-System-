import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

@SuppressWarnings("serial")
public class Login extends JApplet {
	private JTextField textField;
	private JPasswordField passwordField;
	public JPanel panel;
	private JLabel lblPleaseCheckUsername;
	
	private ResultSet result;
	ArrayList usernames = new ArrayList();
	ArrayList passwords = new ArrayList();
	Boolean userMatch = false;
	/**
	 * Create the applet.
	 */
	
	public Login() {
		
	try{	
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
		
		lblPleaseCheckUsername = new JLabel("Please Check Username and Password");
		lblPleaseCheckUsername.setBounds(97, 210, 276, 14);
		lblPleaseCheckUsername.setVisible(false);
		lblPleaseCheckUsername.setForeground(getBackground());
		panel.add(lblPleaseCheckUsername);
		panel.setVisible(true);
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql322429", "sql322429", "xK5*kT6!");
			//Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://gorcruxcom.ipagemysql.com/lmsdatabase", "tyler", "Ambition8143");
			
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `users`");
			
			result = statement.executeQuery();
			
			}
		catch (SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		try {
			while (result.next()){
				usernames.add(result.getString(1));
				passwords.add(result.getString(2));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < usernames.size(); i++){
					if(!userMatch)
					{
						if (usernames.get(i).equals(textField.getText()) && passwords.get(i).equals(passwordField.getText())){
							userMatch = true;
							lblPleaseCheckUsername.setText("match");
						}
						else {
							userMatch = false;
						}
					}
				}
				
				
				if (userMatch) {
					
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
					ftr.printStackTrace();
				}
			}
		});
	}
	catch (Exception e){
		e.printStackTrace();
		lblPleaseCheckUsername.setText(e.toString());
	}

	}
	
	
}
