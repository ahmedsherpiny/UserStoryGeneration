package userStory.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import userStory.environment.Action;
import userStory.environment.EnvObject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import javax.swing.JList;

public class ObjectForm extends JFrame {

	private String[] ActionsList;
	private JPanel contentPane;
	private JTextField textFieldName;
	
	private ArrayList<Action> m_listOfActions;

	private EnvObject m_obj;

	JButton btnAddAction;
	JButton btnModifyAction;
	JButton btnDeleteAction;
	
	JList listActions;
	
	private enum MODE {NEW,MODIFY,NONE};
	private MODE actionsMode;
	
	/**
	 * Create the frame.
	 */
	public ObjectForm(EnvObject obj,final EnvironmentForm parent) {
		m_obj = obj;
		actionsMode = MODE.NONE;
		setTitle("Object");
		setResizable(false);
		setBounds(100, 100, 375, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 29, 46, 14);
		contentPane.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(54, 26, 232, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_obj.setName(textFieldName.getText());
				int numberOfActions = listActions.getModel().getSize();
				for(int i=0;i<numberOfActions;i++)
				{
					m_obj.addAction(new Action(listActions.getModel().getElementAt(i).toString()));
				}	
			//	notify();
				parent.addObject(m_obj);
				setVisible(false);
			}
		});
		btnSave.setBounds(92, 136, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldName.setText("");
				//notify();
				parent.cancelUpdate();
				setVisible(false);
			}
		});
		btnCancel.setBounds(213, 136, 89, 23);
		contentPane.add(btnCancel);
		
		listActions = new JList();
		listActions.addListSelectionListener(new ListSelectionListener() {

		public void valueChanged(ListSelectionEvent arg0) {

			if(listActions.getSelectedIndex()>-1)
			{
				btnModifyAction.setEnabled(true);
				btnDeleteAction.setEnabled(true);
			}
			else
			{
				btnModifyAction.setEnabled(false);
				btnDeleteAction.setEnabled(false);
			}
		}
	});
	listActions.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent arg0) {
			if(listActions.getSelectedIndex()>-1)
			{
				btnModifyAction.setEnabled(true);
				btnDeleteAction.setEnabled(true);
			}
		}
	});

		listActions.setBounds(54, 54, 151, 74);
		contentPane.add(listActions);
		
		JLabel lblActions = new JLabel("Actions");
		lblActions.setBounds(10, 54, 46, 14);
		contentPane.add(lblActions);
		
		btnAddAction = new JButton("Add action");
		btnAddAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionsMode=MODE.NEW;
				Action action = new Action("");
				ActionForm form = new ActionForm(action,ObjectForm.this);
				form.setVisible(true);
			}
		});
		btnAddAction.setBounds(213, 51, 120, 23);
		contentPane.add(btnAddAction);
		
		btnModifyAction = new JButton("Modify action");
		btnModifyAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionsMode=MODE.MODIFY;
				Action action = new Action(listActions.getSelectedValue().toString());
				ActionForm form = new ActionForm(action,ObjectForm.this);
				form.setVisible(true);
			}
		});
		btnModifyAction.setEnabled(false);
		btnModifyAction.setBounds(213, 73, 120, 23);
		contentPane.add(btnModifyAction);
		
		btnDeleteAction = new JButton("Delete action");
		btnDeleteAction.setEnabled(false);
		btnDeleteAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				m_listOfActions.remove(listActions.getSelectedIndex());
				refreshActionList();
				btnModifyAction.setEnabled(false);
				btnDeleteAction.setEnabled(false);
			
			}
		});
		btnDeleteAction.setBounds(213, 97, 120, 23);
		contentPane.add(btnDeleteAction);
		
		updateForm();
	}
	
	private void updateForm()
	{
			textFieldName.setText(m_obj.getName());
			m_listOfActions = MsinGUI.hibernateObj.getObjectActions(m_obj);
			refreshActionList();
	}

	public void addAction(Action action) {
		// TODO Auto-generated method stub
		if (m_listOfActions ==null)
			m_listOfActions = new ArrayList<Action>();
		//remove old object - in case of editing old object
		if(actionsMode == MODE.MODIFY&&listActions.getSelectedIndex()>-1)
			m_listOfActions.remove(listActions.getSelectedIndex());
		m_listOfActions.add(action);
		actionsMode = MODE.NONE;
		refreshActionList();

	}
	
	private void  refreshActionList()
	{
		ActionsList = new String[m_listOfActions.size()];
		for(int i =0; i< m_listOfActions.size();i++)
		{
			ActionsList[i] = m_listOfActions.get(i).getName();//+"";
		}
			
		listActions.setModel(new AbstractListModel() {
			public int getSize() {
				return ActionsList.length;
			}
			public Object getElementAt(int index) {
				return ActionsList[index];
			}
		});
	}
	public void cancelUpdate() {
		actionsMode = MODE.NONE;
	}
}
