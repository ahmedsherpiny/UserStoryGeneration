package userStory.GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTree;

import userStory.persona.PersonaltyCriteria;
import userStory.persona.storyCharacter;
import javax.swing.AbstractListModel;
import javax.swing.JEditorPane;
import java.awt.Canvas;
import java.awt.Label;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.SwingConstants;
import javax.swing.JSlider;

public class CharacterViewer extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldAge;
	private JTextField txtPreofession;
//	private storyCharacter persona;
	private String[] hobbies;
	private JTextField genderTextField;

	/**
	 * Create the panel.
	 */
	public CharacterViewer(final storyCharacter persona) {
		setLayout(null);
				
	//	this.persona=persona;
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(40, 30, 46, 14);
		add(lblName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(257, 58, 46, 14);
		add(lblAge);
		
		JLabel lblProfession = new JLabel("Profession");
		lblProfession.setBounds(40, 89, 66, 14);
		add(lblProfession);
		
		textFieldName = new JTextField();
		textFieldName.setText(persona.getName());
		textFieldName.setEditable(false);
		textFieldName.setBounds(113, 27, 246, 20);
		add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldAge = new JTextField();
		textFieldAge.setText(Integer.toString(persona.getAge()));
		textFieldAge.setEditable(false);
		textFieldAge.setBounds(313, 55, 46, 20);
		add(textFieldAge);
		textFieldAge.setColumns(10);
		
		txtPreofession = new JTextField();
		txtPreofession.setText(persona.getProfession());
		txtPreofession.setEditable(false);
		txtPreofession.setBounds(113, 86, 150, 20);
		add(txtPreofession);
		txtPreofession.setColumns(10);
		
		JLabel lblHobbies = new JLabel("Hobbies");
		lblHobbies.setBounds(5, 133, 46, 14);
		add(lblHobbies);
		hobbies = new String[MsinGUI.hibernateObj.getCharacterHobbies(persona).size()];		
		for(int i=0;i<persona.getHobbies().size();i++)
		{
			hobbies[i] = persona.getHobbiesArray().get(i).getName();
		}
		JList list = new JList();
		list.setEnabled(false);
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return hobbies.length;
			}
			public Object getElementAt(int index) {
				return hobbies[index];
			}
		});
		list.setBounds(5, 158, 100, 131);
		add(list);
		
		JLabel lblGoals = new JLabel("Goals");
		lblGoals.setBounds(400, 11, 46, 14);
		add(lblGoals);
		
		JTree treeGoals = new JTree();
		treeGoals.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Goals") {
				{
					for(int i =0;i<MsinGUI.hibernateObj.getCharacterGoals(persona).size();i++){
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode(persona.getGoalsArray().get(i).getName());
						for (int j=0;j<MsinGUI.hibernateObj.getGoalRequirements(persona.getGoalsArray().get(i)).length;j++){
							node_1.add(new DefaultMutableTreeNode(persona.getGoalsArray().get(i).getRequirementsArray()[j].getName()));
						}
						add(node_1);
					}
				}
			}
		));
		treeGoals.setBounds(400, 29, 141, 122);
		add(treeGoals);
		
		JLabel lblNeeds = new JLabel("Needs");
		lblNeeds.setBounds(400, 165, 46, 14);
		add(lblNeeds);
		
		JTree treeNeeds = new JTree();
		treeNeeds.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Needs") {
					{
						for(int i =0;i<MsinGUI.hibernateObj.getCharacterNeeds(persona).size();i++){
							DefaultMutableTreeNode node_1;
							node_1 = new DefaultMutableTreeNode(persona.getNeedsArray().get(i).getName());
							for (int j=0;j<MsinGUI.hibernateObj.getNeedFulfillers(persona.getNeedsArray().get(i)).length;j++){
								node_1.add(new DefaultMutableTreeNode(persona.getNeedsArray().get(i).getFulfillersArray()[j].getName()));
							}
							add(node_1);
						}
					}
				}
			));
		treeNeeds.setBounds(400, 184, 141, 105);
		add(treeNeeds);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(395, 11, 2, 278);
		add(separator);
		
		JLabel lblPersonality = new JLabel("Personality");
		lblPersonality.setBounds(205, 133, 85, 14);
		add(lblPersonality);
		
		genderTextField = new JTextField();
		String gender = "male";
		if(persona.isFemale())
			gender ="female";
		genderTextField.setText(gender);
		genderTextField.setEditable(false);
		genderTextField.setBounds(113, 55, 86, 20);
		add(genderTextField);
		genderTextField.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(107, 158, 1, 131);
		add(separator_1);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(40, 58, 46, 14);
		add(lblGender);
		
		//add Personality criteria
		final int margin =30;
		if (persona.getPersonality()!=null){
			for(int i=0;i<MsinGUI.hibernateObj.getCharacterSkills(persona).size();i++)
			{
				JLabel lblNewLabel = new JLabel(persona.getPersonality().getSkillsArrayList().get(i).getName());
				lblNewLabel.setBounds(110, 159 + margin*i, 90, 14);
				add(lblNewLabel);
				
				JSlider slider = new JSlider();
				slider.setBounds(105, 170+margin*i, 95, 23);
				slider.setMaximum(PersonaltyCriteria.max);
				slider.setMinimum(PersonaltyCriteria.min);
				slider.setEnabled(false);
				slider.setValue(persona.getPersonality().getSkillsArrayList().get(i).getCriteriaValue());
				slider.setToolTipText(Integer.toString(slider.getValue()));
				add(slider);
				
			}
			
			for(int i=0;i<MsinGUI.hibernateObj.getCharacterValues(persona).size();i++)
			{
				JLabel lblNewLabel_1 = new JLabel(persona.getPersonality().getValuesArrayList().get(i).getName());
				lblNewLabel_1.setBounds(205, 159+margin*i, 90, 14);
				add(lblNewLabel_1);
				
				
				JSlider slider = new JSlider();
				slider.setBounds(200, 170+margin*i, 95, 23);
				slider.setMaximum(PersonaltyCriteria.max);
				slider.setMinimum(PersonaltyCriteria.min);
				slider.setEnabled(false);
				slider.setValue(persona.getPersonality().getValuesArrayList().get(i).getCriteriaValue());
				slider.setToolTipText(Integer.toString(slider.getValue()));
				add(slider);
				
			}
			
			for(int i=0;i<MsinGUI.hibernateObj.getCharacterEmotions(persona).size();i++)
			{
				JLabel lblNewLabel_2 = new JLabel(persona.getPersonality().getEmotionsArrayList().get(i).getName());
				lblNewLabel_2.setBounds(300, 159+margin*i, 90, 14);
				add(lblNewLabel_2);
				
				
				JSlider slider = new JSlider();
				slider.setBounds(295, 170+margin*i, 95, 23);
				slider.setMaximum(PersonaltyCriteria.max);
				slider.setMinimum(PersonaltyCriteria.min);
				slider.setEnabled(false);
				slider.setValue(persona.getPersonality().getEmotionsArrayList().get(i).getCriteriaValue());
				slider.setToolTipText(Integer.toString(slider.getValue()));
				add(slider);
				
			}
		}
		//

	}
}
