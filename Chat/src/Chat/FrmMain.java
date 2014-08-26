package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Component;

import javax.swing.Box;

import Model.User;

import java.awt.Dimension;

public class FrmMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbContacts;
	
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frame = new FrmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected FrmMain() {
		this(null);
	}
	
	/**
	 * Create the frame.
	 */
	public FrmMain(User user) {
		this.user = user;
		
		setTitle("Chating Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlContacts = new JPanel();
		contentPane.add(pnlContacts, BorderLayout.CENTER);
		pnlContacts.setLayout(new BoxLayout(pnlContacts, BoxLayout.Y_AXIS));
		
		JLabel lblContacts = new JLabel("Contacts");
		pnlContacts.add(lblContacts);
		
		JScrollPane scrContacts = new JScrollPane();
		pnlContacts.add(scrContacts);
		
		tbContacts = new JTable();
		scrContacts.setViewportView(tbContacts);
		
		JPanel pnlButtons = new JPanel();
		contentPane.add(pnlButtons, BorderLayout.EAST);
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.Y_AXIS));
		
		JButton btnNewContact = new JButton("Add Contact");
		pnlButtons.add(btnNewContact);
		
		JButton btnDelContact = new JButton("Delete Contact");
		pnlButtons.add(btnDelContact);
		
		JButton btnChat = new JButton("Chat");
		pnlButtons.add(btnChat);
		
		JButton btnClose = new JButton("Close");
		pnlButtons.add(btnClose);
		
		Component raNorth = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(raNorth, BorderLayout.NORTH);
		
		Component raSouth = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(raSouth, BorderLayout.SOUTH);
		
		Component raWest = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(raWest, BorderLayout.WEST);
	}

}
