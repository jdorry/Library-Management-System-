import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


@SuppressWarnings("serial")
class Addcust extends JFrame {
	JTextField t1, t2, t3, t4, t5, t6;
	JTextArea textArea;
	JButton registerButton, resetButton, cancelButton;
	JLabel l1, l2, l3, l4, l5, l6, l7;
	JPanel panel;
	FileReader rd;
	JTextField read;
	FileWriter wr;
	String year = "20";
	Container cp;

	Addcust() {
		super("Add Customer");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception de) {
		
		}
		
		setSize(330, 350);
		setLocation(130, 150);
		setVisible(true);
		cp = getContentPane();
		cp.setLayout(null);

		panel = new JPanel(null, true);

		// For reference: (x, y, length, height)
		
		l1 = new JLabel("Customer ID");
		l1.setBounds(10, 10, 150, 25);
		panel.add(l1);

		t1 = new JTextField();
		t1.setBounds(130, 10, 160, 25);
		panel.add(t1);

		l2 = new JLabel("Customer Name");
		l2.setBounds(10, 40, 150, 25);
		panel.add(l2);

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

		t2 = new JTextField();
		t2.setBounds(130, 40, 160, 25);
		panel.add(t2);

		l3 = new JLabel("Registration Date");
		l3.setBounds(10, 70, 150, 25);
		panel.add(l3);

		t3 = new JTextField(issue);
		t3.setEditable(false);
		t3.setBounds(130, 70, 160, 25);
		panel.add(t3);

		l4 = new JLabel("Book Name");
		l4.setBounds(10, 100, 150, 25);
		panel.add(l4);

		t4 = new JTextField();
		t4.setEditable(false);
		t4.setBounds(130, 100, 160, 25);
		panel.add(t4);

		l5 = new JLabel("Date Of Purchase");
		l5.setBounds(10, 130, 150, 25);
		panel.add(l5);

		t5 = new JTextField();
		t5.setEditable(false);
		t5.setBounds(130, 130, 160, 25);
		panel.add(t5);

		l6 = new JLabel("Date Of Return");
		l6.setBounds(10, 160, 150, 25);
		panel.add(l6);

		t6 = new JTextField();
		t6.setEditable(false);
		t6.setBounds(130, 160, 160, 25);
		panel.add(t6);

		l7 = new JLabel("Details");
		l7.setBounds(10, 190, 150, 25);
		panel.add(l7);

		textArea = new JTextArea();
		textArea.setBounds(130, 190, 160, 70);
		textArea.setLineWrap(true);
		panel.add(textArea);

		registerButton = new JButton("Register");
		registerButton.setBounds(10, 275, 90, 25);
		panel.add(registerButton);

		resetButton = new JButton("Reset");
		resetButton.setBounds(110, 275, 90, 25);
		panel.add(resetButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(210, 275, 90, 25);
		panel.add(cancelButton);

		Color r3 = new Color(122, 145, 195);
		panel.setBackground(r3);
		panel.setBounds(10, 10, 310, 310);
		cp.add(panel);

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t6.setText("");
				t4.setText("");
				t2.setText("");
				textArea.setText("");
				t5.setText("");
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File file = new File("Cust_Details");
					if (file.isFile() && (!file.exists())) {
						file.mkdir();
					}

					@SuppressWarnings("unused")
					File file2 = new File("Cust_Details/pointer.mmm");
					if (file.isFile() && (!file.exists())) {
						FileWriter w1 = new FileWriter(
								"Cust_Details/pointer.mmm");
						w1.write(1 + "");
						w1.close();
					}

					rd = new FileReader("Cust_Details/pointer.mmm");
					read = new JTextField();
					read.read(rd, null);
					rd.close();

					int z = Integer.parseInt(read.getText());

					wr = new FileWriter("Cust_Details/Cus" + z + ".id");
					wr.write(t1.getText() + "");
					wr.close();

					wr = new FileWriter("Cust_Details/Cus" + z + ".name");
					wr.write(t2.getText() + "");
					wr.close();

					wr = new FileWriter("Cust_Details/Cus" + z + ".date");
					wr.write(t3.getText() + "");
					wr.close();

					wr = new FileWriter("Cust_Details/Cus" + z + ".bname");
					wr.write(t4.getText() + "");
					wr.close();

					wr = new FileWriter("Cust_Details/Cus" + z + ".purchase");
					wr.write(t5.getText() + "");
					wr.close();

					wr = new FileWriter("Cust_Details/Cus" + z + ".return");
					wr.write(t6.getText() + "");
					wr.close();

					wr = new FileWriter("Cust_Details/Cus" + z + ".detail");
					wr.write(textArea.getText() + "");
					wr.close();

					z = z + 1;

					wr = new FileWriter("Cust_Details/pointer.mmm");
					wr.write(z + "");
					wr.close();

					setVisible(false);

				} catch (Exception ge) {
					System.out.println(ge);
				}
			}
		});

	}
}