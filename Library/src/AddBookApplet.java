import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.io.File;
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
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class AddBookApplet extends JApplet {
	
	JTextField t1, t2, t3, t4, t5, t6;
	JTextArea a1, a2, a3, a5, a4, a6, a7;
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14;
	JPanel jp1, jp2, jp3, jp4, jp5, jp6, panel;
	//FileReader rd1;
	JTextField read1;
	//FileWriter wr1;
	private JTextField textField_4;
	String bookname, author, publication, issDate, retDate, custid;
	File f;
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
		l1.setBounds(10, 24, 100, 14);
		panel.add(l1);
		
		t1 = new JTextField();
		t1.setBounds(120, 22, 133, 20);
		panel.add(t1);
		t1.setColumns(10);
		
		l2 = new JLabel("Book Author");
		l2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l2.setBounds(10, 64, 100, 14);
		panel.add(l2);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(120, 62, 133, 20);
		panel.add(t2);
		
		l3 = new JLabel("Book Publication");
		l3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l3.setBounds(10, 104, 100, 14);
		panel.add(l3);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(120, 102, 133, 20);
		panel.add(t3);
		
		l4 = new JLabel("Book Issue Date");
		l4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l4.setBounds(10, 144, 100, 14);
		panel.add(l4);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(120, 142, 133, 20);
		panel.add(t4);
		t4.setText("");
		
		l5 = new JLabel("Book Details");
		l5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l5.setBounds(10, 184, 100, 14);
		panel.add(l5);
		
		textField_4 = new JTextField();
		textField_4.setBounds(119, 182, 133, 70);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		//added
		l6 = new JLabel("Cover Image");
		l6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l6.setBounds(10, 272, 100, 14);
		panel.add(l6);
				
		t6 = new JTextField();
		t6.setBounds(119, 272, 133, 20);
		panel.add(t6);
				
		// ***l7 => Cover Image****
		l7 = new JLabel();
		l7.setBounds(262, 47, 140, 215);
		panel.add(l7);
				
		b4 = new JButton("Browse");
		b4.setBounds(262, 272, 89, 23);
		panel.add(b4);
				
		l6 = new JLabel("Image Preview:");
		l6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		l6.setBounds(262, 24, 100, 14);
		l6.setVisible(false);
		panel.add(l6);
		//end add
				
		//moved buttons lower
		b1 = new JButton("Save");
		b1.setBounds(10, 312, 89, 23);
		panel.add(b1);
				
		b2 = new JButton("Reset");
		b2.setBounds(121, 312, 89, 23);
		panel.add(b2);
				
		b3 = new JButton("Cancel");
		b3.setBounds(231, 312, 89, 23);
		panel.add(b3);
		//end changes		
		
		
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
						
						//Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql322429", "sql322429", "xK5*kT6!");
						Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://dbinstance.cdet1nwidztk.us-west-2.rds.amazonaws.com:3306/ClassCalc", "john", "R17A2FZa");
						//Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://gorcruxcom.ipagemysql.com:3306/lmsdatabase", "tyler", "Ambition8143");

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
						statement.close();
						con.close();
						
						LibraryApplet library = new LibraryApplet();
						library.init();
						library.start();
						panel.setVisible(false);
						setLayout(new BorderLayout(800, 600));
						add("Center", library);
						
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
		
		//added
		// source: http://stackoverflow.com/questions/14142932/gui-with-java-gui-builder-for-uploading-an-image-and-displaying-to-a-panelinsid
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				t6.setText(filename);
				try {
					ImageIcon ii=new ImageIcon(scaleImage(140, 215, ImageIO.read(new File(f.getAbsolutePath()))));
					l7.setIcon(ii);
					l6.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		//end add

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
