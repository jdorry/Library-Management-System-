import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class CustLibraryView extends JApplet {
	
	private JList list1, list2, list3, list4, list5, list6;
	private DefaultListModel mo1, mo2, mo3, mo4, mo5, mo6;
	private JLabel ml1, ml2, ml3, ml4, ml5, ml6, sr, b_no;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b12, b15, b20;
	private JTextField t1, t2, t14, read1, jt2, t20;
	private JPanel panel;
	private String loggedInCust;
	FileWriter wr1;
	String stra1 = "", name11 = "", publication11 = "", author11 = "",
			name22 = "";
	int find, bf = 0, bs = 0, bk = 0;
	JProgressBar progress1;
	FileReader rd1,rd2;
	JFrame frame, jf55, jf56, jf57;
	private Connection con;
	private PreparedStatement statement, removeStatement;
	private ResultSet result;
	private ArrayList bookNames = new ArrayList();
	private ArrayList authors = new ArrayList();
	private ArrayList publications = new ArrayList();
	private ArrayList issueDates = new ArrayList();
	private ArrayList returnDates = new ArrayList();
	private ArrayList custIDs = new ArrayList();
	private ArrayList images = new ArrayList();
	private ArrayList pdfs = new ArrayList();
	
	private ArrayList usernames = new ArrayList();
	private ArrayList passwords = new ArrayList();
	private ArrayList userFirstNames = new ArrayList();
	private ArrayList userLastNames = new ArrayList();
	private ArrayList userIDs = new ArrayList();
	private ArrayList regDates = new ArrayList();
	private static final int LEFT_SIDE = 590;
	private int bookCount;
	private String pdfDirect;
	private String imageDirect;
	private static int currentID = 0000;
	private String year = "20";
	private String nextMonth, currentDate;
	
	/**
	 * Create the applet.
	 */
	public CustLibraryView() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			//con = (Connection) DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql322429", "sql322429", "xK5*kT6!");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://dbinstance.cdet1nwidztk.us-west-2.rds.amazonaws.com:3306/ClassCalc", "john", "R17A2FZa");
			//con = (Connection) DriverManager.getConnection("jdbc:mysql://gorcruxcom.ipagemysql.com:3306/lmsdatabase", "tyler", "Ambition8143");
			statement = (PreparedStatement) con.prepareStatement("select * from LibraryDB");
			result = statement.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		panel = new JPanel();
		Color gold = new Color(255, 179, 16);
		
		panel.setBackground(gold);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBounds(9,10,675,515);
		
		Color re = new Color(122, 145, 201);
		Color r = new Color(98, 188, 210);
		
		Color m = new Color(153, 0, 51);
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		
		mo1 = new DefaultListModel();
		list1 = new JList(mo1);
		ml1 = new JLabel("Title");
		ml1.setForeground(m);
		ml1.setBounds(15, 0, 154, 20);
		list1.setBounds(15, 20, 154, 500);
		list1.setToolTipText("Name of Books Present in Database");
		panel.add(list1);
		panel.add(ml1);
		
		mo2 = new DefaultListModel();
		list2 = new JList(mo2);
		ml2 = new JLabel("Author");
		ml2.setForeground(m);
		ml2.setBounds(140 + 30, 0, 99, 20);
		list2.setToolTipText("Name of Book Authors Present in Database");
		list2.setBounds(140 + 30, 20, 99, 500);
		panel.add(list2);
		panel.add(ml2);
		
		mo3 = new DefaultListModel();
		list3 = new JList(mo3);
		ml3 = new JLabel("Publication");
		ml3.setForeground(m);
		ml3.setBounds(240 + 30, 0, 99, 20);
		list3.setToolTipText("Name of Books Publication Present in Database");
		list3.setBounds(240 + 30, 20, 99, 500);
		panel.add(ml3);
		panel.add(list3);

		mo4 = new DefaultListModel();
		list4 = new JList(mo4);
		ml4 = new JLabel("Issued");
		ml4.setForeground(m);
		ml4.setBounds(340 + 30, 0, 70, 20);
		list4.setToolTipText("Date of Issue Present in Database");
		list4.setBounds(340 + 30, 20, 70, 500);
		panel.add(ml4);
		panel.add(list4);

		mo5 = new DefaultListModel();
		list5 = new JList(mo5);
		ml5 = new JLabel("Returned");
		ml5.setForeground(m);
		ml5.setBounds(411 + 30, 0, 70, 20);
		list5.setToolTipText("Date of Return Present in Database");
		list5.setBounds(411 + 30, 20, 70, 500);
		panel.add(ml5);
		panel.add(list5);

		mo6 = new DefaultListModel();
		list6 = new JList(mo6);
		ml6 = new JLabel("Cust. ID");
		ml6.setForeground(m);
		ml6.setBounds(482 + 30, 0, 60, 20);
		list6.setToolTipText("ID of customer that borrowed the book last time ");
		list6.setBounds(482 + 30, 20, 60, 500);
		panel.add(ml6);
		panel.add(list6);
		
		JPanel jp2 = new JPanel();
		jp2.setBounds(LEFT_SIDE, 20, 180, 100);
		panel.add(jp2);

		b3 = new JButton("View Book Details");
		b3.setBounds(10, 80, 120, 25);
		jp2.add(b3);
		
		b12 = new JButton("Check Out Book");
		b12.setBounds(10, 115, 120, 25);
		jp2.add(b12);
		
		b20 = new JButton("Return Current Book");
		b20.setBounds(LEFT_SIDE + 5, 205, 120, 25);
		jp2.add(b20);
		
		jp2.setBackground(m);
		
		JPanel jp3 = new JPanel();
		jp3.setBackground(m);
		jp3.setBounds(LEFT_SIDE, 120, 180, 175);
		panel.add(jp3);
		
		b4 = new JButton("View All Books");
		b4.setBounds(10, 150, 120, 25);
		jp3.add(b4);
		
		t1 = new JTextField();
		jp3.add(t1);
		t1.setColumns(10);
		
		b5 = new JButton("Search Book Name");
		jp3.add(b5);
		b5.setMnemonic(KeyEvent.VK_N);
		
		t14 = new JTextField();
		jp3.add(t14);
		t14.setColumns(10);
		
		b10 = new JButton("Search Author");
		jp3.add(b10);
		b10.setMnemonic(KeyEvent.VK_N);
		
		b_no = new JLabel();
		b_no.setForeground(Color.red);
		b_no.setBounds(161, 450, 216, 20);
		panel.add(b_no);
		
		progress1 = new JProgressBar();
		progress1.setBackground(Color.GREEN);
		progress1.setBounds(524, 317, 141, 25);
		//panel.add(progress1);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(LEFT_SIDE + 45, 305, 89, 25);
		panel.add(btnLogout);
		
		for (int x = 1; x < 100; x++) {
			sr = new JLabel(x + "\n");
			sr.setForeground(re);
			sr.setBounds(0, 20, 10, 500);
			panel.add(sr);
		}
		
		//JScrollPane nm = new JScrollPane(panel, v, h);
		//nm.setBounds(10, 20, 600, 500);
		//panel.add(nm);
		
		panel.setVisible(true);
		try {
			int bookCount;
			
			bookCount = 0;
			
			while (result.next())
			{

				mo1.addElement(result.getString(1));
				bookNames.add(result.getString(1));
				mo2.addElement(result.getString(2));
				authors.add(result.getString(2));
				mo3.addElement(result.getString(3));
				publications.add(result.getString(3));
				mo4.addElement(result.getString(4));
				issueDates.add(result.getString(4));
				mo5.addElement(result.getString(5));
				returnDates.add(result.getString(5));
				mo6.addElement(result.getString(6));
				custIDs.add(result.getString(6));
				
				images.add(result.getString(7));
				pdfs.add(result.getString(8));
				bookCount++;
			}
			
			b_no.setText("Total Books = " + bookCount + " (Book's)");
			
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://dbinstance.cdet1nwidztk.us-west-2.rds.amazonaws.com:3306/ClassCalc", "john", "R17A2FZa");
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("SELECT * FROM `users`");
			
			result = statement.executeQuery();
			
			currentID = 0000;
			while (result.next())
			{
				usernames.add(result.getString(1));
				passwords.add(result.getString(2));
				userIDs.add(result.getString(5));
				userFirstNames.add(result.getString(3));
				userLastNames.add(result.getString(4));
				regDates.add(result.getString(6));
				currentID++;
			}
			statement.close();
			con.close();
			
		} catch (Exception der) {
			b_no.setText("Error Occurs: \n" + der);
		}
		
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bookCount;
				boolean bookFound;
				
				bookFound = false;				
				bookCount = 0;
				
				try {
					int bs1 = 0;
					
					ml1.setText("Title");
					ml2.setText("Author");
					ml3.setText("Publication");
					ml4.setText("Issued");
					ml5.setText("Returned");
					ml6.setText("Cust. ID");
					
					progress1.setValue(0);
					mo1.removeAllElements();
					mo2.removeAllElements();
					mo3.removeAllElements();
					mo4.removeAllElements();
					mo5.removeAllElements();
					mo6.removeAllElements();

					if (!t1.getText().equals("")) {
						
						for(int i = 0; i < bookNames.size(); i++)
						{
							if(bookNames.get(i).equals(t1.getText()) || publications.get(i).equals(t1.getText()))
							{
								mo1.addElement(bookNames.get(i));
								mo2.addElement(authors.get(i));
								mo3.addElement(publications.get(i));
								mo4.addElement(issueDates.get(i));
								mo5.addElement(returnDates.get(i));
								mo6.addElement((String)custIDs.get(i));
								bookFound = true;
								bookCount++;
							}
						}
						if(bookFound)
							b_no.setText("Total Books = " + bookCount + " Books");
						else
							b_no.setText("Book not found.");
						
						
					} 
					else {
						progress1.setValue(0);
						b_no.setText("Please Enter the Book Name or Publication");
					}

				} catch (Exception der) {
					System.out.println("Error:" + der);
				}

			}
			
		});
		
		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int bookCount;
				boolean bookFound;
				
				bookCount = 0;
				bookFound = false;
				
				try {
					int bs2 = 0;
					progress1.setValue(0);
					
					ml1.setText("Book Name");
					ml2.setText("Author");
					ml3.setText("Publication");
					ml4.setText("Issue Date");
					ml5.setText("Return Date");
					ml6.setText("Cust. ID.");
					
					mo1.removeAllElements();
					mo2.removeAllElements();
					mo3.removeAllElements();
					mo4.removeAllElements();
					mo5.removeAllElements();
					mo6.removeAllElements();

					if (!t14.getText().equals("")) {
						
						for(int i = 0; i < authors.size(); i++)
						{
							if(authors.get(i).equals(t14.getText()))
							{
								mo1.addElement(bookNames.get(i));
								mo2.addElement(authors.get(i));
								mo3.addElement(publications.get(i));
								mo4.addElement(issueDates.get(i));
								mo5.addElement(returnDates.get(i));
								mo6.addElement(custIDs.get(i));
								bookFound = true;
								bookCount++;
							}
						}
						if(bookFound)
							b_no.setText("Total Books = " + bookCount + " (Book's)");
						else
							b_no.setText("Book not found.");
						
						
					} 
					else 
					{
						progress1.setValue(0);
						b_no.setText("Please Enter the Book Author name.");
					}
				} catch (Exception der) {
					System.out.println("Error:" + der);
				}

			}
		});
	
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// view v=new view(String str,string info,boolean val);

				} catch (Exception der) {
				}
			}
		});

		// End of book details


		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int bookCount;
				
				bookCount = 0;
				
				try {
					
					ml1.setText("Title");
					ml2.setText("Author");
					ml3.setText("Publication");
					ml4.setText("Issued");
					ml5.setText("Returned");
					ml6.setText("Cust. ID");
					
					mo1.removeAllElements();
					mo2.removeAllElements();
					mo3.removeAllElements();
					mo4.removeAllElements();
					mo5.removeAllElements();
					mo6.removeAllElements();
					
					for(int i = 0; i < bookNames.size(); i++)
					{
						mo1.addElement(bookNames.get(i));
						mo2.addElement(authors.get(i));
						mo3.addElement(publications.get(i));
						mo4.addElement(issueDates.get(i));
						mo5.addElement(returnDates.get(i));
						mo6.addElement(custIDs.get(i));
						bookCount++;
					}
					b_no.setText("Total Books = " + bookCount + " Books");
					
				} catch (Exception der) {
					b_no.setText("Error Occurs: \n" + der);
				}

			}
		});
		// End of View all Book's

		// This source code is used to show information of all customers
		// Author :Pravin Rane

		
		// End of showing customer's Info.


		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ad = list1.getSelectedIndex();
					String str34 = (String) mo1.getElementAt(ad);

					jf55 = new JFrame("Book Details");
					jf55.setLayout(null);
					jf55.setLocation(160, 180);

					JTextArea ak47 = new JTextArea();
					ak47.setEditable(false);
					ak47.setText("Book Details"
							+ "\n");
					ak47.setBounds(10, 10, 250, 250);
					jf55.add(ak47);
					
					
					//added
					boolean bookFound = false;

					
					for(int i = 0; i < bookNames.size(); i++) {
						if(bookNames.get(i).equals(str34)) {
							ak47.setText(ak47.getText() + "\n" + " Title: " + bookNames.get(i));
							ak47.setText(ak47.getText() + "\n" + " Author: " + authors.get(i));
							ak47.setText(ak47.getText() + "\n" + " Publication:  " + publications.get(i));
							ak47.setText(ak47.getText() + "\n" + " Issue Date: " + issueDates.get(i));
							ak47.setText(ak47.getText() + "\n" + " Return Date: " + returnDates.get(i));
							ak47.setText(ak47.getText() + "\n" + " Cust. ID: " + custIDs.get(i));
							imageDirect = (String) images.get(i);
							pdfDirect = (String) pdfs.get(i);

							JLabel coverText = new JLabel("Cover: ");
							coverText.setBounds(270, 10, 133, 20);
							jf55.add(coverText);
							
							JLabel l7 = new JLabel();
							l7.setBounds(270, 33, 140, 215);
							jf55.add(l7);
							
							if(imageDirect.equals("")){
								ImageIcon ii=new ImageIcon(scaleImage(140, 215, ImageIO.read(new File("/Users/Jorgensen/Developer/Library Mangement/no-image.jpg"))));
								l7.setIcon(ii);
							}
							else{
								File f = new File(imageDirect);
								try {
									ImageIcon ii=new ImageIcon(scaleImage(140, 215, ImageIO.read(new File(f.getAbsolutePath()))));
									l7.setIcon(ii);
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
							
							
							break;
						}
					}
					

					//Added by Zach.
					JButton pdfButton = new JButton("View PDF");
					pdfButton.setBounds(140, 270, 89, 25);
					pdfButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(pdfDirect != null){
									PDFviewer viewframe = new PDFviewer(pdfDirect);
									viewframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
									viewframe.pack();
									viewframe.setVisible(true);
								}
						}
					});
					if(!pdfDirect.equals("")){
						jf55.add(pdfButton);
					}
					
					//end add 

					Button b1 = new Button("Ok");
					b1.setBounds(30, 270, 100, 25);
					jf55.add(b1);
					b1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							jf55.setVisible(false);
						}
					});
					

					// bounds of book details screen
					jf55.setSize(420, 325);
					
					jf55.setVisible(true);
					
				} catch (Exception de) {
					JOptionPane.showMessageDialog((Component) null,
							"Please Select a Title From the List",
							"Library Management(Pravin Rane)",
							JOptionPane.OK_OPTION);
				}

			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login newLogin = new Login();
				newLogin.init();
				newLogin.start();
				panel.setVisible(false);
				setLayout(new BorderLayout(800, 600));
				add("Center", newLogin);
			}
		});
		
		
		
		b12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ad = list1.getSelectedIndex();
					String str34 = (String) mo1.getElementAt(ad);
					


					jf57 = new JFrame("Book Details");
					jf57.setLayout(null);
					jf57.setLocation(160, 180);

					JTextArea ak47 = new JTextArea();
					ak47.setEditable(false);
					ak47.setText("Book Details"
							+ "\n");
					ak47.setBounds(10, 10, 250, 250);
					jf57.add(ak47);
					
					
					//added
					boolean bookFound = false;
					
					Date todayDate = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(todayDate);
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
					currentDate = mon + "/" + date;
					
					if(mon == 12){
						mon = 1;
					}
					else{
						mon++;
					}
					
					nextMonth = mon + "/" + date;
					
					for(int i = 0; i < bookNames.size(); i++) {
						if(bookNames.get(i).equals(str34)) {
							ak47.setText(ak47.getText() + "\n" + " Title: " + bookNames.get(i));
							ak47.setText(ak47.getText() + "\n" + " Author: " + authors.get(i));
							ak47.setText(ak47.getText() + "\n" + " Publication:  " + publications.get(i));
							ak47.setText(ak47.getText() + "\n" + " Issue Date: " + currentDate);
							ak47.setText(ak47.getText() + "\n" + " Return Date: " + nextMonth);
							if(loggedInCust == null){
								ak47.setText(ak47.getText() + "\n" + " Cust. ID: ");
							}
							else{
								ak47.setText(ak47.getText() + "\n" + " Cust. ID: " + loggedInCust);
							}
							imageDirect = (String) images.get(i);
							pdfDirect = (String) pdfs.get(i);

							JLabel coverText = new JLabel("Cover: ");
							coverText.setBounds(270, 10, 133, 20);
							jf57.add(coverText);
							
							JLabel l7 = new JLabel();
							l7.setBounds(270, 33, 140, 215);
							jf57.add(l7);
							
							if(imageDirect.equals("")){
								ImageIcon ii=new ImageIcon(scaleImage(140, 215, ImageIO.read(new File("/Users/Jorgensen/Developer/Library Mangement/no-image.jpg"))));
								l7.setIcon(ii);
							}
							else{
								File f = new File(imageDirect);
								try {
									ImageIcon ii=new ImageIcon(scaleImage(140, 215, ImageIO.read(new File(f.getAbsolutePath()))));
									l7.setIcon(ii);
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
							break;
							
						}
					}
					
					JButton checkOutButton = new JButton("Check Out");
					checkOutButton.setBounds(30, 270, 100, 25);
					checkOutButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							

							int updateSuccess;
							
							int ad1 = list1.getSelectedIndex();
							String str35 = (String) mo1.getElementAt(ad1);
							int bookIndex = 0;

							for(int i = 0; i < bookNames.size(); i++) {
								if(bookNames.get(i).equals(str35)) {
									bookIndex = i;
								}
							}
							
							
							boolean idMatches = false;
							
							for(int i = 0; i < custIDs.size(); i++) {
								if(custIDs.get(i).equals(loggedInCust) && i != bookIndex) {
									idMatches = true;
								}
							}
							
							if(!idMatches){
							
							try{					
								
								//con = (Connection) DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql322429", "sql322429", "xK5*kT6!");.
								Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://dbinstance.cdet1nwidztk.us-west-2.rds.amazonaws.com:3306/ClassCalc", "john", "R17A2FZa");
								
								String sql = "UPDATE LibraryDB SET issuedate ='" + currentDate +
										"' , rturndate ='"+ nextMonth +
										"' , custid ='"+ loggedInCust +
										"' WHERE bookname ='" + str35 + "'";
								PreparedStatement updateBook = (PreparedStatement) con.prepareStatement(sql);
								updateBook.executeUpdate();
								
								issueDates.set(bookIndex, currentDate);
								returnDates.set(bookIndex, nextMonth);
								custIDs.set(bookIndex, loggedInCust);
								
								mo1.removeAllElements();
								mo2.removeAllElements();
								mo3.removeAllElements();
								mo4.removeAllElements();
								mo5.removeAllElements();
								mo6.removeAllElements();
								
								for(int i = 0; i < bookNames.size(); i++)
								{
									mo1.addElement(bookNames.get(i));
									mo2.addElement(authors.get(i));
									mo3.addElement(publications.get(i));
									mo4.addElement(issueDates.get(i));
									mo5.addElement(returnDates.get(i));
									mo6.addElement(custIDs.get(i));
									bookCount++;
								}
								jf57.setVisible(false);

								
								
							}
							catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								jf57.setVisible(false);
							}
							
							
							}
							else{
								JOptionPane.showMessageDialog((Component) null,
										"Only One Book Can Be Checked Out at Once",
										"Library Management(Pravin Rane)",
										JOptionPane.OK_OPTION);
							}
							
							
							
							
							
						}
					});
					
					jf57.add(checkOutButton);

					Button b1 = new Button("Cancel");
					b1.setBounds(130, 270, 100, 25);
					jf57.add(b1);
					b1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							jf57.setVisible(false);
						}
					});
					
					// bounds of book details screen
					jf57.setSize(420, 325);
					
					jf57.setVisible(true);
					
					}

				 catch (Exception de) {
					JOptionPane.showMessageDialog((Component) null,
							"Please Select a Title From the List",
							"Library Management(Pravin Rane)",
							JOptionPane.OK_OPTION);
				}

			}
		});
		
		b20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		

							boolean idMatches = false;
							int indexMatch = 0;
							
							for(int i = 0; i < custIDs.size(); i++) {
								if(custIDs.get(i).equals(loggedInCust)) {
									idMatches = true;
									indexMatch = i;
								}
							}
							
							if(idMatches){
							
							try{					
								
								//con = (Connection) DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql322429", "sql322429", "xK5*kT6!");.
								Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://dbinstance.cdet1nwidztk.us-west-2.rds.amazonaws.com:3306/ClassCalc", "john", "R17A2FZa");
								String dash = "-";
								String sql = "UPDATE LibraryDB SET issuedate ='" + dash +
										"' , rturndate ='"+ dash +
										"' , custid ='"+ dash +
										"' WHERE bookname ='" + bookNames.get(indexMatch) + "'";
								PreparedStatement updateBook = (PreparedStatement) con.prepareStatement(sql);
								updateBook.executeUpdate();
								

								issueDates.set(indexMatch, dash);
								returnDates.set(indexMatch, dash);
								custIDs.set(indexMatch, dash);
								
								mo1.removeAllElements();
								mo2.removeAllElements();
								mo3.removeAllElements();
								mo4.removeAllElements();
								mo5.removeAllElements();
								mo6.removeAllElements();
								
								for(int i = 0; i < bookNames.size(); i++)
								{
									mo1.addElement(bookNames.get(i));
									mo2.addElement(authors.get(i));
									mo3.addElement(publications.get(i));
									mo4.addElement(issueDates.get(i));
									mo5.addElement(returnDates.get(i));
									mo6.addElement(custIDs.get(i));
									bookCount++;
								}

								JOptionPane.showMessageDialog((Component) null,
										"Book Checked Out Successfully",
										"Library Management(Pravin Rane)",
										JOptionPane.INFORMATION_MESSAGE);
								
							}
							catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();

							}
							
							
							}
							else{
								JOptionPane.showMessageDialog((Component) null,
										"No Book Currently Checked Out",
										"Library Management(Pravin Rane)",
										JOptionPane.OK_OPTION);
							}
							
							
							
							
							
						}
					});


		
		
		
		
	}
	public void SetLoginCustomer(String s)
	{
		loggedInCust = s;
	}
	
	//added
	// source: http://stackoverflow.com/questions/14142932/gui-with-java-gui-builder-for-uploading-an-image-and-displaying-to-a-panelinsid
		public static BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
		    BufferedImage bi;
		    bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		    Graphics2D g2d = (Graphics2D) bi.createGraphics();
		    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
		    g2d.drawImage(img, 0, 0, w, h, null);
		    g2d.dispose();
		    return bi;
		}
		//end add
}
