package userStory.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import userStory.environment.EnvEvent;
import userStory.environment.EnvObject;
import userStory.environment.Environment;
import userStory.persona.Emotion;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class EnvironmentForm extends JFrame {

	private JPanel contentPane;
	
	private ArrayList<Environment> m_listOfEnvironments;

	private ArrayList<EnvObject> m_listOfObjects;
	private ArrayList<EnvEvent> m_listOfEvents;
	
	private String[] environmentsList;
	private String[] ObjectsList;
	private String[] actionsList;
	
	JList listEnviornments;
	private JTextField textName;

	private JButton btnCancel;

	private JButton btnAccept;

	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JList listObjects;
	private JTree treeEvents;
	private JButton btnAddObject;
	private JButton btnRemoveObject;
	private JButton btnEditObject;
	private JButton btnAddEvent;
	private JButton btnRemoveEvent;
	private JButton btnEditAction;
	
	private enum MODE {NEW,MODIFY,NONE};
	private MODE mode;
	private MODE objectsMode;
	private MODE eventsMode;


	/**
	 * Create the frame.
	 */
	public EnvironmentForm(ArrayList<Environment> listOfEnvironments) {
		m_listOfEnvironments = listOfEnvironments;
		
		mode = MODE.NONE;
		objectsMode=MODE.NONE;
		eventsMode=MODE.NONE;
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Environment");

		
		listEnviornments = new JList();
		listEnviornments.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {

				if(listEnviornments.getSelectedIndex()>-1)
				{
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
					btnAddEvent.setEnabled(true);
					btnAddObject.setEnabled(true);
					textName.setText(m_listOfEnvironments.get(listEnviornments.getSelectedIndex()).getName());
					//m_listOfObjects=m_listOfEnvironments.get(listEnviornments.getSelectedIndex()).getObjectsArray();
					//m_listOfEvents=m_listOfEnvironments.get(listEnviornments.getSelectedIndex()).getEventsArray();
					
					m_listOfObjects= MsinGUI.hibernateObj.getEnvironmentObjects(m_listOfEnvironments.get(listEnviornments.getSelectedIndex()));
					m_listOfEvents=MsinGUI.hibernateObj.getEnvironmentEvents(m_listOfEnvironments.get(listEnviornments.getSelectedIndex()));
					refreshEventList();
					refreshObjectList();
				}
				else
				{
					btnEdit.setEnabled(false);
					btnDelete.setEnabled(false);
				}
			}
		});
		listEnviornments.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(listEnviornments.getSelectedIndex()>-1)
				{
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
					textName.setText(m_listOfEnvironments.get(listEnviornments.getSelectedIndex()).getName());
					m_listOfObjects=m_listOfEnvironments.get(listEnviornments.getSelectedIndex()).getObjectsArray();
					m_listOfEvents=m_listOfEnvironments.get(listEnviornments.getSelectedIndex()).getEventsArray();
				}
			}
		});
		listEnviornments.setBounds(303, 32, 131, 184);
		contentPane.add(listEnviornments);
		
		JLabel lblAvailableEnvironments = new JLabel("Available Environments");
		lblAvailableEnvironments.setBounds(303, 7, 141, 14);
		contentPane.add(lblAvailableEnvironments);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode=MODE.NEW;
				clearScreen();
				goToEditMode();
			}
		});
		btnAdd.setBounds(303, 225, 131, 23);
		contentPane.add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode=MODE.MODIFY;
			//	textName.setText(m_listOfEnvironments.get(listEnviornments.getSelectedIndex()).getName());
			//	listObjects.add(m_listOfEnvironments.get(listEnviornments.getSelectedIndex()).g, listObjects)
				goToEditMode();
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(303, 248, 64, 23);
		contentPane.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	m_listOfEnvironments.remove(listEnviornments.getSelectedIndex());
				MsinGUI.hibernateObj.deleteEnvironment(m_listOfEnvironments.get(listEnviornments.getSelectedIndex()));
				refeshEnviornmentList();
				btnEdit.setEnabled(false);
				btnDelete.setEnabled(false);
				
				backToNormalMode();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(370, 248, 64, 23);
		contentPane.add(btnDelete);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 33, 46, 14);
		contentPane.add(lblName);
		
		textName = new JTextField();
		textName.setEnabled(false);
		textName.setBounds(53, 30, 204, 23);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblObjects = new JLabel("Objects");
		lblObjects.setBounds(10, 79, 46, 14);
		contentPane.add(lblObjects);
		
		JLabel lblEvents = new JLabel("Events");
		lblEvents.setBounds(156, 79, 46, 14);
		contentPane.add(lblEvents);
		
		listObjects = new JList();
		listObjects.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(listObjects.getSelectedIndex()>-1)
				{
					btnEditObject.setEnabled(true);
					btnRemoveObject.setEnabled(true);
				}
				else
				{
					btnEditObject.setEnabled(false);
					btnRemoveObject.setEnabled(false);
				}
				
			}
		});
		listObjects.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(listObjects.getSelectedIndex()>-1)
				{
					btnEditObject.setEnabled(true);
					btnRemoveObject.setEnabled(true);
				}
				else
				{
					btnEditObject.setEnabled(false);
					btnRemoveObject.setEnabled(false);
				}
			}
		});
		listObjects.setEnabled(false);
		listObjects.setBounds(10, 104, 122, 133);
		contentPane.add(listObjects);
		
		treeEvents = new JTree();
		treeEvents.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Events") {
				{
				}
			}
		));
		treeEvents.setEnabled(false);
		treeEvents.setBounds(150, 104, 139, 133);
		contentPane.add(treeEvents);
		
		btnAddObject = new JButton("+");
		btnAddObject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objectsMode=MODE.NEW;
				EnvObject obj = new EnvObject("");
				ObjectForm form = new ObjectForm(obj,EnvironmentForm.this);
				form.setVisible(true);
			}
		});
		btnAddObject.setEnabled(false);
		btnAddObject.setToolTipText("Add object");
		btnAddObject.setBounds(10, 248, 43, 23);
		contentPane.add(btnAddObject);
		
		btnRemoveObject = new JButton("-");
		btnRemoveObject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_listOfObjects.remove(listObjects.getSelectedIndex());
			/*	if(mode==MODE.MODIFY)
				{
					m_listOfEnvironments.get(listEnviornments.getSelectedIndex()).removeObject(listObjects.getSelectedIndex());
				}*/
				refreshObjectList();
			}
		});
		btnRemoveObject.setEnabled(false);
		btnRemoveObject.setToolTipText("Remove Object");
		btnRemoveObject.setBounds(52, 248, 43, 23);
		contentPane.add(btnRemoveObject);
		
		btnEditObject = new JButton("E");
		btnEditObject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objectsMode=MODE.MODIFY;
			//	EnvObject obj = new EnvObject(listObjects.getSelectedValue().toString());
				EnvObject obj = m_listOfObjects.get(listObjects.getSelectedIndex());
				ObjectForm form = new ObjectForm(obj,EnvironmentForm.this);
				form.setVisible(true);
			}
		});
		btnEditObject.setEnabled(false);
		btnEditObject.setToolTipText("Edit");
		btnEditObject.setBounds(94, 248, 43, 23);
		contentPane.add(btnEditObject);
		
		btnAddEvent = new JButton("+");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsMode=MODE.NEW;
				EnvEvent event = new EnvEvent("",new Emotion(""),0);
				EventForm form = new EventForm(event,EnvironmentForm.this);
				form.setVisible(true);
			}
		});
		btnAddEvent.setEnabled(false);
		btnAddEvent.setToolTipText("Add an event");
		btnAddEvent.setBounds(150, 248, 43, 23);
		contentPane.add(btnAddEvent);
		
		btnRemoveEvent = new JButton("-");
		btnRemoveEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//m_listOfEvents.remove(treeActions.getSelectedIndex());
			}
		});
		btnRemoveEvent.setEnabled(false);
		btnRemoveEvent.setToolTipText("Remove event");
		btnRemoveEvent.setBounds(199, 248, 43, 23);
		contentPane.add(btnRemoveEvent);
		
		btnEditAction = new JButton("E");
		btnEditAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventsMode=MODE.MODIFY;
			}
		});
		btnEditAction.setEnabled(false);
		btnEditAction.setBounds(248, 248, 43, 23);
		contentPane.add(btnEditAction);
		
		btnAccept = new JButton("");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mode == MODE.NEW)
				{
					//Add new Environment
					Environment env = new Environment(textName.getText());
					int numberOfObjects = m_listOfObjects.size();
		
					for(int i=0;i<numberOfObjects;i++)
					{
						env.addObject(m_listOfObjects.get(i));
					}
					
					int NumberOfEvents = treeEvents.getModel().getChildCount(treeEvents.getModel().getRoot());
					for(int j =0;j<NumberOfEvents;j++)
					{
						Object currentNode = treeEvents.getModel().getChild(treeEvents.getModel().getRoot(), j);
						EnvEvent event = new EnvEvent(currentNode.toString(),new Emotion(treeEvents.getModel().getChild(currentNode, 0).toString()),new Integer(treeEvents.getModel().getChild(currentNode, 1).toString()));
						env.addEvent(event);
					}
					m_listOfEnvironments.add(env);
					MsinGUI.hibernateObj.addEnvironment(env);
					String message ="New Environment added";
					JOptionPane.showMessageDialog(EnvironmentForm.this, message);
				}
				else if (mode == MODE.MODIFY)
				{
					m_listOfEnvironments.get(listEnviornments.getSelectedIndex()).setName(textName.getText());
			//		String message ="Environment name modified";
			//		JOptionPane.showMessageDialog(NeedsForm.this, message);
					MsinGUI.hibernateObj.updateEnvironment(m_listOfEnvironments.get(listEnviornments.getSelectedIndex()));
				}

				refeshEnviornmentList();
				backToNormalMode();	
			}
		});
		btnAccept.setToolTipText("accept");
		btnAccept.setIcon(new ImageIcon(EnvironmentForm.class.getResource("/icons/accept.png")));
		btnAccept.setBounds(258, 30, 22, 23);
		btnAccept.setVisible(false);
		contentPane.add(btnAccept);
		
		btnCancel = new JButton("");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	textFieldNeedName.setText("");
				textFieldNeedName.setEditable(false);
				textAreaFulfillers.setText("");
				textAreaFulfillers.setEditable(false);
				
				
				mode = MODE.NONE;
*/
				backToNormalMode();
			}
		});
		btnCancel.setIcon(new ImageIcon(EnvironmentForm.class.getResource("/icons/reject.png")));
		btnCancel.setToolTipText("cancel");
		btnCancel.setBounds(280, 30, 22, 23);
		btnCancel.setVisible(false);
		contentPane.add(btnCancel);

		
		refeshEnviornmentList();
	}
	
	private void  refeshEnviornmentList()
	{
		m_listOfEnvironments = MsinGUI.hibernateObj.getEnvironments();
		environmentsList=new String[m_listOfEnvironments.size()];
		for(int i =0; i< m_listOfEnvironments.size();i++)
		{
			environmentsList[i] = m_listOfEnvironments.get(i).getName();//+"";
		}
			
		listEnviornments.setModel(new AbstractListModel() {
			public int getSize() {
				return environmentsList.length;
			}
			public Object getElementAt(int index) {
				return environmentsList[index];
			}
		});
	}
	
	private void  refreshObjectList()
	{
		ObjectsList=new String[m_listOfObjects.size()];
		for(int i =0; i< m_listOfObjects.size();i++)
		{
			ObjectsList[i] = m_listOfObjects.get(i).getName();//+"";
		}
			
		listObjects.setModel(new AbstractListModel() {
			public int getSize() {
				return ObjectsList.length;
			}
			public Object getElementAt(int index) {
				return ObjectsList[index];
			}
		});
	}
	
	private void  refreshEventList()
	{

		actionsList=new String[m_listOfEvents.size()];
		for(int i =0; i< m_listOfEvents.size();i++)
		{
			actionsList[i] = m_listOfEvents.get(i).getName();//+"";
		}
			
		treeEvents.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Events") {
					{
						for(int i =0;i<m_listOfEvents.size();i++){
							DefaultMutableTreeNode node_1;
							node_1 = new DefaultMutableTreeNode(m_listOfEvents.get(i).getName());
							//node_1.add(new DefaultMutableTreeNode(m_listOfEvents.get(i).getAffectedEmotion().getName()));
							node_1.add(new DefaultMutableTreeNode(MsinGUI.hibernateObj.getAffectedEmotionName(m_listOfEvents.get(i))));
							node_1.add(new DefaultMutableTreeNode(m_listOfEvents.get(i).getEmotionEffect()));
							//node_1.add(new DefaultMutableTreeNode(m_listOfEvents.get(i).getEmotionEffect()));
							this.add(node_1);
						}
					}
				}
			));
	}
	
	private void goToEditMode()
	{
		btnAdd.setEnabled(false);
		btnEdit.setEnabled(false);
		btnAccept.setVisible(true);
		btnCancel.setVisible(true);
		
		textName.setEnabled(true);
		btnAddObject.setEnabled(true);
		btnAddEvent.setEnabled(true);
		
	//	if (mode == MODE.MODIFY)
		{
			listObjects.setEnabled(true);
			treeEvents.setEnabled(true);
		}
		
	}
	
	public void addObject(EnvObject obj)
	{
		if (m_listOfObjects ==null)
			m_listOfObjects = new ArrayList<EnvObject>();
		//remove old object - in case of editing old object
		if(objectsMode == MODE.MODIFY&&listObjects.getSelectedIndex()>-1)
			m_listOfObjects.remove(listObjects.getSelectedIndex());
		m_listOfObjects.add(obj);
		objectsMode = MODE.NONE;
		refreshObjectList();
		
	}
	private void backToNormalMode()
	{
		textName.setEnabled(false);
		btnAdd.setEnabled(true);
		clearScreen();
		mode = MODE.NONE;

		btnAddEvent.setEnabled(false);
		btnAddObject.setEnabled(false);
		btnEditAction.setEnabled(false);
		btnEditObject.setEnabled(false);
		btnRemoveEvent.setEnabled(false);
		btnRemoveObject.setEnabled(false);
		
		btnAccept.setVisible(false);
		btnCancel.setVisible(false);
		m_listOfEvents=null;
		m_listOfObjects=null;

	}

	public void addEvent(EnvEvent m_event) {
		if (m_listOfEvents ==null)
			m_listOfEvents = new ArrayList<EnvEvent>();
		//if(eventsMode == MODE.MODIFY&&treeActions.getSelectedIndex()>-1) m_listOfEvents.remove(selected)
			
		m_listOfEvents.add(m_event);
		eventsMode=MODE.NONE;
		refreshEventList();		
	}

	public void cancelUpdate() {
		objectsMode = MODE.NONE;
		eventsMode = MODE.NONE;
		
	}
	
	private void clearScreen()
	{
		textName.setText("");
		listObjects.setModel(new AbstractListModel() {
			public int getSize() {
				return 0;
			}

			@Override
			public Object getElementAt(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		treeEvents.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Events") {
					{
					}
				}
			));

	}
}
