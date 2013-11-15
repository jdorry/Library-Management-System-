import javax.swing.JApplet;
import javax.swing.JInternalFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class LibraryApplet extends JApplet {

	private JList list1, list2, list3, list4, list5, list6;
	private DefaultListModel mo1, mo2, mo3, mo4, mo5, mo6;
	private JLabel ml1, ml2, ml3, ml4, ml5, ml6, ml7;
	/**
	 * Create the applet.
	 */
	public LibraryApplet() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBounds(getBounds());
		
		Color re = new Color(122, 145, 201);
		
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
		
		panel.setVisible(true);
	}
}
