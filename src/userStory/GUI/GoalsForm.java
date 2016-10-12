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

import userStory.persona.Goal;
import userStory.persona.GoalRequirement;
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
import javax.swing.JTree;
import javax.swing.JLabel;

public class GoalsForm extends JFrame {

	private JPanel contentPane;
	private ArrayList<Goal> m_goalList;
	private JButton btnModifyGoal;
	private JButton btnDeleteGoal;
	private JTextField textFieldGoalName;
	private JButton btnAccept;
	private JButton btnCancel;
	private JButton btnAddNewGoal;
	private JList listGoals;
	private String[] goalsList ={""};
	private JTextArea textAreaRequirements;
	private storyCharacter m_persona;
	
	private enum MODE {NEW,MODIFY,NONE};
	private MODE mode;
	
	/**
	 * Create the frame.
	 * @param persona 
	 */
	public GoalsForm(ArrayList<Goal> goals, storyCharacter persona) {
		m_goalList = goals;
		m_persona = persona;
		setTitle("Goals");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		mode=MODE.NONE;
		
	
		btnAddNewGoal = new JButton("New goal");
		btnAddNewGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddNewGoal.setEnabled(false);
				textFieldGoalName.setText("");
				textFieldGoalName.setEditable(true);
				textAreaRequirements.setText("");
				textAreaRequirements.setEditable(true);
				btnAccept.setVisible(true);
				btnCancel.setVisible(true);
				mode = MODE.NEW;
			}
		});
		btnAddNewGoal.setBounds(10, 198, 95, 23);
		contentPane.add(btnAddNewGoal);
		
		btnModifyGoal = new JButton("Modify");
		btnModifyGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnModifyGoal.setEnabled(false);
				
				textFieldGoalName.setEditable(true);
				btnAccept.setVisible(true);
				btnCancel.setVisible(true);
				mode = MODE.MODIFY;
			}
		});
		btnModifyGoal.setEnabled(false);
		btnModifyGoal.setBounds(125, 198, 95, 23);
		contentPane.add(btnModifyGoal);
		
		btnDeleteGoal = new JButton("Delete");
		btnDeleteGoal.setEnabled(false);
		btnDeleteGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] selected = listGoals.getSelectedIndices();
				for (int i=0;i<selected.length;i++)
				{
				//	m_goalList.remove(selected[i]);
					MsinGUI.hibernateObj.deleteGoal(m_goalList.get(selected[i]));
				}
				refreshGoalsTextList();
			}
		});
		btnDeleteGoal.setBounds(78, 232, 71, 23);
		contentPane.add(btnDeleteGoal);
		
		textFieldGoalName = new JTextField();
		textFieldGoalName.setEditable(false);
		textFieldGoalName.setBounds(10, 32, 155, 20);
		contentPane.add(textFieldGoalName);
		textFieldGoalName.setColumns(10);
		
		btnAccept = new JButton("");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mode == MODE.NEW)
				{
					//Add new goal
					String[] requirementsStr = textAreaRequirements.getText().split(",|\n");
					GoalRequirement[] requirements = null;
					if(requirementsStr.length>0)
					{
						requirements=new GoalRequirement[requirementsStr.length];
						for(int i=0;i<requirementsStr.length;i++)
						{
							requirements[i] = new GoalRequirement(requirementsStr[i]);
						}
					}
					Goal goal = new Goal(textFieldGoalName.getText(), requirements);
					m_goalList.add(goal);
					if(m_persona==null)
						MsinGUI.hibernateObj.addGoal(goal);
					else
					{
						MsinGUI.hibernateObj.addGoal(goal);
						m_persona.addGoal(goal);
					//	MsinGUI.hibernateObj.addCharacter(m_persona);
					}
	//				m_goalList.add(new Goal(textFieldGoalName.getText()));
					String message ="New goal added";
					JOptionPane.showMessageDialog(GoalsForm.this, message);				
				}
				else if (mode == MODE.MODIFY)
				{
					m_goalList.get(listGoals.getSelectedIndex()).setName(textFieldGoalName.getText());
					MsinGUI.hibernateObj.modifyGoal(m_goalList.get(listGoals.getSelectedIndex()));
					String message ="Goal name modified";
					JOptionPane.showMessageDialog(GoalsForm.this, message);				
				}
				refreshGoalsTextList();
				textFieldGoalName.setEditable(false);
				textFieldGoalName.setText("");
				textAreaRequirements.setEditable(false);
				textAreaRequirements.setText("");
				btnAddNewGoal.setEnabled(true);
				btnAccept.setVisible(false);
				btnCancel.setVisible(false);
				mode = MODE.NONE;
	
			}
		});
		btnAccept.setToolTipText("accept");
		btnAccept.setIcon(new ImageIcon(GoalsForm.class.getResource("/icons/accept.png")));
		btnAccept.setBounds(172, 32, 22, 23);
		btnAccept.setVisible(false);
		contentPane.add(btnAccept);
		
		btnCancel = new JButton("");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldGoalName.setText("");
				textFieldGoalName.setEditable(false);
				textAreaRequirements.setText("");
				textAreaRequirements.setEditable(false);
				btnAddNewGoal.setEnabled(true);
				btnAccept.setVisible(false);
				btnCancel.setVisible(false);
				mode = MODE.NONE;

			}
		});
		btnCancel.setIcon(new ImageIcon(GoalsForm.class.getResource("/icons/reject.png")));
		btnCancel.setToolTipText("cancel");
		btnCancel.setBounds(203, 32, 22, 23);
		btnCancel.setVisible(false);
		contentPane.add(btnCancel);
		
		listGoals = new JList();
		listGoals.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(listGoals.getSelectedIndex()>-1)
				{
					btnModifyGoal.setEnabled(true);
					btnDeleteGoal.setEnabled(true);
				}
			}
		});
		listGoals.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(listGoals.getSelectedIndex()>-1)
				{
					btnModifyGoal.setEnabled(true);
					btnDeleteGoal.setEnabled(true);
					textFieldGoalName.setText(m_goalList.get(listGoals.getSelectedIndex()).getName());
					String requirements="";
					GoalRequirement[] requiremetsArray = MsinGUI.hibernateObj.getGoalRequirements(m_goalList.get(listGoals.getSelectedIndex()));					for(int i=0; i<requiremetsArray.length;i++)
					{
						requirements+=requiremetsArray[i].getName() +"\n";
					}
					if (requirements.length()>2)
						requirements=requirements.substring(0,requirements.length()-1);
					textAreaRequirements.setText(requirements);
				}
				else
				{
					btnModifyGoal.setEnabled(false);
					btnDeleteGoal.setEnabled(false);
				}
			}
		});
		listGoals.setBounds(253, 10, 181, 251);
		contentPane.add(listGoals);
		
		textAreaRequirements = new JTextArea();
		textAreaRequirements.setEditable(false);
		textAreaRequirements.setBounds(10, 88, 199, 100);
		contentPane.add(textAreaRequirements);
		
		JLabel lblGoal = new JLabel("Goal");
		lblGoal.setBounds(10, 11, 46, 14);
		contentPane.add(lblGoal);
		
		JLabel lblRequirements = new JLabel("Requirements");
		lblRequirements.setBounds(10, 63, 103, 14);
		contentPane.add(lblRequirements);
	
		
		//refresh hobby List
		refreshGoalsTextList();
	}
	private void  refreshGoalsTextList()
	{
	//	m_goalList = MsinGUI.hibernateObj.getGoals();
		goalsList=new String[m_goalList.size()];
		for(int i =0; i< m_goalList.size();i++)
		{
			goalsList[i] = m_goalList.get(i).getName();//+"";
		}
	//	if(hobbyList.length()>=2)	hobbyList=hobbyList.substring(0,hobbyList.length()-1);
			
		listGoals.setModel(new AbstractListModel() {
			public int getSize() {
				return goalsList.length;
			}
			public Object getElementAt(int index) {
				return goalsList[index];
			}
		});
	}
}
