import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
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
	private JLabel ml1, ml2, ml3, ml4, ml5, ml6, ml7, sr, b_no;
	private JButton b3, b4, b5, b10;
	private JTextField t1, t3, t14, read1, jt2;
	private JPanel panel_1;
	private JPanel panel_2, panel;
	FileWriter wr1;
	String stra1 = "", name11 = "", publication11 = "", author11 = "",
			name22 = "";
	int find, bf = 0, bs = 0, bk = 0;
	JProgressBar progress1;
	FileReader rd1,rd2;
	JFrame frame, jf55;
	private String 	loggedInCust;
	private Connection con;
	private PreparedStatement statement, removeStatement;
	private ResultSet result;
	private ArrayList bookNames = new ArrayList();
	private ArrayList authors = new ArrayList();
	private ArrayList publications = new ArrayList();
	private ArrayList issueDates = new ArrayList();
	private ArrayList returnDates = new ArrayList();
	private ArrayList custIDs = new ArrayList();
	private ArrayList userIDs = new ArrayList();
	private ArrayList userFirstNames = new ArrayList();
	private ArrayList userLastNames = new ArrayList();
	private ArrayList regDates = new ArrayList();
	private static final int LEFT_SIDE = 524;
	private int bookCount;
	
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
		panel.setBackground(SystemColor.info);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBounds(9,10,675,515);
		
		Color re = new Color(122, 145, 201);
		Color m = new Color(139, 0, 0);
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		
		mo1 = new DefaultListModel();
		list1 = new JList(mo1);
		ml1 = new JLabel("Book's Name");
		ml1.setForeground(m);
		ml1.setBounds(10, 11, 65, 14);
		list1.setBounds(10, 25, 99, 414);
		list1.setToolTipText("Name of Book's Present in Database");
		panel.add(list1);
		panel.add(ml1);
		
		mo2 = new DefaultListModel();
		list2 = new JList(mo2);
		ml2 = new JLabel("Author");
		ml2.setForeground(m);
		ml2.setBounds(119, 8, 99, 20);
		list2.setToolTipText("Name of Book Author's Present in Database");
		list2.setBounds(109, 25, 99, 414);
		panel.add(list2);
		panel.add(ml2);
		
		mo3 = new DefaultListModel();
		list3 = new JList(mo3);
		ml3 = new JLabel("Publication");
		ml3.setForeground(m);
		ml3.setBounds(218, 8, 99, 20);
		list3.setToolTipText("Name of Book's Publication Present in Database");
		list3.setBounds(208, 25, 99, 414);
		panel.add(ml3);
		panel.add(list3);

		mo4 = new DefaultListModel();
		list4 = new JList(mo4);
		ml4 = new JLabel("  Issue Date");
		ml4.setForeground(m);
		ml4.setBounds(307, 8, 70, 20);
		list4.setToolTipText("Date of Issue Present in Database");
		list4.setBounds(307, 25, 70, 414);
		panel.add(ml4);
		panel.add(list4);

		mo5 = new DefaultListModel();
		list5 = new JList(mo5);
		ml5 = new JLabel("   Return Date");
		ml5.setForeground(m);
		ml5.setBounds(377, 8, 70, 20);
		list5.setToolTipText("Date of Return Present in Database");
		list5.setBounds(377, 25, 70, 414);
		panel.add(ml5);
		panel.add(list5);

		mo6 = new DefaultListModel();
		list6 = new JList(mo6);
		ml6 = new JLabel("   Cust. ID");
		ml6.setForeground(m);
		ml6.setBounds(447, 8, 60, 20);
		list6.setToolTipText("ID of customer that purchase the book last time ");
		list6.setBounds(447, 25, 60, 414);
		panel.add(ml6);
		panel.add(list6);
		
		JPanel jp2 = new JPanel();
		jp2.setBounds(LEFT_SIDE, 20, 160, 75);
		panel.add(jp2);

		b3 = new JButton("View Book Details");
		b3.setBounds(10, 80, 120, 25);
		jp2.add(b3);

		b4 = new JButton("View All Book's");
		b4.setBounds(10, 115, 120, 25);
		jp2.add(b4);

		Color r = new Color(122, 145, 201);
		jp2.setBackground(m);
		
		JPanel jp3 = new JPanel();
		jp3.setBackground(m);
		jp3.setBounds(LEFT_SIDE, 105, 160, 75);
		panel.add(jp3);
		
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
		btnLogout.setBounds(LEFT_SIDE + 29, 190, 89, 25);
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
				bookCount++;
			}
			
			b_no.setText("Total Books = " + bookCount + " (Book's)");
			
			
		} catch (Exception der) {
			b_no.setText("Error Occurs: \n" + der);
		}
		
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean bookFound;
				
				bookFound = false;				
				bookCount = 0;
				
				try {
					int bs1 = 0;
					
					ml1.setText("Book Name");
					ml2.setText("Author");
					ml3.setText("Publication");
					ml4.setText("Issue Date");
					ml5.setText("Return Date");
					ml6.setText("Cust. ID.");
					
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
							b_no.setText("Total Books = " + bookCount + " (Book's)");
						else
							b_no.setText("Book not found.");
						
						
					} 
					else {
						progress1.setValue(0);
						b_no.setText("Please Enter the Book Name or Publcation");
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
					b_no.setText("Total Books = " + bookCount + " (Book's)");
					
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
					jf55.setVisible(true);
					jf55.getContentPane().setLayout(null);
					jf55.setLocation(160, 180);

					JTextArea ak47 = new JTextArea();
					ak47.setEditable(false);
					ak47.setText("Book Details"
							+ "\n"
							+ "**************************************************");
					ak47.setBounds(10, 10, 250, 250);
					jf55.getContentPane().add(ak47);

					Button b1 = new Button("Ok");
					b1.setBounds(80, 270, 100, 25);
					jf55.getContentPane().add(b1);
					b1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							jf55.setVisible(false);
						}
					});

					jf55.setSize(285, 335);

					rd2 = new FileReader("Database/pointer.mmm");
					jt2 = new JTextField();
					jt2.read(rd2, null);
					rd2.close();
					int nov = Integer.parseInt(jt2.getText());

					for (int i = 1; i < nov; i++) {
						rd1 = new FileReader("Database/" + i + ".name");
						read1 = new JTextField();
						read1.read(rd1, null);
						String hj = read1.getText();
						rd1.close();

						if (hj.equals(str34)) {
							rd1 = new FileReader("Database/" + i + ".name");
							read1 = new JTextField();
							read1.read(rd1, null);
							if (!read1.getText().equals("")) {
								ak47.setText(ak47.getText() + "\n"
										+ "Book Name :" + read1.getText());
								rd1.close();

								rd1 = new FileReader("Database/" + i
										+ ".author");
								read1 = new JTextField();
								read1.read(rd1, null);
								ak47.setText(ak47.getText() + "\n"
										+ "Book Author :" + read1.getText());
								rd1.close();

								rd1 = new FileReader("Database/" + i
										+ ".publication");
								read1 = new JTextField();
								read1.read(rd1, null);
								ak47.setText(ak47.getText() + "\n"
										+ "Book Publication :"
										+ read1.getText());
								rd1.close();

								rd1 = new FileReader("Database/" + i + ".issue");
								read1 = new JTextField();
								read1.read(rd1, null);
								if (!read1.getText().equals("")) {
									ak47.setText(ak47.getText() + "\n"
											+ "Issue Date :" + read1.getText());
								} else {
									ak47.setText(ak47.getText() + "\n"
											+ "Issue Date : None");
								}
								rd1.close();

								rd1 = new FileReader("Database/" + i
										+ ".return");
								read1 = new JTextField();
								read1.read(rd1, null);
								ak47.setText(ak47.getText() + "\n"
										+ "Return Date :" + read1.getText());
								rd1.close();

								rd1 = new FileReader("Database/" + i + ".id");
								read1 = new JTextField();
								read1.read(rd1, null);
								ak47.setText(ak47.getText() + "\n"
										+ "Cust. Id :" + read1.getText());
								rd1.close();

								rd1 = new FileReader("Database/" + i
										+ ".detail");
								read1 = new JTextField();
								read1.read(rd1, null);
								if (!read1.getText().equals("")) {
									ak47.setText(ak47.getText() + "\n"
											+ "Other Details : \n"
											+ read1.getText());
								} else {
									ak47.setText(ak47.getText() + "\n"
											+ "Other Details : Not Found");
								}
								rd1.close();

							}
						}
					}

				} catch (Exception de) {
					JOptionPane.showMessageDialog((Component) null,
							"Please Select Book Name from List",
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
	}
	public void SetLoginCustomer(String s)
	{
		loggedInCust = s;
	}
}
