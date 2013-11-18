import javax.swing.JApplet;
import javax.swing.JInternalFrame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;

import javax.swing.Box;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

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
import java.util.Date;


public class LibraryApplet extends JApplet {

	private JList list1, list2, list3, list4, list5, list6;
	private DefaultListModel mo1, mo2, mo3, mo4, mo5, mo6;
	private JLabel ml1, ml2, ml3, ml4, ml5, ml6, ml7, sr, b_no;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b15;
	private JTextField t1, t2, t3, t14, read1, jt2;
	private JPanel panel_1;
	private JPanel panel_2, panel;
	FileWriter wr1;
	String stra1 = "", name11 = "", publication11 = "", author11 = "",
			name22 = "";
	int find, bf = 0, bs = 0, bk = 0;
	JProgressBar progress1;
	FileReader rd1,rd2;
	JFrame frame, jf55;
	private Connection con;
	private PreparedStatement statement, removeStatement;
	private ResultSet result;
	private ArrayList bookNames = new ArrayList();
	private ArrayList authors = new ArrayList();
	private ArrayList publications = new ArrayList();
	private ArrayList issueDates = new ArrayList();
	private ArrayList returnDates = new ArrayList();
	private ArrayList<String> custIDs = new ArrayList<String>();
	/**
	 * Create the applet.
	 */
	public LibraryApplet() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql322429", "sql322429", "xK5*kT6!");
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
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		
		mo1 = new DefaultListModel();
		list1 = new JList(mo1);
		JLabel ml1 = new JLabel("Book's Name");
		ml1.setBounds(10, 11, 65, 14);
		list1.setBounds(10, 25, 99, 414);
		list1.setToolTipText("Name of Book's Present in Database");
		panel.add(list1);
		panel.add(ml1);
		
		mo2 = new DefaultListModel();
		list2 = new JList(mo2);
		ml2 = new JLabel("Author");
		ml2.setForeground(re);
		ml2.setBounds(119, 8, 99, 20);
		list2.setToolTipText("Name of Book Author's Present in Database");
		list2.setBounds(109, 25, 99, 414);
		panel.add(list2);
		panel.add(ml2);
		
		mo3 = new DefaultListModel();
		list3 = new JList(mo3);
		ml3 = new JLabel("Publication");
		ml3.setForeground(re);
		ml3.setBounds(218, 8, 99, 20);
		list3.setToolTipText("Name of Book's Publication Present in Database");
		list3.setBounds(208, 25, 99, 414);
		panel.add(ml3);
		panel.add(list3);

		mo4 = new DefaultListModel();
		list4 = new JList(mo4);
		ml4 = new JLabel("  Issue Date");
		ml4.setForeground(re);
		ml4.setBounds(307, 8, 70, 20);
		list4.setToolTipText("Date of Issue Present in Database");
		list4.setBounds(307, 25, 70, 414);
		panel.add(ml4);
		panel.add(list4);

		mo5 = new DefaultListModel();
		list5 = new JList(mo5);
		ml5 = new JLabel("   Return Date");
		ml5.setForeground(re);
		ml5.setBounds(377, 8, 70, 20);
		list5.setToolTipText("Date of Return Present in Database");
		list5.setBounds(377, 25, 70, 414);
		panel.add(ml5);
		panel.add(list5);

		mo6 = new DefaultListModel();
		list6 = new JList(mo6);
		ml6 = new JLabel("   Cust. ID");
		ml6.setForeground(re);
		ml6.setBounds(447, 8, 60, 20);
		list6.setToolTipText("ID of customer that purchase the book last time ");
		list6.setBounds(447, 25, 60, 414);
		panel.add(ml6);
		panel.add(list6);
		
		JPanel jp2 = new JPanel();
		jp2.setBounds(524, 25, 141, 122);
		panel.add(jp2);
		
		b1 = new JButton("Add Book");
		b1.setBounds(10, 10, 120, 25);
		jp2.add(b1);

		b2 = new JButton("Delete Book");
		b2.setBounds(10, 45, 120, 25);
		jp2.add(b2);

		b3 = new JButton("View Book Details");
		b3.setBounds(10, 80, 120, 25);
		jp2.add(b3);

		b4 = new JButton("View All Book's");
		b4.setBounds(10, 115, 120, 25);
		jp2.add(b4);

		Color r = new Color(122, 145, 201);
		jp2.setBackground(SystemColor.activeCaptionBorder);
		
		JPanel jp3 = new JPanel();
		jp3.setBackground(SystemColor.activeCaptionBorder);
		jp3.setBounds(524, 158, 141, 122);
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
		
		JPanel jp4 = new JPanel();
		jp4.setBackground(SystemColor.activeCaptionBorder);
		jp4.setBounds(524, 291, 141, 184);
		panel.add(jp4);
		
		b15 = new JButton("Add Customer");
		jp4.add(b15);
		
		b6 = new JButton("View All Customers");
		jp4.add(b6);
		
		t2 = new JTextField();
		jp4.add(t2);
		t2.setColumns(10);
		
		b7 = new JButton("Search");
		jp4.add(b7);
		
		b8 = new JButton("Delete Customer");
		jp4.add(b8);
		
		b9 = new JButton("View Cust. Details");
		jp4.add(b9);
		
		b_no = new JLabel();
		b_no.setForeground(Color.red);
		b_no.setBounds(161, 450, 216, 20);
		panel.add(b_no);
		
		progress1 = new JProgressBar();
		progress1.setBackground(Color.GREEN);
		progress1.setBounds(524, 486, 141, 25);
		panel.add(progress1);
		
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
				int bookCount;
				boolean bookFound;
				
				bookFound = false;				
				bookCount = 0;
				
				try {
					int bs1 = 0;
					
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
		/*
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (Exception dert) {
					System.out.println(dert);
				}
			}
		});

		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager
							.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (Exception dert) {
					System.out.println(dert);
				}
			}
		});

		item6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (Exception dert) {
					System.out.println(dert);
				}
			}
		});

		item61.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date d = new Date();
					int my1 = d.getDate();
					int my2 = d.getMonth();

					FileDialog fd = new FileDialog(frame,
							"Save Database Backup File (Library Management )");
					fd.setMode(FileDialog.SAVE);
					fd.setFile("Database" + my1 + "." + my2 + ".rar");
					fd.setVisible(true);
					String dir = fd.getDirectory();
					String path = fd.getFile();

					String command = "jar cvf Database1.rar Database/*.*";
					Runtime r = Runtime.getRuntime();
					r.exec(command);

					String command2 = "jar cvf  Database2.rar Cust_Details/*.*";
					Runtime r2 = Runtime.getRuntime();
					r2.exec(command2);

					try {
						Thread.sleep(1000);
					} catch (Exception fr) {
					}
					String command3 = "jar cvf " + dir + path
							+ " Database1.rar  Database2.rar";
					Runtime r3 = Runtime.getRuntime();
					r3.exec(command3);

					System.out.println("Database Backup Sucessfully!");

				} catch (Exception dert) {
					System.out.println(dert);
				}
			}
		});

		item612.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		*/
		
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// view v=new view(String str,string info,boolean val);

				} catch (Exception der) {
				}
			}
		});

		// End of book details

		b15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Addcust ad = new Addcust();
				//ad.setVisible(true);
				//ad.setSize(380, 400);
				//ad.setLocation(80, 140);
				//LibraryApplet library = new LibraryApplet();
				AddCustApplet aCA = new AddCustApplet();
				aCA.init();
				aCA.start();
				panel.setVisible(false);
				//setLayout(new BorderLayout(800, 600));
				add("Center", aCA);
			}
		});
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//addBook a = new addBook();
				//LibraryApplet library = new LibraryApplet();
				AddBookApplet aBA = new AddBookApplet();
				aBA.init();
				aBA.start();
				panel.setVisible(false);
				setLayout(new BorderLayout(800, 600));
				add("Center", aBA);
			}
		});

		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int bookCount;
				
				bookCount = 0;
				
				try {
					
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

		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					/*
					ml1.setText("Cust. ID");
					ml2.setText("Cust. Name");
					ml3.setText("Registration Date");
					ml4.setText("Book Name");
					ml5.setText("Purchase Date");
					ml6.setText("Return Date");
					*/
					int z12 = 0;

					mo1.removeAllElements();
					mo2.removeAllElements();
					mo3.removeAllElements();
					mo4.removeAllElements();
					mo5.removeAllElements();
					mo6.removeAllElements();

					rd2 = new FileReader("Cust_Details/pointer.mmm");
					jt2 = new JTextField();
					jt2.read(rd2, null);
					rd2.close();

					int no = Integer.parseInt(jt2.getText());
					int tt = no - 1;
					// b_no.setText("Total Customer's :"+tt );

					for (int v = 1; v < no; v++) {

						rd2 = new FileReader("Cust_Details/Cus" + v + ".id");
						jt2 = new JTextField();
						jt2.read(rd2, null);
						if (!jt2.getText().equals("")) {
							z12++;
							b_no.setText("Total Customers = " + z12);
							mo1.addElement(jt2.getText() + "");
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".name");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							mo2.addElement(jt2.getText() + "");
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".date");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							mo3.addElement(jt2.getText() + "");
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".bname");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							if (!jt2.getText().equals("")) {
								mo4.addElement(jt2.getText() + "");
							} else {
								mo4.addElement(jt2.getText() + "   _");
							}
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".purchase");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							if (!jt2.getText().equals("")) {
								mo5.addElement(jt2.getText() + "");
							} else {
								mo5.addElement(jt2.getText() + "   _");
							}
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".return");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							if (!jt2.getText().equals("")) {
								mo6.addElement(jt2.getText() + "");
							} else {
								mo6.addElement(jt2.getText() + "   _");
							}
							rd2.close();
						}
					}

				} catch (Exception ser) {
					System.out.println(ser);
				}
			}
		});
		// End of showing customer's Info.

		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					int ad = list1.getSelectedIndex();
					String str = (String) mo1.getElementAt(ad);

					System.out.println(str);

					cust_detail d = new cust_detail(str);
					d.setVisible(true);
					d.setSize(300, 550);
					d.setLocation(100, 0);
				} catch (Exception fr) {
					JOptionPane
							.showMessageDialog(
									(Component) null,
									"Please Select Customer ID from List of Cust. ID. ",
									"Library Management(Pravin Rane)",
									JOptionPane.OK_OPTION);
					try {
						/*
						ml1.setText("Cust. ID");
						ml2.setText("Cust. Name");
						ml3.setText("Registration Date");
						ml4.setText("Book Name");
						ml5.setText("Purchase Date");
						ml6.setText("Return Date");
						*/
						mo1.removeAllElements();
						mo2.removeAllElements();
						mo3.removeAllElements();
						mo4.removeAllElements();
						mo5.removeAllElements();
						mo6.removeAllElements();

						rd2 = new FileReader("Cust_Details/pointer.mmm");
						jt2 = new JTextField();
						jt2.read(rd2, null);
						rd2.close();
						int no = Integer.parseInt(jt2.getText());

						for (int v = 1; v < no; v++) {
							rd2 = new FileReader("Cust_Details/Cus" + v + ".id");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							mo1.addElement(jt2.getText() + "");
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".name");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							mo2.addElement(jt2.getText() + "");
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".date");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							mo3.addElement(jt2.getText() + "");
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".bname");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							if (!jt2.getText().equals("")) {
								mo4.addElement(jt2.getText() + "");
							} else {
								mo4.addElement(jt2.getText() + "   _");
							}
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".purchase");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							if (!jt2.getText().equals("")) {
								mo5.addElement(jt2.getText() + "");
							} else {
								mo5.addElement(jt2.getText() + "   _");
							}
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".return");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							if (!jt2.getText().equals("")) {
								mo6.addElement(jt2.getText() + "");
							} else {
								mo6.addElement(jt2.getText() + "   _");
							}
							rd2.close();
						}
					} catch (Exception fg) {
					}
				}

			}
		});

		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mysearchid = t2.getText();
				try {
					/*
					ml1.setText("Cust. ID");
					ml2.setText("Cust. Name");
					ml3.setText("Registration Date");
					ml4.setText("Book Name");
					ml5.setText("Purchase Date");
					ml6.setText("Return Date");
					*/
					mo1.removeAllElements();
					mo2.removeAllElements();
					mo3.removeAllElements();
					mo4.removeAllElements();
					mo5.removeAllElements();
					mo6.removeAllElements();

					if (!t2.getText().equals("")) {
						rd2 = new FileReader("Cust_Details/pointer.mmm");
						jt2 = new JTextField();
						jt2.read(rd2, null);
						rd2.close();
						int no = Integer.parseInt(jt2.getText());

						int len3 = t2.getText().length();

						for (int v = 1; v < no; v++) {
							name22 = "";
							int lg = 0;
							rd2 = new FileReader("Cust_Details/Cus" + v + ".id");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							// mo1.addElement(jt2.getText()+"");
							String s1 = jt2.getText();
							rd2.close();

							rd2 = new FileReader("Cust_Details/Cus" + v
									+ ".name");
							jt2 = new JTextField();
							jt2.read(rd2, null);
							// mo2.addElement(jt2.getText()+"");
							String s2 = jt2.getText();
							rd2.close();

							for (int z = 0; z < len3; z++) {
								name22 = name22 + s2.charAt(z);
							}

							if (s1.toLowerCase().equals(mysearchid)
									|| s1.toUpperCase().equals(mysearchid)
									|| s2.toLowerCase().equals(mysearchid)
									|| s2.toUpperCase().equals(mysearchid)
									|| name22.toUpperCase().equals(mysearchid)
									|| name22.toLowerCase().equals(mysearchid)) {
								lg++;
								b_no.setText("Customer Found :" + lg);
								rd2 = new FileReader("Cust_Details/Cus" + v
										+ ".id");
								jt2 = new JTextField();
								jt2.read(rd2, null);
								mo1.addElement(jt2.getText() + "");
								rd2.close();

								rd2 = new FileReader("Cust_Details/Cus" + v
										+ ".name");
								jt2 = new JTextField();
								jt2.read(rd2, null);
								mo2.addElement(jt2.getText() + "");
								rd2.close();

								rd2 = new FileReader("Cust_Details/Cus" + v
										+ ".date");
								jt2 = new JTextField();
								jt2.read(rd2, null);
								mo3.addElement(jt2.getText() + "");
								rd2.close();

								rd2 = new FileReader("Cust_Details/Cus" + v
										+ ".bname");
								jt2 = new JTextField();
								jt2.read(rd2, null);
								if (!jt2.getText().equals("")) {
									mo4.addElement(jt2.getText() + "");
								} else {
									mo4.addElement(jt2.getText() + "   _");
								}
								rd2.close();

								rd2 = new FileReader("Cust_Details/Cus" + v
										+ ".purchase");
								jt2 = new JTextField();
								jt2.read(rd2, null);
								if (!jt2.getText().equals("")) {
									mo5.addElement(jt2.getText() + "");
								} else {
									mo5.addElement(jt2.getText() + "   _");
								}
								rd2.close();

								rd2 = new FileReader("Cust_Details/Cus" + v
										+ ".return");
								jt2 = new JTextField();
								jt2.read(rd2, null);
								if (!jt2.getText().equals("")) {
									mo6.addElement(jt2.getText() + "");
								} else {
									mo6.addElement(jt2.getText() + "   _");
								}
								rd2.close();
							}
						}
					} else {
						progress1.setValue(0);
						b_no.setText("User Input Error!");
						JOptionPane.showMessageDialog((Component) null,
								"Please Enter Customer Id or Name",
								"Library Management(Pravin Rane)",
								JOptionPane.OK_OPTION);
					}

				} catch (Exception de) {
				}
			}
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int listIndex, arrayIndex, i, bookCount;
				String removeBookName;
				
				arrayIndex = 0;
				bookCount = 0;
				
				try {
					
					listIndex = list1.getSelectedIndex();
					removeBookName = (String) mo1.getElementAt(listIndex);
					
					mo1.removeAllElements();
					mo2.removeAllElements();
					mo3.removeAllElements();
					mo4.removeAllElements();
					mo5.removeAllElements();
					mo6.removeAllElements();
					
					for(i = 0; i < bookNames.size(); i++)
					{
						if(bookNames.get(i).equals(removeBookName))
						{
							arrayIndex = i;
						}
					}
					//DELETE FROM Customers
					//WHERE CustomerName='Alfreds Futterkiste' AND ContactName='Maria Anders';
					String theStatement = "DELETE FROM LibraryDB" +
					" WHERE bookname='" + bookNames.get(arrayIndex) + "' AND author='" + authors.get(arrayIndex) + "' AND " +
					"publication='" + publications.get(arrayIndex) + "' AND issuedate='" + issueDates.get(arrayIndex) + "' AND " +
					"returndate='" + returnDates.get(arrayIndex) + "' AND custid='" + custIDs.get(arrayIndex) + "'";
					System.out.println(theStatement);
					removeStatement = (PreparedStatement) con.prepareStatement(theStatement);
					
					
					bookNames.remove(arrayIndex);
					authors.remove(arrayIndex);
					publications.remove(arrayIndex);
					issueDates.remove(arrayIndex);
					returnDates.remove(arrayIndex);
					custIDs.remove(arrayIndex);
					
					removeStatement.executeUpdate();
					
					for(i = 0; i < bookNames.size(); i ++)
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
				} catch (Exception fr) {
				}

			}
		});

		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ad = list1.getSelectedIndex();
					String str34 = (String) mo1.getElementAt(ad);

					jf55 = new JFrame("Book Details");
					jf55.setVisible(true);
					jf55.setLayout(null);
					jf55.setLocation(160, 180);

					JTextArea ak47 = new JTextArea();
					ak47.setEditable(false);
					ak47.setText("Book Details"
							+ "\n"
							+ "**************************************************");
					ak47.setBounds(10, 10, 250, 250);
					jf55.add(ak47);

					Button b1 = new Button("Ok");
					b1.setBounds(80, 270, 100, 25);
					jf55.add(b1);
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
		
	}
}
