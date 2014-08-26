package Chat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.Box;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Model.User;

public class FrmMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbContact;
	
	private User user; // user self
	
	// ------- for contact table -----------
	private boolean isResponseTbContactSelecetedChanged=true;
	private DefaultTableModel tbContactModel;
	
	private final int N_Table_Contact_Columns = 3;
	private final String[] Table_Contact_ColumnTitle = { "userId", "userName", "userIP", "userObj" };	
	private final int Table_Contact_UserObjColIndex=3; //the column index of user object in the contact table
	// --------------------------------------
	
	
	/**
	 * For tbUser, when select a row, then refresh the corresponding values in
	 *   panelUserInfo
	 * @author hl
	 *
	 */
	private class SelectionListener implements ListSelectionListener {
        //JTable table;

        SelectionListener(JTable table) {
            //this.table = table;
        }
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
        		if (!getResponseTbContactSelectedChanged()) {
        			return;
        		}
   
                //Event handling
//                User user = getSelectedUser();
//                pnlUserInfo.ReadFrom(user);
            }
    }
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frame = new FrmMain(null);
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
	public FrmMain(User user) {
		this.user = user;
		
		setTitle("Chating Home");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlContacts = new JPanel();
		contentPane.add(pnlContacts, BorderLayout.CENTER);
		pnlContacts.setLayout(new BoxLayout(pnlContacts, BoxLayout.Y_AXIS));
		
		JLabel lblContacts = new JLabel("Contacts");
		lblContacts.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblContacts.setVerticalAlignment(SwingConstants.TOP);
		pnlContacts.add(lblContacts);
		
		JScrollPane scrContacts = new JScrollPane();
		pnlContacts.add(scrContacts);
		
		tbContact = new JTable();
		scrContacts.setViewportView(tbContact);
		
		JPanel pnlButtons = new JPanel();
		contentPane.add(pnlButtons, BorderLayout.EAST);
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.Y_AXIS));
		
		JButton btnNewContact = new JButton("Add Contact");
		btnNewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgAddContact dlgAddContact =new DlgAddContact(FrmMain.this.user);
				dlgAddContact.setModal(true);
		    	
		    	// When frmAddUser closed (User clicked "Add" button, then this frame will add the user into Library)
		    	//dlgAddUser.addWindowListener(new FrmAddUserClosedListener(dlgAddUser));
		    	dlgAddContact.setVisible(true);
		    	
				if (dlgAddContact.isActionAdd()) {
		    		User contact = dlgAddContact.getContact();
		    		if (contact != null)
		    		{	
		    			addContact(contact);
		    			refreshTableContactUI();
		    		}
		    	}
			}
		});
		pnlButtons.add(btnNewContact);
		
		JButton btnDelContact = new JButton("Delete Contact");
		btnDelContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelectedContact();
				refreshTableContactUI();
			}
		});
		pnlButtons.add(btnDelContact);
		
		JButton btnChat = new JButton("Chat");
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User fromUser = FrmMain.this.user;
				User toUser = getSelectedContact();
				
				if (fromUser==null) {
					JOptionPane.showMessageDialog(null, "Current user not exist","Failed", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (toUser==null) {
					JOptionPane.showMessageDialog(null, "Please select a contact to chat!","Failed", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				FrmChating frmChating = new FrmChating(fromUser, toUser);
				frmChating.setVisible(true);
			}
		});
		pnlButtons.add(btnChat);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.dispose();
			}
		});
		pnlButtons.add(btnClose);
		
		Component raNorth = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(raNorth, BorderLayout.NORTH);
		
		Component raSouth = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(raSouth, BorderLayout.SOUTH);
		
		Component raWest = Box.createRigidArea(new Dimension(20, 20));
		contentPane.add(raWest, BorderLayout.WEST);
		
		
		// --------------- init table ---------------
		initContactTable();
	}

	
	private void initContactTable() {
		/// TbUser
		Object rowData[][] = {};
		tbContactModel = new DefaultTableModel(rowData,
				Table_Contact_ColumnTitle);
		tbContact= new JTable();
		tbContact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbContact.setModel(tbContactModel);
		SelectionListener listener = new SelectionListener(tbContact);
		tbContact.getSelectionModel().addListSelectionListener(listener);
	}
	
	/**
	 * Get current selected user
	 * @return current selected user in the User Table,if no user is selected, return null
	 */
	private User getSelectedContact() {
		JTable table = this.tbContact;
		int selRow = table.getSelectedRow();
		if (selRow<0) //no selection
			return null;
        User user = (User)table.getValueAt(selRow, Table_Contact_UserObjColIndex);
        return user;
	}
	
	/**
	 * Fill the table with books according to the UI options
	 */
	private void refreshTableContactUI() {
		this.setResponseTbContactSelectedChanged(false);
		refreshTableContact();
		this.setResponseTbContactSelectedChanged(true);
		
	}
	
	private void refreshTableContact() {
		// clear the table
		int n = tbContactModel.getRowCount();
		for (int i=0;i<n;i++)
			tbContactModel.removeRow(0);
		
		Object[][] data = getAllContactTableData();
		
		if (data==null) {
			return;
		}
		
		int nDataRows = data.length;
		for (int i=0;i<nDataRows;i++){
			this.tbContactModel.addRow(data[i]);
		}
	}
	
	private Object[] createTableContactRowData(User user) {
		Object[] row = new Object[N_Table_Contact_Columns];
		row[0]=user.getId();
		row[1]=user.getName();
		row[2]=user.getIp();
		row[3]=user;
		return row;
	}
	
	private Object[][] getAllContactTableData() {
		if (this.user==null)
			return null;
		else {
			Map<Integer,User> contacts =user.getContacts();
			int n = contacts.size();//number of users
			Object[][] data = new Object[n][];
			int i=0;
			Set<Entry<Integer,User>> set = contacts.entrySet();
			for (Entry<Integer, User> contact: set) {
				data[i] = createTableContactRowData(contact.getValue());
				i++;
			}

			return data;
		}
	}
	
	/**
	 * Set whether will the TbBooksSelectedChanged envent handler will be triggered
	 */
	private void setResponseTbContactSelectedChanged(boolean enable) {
		isResponseTbContactSelecetedChanged = enable;
	}
	
	private boolean getResponseTbContactSelectedChanged() {
		return isResponseTbContactSelecetedChanged;
	}
	
	
	private void addContact(User contact) {
		if (contact==null) {
			JOptionPane.showMessageDialog(null, "Cannot add null user!");
			return;
		}
		
		boolean suc = this.user.addContact(contact);
		if (!suc) {
			JOptionPane.showMessageDialog(null, "Add contact failed. Please check the user is valid!", "Failed", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void deleteSelectedContact() {
		User contact =  getSelectedContact();
		if (contact==null) {
			JOptionPane.showMessageDialog(null, "Please select a contact to delete!","Failed", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//need return value
		boolean suc = this.user.removeContact(contact.getId());
		if (!suc) {
			JOptionPane.showMessageDialog(null, "User not exists.","Failed", JOptionPane.ERROR_MESSAGE);
		} else
		{
			JOptionPane.showMessageDialog(null, "user "+contact.getName()+" deleted.");
		}
	}
}
