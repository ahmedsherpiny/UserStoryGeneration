package userStory.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import userStory.persona.Need;
import userStory.persona.NeedFulfiller;
import userStory.persona.storyCharacter;

public class NeedsForm extends JFrame {

	private JPanel contentPane;
	private ArrayList<Need> m_NeedList;
	private JButton btnModifyNeed;
	private JButton btnDeleteNeed;
	private JTextField textFieldNeedName;
	private JButton btnAccept;
	private JButton btnCancel;
	private JButton btnAddNewNeed;
	private JList listNeeds;
	private String[] needsList ={""};
	private JTextArea textAreaFulfillers;
	
	private enum MODE {NEW,MODIFY,NONE};
	private MODE mode;
	private JButton btnAddNeedTochar;
	private storyCharacter m_persona;
	
	/**
	 * Create the frame.
	 * @param persona 
	 */
	 public NeedsForm(ArrayList<Need> needs, storyCharacter persona) {
		m_NeedList = needs;
		m_persona = persona;
		setTitle("Needs");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		mode=MODE.NONE;
		
	
		btnAddNewNeed = new JButton("New need");
		btnAddNewNeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddNewNeed.setEnabled(false);
				textFieldNeedName.setText("");
				textFieldNeedName.setEditable(true);
				textAreaFulfillers.setText("");
				textAreaFulfillers.setEditable(true);
				btnAccept.setVisible(true);
				btnCancel.setVisible(true);
				mode = MODE.NEW;
			}
		});
		btnAddNewNeed.setBounds(10, 198, 95, 23);
		contentPane.add(btnAddNewNeed);
		
		btnModifyNeed = new JButton("Modify");
		btnModifyNeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnModifyNeed.setEnabled(false);
				
				textFieldNeedName.setEditable(true);
				btnAccept.setVisible(true);
				btnCancel.setVisible(true);
				mode = MODE.MODIFY;
			}
		});
		btnModifyNeed.setEnabled(false);
		btnModifyNeed.setBounds(125, 198, 95, 23);
		contentPane.add(btnModifyNeed);
		
		btnDeleteNeed = new JButton("Delete");
		btnDeleteNeed.setEnabled(false);
		btnDeleteNeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] selected = listNeeds.getSelectedIndices();
				for (int i=0;i<selected.length;i++)
				{
				//	m_NeedList.remove(selected[i]);
					MsinGUI.hibernateObj.deleteNeed(m_NeedList.get(selected[i]));
				}
				refreshNeedsTextList();
			}
		});
		btnDeleteNeed.setBounds(78, 232, 71, 23);
		contentPane.add(btnDeleteNeed);
		
		textFieldNeedName = new JTextField();
		textFieldNeedName.setEditable(false);
		textFieldNeedName.setBounds(10, 32, 155, 20);
		contentPane.add(textFieldNeedName);
		textFieldNeedName.setColumns(10);
		
		btnAccept = new JButton("");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mode == MODE.NEW)
				{
					//Add new need
					String[] fulfillerssStr = textAreaFulfillers.getText().split(",|\n");
					NeedFulfiller[] fulfillers = new NeedFulfiller[fulfillerssStr.length];
					for(int i=0;i<fulfillerssStr.length;i++)
					{
						fulfillers[i] = new NeedFulfiller(fulfillerssStr[i]);
					}
					Need need = new Need(textFieldNeedName.getText(), fulfillers);

					m_NeedList.add(need);
					if(m_persona==null)
						MsinGUI.hibernateObj.addNeed(need);
					else
					{
						MsinGUI.hibernateObj.addNeed(need);
						m_persona.addNeed(need);
					//	MsinGUI.hibernateObj.addCharacter(m_persona);
					}

	//				m_goalList.add(new Goal(textFieldGoalName.getText()));
					String message ="New need added";
					JOptionPane.showMessageDialog(NeedsForm.this, message);				
				}
				else if (mode == MODE.MODIFY)
				{
					m_NeedList.get(listNeeds.getSelectedIndex()).setName(textFieldNeedName.getText());
					MsinGUI.hibernateObj.modifyNeed(m_NeedList.get(listNeeds.getSelectedIndex()));
					String message ="Need name modified";
					JOptionPane.showMessageDialog(NeedsForm.this, message);				
				}
				refreshNeedsTextList();
				textFieldNeedName.setEditable(false);
				textFieldNeedName.setText("");
				textAreaFulfillers.setEditable(false);
				textAreaFulfillers.setText("");
				btnAddNewNeed.setEnabled(true);
				btnAccept.setVisible(false);
				btnCancel.setVisible(false);
				mode = MODE.NONE;
	
			}
		});
		btnAccept.setToolTipText("accept");
		//	btnAccept.setIcon(new ImageIcon(new File(".").getCanonicalPath() + File.separatorChar + "icons" + File.separatorChar + "accept.png"));
			btnAccept.setIcon(new ImageIcon(NeedsForm.class.getResource("/icons/accept.png")));
	
		btnAccept.setBounds(172, 32, 22, 23);
		btnAccept.setVisible(false);
		contentPane.add(btnAccept);
		
		btnCancel = new JButton("");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNeedName.setText("");
				textFieldNeedName.setEditable(false);
				textAreaFulfillers.setText("");
				textAreaFulfillers.setEditable(false);
				btnAddNewNeed.setEnabled(true);
				btnAccept.setVisible(false);
				btnCancel.setVisible(false);
				mode = MODE.NONE;

			}
		});
		btnCancel.setIcon(new ImageIcon(NeedsForm.class.getResource("/icons/reject.png")));
		btnCancel.setToolTipText("cancel");
		btnCancel.setBounds(203, 32, 22, 23);
		btnCancel.setVisible(false);
		contentPane.add(btnCancel);
		
		btnAddNeedTochar = new JButton("add to character");
		btnAddNeedTochar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshNeedsTextList();
				
			}
		});
		btnAddNeedTochar.setToolTipText("add this need to the character");
		//	btnAccept.setIcon(new ImageIcon(new File(".").getCanonicalPath() + File.separatorChar + "icons" + File.separatorChar + "accept.png"));
		btnAddNeedTochar.setIcon(new ImageIcon("icons" + File.separatorChar + "accept.png"));
	
		btnAddNeedTochar.setBounds(88, 7, 139, 23);
		btnAddNeedTochar.setVisible(false);
		contentPane.add(btnAddNeedTochar);

		
		
		listNeeds = new JList();
		listNeeds.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(listNeeds.getSelectedIndex()>-1)
				{
					btnModifyNeed.setEnabled(true);
					btnDeleteNeed.setEnabled(true);
				}
			}
		});
		listNeeds.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(listNeeds.getSelectedIndex()>-1)
				{
					btnModifyNeed.setEnabled(true);
					btnDeleteNeed.setEnabled(true);
					textFieldNeedName.setText(m_NeedList.get(listNeeds.getSelectedIndex()).getName());
					String fulfillers="";
					NeedFulfiller[] fulfillersArray = MsinGUI.hibernateObj.getNeedFulfillers(m_NeedList.get(listNeeds.getSelectedIndex()));					
					
					for(int i=0; i<fulfillersArray.length;i++)
					{
						fulfillers+=((NeedFulfiller)fulfillersArray[i]).getName() +"\n";
					}
					if (fulfillers.length()>2)
						fulfillers=fulfillers.substring(0,fulfillers.length()-1);
					textAreaFulfillers.setText(fulfillers);
				}
				else
				{
					btnModifyNeed.setEnabled(false);
					btnDeleteNeed.setEnabled(false);
				}
			}
		});
		listNeeds.setBounds(253, 10, 181, 251);
		contentPane.add(listNeeds);
		
		textAreaFulfillers = new JTextArea();
		textAreaFulfillers.setEditable(false);
		textAreaFulfillers.setBounds(10, 88, 199, 100);
		contentPane.add(textAreaFulfillers);
		
		JLabel lblNeed = new JLabel("Need");
		lblNeed.setBounds(10, 11, 46, 14);
		contentPane.add(lblNeed);
		
		JLabel lblFulfillers = new JLabel("Can be fulfilled by");
		lblFulfillers.setBounds(10, 63, 103, 14);
		contentPane.add(lblFulfillers);
	
		
		//refresh Needs List
		refreshNeedsTextList();
	}
	private void  refreshNeedsTextList()
	{
	//	m_NeedList = MsinGUI.hibernateObj.getNeeds();
		needsList=new String[m_NeedList.size()];
		for(int i =0; i< m_NeedList.size();i++)
		{
			needsList[i] = m_NeedList.get(i).getName();//+"";
		}
	//	if(hobbyList.length()>=2)	hobbyList=hobbyList.substring(0,hobbyList.length()-1);
			
		listNeeds.setModel(new AbstractListModel() {
			public int getSize() {
				return needsList.length;
			}
			public Object getElementAt(int index) {
				return needsList[index];
			}
		});
	}
}
