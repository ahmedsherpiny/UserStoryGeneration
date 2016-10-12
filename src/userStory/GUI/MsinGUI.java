package userStory.GUI;

import hibernate.HibernateClass;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.ConvolveOp;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;

import userStory.environment.Environment;
import userStory.persona.Goal;
import userStory.persona.Hobby;
import userStory.persona.Need;
import userStory.persona.Personality;
import userStory.persona.storyCharacter;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JRadioButton;

public class MsinGUI {

	static public ArrayList<Need> globalNeeds = new ArrayList<Need>();
	static public ArrayList<Hobby> globalHobbies = new ArrayList<Hobby>();
	static public Personality globalPersonalities = new Personality();
	static public ArrayList<Goal> globalGoals = new ArrayList<Goal>();

	
	static public ArrayList<storyCharacter> ListOfCharcters = new ArrayList<storyCharacter>();
	static public ArrayList<Environment> ListOfEnvironments = new ArrayList<Environment>();
	public static HibernateClass hibernateObj;

	private JFrame frmStoryGenerator;

	private String SourceCodeBuffer; //this used to save the source code used to create the tasks done using the GUI 
	private JTextField nameText;
	private Panel panel;	
	private JTextField ageText;
	private JTextField professionText;
	private storyCharacter persona;
	private JButton btnViewCharacter;
	private JButton btnDeleteCharacter;
	private JButton btnPersonality;
	private JButton btnHobbies;
	private JButton btnGoals;
	private JButton btnNeeds;
	private JButton btnRefreshCharacter;

	private Personality personality;
	private JMenuItem mntmAddANeed;
	private JMenu mnHobbies;
	private JMenuItem mntmAddAHobby;
	private JMenu mnPersonality;
	private JMenuItem mntmAddEmotion;
	private JMenuItem mntmAddSkill;
	private JMenuItem mntmAddValue;
	private JMenu mnEnvironment;
	private JMenuItem mntmNewEnvironment;
	private JMenuItem mntmConnectToEnvironment;
	private JMenuItem mntmConnectToC;
	private JMenu mnGoals;
	private JMenuItem mntmAddAGoal;
	private ArrayList<storyCharacter> m_CharacterList;
	private JList listCharacters;
	private String[] charctersList;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MsinGUI window = new MsinGUI();
					window.frmStoryGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MsinGUI() {
		hibernateObj = new HibernateClass();
		hibernateObj.init();

		initialize();
		
		//initialize global needs,goals,hobbies and personality
		globalGoals = hibernateObj.getGoals();
		globalHobbies = hibernateObj.getHobbies();
		globalNeeds = hibernateObj.getNeeds();
		globalPersonalities = hibernateObj.getPersonality();
		
		refreshCharacterList();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStoryGenerator = new JFrame();
		frmStoryGenerator.setTitle("Story generator");
		frmStoryGenerator.setResizable(false);
		frmStoryGenerator.getContentPane().setEnabled(false);
		frmStoryGenerator.setBounds(100, 100, 465, 387);
		frmStoryGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		personality = new Personality();
		
		JMenuBar menuBar = new JMenuBar();
		frmStoryGenerator.setJMenuBar(menuBar);
		
		JMenu mnCharacter = new JMenu("Character");
		menuBar.add(mnCharacter);
		
		JMenuItem mntmAddNewCharacter = new JMenuItem("Add new character");
		mntmAddNewCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel();
				btnViewCharacter.setEnabled(false);
				btnRefreshCharacter.setEnabled(true);
				persona = null;
			}

		});
		mnCharacter.add(mntmAddNewCharacter);
		
		mntmConnectToEnvironment = new JMenuItem("Connect to an Environment");
		mntmConnectToEnvironment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectCharacterToEnvironment();
			}
		});
		
		JMenu mnNewMenu = new JMenu("Needs");
		mnCharacter.add(mnNewMenu);
		
		mntmAddANeed = new JMenuItem("Add a need");
		mntmAddANeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NeedsForm form = new NeedsForm(globalNeeds,null);
				form.setVisible(true);
			}
		});
		mnNewMenu.add(mntmAddANeed);
		
		mnHobbies = new JMenu("Hobbies");
		mnCharacter.add(mnHobbies);
		
		mntmAddAHobby = new JMenuItem("Add a hobby");
		mnHobbies.add(mntmAddAHobby);
		mntmAddAHobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HobbiesForm form = new HobbiesForm(globalHobbies,null);
				form.setVisible(true);
			}
		});
		
		mnPersonality = new JMenu("Personality");
		mnCharacter.add(mnPersonality);
		
		mntmAddEmotion = new JMenuItem("Add emotion");
		mntmAddEmotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPersonalityForm();
			}
		});
		mnPersonality.add(mntmAddEmotion);
		
		mntmAddSkill = new JMenuItem("Add skill");
		mntmAddSkill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPersonalityForm();
			}
		});
		mnPersonality.add(mntmAddSkill);
		
		mntmAddValue = new JMenuItem("Add value");
		mntmAddValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPersonalityForm();
			}
		});
		mnPersonality.add(mntmAddValue);
		
		mnGoals = new JMenu("Goals");
		mnCharacter.add(mnGoals);
		
		mntmAddAGoal = new JMenuItem("Add a goal");
		mntmAddAGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoalsForm form = new GoalsForm(globalGoals,null);
				form.setVisible(true);
			}
		});
		mnGoals.add(mntmAddAGoal);
		mnCharacter.add(mntmConnectToEnvironment);
		
		mnEnvironment = new JMenu("Environment");
		menuBar.add(mnEnvironment);
		
		mntmNewEnvironment = new JMenuItem("New environment");
		mntmNewEnvironment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnvironmentForm form = new EnvironmentForm(ListOfEnvironments);
				form.setVisible(true);
			}
		});
		mnEnvironment.add(mntmNewEnvironment);
		
		mntmConnectToC = new JMenuItem("Connect to a character");
		mntmConnectToC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectCharacterToEnvironment();
			}
		});
		mnEnvironment.add(mntmConnectToC);
		
		JMenu mnGenerateUserStory = new JMenu("User story");
		menuBar.add(mnGenerateUserStory);
		
		JMenuItem mntmGenerate = new JMenuItem("Generate");
		mntmGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GenerateStoryForm form = new GenerateStoryForm();
				form.setVisible(true);
			}
		});
		mnGenerateUserStory.add(mntmGenerate);
		frmStoryGenerator.getContentPane().setLayout(null);
		
		panel = new Panel();
		panel.setVisible(false);
		
		JLabel lblStoredCharacters = new JLabel("Available characters");
		lblStoredCharacters.setBounds(267, 118, 155, 17);
		frmStoryGenerator.getContentPane().add(lblStoredCharacters);
		panel.setBounds(10, 118, 251, 214);
		frmStoryGenerator.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name*");
		lblName.setBounds(10, 11, 46, 20);
		panel.add(lblName);
		
		nameText = new JTextField();
		nameText.setBounds(76, 11, 172, 20);
		panel.add(nameText);
		nameText.setColumns(10);
		
		JLabel lblAge = new JLabel("Age*");
		lblAge.setBounds(10, 41, 46, 14);
		panel.add(lblAge);
		
		ageText = new JTextField();
		ageText.setBounds(76, 42, 36, 20);
		panel.add(ageText);
		ageText.setColumns(10);
		
		JLabel lblProfession = new JLabel("Profession");
		lblProfession.setBounds(10, 91, 66, 14);
		panel.add(lblProfession);
		
		professionText = new JTextField();
		professionText.setBounds(76, 88, 100, 20);
		panel.add(professionText);
		professionText.setColumns(10);
		
		btnPersonality = new JButton("Personality");
		btnPersonality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				persona.setPersonality(personality);
				PersonalityForm form = new PersonalityForm(persona.getPersonality());
				form.setVisible(true);
			}
		});
		btnPersonality.setEnabled(false);
		btnPersonality.setBounds(10, 112, 109, 23);
		panel.add(btnPersonality);
		
		btnNeeds = new JButton("Needs");
		btnNeeds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NeedsForm form = new NeedsForm(hibernateObj.getCharacterNeeds(persona),persona);
				form.setVisible(true);
			}
		});
		btnNeeds.setEnabled(false);
		btnNeeds.setBounds(137, 112, 104, 23);
		panel.add(btnNeeds);
		
		btnHobbies = new JButton("Hobbies");
		btnHobbies.setEnabled(false);
		btnHobbies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HobbiesForm form = new HobbiesForm(hibernateObj.getCharacterHobbies(persona),persona);
				form.setVisible(true);
			}
		});
		btnHobbies.setBounds(10, 146, 109, 23);
		panel.add(btnHobbies);
		
		btnGoals = new JButton("Goals");
		btnGoals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GoalsForm form = new GoalsForm(hibernateObj.getCharacterGoals(persona),persona);
				form.setVisible(true);
			}
		});
		btnGoals.setEnabled(false);
		btnGoals.setBounds(137, 146, 104, 23);
		panel.add(btnGoals);
		
		btnRefreshCharacter = new JButton("Save character");
		btnRefreshCharacter.setBounds(53, 180, 150, 23);
		panel.add(btnRefreshCharacter);
		
		JLabel lblGender = new JLabel("Gender*");
		lblGender.setBounds(10, 66, 46, 14);
		panel.add(lblGender);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(76, 62, 91, 23);
		panel.add(rdbtnFemale);
		rdbtnFemale.setSelected(true);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(169, 62, 75, 23);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnFemale);
		group.add(rdbtnMale);
		
		panel.add(rdbtnMale);
		
		btnViewCharacter = new JButton("View character");
		btnViewCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	hibernateObj.updateCharacter(persona);
				CharacterViewer charcterViewr = new CharacterViewer(persona);
				JFrame frame = new JFrame();
				frame.setTitle("Character viewer");
				frame.setResizable(false);
				frame.setBounds(100, 100, 570, 320);
				frame.getContentPane().add(charcterViewr);
				frame.setVisible(true);
			}
		});
		btnViewCharacter.setEnabled(false);
		btnViewCharacter.setBounds(267, 280, 155, 23);
		frmStoryGenerator.getContentPane().add(btnViewCharacter);
		
		
		listCharacters = new JList();
		listCharacters.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(listCharacters.getSelectedIndex()>-1)
				{
					btnViewCharacter.setEnabled(true);
					btnDeleteCharacter.setEnabled(true);
					//
					btnPersonality.setEnabled(true);
					btnHobbies.setEnabled(true);
					btnGoals.setEnabled(true);
					btnNeeds.setEnabled(true);
				//	btnRefreshCharacter.setEnabled(false);
					//
					persona=hibernateObj.getCharacters().get(listCharacters.getSelectedIndex());
					showPanel();
					nameText.setText(persona.getName());
					ageText.setText(String.valueOf(persona.getAge()));
					professionText.setText(persona.getProfession());
					if(!persona.isFemale())
					{
						rdbtnMale.setSelected(true);
					}
					else
					{
						rdbtnFemale.setSelected(true);
					}
					
				}
				else
				{
					btnViewCharacter.setEnabled(false);
					btnDeleteCharacter.setEnabled(false);
					
					//
					btnPersonality.setEnabled(false);
					btnHobbies.setEnabled(false);
					btnGoals.setEnabled(false);
					btnNeeds.setEnabled(false);
					//
				}
			}
		});
		listCharacters.setBounds(269, 54, 155, 137);
		frmStoryGenerator.getContentPane().add(listCharacters);
		btnRefreshCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshCharacter();
			}
		});
		
		scrollPane = new JScrollPane(listCharacters);
		scrollPane.setBounds(267, 132, 155, 137);
		frmStoryGenerator.getContentPane().add(scrollPane);
		
		JTextArea txtrWelcomeToUser = new JTextArea();
		txtrWelcomeToUser.setWrapStyleWord(true);
		txtrWelcomeToUser.setEditable(false);
		txtrWelcomeToUser.setText("Welcome to user story generator\r\nTo generate a story, you have to add a character\r\nor select existing character, and fill in their hobbies,\r\ngoals, needs and personality criteria.\r\nThen add environment(s) where the story will take place.\r\nAfterwards you need to connect characters to the initial \r\nenvironment \r\n");
		txtrWelcomeToUser.setBounds(10, -4, 449, 111);
		frmStoryGenerator.getContentPane().add(txtrWelcomeToUser);
		
		btnDeleteCharacter = new JButton("Delete Character");
		btnDeleteCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hibernateObj.deleteCharacter(persona);
				refreshCharacterList();
			}
		});
		btnDeleteCharacter.setEnabled(false);
		btnDeleteCharacter.setBounds(267, 309, 155, 23);
		frmStoryGenerator.getContentPane().add(btnDeleteCharacter);
		
		
		//refreshCharacterList();
	}
	
	private void refreshCharacterList() {
		m_CharacterList = MsinGUI.hibernateObj.getCharacters();
		charctersList = new String[m_CharacterList.size()];
		for(int i =0; i< m_CharacterList.size();i++)
		{
			charctersList[i] = m_CharacterList.get(i).getName();//+"";
		}
	//	if(hobbyList.length()>=2)	hobbyList=hobbyList.substring(0,hobbyList.length()-1);
			
		listCharacters.setModel(new AbstractListModel() {
			public int getSize() {
				return charctersList.length;
			}
			public Object getElementAt(int index) {
				return charctersList[index];
			}
		});
	
	}

	private void showPanel() {
		
		panel.setVisible(true);
	}
	private void refreshCharacter() {
		try{
			if (persona==null)
			{
				persona = new storyCharacter(nameText.getText(), Integer.valueOf(ageText.getText()), professionText.getText());
				
				enableCharacterAdditionalData();
				btnViewCharacter.setEnabled(true);
				char gender = 'F';
				if(rdbtnMale.isSelected())
					gender = 'M';
				persona.setGender(gender);
				ListOfCharcters.add(persona);
			}
			else
			{
				persona.setName(nameText.getText());
				persona.setAge(Integer.valueOf(ageText.getText()));
				persona.setProfession(professionText.getText());
				char gender = 'F';
				if(rdbtnMale.isSelected())
					gender = 'M';
				persona.setGender(gender);
			}
			hibernateObj.addCharacter(persona);
		} catch (NumberFormatException e)
		{
			String message ="Error in Age: Please use only integers";
			JOptionPane.showMessageDialog(this.frmStoryGenerator, message);
		}
	//	outputText.setText(persona.toString());
		refreshCharacterList();
	}
	private void enableCharacterAdditionalData()
	{
		btnGoals.setEnabled(true);
		btnNeeds.setEnabled(true);
		btnHobbies.setEnabled(true);
		btnPersonality.setEnabled(true);
	}
	private void showPersonalityForm()
	{
		PersonalityForm form = new PersonalityForm(globalPersonalities,false);
		form.setVisible(true);
	}
	private void ConnectCharacterToEnvironment()
	{
		CharacterToEnvironment form = new CharacterToEnvironment();
		form.setVisible(true);
	}
}
