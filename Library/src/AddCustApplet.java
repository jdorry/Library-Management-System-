import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;

import com.mysql.jdbc.PreparedStatement;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddCustApplet extends JApplet {

	JTextField t1, t2, t3, t4, t5, t6, t21, t51, t52, user, pass;
	JTextArea textArea;
	JButton registerButton, resetButton, cancelButton;
	JLabel l1, l2, l3, l4, l5, l6, l7, l21, l51, l52, userLabel, passLabel, info;
	JPanel panel;
	FileReader rd1;
	JTextField read1;
	FileWriter wr1;
	String year = "20";
	
	private ResultSet result;
	private PreparedStatement statement;
	private Connection con;
	
	private ArrayList userIDs = new ArrayList();
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public AddCustApplet() {
		
		try {
			//con = (Connection) DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql322429", "sql322429", "xK5*kT6!");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://dbinstance.cdet1nwidztk.us-west-2.rds.amazonaws.com:3306/ClassCalc", "john", "R17A2FZa");
			//con = (Connection) DriverManager.getConnection("jdbc:mysql://gorcruxcom.ipagemysql.com:3306/lmsdatabase", "tyler", "Ambition8143");
			statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `users`");
			result = statement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while (result.next())
			{
				userIDs.add(result.getString(5));
			}
			statement.close();
			con.close();
		} catch (Exception derp) {
			System.out.println("error");
		}
		
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
		String issue = mon + "/" + date + "/" + 13;
		
		l1 = new JLabel("Customer ID");
		l1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l1.setBounds(10, 132, 99, 14);
		panel.add(l1);
		
		t1 = new JTextField();
		t1.setBounds(119, 132, 133, 20);

		String stringID = Integer.toString(randGen());
		
		boolean finished = false;
		// fix
		while(!finished){
			for(int i = 0; i < userIDs.size(); i++){
				if(userIDs.get(i).equals(stringID)){
					stringID = Integer.toString(randGen());
					break;
				}
				if((userIDs.size()-1) == i){
					finished = true;
				}
			}
		}
		
		t1.setText(stringID);
		t1.setEditable(false);
		panel.add(t1);
		t1.setColumns(10);
		
		l2 = new JLabel("First Name");
		l2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l2.setBounds(10, 75, 72, 20);
		panel.add(l2);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(119, 75, 133, 20);
		panel.add(t2);
		
		userLabel = new JLabel("Username");
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userLabel.setBounds(10, 23, 72, 14);
		panel.add(userLabel);
		
		user = new JTextField();
		user.setColumns(10);
		user.setBounds(119, 21, 133, 20);
		panel.add(user);
		
		passLabel = new JLabel("Password");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passLabel.setBounds(10, 48, 72, 20);
		panel.add(passLabel);
		
		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(119, 48, 133, 20);
		panel.add(pass);
		
		l21 = new JLabel("Last Name");
		l21.setFont(new Font("Tahoma", Font.PLAIN,12));
		l21.setBounds(10, 103, 99, 14);
		panel.add(l21);
		
		t21 = new JTextField();
		t21.setColumns(10);
		t21.setBounds(119, 103, 133, 20);
		panel.add(t21);
		
		l3 = new JLabel("Registation Date");
		l3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l3.setBounds(10, 160, 99, 14);
		panel.add(l3);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(119, 160, 133, 20);
		t3.setText(issue);
		t3.setEditable(false);
		panel.add(t3);
		
		
		registerButton = new JButton("Register");
		registerButton.setBounds(10, 200, 90, 25);
		panel.add(registerButton);

		resetButton = new JButton("Reset");
		resetButton.setBounds(110, 200, 90, 25);
		panel.add(resetButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(210, 200, 90, 25);
		panel.add(cancelButton);
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					
					if(!user.getText().equals("") && !pass.getText().equals("") && !t1.getText().equals(""))
					{
						Class.forName("com.mysql.jdbc.Driver");
						
						Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://dbinstance.cdet1nwidztk.us-west-2.rds.amazonaws.com:3306/ClassCalc", "john", "R17A2FZa");
						//Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql322429", "sql322429", "xK5*kT6!");
						//Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://gorcruxcom.ipagemysql.com:3306/lmsdatabase", "tyler", "Ambition8143");

						PreparedStatement statement = null; 
								statement = (PreparedStatement) con.prepareStatement("INSERT INTO " +
										"users(username, password, custfirstname, custlastname, ID, regdate) " +
										"VALUES(?, ?, ?, ?, ?, ?)");
						statement.setString(1, user.getText());
						statement.setString(2, pass.getText());
						statement.setString(3, t2.getText());
						statement.setString(4, t21.getText());
						statement.setString(5, t1.getText());
						statement.setString(6, t3.getText());
						statement.executeUpdate();
						statement.close();
						con.close();		
						
						
						LibraryApplet library = new LibraryApplet();
						library.init();
						library.start();
						panel.setVisible(false);
						setLayout(new BorderLayout(800, 600));
						add("Center", library);
						//LibraryApplet.currentIDPlus();
					}
					else{
						info = new JLabel("Please Enter Username and Password");
						info.setFont(new Font("Tahoma", Font.PLAIN, 12));
						info.setBounds(10, 230, 220, 25);
						info.setForeground(Color.RED);
						panel.add(info);
						repaint();
						
					}
					
				} catch (Exception gr) {
				}
			}
		});

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info.setVisible(false);
				t2.setText("");
				user.setText("");
				pass.setText("");
				t21.setText("");
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
	
	private int randGen() {
	    Random r = new Random();
	    return ((1 + r.nextInt(2)) * 1000 + r.nextInt(1000));
	}

}
