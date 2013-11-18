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

import javax.swing.JButton;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddBookApplet extends JApplet {
	
	JTextField t1, t2, t3, t4, t5, t6;
	JTextArea a1, a2, a3, a5, a4, a6, a7;
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14;
	JPanel jp1, jp2, jp3, jp4, jp5, jp6, panel;
	//FileReader rd1;
	JTextField read1;
	//FileWriter wr1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	String bookname, author, publication, issDate, retDate, custid;
	//Statement statement = null;
	//private ResultSet result;
	/**
	 * Create the applet.
	 */
	public AddBookApplet() {
		
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
		t4.setText("-");
		
		l5 = new JLabel("Book Details");
		l5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l5.setBounds(10, 188, 72, 14);
		panel.add(l5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(119, 186, 133, 56);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		b1 = new JButton("Save");
		b1.setBounds(10, 266, 89, 23);
		panel.add(b1);
		
		b2 = new JButton("Reset");
		b2.setBounds(121, 266, 89, 23);
		panel.add(b2);
		
		b3 = new JButton("Cancel");
		b3.setBounds(231, 266, 89, 23);
		panel.add(b3);
		
		
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					bookname = t1.getText();
					author = t2.getText();
					publication = t3.getText();
					issDate = t4.getText();
					retDate = "-";
					custid = "-";
					try{
						Class.forName("com.mysql.jdbc.Driver").newInstance();
						
						Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql322429", "sql322429", "xK5*kT6!");
						
						PreparedStatement statement = null; 
								statement = (PreparedStatement) con.prepareStatement("INSERT INTO " +
										"LibraryDB(bookname, author, publication, issuedate, rturndate, custid) " +
										"VALUES(?, ?, ?, ?, ?, ?)");
						statement.setString(1, bookname);
						statement.setString(2, author);
						statement.setString(3, publication);
						statement.setString(4, issDate);
						statement.setString(5, retDate);
						statement.setString(6, custid);
						statement.executeUpdate();
						
					}
					catch (SQLException e1){
						e1.printStackTrace();
					} catch (InstantiationException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					/* rd1 = new FileReader("Database/pointer.mmm");
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
					wr1.close(); */

					setVisible(false);
				} catch (Exception gr) {
				}
			}
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				a1.setText("");
			}
		});

		b3.addActionListener(new ActionListener() {
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
