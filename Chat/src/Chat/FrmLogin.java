package Chat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;

public class FrmLogin {

	private JFrame frmLogin;
	private JTextField txtIP;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin window = new FrmLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblWelcome = new JLabel("Welcome to Chat!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 30));
		frmLogin.getContentPane().add(lblWelcome, BorderLayout.NORTH);
		
		JPanel pnlUserInfo = new JPanel();
		frmLogin.getContentPane().add(pnlUserInfo, BorderLayout.CENTER);
		pnlUserInfo.setLayout(new BoxLayout(pnlUserInfo, BoxLayout.Y_AXIS));
		
		Component verticalGlue = Box.createVerticalGlue();
		pnlUserInfo.add(verticalGlue);
		
		JPanel pnlName = new JPanel();
		pnlUserInfo.add(pnlName);
		
		JLabel lblName = new JLabel("Your name");
		pnlName.add(lblName);
		
		txtName = new JTextField();
		pnlName.add(txtName);
		txtName.setColumns(10);
		
		JPanel pnlIP = new JPanel();
		pnlUserInfo.add(pnlIP);
		
		JLabel lblIP = new JLabel("Your IP");
		pnlIP.add(lblIP);
		
		txtIP = new JTextField();
		txtIP.setForeground(Color.RED);
		pnlIP.add(txtIP);
		txtIP.setColumns(10);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		pnlUserInfo.add(verticalGlue_1);
		
		JPanel pnlButtons = new JPanel();
		frmLogin.getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("Login");
		pnlButtons.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		pnlButtons.add(btnExit);
	}

}
