package userStory.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import userStory.environment.Action;
import userStory.environment.EnvObject;
import userStory.environment.Environment;
import userStory.persona.storyCharacter;
import java.awt.FlowLayout;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import org.w3c.dom.ls.LSInput;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class CharToEnvDetailsForm extends JFrame {

	private storyCharacter m_char;
	private Environment m_env;
	private JTextField textFieldCurrentEnv;
	private JTextField textFieldEnvName;
	private JTextField textFieldCharName;
	
	private JButton btnAddAction;
	
	private JList listCharacterActions,listEnvironmentObjects,listEnvironemntActions;
	private String[] EnvObjectsList,CharActionList,EnvActionList;
	/**
	 * Create the frame.
	 */
	public CharToEnvDetailsForm(storyCharacter character, Environment env) {
		setTitle("connect charachter and environment");
		setResizable(false);
		m_char = character;
		m_env = env;
		setBounds(100, 100, 508, 300);
		getContentPane().setLayout(null);
		
		JLabel lblCharacter = new JLabel("Character");
		lblCharacter.setBounds(10, 11, 94, 14);
		getContentPane().add(lblCharacter);
		
		JLabel lblEnvironment = new JLabel("Environment");
		lblEnvironment.setBounds(190, 11, 114, 14);
		getContentPane().add(lblEnvironment);
		
		JLabel lblCurruntEnv = new JLabel("Currunt Environment");
		lblCurruntEnv.setBounds(10, 36, 132, 14);
		getContentPane().add(lblCurruntEnv);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(190, 36, 46, 14);
		getContentPane().add(lblName);
		
		textFieldCurrentEnv = new JTextField();
		textFieldCurrentEnv.setEditable(false);
		textFieldCurrentEnv.setBounds(10, 59, 150, 20);
		getContentPane().add(textFieldCurrentEnv);
		textFieldCurrentEnv.setColumns(10);
		
		textFieldEnvName = new JTextField();
		textFieldEnvName.setEditable(false);
		textFieldEnvName.setBounds(190, 59, 157, 20);
		getContentPane().add(textFieldEnvName);
		textFieldEnvName.setColumns(10);
		
		JButton btnSetCurrentEnvironment = new JButton("Set current environment");
		btnSetCurrentEnvironment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_char.setCurrentEnvironment(m_env);
				MsinGUI.hibernateObj.updateCharacter(m_char);
				fillItems();
			}
		});
		btnSetCurrentEnvironment.setBounds(78, 90, 174, 23);
		getContentPane().add(btnSetCurrentEnvironment);
		
		listCharacterActions = new JList();
		listCharacterActions.setEnabled(false);
		listCharacterActions.setBounds(10, 138, 151, 96);
		getContentPane().add(listCharacterActions);
		
		listEnvironemntActions = new JList();
		listEnvironemntActions.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(listEnvironemntActions.getSelectedIndex()>-1)
					btnAddAction.setEnabled(true);
			}
		});
		listEnvironemntActions.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(listEnvironemntActions.getSelectedIndex()>-1)
					btnAddAction.setEnabled(true);
			}
		});
		listEnvironemntActions.setBounds(351, 138, 151, 96);
		getContentPane().add(listEnvironemntActions);
		
		btnAddAction = new JButton("Add action");
		btnAddAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_char.addAction(m_env.getObjectsArray().get(listEnvironmentObjects.getSelectedIndex()).getActionsArray().get(listEnvironemntActions.getSelectedIndex()));
				fillItems();
			}
		});
		btnAddAction.setEnabled(false);
		btnAddAction.setBounds(110, 239, 114, 23);
		getContentPane().add(btnAddAction);
		
		JLabel lblActions = new JLabel("Actions");
		lblActions.setBounds(10, 115, 46, 14);
		getContentPane().add(lblActions);
		
		JLabel lblActions_1 = new JLabel("Actions");
		lblActions_1.setBounds(372, 115, 46, 14);
		getContentPane().add(lblActions_1);
		
		textFieldCharName = new JTextField();
		textFieldCharName.setEditable(false);
		textFieldCharName.setBounds(78, 8, 86, 20);
		getContentPane().add(textFieldCharName);
		textFieldCharName.setColumns(10);
		
		JLabel lblObjects = new JLabel("Objects");
		lblObjects.setBounds(190, 115, 46, 14);
		getContentPane().add(lblObjects);
		
		listEnvironmentObjects = new JList();
		listEnvironmentObjects.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				fillEnvironmentActionList();
			}
		});
		listEnvironmentObjects.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				fillEnvironmentActionList();
			}

		});
		listEnvironmentObjects.setBounds(190, 138, 151, 96);
		getContentPane().add(listEnvironmentObjects);
		
		fillItems();

	}
	private void fillItems()
	{
		textFieldCharName.setText(m_char.getName());
		if(m_char.getCurrentEnvironment()!=null)
		{
			textFieldCurrentEnv.setText(MsinGUI.hibernateObj.getCharacterCurrentEnvironmentName(m_char));
		}
		textFieldEnvName.setText(m_env.getName());

		//Lists
		ArrayList<EnvObject> objects = MsinGUI.hibernateObj.getEnvironmentObjects(m_env);
		EnvObjectsList = new String[objects.size()];
		for(int i =0; i< objects.size();i++)
		{
			EnvObjectsList[i] =objects.get(i).getName();//+"";
		}
			
		listEnvironmentObjects.setModel(new AbstractListModel() {
			public int getSize() {
				return EnvObjectsList.length;
			}
			public Object getElementAt(int index) {
				return EnvObjectsList[index];
			}
		});
		
		ArrayList<Action> actions = MsinGUI.hibernateObj.getCharacterActions(m_char);
		CharActionList = new String[actions.size()];
		for(int i =0; i< actions.size();i++)
		{
			CharActionList[i] = actions.get(i).getName();//+"";
		}
			
		listCharacterActions.setModel(new AbstractListModel() {
			public int getSize() {
				return CharActionList.length;
			}
			public Object getElementAt(int index) {
				return CharActionList[index];
			}
		});
		
	}
	
	private void fillEnvironmentActionList() {
		int selectedObject = listEnvironmentObjects.getSelectedIndex();
		if(selectedObject>-1)
		{
			EnvObject object = MsinGUI.hibernateObj.getEnvironmentObjects(m_env).get(selectedObject);
			ArrayList<Action> actions = MsinGUI.hibernateObj.getObjectActions(object);
			EnvActionList = new String[actions.size()];
			for(int i =0; i< actions.size();i++)
			{
				EnvActionList[i] = actions.get(i).getName();//+"";
			}
				
			listEnvironemntActions.setModel(new AbstractListModel() {
				public int getSize() {
					return EnvActionList.length;
				}
				public Object getElementAt(int index) {
					return EnvActionList[index];
				}
			});
		}
	}
}
