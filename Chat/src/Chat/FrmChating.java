package Chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SpringLayout;

public class FrmChating extends JFrame {

	private JPanel contentPane;
	private JTextField txtFrom;
	private JTextField txtFromIP;
	private JTextField txtTo;
	private JTextField txtToIP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmChating frame = new FrmChating();
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
	public FrmChating() {
		setTitle("Chating");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 60, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 27, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 185, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -29, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane);
		
		JTextArea txtHistory = new JTextArea();
		scrollPane.setViewportView(txtHistory);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane_1, 13, SpringLayout.SOUTH, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane_1, 27, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane_1, -29, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane_1);
		
		JTextArea txtMessage = new JTextArea();
		scrollPane_1.setViewportView(txtMessage);
		
		Box horizontalBox = Box.createHorizontalBox();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane_1, -15, SpringLayout.NORTH, horizontalBox);
		sl_contentPane.putConstraint(SpringLayout.NORTH, horizontalBox, -44, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, horizontalBox, 27, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, horizontalBox, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, horizontalBox, 0, SpringLayout.EAST, scrollPane);
		contentPane.add(horizontalBox);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton btnSend = new JButton("Send");
		horizontalBox.add(btnSend);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea);
		
		JButton btnClose = new JButton("Close");
		horizontalBox.add(btnClose);
		
		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 27, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 53, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, scrollPane);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblFrom = new JLabel("From");
		panel_1.add(lblFrom);
		
		txtFrom = new JTextField();
		panel_1.add(txtFrom);
		txtFrom.setColumns(10);
		
		JLabel lblFromIP = new JLabel("From IP");
		panel_1.add(lblFromIP);
		
		txtFromIP = new JTextField();
		panel_1.add(txtFromIP);
		txtFromIP.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblTo = new JLabel("To");
		panel_2.add(lblTo);
		
		txtTo = new JTextField();
		panel_2.add(txtTo);
		txtTo.setColumns(10);
		
		JLabel lblToIP = new JLabel("To IP");
		panel_2.add(lblToIP);
		
		txtToIP = new JTextField();
		panel_2.add(txtToIP);
		txtToIP.setColumns(10);
	}
}
