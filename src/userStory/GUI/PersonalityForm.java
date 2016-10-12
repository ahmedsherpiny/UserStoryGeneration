package userStory.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSlider;

import userStory.persona.Emotion;
import userStory.persona.Personality;
import userStory.persona.PersonaltyCriteria;
import userStory.persona.Skill;
import userStory.persona.Value;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PersonalityForm extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPersonalityName;
	private Personality m_personality;
	
	private boolean m_showSlider;

	private enum CRITERIA {SKILL,VALUE,EMOTION};
	private CRITERIA criteria;
	private enum MODE {NEW,MODIFY,NONE};
	private MODE mode;

	private JButton btnAccept;
	private JButton btnCancel;
	private String[] skillsList;
	private JList listSkills;
	private JList listValues;
	private JList listEmotions;
	private String[] emotionsList;
	private String[] valuesList;
	private JLabel lblCriteriaValue;
	private JSlider slider;
	private JLabel lblPersonalityCriteria;
	private JButton btnAddEmotion;
	private JButton btnAddSkill;
	private JButton btnAddValue;
	private JButton btnDelete;


	/**
	 * Create the frame.
	 */
	public PersonalityForm(Personality personality, boolean showSlider) {
		m_personality=personality;
		m_showSlider = showSlider;
		mode = MODE.NONE;
		setResizable(false);
		setTitle("Personality");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAddSkill = new JButton("Add skill");
		btnAddSkill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = MODE.NEW;
				criteria=CRITERIA.SKILL;
				startAddingPersonalityCriteria();
			}
		});
		btnAddSkill.setBounds(10, 11, 89, 23);
		contentPane.add(btnAddSkill);
		
		btnAddValue = new JButton("Add value");
		btnAddValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = MODE.NEW;
				criteria=CRITERIA.VALUE;
				startAddingPersonalityCriteria();
			}
		});
		btnAddValue.setBounds(119, 11, 89, 23);
		contentPane.add(btnAddValue);
		
		btnAddEmotion = new JButton("Add emotion");
		btnAddEmotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = MODE.NEW;
				criteria=CRITERIA.EMOTION;
				startAddingPersonalityCriteria();
			}
		});
		btnAddEmotion.setBounds(231, 11, 111, 23);
		contentPane.add(btnAddEmotion);
		
		lblPersonalityCriteria = new JLabel("personality Criteria");
		lblPersonalityCriteria.setVisible(false);
		lblPersonalityCriteria.setBounds(10, 51, 50, 14);
		contentPane.add(lblPersonalityCriteria);
		
		textFieldPersonalityName = new JTextField();
		textFieldPersonalityName.setVisible(false);
		textFieldPersonalityName.setBounds(69, 48, 86, 20);
		contentPane.add(textFieldPersonalityName);
		textFieldPersonalityName.setColumns(10);
		
		lblCriteriaValue = new JLabel("Criteria value");
		lblCriteriaValue.setVisible(false);
		lblCriteriaValue.setBounds(162, 51, 33, 14);
		contentPane.add(lblCriteriaValue);
		
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblCriteriaValue.setText(Integer.toString(slider.getValue()));
			}
		});
		slider.setValue((PersonaltyCriteria.max-PersonaltyCriteria.min)/2);
		slider.setMaximum(PersonaltyCriteria.max);
		slider.setMinimum(PersonaltyCriteria.min);
		slider.setVisible(false);
		slider.setBounds(187, 45, 200, 23);
		contentPane.add(slider);
		
		
		JLabel lblSkills = new JLabel("Skills");
		lblSkills.setBounds(30, 76, 46, 14);
		contentPane.add(lblSkills);
		
		JLabel lblValues = new JLabel("Values");
		lblValues.setBounds(187, 76, 46, 14);
		contentPane.add(lblValues);
		
		JLabel lblEmotions = new JLabel("Emotions");
		lblEmotions.setBounds(341, 79, 79, 14);
		contentPane.add(lblEmotions);
		
		btnAccept = new JButton("");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String criteriaName = textFieldPersonalityName.getText();
				int criteriaValue = slider.getValue();
				if(criteria == CRITERIA.SKILL)
				{
					Skill skill = new Skill(criteriaName, criteriaValue);
					m_personality.addSkill(skill);
				}
				else if(criteria == CRITERIA.VALUE)
				{
					Value value = new Value(criteriaName, criteriaValue);
					m_personality.addValue(value);
				}
				else if(criteria == CRITERIA.EMOTION)
				{
					Emotion emotion = new Emotion(criteriaName, criteriaValue);
					m_personality.addEmotion(emotion);
				}
				MsinGUI.hibernateObj.addPersonality(m_personality);
				refreshPersonalityTextLists();
				
				btnAccept.setVisible(false);
				btnCancel.setVisible(false);
				textFieldPersonalityName.setText("");
				textFieldPersonalityName.setVisible(false);
				slider.setVisible(false);
				lblPersonalityCriteria.setText("");
				lblPersonalityCriteria.setVisible(false);
				lblCriteriaValue.setVisible(false);
				btnAddEmotion.setEnabled(true);
				btnAddSkill.setEnabled(true);
				btnAddValue.setEnabled(true);
			}
		});
		btnAccept.setToolTipText("accept");
		btnAccept.setIcon(new ImageIcon(PersonalityForm.class.getResource("/icons/accept.png")));
		btnAccept.setBounds(390, 45, 22, 23);
		btnAccept.setVisible(false);
		contentPane.add(btnAccept);
		
		btnCancel = new JButton("");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnAccept.setVisible(false);
				btnCancel.setVisible(false);
				textFieldPersonalityName.setText("");
				textFieldPersonalityName.setVisible(false);
				slider.setVisible(false);
				lblPersonalityCriteria.setText("");
				lblPersonalityCriteria.setVisible(false);
				lblCriteriaValue.setVisible(false);
				btnAddEmotion.setEnabled(true);
				btnAddSkill.setEnabled(true);
				btnAddValue.setEnabled(true);
			}
		});
		btnCancel.setIcon(new ImageIcon(PersonalityForm.class.getResource("/icons/reject.png")));
		btnCancel.setToolTipText("cancel");
		btnCancel.setBounds(417, 45, 22, 23);
		btnCancel.setVisible(false);
		contentPane.add(btnCancel);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = MODE.MODIFY;
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(10, 238, 129, 23);
		contentPane.add(btnEdit);
		
		listSkills = new JList();
		listSkills.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				EnableEditing();
			}
		});
		listSkills.setBounds(10, 102, 105, 112);
		contentPane.add(listSkills);
		
		listValues = new JList();
		listValues.setBounds(162, 102, 105, 112);
		contentPane.add(listValues);
		
		listEmotions = new JList();
		listEmotions.setBounds(315, 102, 105, 112);
		contentPane.add(listEmotions);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setBounds(187, 238, 151, 23);
		contentPane.add(btnDelete);

		refreshPersonalityTextLists();
		
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public PersonalityForm(Personality personality) {
		this(personality,true);
	}
	
	private void EnableEditing() {
		// TODO Auto-generated method stub
		if(listSkills.getSelectedIndex()>-1||listValues.getSelectedIndex()>-1||listEmotions.getSelectedIndex()>-1)
		{
//			btn.setEnabled(true);
//			btnDeleteGoal.setEnabled(true);
//			textFieldGoalName.setText(m_goalList.get(listGoals.getSelectedIndex()).getName());
//			String requirements="";
//			for(int i=0; i<m_goalList.get(listGoals.getSelectedIndex()).getRequirements().length;i++)
//			{
//				requirements+=((GoalRequirement)m_goalList.get(listGoals.getSelectedIndex()).getRequirements()[i]).getName() +"\n";
//			}
//			if (requirements.length()>2)
//				requirements=requirements.substring(0,requirements.length()-2);
//			textAreaRequirements.setText(requirements);
		}
		else
		{
//			btnModifyGoal.setEnabled(false);
//			btnDeleteGoal.setEnabled(false);
		}
	}

	private void  refreshPersonalityTextLists()
	{
	//	m_personality = MsinGUI.hibernateObj.getPersonality();
		if (m_personality == null)
		{
			m_personality = new Personality();
		//	MsinGUI.hibernateObj.addPersonality(m_personality);
		}
		ArrayList<Skill> skills = MsinGUI.hibernateObj.getSkills(m_personality);
		skillsList = new String[skills.size()];

		for(int i =0; i< skills.size();i++)
		{
			skillsList[i] = skills.get(i).getName()+"\t "+skills.get(i).getCriteriaValue();
		}
	//	if(hobbyList.length()>=2)	hobbyList=hobbyList.substring(0,hobbyList.length()-1);
			
		listSkills.setModel(new AbstractListModel(){
			public int getSize() {
				return skillsList.length;
			}
			public Object getElementAt(int index) {
				return skillsList[index];
			}
		});
		
		ArrayList<Value> values = MsinGUI.hibernateObj.getValues(m_personality);
		valuesList = new String[values.size()];
		for(int i =0; i< values.size();i++)
		{
			valuesList[i] = values.get(i).getName()+"\t "+values.get(i).getCriteriaValue();
		}

		listValues.setModel(new AbstractListModel(){
			public int getSize() {
				return valuesList.length;
			}
			public Object getElementAt(int index) {
				return valuesList[index];
			}
		});
		
		ArrayList<Emotion> emotions = MsinGUI.hibernateObj.getEmotions(m_personality);
		emotionsList = new String[emotions.size()];
		for(int i =0; i< emotions.size();i++)
		{
			emotionsList[i] =emotions.get(i).getName()+"\t "+emotions.get(i).getCriteriaValue();
		}
	//	if(hobbyList.length()>=2)	hobbyList=hobbyList.substring(0,hobbyList.length()-1);
			
		listEmotions.setModel(new AbstractListModel(){
			public int getSize() {
				return emotionsList.length;
			}
			public Object getElementAt(int index) {
				return emotionsList[index];
			}
		});
	}
	private void startAddingPersonalityCriteria() {
		// TODO Auto-generated method stub
		lblPersonalityCriteria.setVisible(true);
		lblCriteriaValue.setVisible(true);
		textFieldPersonalityName.setVisible(true);
		if(m_showSlider)
		{
			slider.setVisible(true);
		}
		btnAccept.setVisible(true);
		btnCancel.setVisible(true);
		
		btnAddEmotion.setEnabled(false);
		btnAddSkill.setEnabled(false);
		btnAddValue.setEnabled(false);
		
		if(criteria==CRITERIA.SKILL)
		{
			lblPersonalityCriteria.setText("Skill:");
		}
		else if (criteria == CRITERIA.VALUE)
		{
			lblPersonalityCriteria.setText("Value:");
		}
		else if (criteria == CRITERIA.EMOTION)
		{
			lblPersonalityCriteria.setText("Emotion:");
		}
}
}
	
