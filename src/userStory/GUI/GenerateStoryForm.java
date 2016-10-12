package userStory.GUI;

import jade.core.AID;
import jade.core.AgentContainer;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;


import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import userStory.jade.AgentFactory;
import userStory.jade.AuthorAgent;
import userStory.jade.CharacterAgent;
import userStory.persona.storyCharacter;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerateStoryForm extends JFrame {

	private AgentFactory factory;
	private JPanel contentPane;
	private ArrayList<storyCharacter> availableCharactersList;
	private String[] availableCharactersArray;
	private ArrayList<storyCharacter> selectedCharactersList;
	private String[] selectedCharactersArray;
	private JList listAvailableCharacters;
	private JList listSelectedCharacters;
	private JButton btn_selectChar;
	private JButton btn_unselectChar;
	private JButton btnGenerateStory;
	/**
	 * Create the application.
	 */
	public GenerateStoryForm() {
		setTitle("Generate story");
		initialize();
		availableCharactersList = MsinGUI.hibernateObj.getCharacters();
		updateAvailableCharactersList();
	}

	private void updateAvailableCharactersList() {
		// TODO Auto-generated method stub
		
	//	if(hobbyList.length()>=2)	hobbyList=hobbyList.substring(0,hobbyList.length()-1);
		availableCharactersArray = new String[availableCharactersList.size()];
		for(int i =0; i< availableCharactersList.size();i++)
		{
			availableCharactersArray[i] = availableCharactersList.get(i).getName();//+"";
		}
		listAvailableCharacters.setModel(new AbstractListModel() {
			public int getSize() {
				return availableCharactersArray.length;
			}
			public Object getElementAt(int index) {
				return availableCharactersArray[index];
			}
		});

	}

	
	private void updateSelectedCharactersList() {
		// TODO Auto-generated method stub
		
	//	if(hobbyList.length()>=2)	hobbyList=hobbyList.substring(0,hobbyList.length()-1);
		selectedCharactersArray = new String[selectedCharactersList.size()];
		for(int i =0; i< selectedCharactersList.size();i++)
		{
			selectedCharactersArray[i] = selectedCharactersList.get(i).getName();//+"";
		}	
		listSelectedCharacters.setModel(new AbstractListModel() {
			public int getSize() {
				return selectedCharactersArray.length;
			}
			public Object getElementAt(int index) {
				return selectedCharactersArray[index];
			}
		});

	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//run JADE
		AgentFactory factory = new AgentFactory();
		factory.run();
		
		setBounds(100, 100, 426, 300);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseChooseCharacters = new JLabel("Please choose character of the story");
		lblPleaseChooseCharacters.setBounds(10, 22, 373, 14);
		contentPane.add(lblPleaseChooseCharacters);
		
		JLabel lblCharacters = new JLabel("Available Characters");
		lblCharacters.setBounds(20, 55, 169, 14);
		contentPane.add(lblCharacters);
		
		JLabel lblSelectedCharacters = new JLabel("Selected Characters");
		lblSelectedCharacters.setBounds(241, 55, 165, 14);
		contentPane.add(lblSelectedCharacters);
		
		listAvailableCharacters = new JList();
		listAvailableCharacters.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(listAvailableCharacters.getSelectedIndex()>-1)
					btn_selectChar.setEnabled(true);
				else 
					btn_selectChar.setEnabled(false);
			}
		});
		listAvailableCharacters.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(listAvailableCharacters.getSelectedIndex()>-1)
					btn_selectChar.setEnabled(true);
				else 
					btn_selectChar.setEnabled(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
			//	btn_selectChar.setEnabled(false);
			}
		});
		listAvailableCharacters.setBounds(17, 80, 145, 137);
		contentPane.add(listAvailableCharacters);
		
		listSelectedCharacters = new JList();
		listSelectedCharacters.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(listSelectedCharacters.getSelectedIndex()>-1)
					btn_unselectChar.setEnabled(true);
				else
					btn_unselectChar.setEnabled(false);
			}
		});
		listSelectedCharacters.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(listSelectedCharacters.getSelectedIndex()>-1)
					btn_unselectChar.setEnabled(true);
				else
					btn_unselectChar.setEnabled(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				//btn_unselectChar.setEnabled(false);
			}
		});
		listSelectedCharacters.setBounds(238, 80, 145, 137);
		contentPane.add(listSelectedCharacters);
		
		btn_selectChar = new JButton("->");
		btn_selectChar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedCharIndex =listAvailableCharacters.getSelectedIndex();
				selectedCharactersList.add(availableCharactersList.get(selectedCharIndex));
				availableCharactersList.remove(selectedCharIndex);
				updateAvailableCharactersList();
				updateSelectedCharactersList();
				if(selectedCharactersList.size()>0)
				{
					btnGenerateStory.setEnabled(true);
				}
			}
		});
		btn_selectChar.setEnabled(false);
		btn_selectChar.setBounds(172, 109, 56, 23);
		contentPane.add(btn_selectChar);
		
		btn_unselectChar = new JButton("<-");
		btn_unselectChar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int unselectedCharIndex =listSelectedCharacters.getSelectedIndex();
				availableCharactersList.add(selectedCharactersList.get(unselectedCharIndex));
				selectedCharactersList.remove(unselectedCharIndex);
				updateAvailableCharactersList();
				updateSelectedCharactersList();
				if(selectedCharactersList.size()==0)
				{
					btnGenerateStory.setEnabled(false);
				}
			}
		});
		btn_unselectChar.setEnabled(false);
		btn_unselectChar.setBounds(172, 167, 56, 23);
		contentPane.add(btn_unselectChar);
		
		btnGenerateStory = new JButton("Generate Story");
		btnGenerateStory.setEnabled(false);
		btnGenerateStory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Runtime rt = Runtime.instance();
				// Create a default profile
				ProfileImpl p = new ProfileImpl(false);
				
				jade.wrapper.AgentContainer authorContainer = rt.createAgentContainer(p); // get a container controller for creating new agents
				
				//create authorAgen
				AuthorAgent authorAgent = new AuthorAgent(selectedCharactersList);
				try {
					AgentController t1 = authorContainer.acceptNewAgent("The Author", authorAgent);				
					t1.start();
				} catch (StaleProxyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
				//Add agents
				jade.wrapper.AgentContainer container = rt.createAgentContainer(p); // get a container controller for creating new agents
			
				ArrayList<CharacterAgent> agentList = new ArrayList<CharacterAgent>();
				for(int i =0;i<listSelectedCharacters.getModel().getSize();i++)
				{
					if(isCharacterReady(selectedCharactersList.get(i)))
					{
						CharacterAgent agent = new CharacterAgent(selectedCharactersList.get(i));
					
						
					/*	try {
							Object[] args = new Object[1];
							args[0]= selectedCharactersList.get(i);
							AgentController t1 = container.createNewAgent(selectedCharactersList.get(i).getName(), "userStory.jade.CharacterAgent", args);				
							t1.start();
						} catch (StaleProxyException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
						try {
							AgentController t1 = container.acceptNewAgent(selectedCharactersList.get(i).getName(), agent);				
							t1.start();
						} catch (StaleProxyException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					//agent.run();
					}
					else
					{
						String message ="Character ("+selectedCharactersList.get(i).getName()+") can not be used in story. Please ensure that all information are provided";
						JOptionPane.showMessageDialog(GenerateStoryForm.this, message);
					}
				}
				
				authorAgent.startGeneratingTheStory();
				
				authorAgent.printStory();
			}

			private boolean isCharacterReady(storyCharacter storyCharacter) {
				if (storyCharacter.getCurrentEnvironment() == null)
				{
					String message ="Character ("+storyCharacter.getName()+") is not setteled in any environment. Would you like to set it now?";
					int dialogResult = JOptionPane.showConfirmDialog(GenerateStoryForm.this, message, "Missing environment", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION)
					{
						CharacterToEnvironment form = new CharacterToEnvironment();
						form.setVisible(true);
					}
					return false;
				}
				if(MsinGUI.hibernateObj.getCharacterGoals(storyCharacter).size()<=0)
				{
					String message ="Character ("+storyCharacter.getName()+") does not have any goals. Would you like to set it now?";
					int dialogResult = JOptionPane.showConfirmDialog(GenerateStoryForm.this, message, "Missing goal", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION)
					{
						GoalsForm form = new GoalsForm(MsinGUI.hibernateObj.getCharacterGoals(storyCharacter),storyCharacter);
						form.setVisible(true);
					}
					return false;
				}
				return true;
			}
		});
		btnGenerateStory.setBounds(125, 228, 153, 23);
		contentPane.add(btnGenerateStory);
		
		JScrollPane scrollPane = new JScrollPane(listAvailableCharacters);
		scrollPane.setBounds(20, 80, 143, 137);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(listSelectedCharacters);
		scrollPane_1.setBounds(239, 82, 144, 135);
		contentPane.add(scrollPane_1);
		
		availableCharactersList = new ArrayList<storyCharacter>();
		selectedCharactersList = new ArrayList<storyCharacter>();
	}
}
