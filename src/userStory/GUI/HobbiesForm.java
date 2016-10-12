package userStory.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;
import javax.swing.JTextArea;
import javax.swing.JButton;

import userStory.persona.Hobby;
import userStory.persona.storyCharacter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import java.awt.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class HobbiesForm extends JFrame {

	private JPanel contentPane;
	private ArrayList<Hobby> m_hobbyList;
	private JButton btnModifyHobby;
	private JButton btnDeleteHobby;
	private JTextField textFieldNewHobby;
	private JButton btnAcceptNewHobby;
	private JButton btnCancelNewHobby;
	private JButton btnAddNewHobby;
	private JList listHobbies;
	private String[] hobbyList ={""};
	private JTextField textModyHobby;
	private JButton btnAcceptModifyHobby;
	private JButton btnCancelModifyHobby;
	private storyCharacter m_persona;
	/**
	 * Create the frame.
	 * @param persona 
	 */
	public HobbiesForm(ArrayList<Hobby> hobbies, storyCharacter persona) {
		m_hobbyList = hobbies;
		m_persona = persona;
		setTitle("Hobbies");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAddNewHobby = new JButton("New hobby");
		btnAddNewHobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddNewHobby.setEnabled(false);
				textFieldNewHobby.setVisible(true);
				btnAcceptNewHobby.setVisible(true);
				btnCancelNewHobby.setVisible(true);
			}
		});
		btnAddNewHobby.setBounds(10, 29, 111, 23);
		contentPane.add(btnAddNewHobby);
		
		btnModifyHobby = new JButton("Modify");
		btnModifyHobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnModifyHobby.setEnabled(false);
				textModyHobby.setText((String)listHobbies.getSelectedValue());
				textModyHobby.setVisible(true);
				btnAcceptModifyHobby.setVisible(true);
				btnCancelModifyHobby.setVisible(true);
			}
		});
		btnModifyHobby.setEnabled(false);
		btnModifyHobby.setBounds(10, 85, 111, 23);
		contentPane.add(btnModifyHobby);
		
		btnDeleteHobby = new JButton("Delete");
		btnDeleteHobby.setEnabled(false);
		btnDeleteHobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] selected = listHobbies.getSelectedIndices();
				for (int i=0;i<selected.length;i++)
				{
					MsinGUI.hibernateObj.deleteHobby(m_hobbyList.get(i));
					m_hobbyList.remove(selected[i]);
				}
				refreshHobbiesTextList();
			}
		});
		btnDeleteHobby.setBounds(10, 145, 111, 23);
		contentPane.add(btnDeleteHobby);
		
		textFieldNewHobby = new JTextField();
		textFieldNewHobby.setBounds(10, 60, 155, 20);
		textFieldNewHobby.setVisible(false);
		contentPane.add(textFieldNewHobby);
		textFieldNewHobby.setColumns(10);
		
		btnAcceptNewHobby = new JButton("");
		btnAcceptNewHobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_hobbyList.add(new Hobby(textFieldNewHobby.getText()));
				if(m_persona==null)
					MsinGUI.hibernateObj.addHobby(new Hobby(textFieldNewHobby.getText()));
				else
				{
					Hobby hobby =new Hobby(textFieldNewHobby.getText());
					MsinGUI.hibernateObj.addHobby(hobby);
					m_persona.addHobby(hobby);
				}
				String message ="New hobby added";
				JOptionPane.showMessageDialog(HobbiesForm.this, message);
				refreshHobbiesTextList();
				textFieldNewHobby.setText("");
				btnAddNewHobby.setEnabled(true);
				textFieldNewHobby.setVisible(false);
				btnAcceptNewHobby.setVisible(false);
				btnCancelNewHobby.setVisible(false);
			}
		});
		btnAcceptNewHobby.setToolTipText("accept");
		btnAcceptNewHobby.setIcon(new ImageIcon(HobbiesForm.class.getResource("/icons/accept.png")));
		btnAcceptNewHobby.setBounds(167, 59, 22, 23);
		btnAcceptNewHobby.setVisible(false);
		contentPane.add(btnAcceptNewHobby);
		
		btnCancelNewHobby = new JButton("");
		btnCancelNewHobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNewHobby.setText("");
				btnAddNewHobby.setEnabled(true);
				textFieldNewHobby.setVisible(false);
				btnAcceptNewHobby.setVisible(false);
				btnCancelNewHobby.setVisible(false);
			}
		});
		btnCancelNewHobby.setIcon(new ImageIcon(HobbiesForm.class.getResource("/icons/reject.png")));
		btnCancelNewHobby.setToolTipText("cancel");
		btnCancelNewHobby.setBounds(193, 59, 22, 23);
		btnCancelNewHobby.setVisible(false);
		contentPane.add(btnCancelNewHobby);
		
		listHobbies = new JList();
		listHobbies.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(listHobbies.getSelectedIndex()>-1)
				{
					btnModifyHobby.setEnabled(true);
					btnDeleteHobby.setEnabled(true);
				}
			}
		});
		listHobbies.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(listHobbies.getSelectedIndex()>-1)
				{
					btnModifyHobby.setEnabled(true);
					btnDeleteHobby.setEnabled(true);
				}
				else
				{
					btnModifyHobby.setEnabled(false);
					btnDeleteHobby.setEnabled(false);
				}
			}
		});
		listHobbies.setBounds(225, 10, 209, 251);
		contentPane.add(listHobbies);
		
		textModyHobby = new JTextField();
		textModyHobby.setBounds(10, 114, 151, 20);
		contentPane.add(textModyHobby);
		textModyHobby.setColumns(10);
		textModyHobby.setVisible(false);
		
		btnAcceptModifyHobby = new JButton("");
		btnAcceptModifyHobby.setToolTipText("accept");
		btnAcceptModifyHobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_hobbyList.get(listHobbies.getSelectedIndex()).setName(textModyHobby.getText());
				MsinGUI.hibernateObj.modifyHobby(m_hobbyList.get(listHobbies.getSelectedIndex()));
				String message ="Hobby "+ textModyHobby.getText()+" modified";
				JOptionPane.showMessageDialog(HobbiesForm.this, message);
				refreshHobbiesTextList();	
				
				textModyHobby.setText("");
				textModyHobby.setVisible(false);
				btnAcceptModifyHobby.setVisible(false);
				btnCancelModifyHobby.setVisible(false);
			}
		});
		btnAcceptModifyHobby.setIcon(new ImageIcon(HobbiesForm.class.getResource("/icons/accept.png")));
		btnAcceptModifyHobby.setBounds(167, 113, 22, 23);
		btnAcceptModifyHobby.setVisible(false);
		contentPane.add(btnAcceptModifyHobby);
		
		btnCancelModifyHobby = new JButton("");
		btnCancelModifyHobby.setToolTipText("cancel");
		btnCancelModifyHobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textModyHobby.setText("");
				textModyHobby.setVisible(false);
				btnAcceptModifyHobby.setVisible(false);
				btnCancelModifyHobby.setVisible(false);
			}
		});
		btnCancelModifyHobby.setIcon(new ImageIcon(HobbiesForm.class.getResource("/icons/reject.png")));
		btnCancelModifyHobby.setBounds(193, 113, 22, 23);
		btnCancelModifyHobby.setVisible(false);
		contentPane.add(btnCancelModifyHobby);
	
		
		//refresh hobby List
		refreshHobbiesTextList();
	}
	private void  refreshHobbiesTextList()
	{
	//	m_hobbyList = MsinGUI.hibernateObj.getHobbies();
		hobbyList=new String[m_hobbyList.size()];
		for(int i =0; i< m_hobbyList.size();i++)
		{
			hobbyList[i] = m_hobbyList.get(i).getName();//+"";
		}
	//	if(hobbyList.length()>=2)	hobbyList=hobbyList.substring(0,hobbyList.length()-1);
			
		listHobbies.setModel(new AbstractListModel() {
			public int getSize() {
				return hobbyList.length;
			}
			public Object getElementAt(int index) {
				return hobbyList[index];
			}
		});
	}
}
