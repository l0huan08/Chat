package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;

import Model.User;

public class DlgAddContact extends JDialog {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtIP;
	
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgAddContact frame = new DlgAddContact(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DlgAddContact(User user) {
		this.user = user;
		
		setTitle("Add Contact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlInfo = new JPanel();
		contentPane.add(pnlInfo, BorderLayout.CENTER);
		SpringLayout sl_pnlInfo = new SpringLayout();
		pnlInfo.setLayout(sl_pnlInfo);
		
		Box verticalBox = Box.createVerticalBox();
		sl_pnlInfo.putConstraint(SpringLayout.NORTH, verticalBox, 75, SpringLayout.NORTH, pnlInfo);
		sl_pnlInfo.putConstraint(SpringLayout.WEST, verticalBox, 10, SpringLayout.WEST, pnlInfo);
		sl_pnlInfo.putConstraint(SpringLayout.SOUTH, verticalBox, 162, SpringLayout.NORTH, pnlInfo);
		sl_pnlInfo.putConstraint(SpringLayout.EAST, verticalBox, -134, SpringLayout.EAST, pnlInfo);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		JLabel lblName = new JLabel("Name");
		horizontalBox_1.add(lblName);
		
		txtName = new JTextField();
		horizontalBox_1.add(txtName);
		txtName.setColumns(10);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);
		
		JLabel lblIP = new JLabel("IP");
		horizontalBox_2.add(lblIP);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox_2.add(rigidArea);
		
		txtIP = new JTextField();
		horizontalBox_2.add(txtIP);
		txtIP.setColumns(10);
		pnlInfo.add(verticalBox);
		
		Box horizontalBox = Box.createHorizontalBox();
		contentPane.add(horizontalBox, BorderLayout.SOUTH);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton btnAdd = new JButton("Add");
		horizontalBox.add(btnAdd);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea_1);
		
		JButton btnCancel = new JButton("Cancel");
		horizontalBox.add(btnCancel);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue_1);
	}
	
	public boolean isActionAdd() {
		// TODO: need implemented
		return false;
	}
	
	public User getContact() {
		// TODO: need implemented
		return null;
	}
}
