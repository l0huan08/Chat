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

import Model.User;
import Model.UserMessager;
import Network.Message;
import Network.MessageType;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

public class FrmChating extends JFrame {

	private JPanel contentPane;
	private JTextField txtFrom;
	private JTextField txtFromIP;
	private JTextField txtTo;
	private JTextField txtToIP;

	private JTextArea txtMessage;
	private JTextArea txtHistory;
	
	private StringBuilder sbHistory; 
	private ArrayList<String> historyMessages;
	
	private User fromUser; // the current user
	private User toUser; // the user to chat with
	private UserMessager fromMessager;
	
	private class Receiver implements Runnable {
		private UserMessager receiverMessager; 
		private User receiveUser;
		
		public Receiver(User receiveUser, UserMessager receiverMessager) {
			this.receiveUser=receiveUser;
			this.receiverMessager = receiverMessager;
		}
		
		@Override
		public void run() {
			//receiverMessager = new UserMessager();
			//receiverMessager.setUser(receiveUser);
			
			while (true) {
				Message msg = receiverMessager.receiveMessage();
				if (msg==null)
					continue;
				
				if (msg.getType()==MessageType.ChatText) {
					String orgMsg = msg.getContent();
					String newMsg = wrapChatMessage(msg.getSrcName(), orgMsg, false, true);
					
					// update message box
					historyMessages.add(newMsg);
					sbHistory.append(newMsg);
					refreshTxtHistory();
				}
			}
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmChating frame = new FrmChating(null,null);
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
	public FrmChating(User fromUser, User toUser) {
		this.fromUser=fromUser;
		this.toUser=toUser;
		if (fromUser!=null) {
			fromMessager=new UserMessager();
			fromMessager.setUser(fromUser);
		}
		
		
		sbHistory = new StringBuilder();
		historyMessages = new ArrayList<String>();
		
		setTitle("Chating");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 456, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 60, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 27, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -29, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane);
		
		txtHistory = new JTextArea();
		scrollPane.setViewportView(txtHistory);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -20, SpringLayout.NORTH, scrollPane_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane_1, -170, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane_1, 27, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane_1, -50, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane_1, -29, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane_1);
		
		txtMessage = new JTextArea();
		scrollPane_1.setViewportView(txtMessage);
		
		Box horizontalBox = Box.createHorizontalBox();
		sl_contentPane.putConstraint(SpringLayout.WEST, horizontalBox, 27, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, horizontalBox, -29, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, horizontalBox, -44, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, horizontalBox, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(horizontalBox);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendCurrentMessage();
			}
		});
		horizontalBox.add(btnSend);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox.add(rigidArea);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		horizontalBox.add(btnClose);
		
		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 5, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 27, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, 53, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, -29, SpringLayout.EAST, contentPane);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JLabel lblFrom = new JLabel("From");
		panel_1.add(lblFrom);
		
		txtFrom = new JTextField();
		txtFrom.setForeground(Color.RED);
		txtFrom.setEditable(false);
		panel_1.add(txtFrom);
		txtFrom.setColumns(10);
		
		JLabel lblFromIP = new JLabel("From IP");
		panel_1.add(lblFromIP);
		
		txtFromIP = new JTextField();
		txtFromIP.setForeground(Color.RED);
		txtFromIP.setEditable(false);
		panel_1.add(txtFromIP);
		txtFromIP.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblTo = new JLabel("To");
		panel_2.add(lblTo);
		
		txtTo = new JTextField();
		txtTo.setForeground(Color.BLUE);
		txtTo.setEditable(false);
		panel_2.add(txtTo);
		txtTo.setColumns(10);
		
		JLabel lblToIP = new JLabel("To IP");
		panel_2.add(lblToIP);
		
		txtToIP = new JTextField();
		txtToIP.setForeground(Color.BLUE);
		txtToIP.setEditable(false);
		panel_2.add(txtToIP);
		txtToIP.setColumns(10);
		
		
		//--------------- init -------------------------
		initHeader();
		

		//------------- begin listen new messages -----
		Receiver receiver = new Receiver(fromUser,fromMessager);
		Thread receiveThread = new Thread(receiver);
	}
	
	private void initHeader() {
		if (fromUser==null || toUser==null)
			return;
		
		this.txtFrom.setText(fromUser.getName());
		this.txtFromIP.setText(fromUser.getIp());
		this.txtTo.setText(toUser.getName());
		this.txtToIP.setText(toUser.getIp());
	}
	
	private void refreshTxtHistory() {
		this.txtHistory.setText(sbHistory.toString());
	}
	
	private void sendCurrentMessage() {
		if (fromMessager==null)
			return;
		
		String orgMsg = txtMessage.getText();
		boolean suc = fromMessager.sendMessage(FrmChating.this.toUser, orgMsg);
		String msg =wrapChatMessage(fromUser.getName(),orgMsg,true,suc);
				
		// update message box
		historyMessages.add(msg);
		sbHistory.append(msg);
		refreshTxtHistory();
		
		if (suc) {
			txtMessage.setText("");
		}
	}
	
	/**
	 * Wrap a message.
	 * For example, if send a message "hello" from user "hl"
	 * the the output should be "[hl:2014.4.5 3:20]hello"
	 * @param sourceName 	The sender name of this message.
	 * @param content 	Message original content.
	 * @param isSend 	Is send or receive this message.
	 * @param success 	Is the operation successful. 
	 * @return
	 */
	private String wrapChatMessage(String sourceName, String content, boolean isSend, boolean success) {
		if (sourceName==null)
			return null;
		
		String sender = sourceName;
		String time =  new Date().toString();
		String msg = "["+sender+":"+time+"]\n"+ txtMessage.getText();
		
		if ( !success ) {
			if (isSend) {
				msg = "[SEND FAILED]" + msg;
			} else {
				msg = "[RECEIVE FAILED]" + msg;
			}
		}
		
		msg = msg + "\n------------\n";
		
		return msg;
	}
}
