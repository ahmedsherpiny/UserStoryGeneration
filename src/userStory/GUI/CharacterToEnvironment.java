package userStory.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CharacterToEnvironment extends JFrame {

	private JPanel contentPane;
	
	private String Charcaters[], Environments[];
	private JList listCharachters, listEnvironments;
	private JButton btnViewEdit;

	/**
	 * Create the frame.
	 */
	public CharacterToEnvironment() {
		setTitle("Connect character and enviornment");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListOfAvailable = new JLabel("List of available characters");
		lblListOfAvailable.setBounds(29, 27, 178, 14);
		contentPane.add(lblListOfAvailable);
		
		JLabel lblListOfAvailable_1 = new JLabel("List of available enviroments");
		lblListOfAvailable_1.setBounds(231, 27, 167, 14);
		contentPane.add(lblListOfAvailable_1);
		
		btnViewEdit = new JButton("View/Edit");
		btnViewEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 CharToEnvDetailsForm form = new CharToEnvDetailsForm(MsinGUI.ListOfCharcters.get(listCharachters.getSelectedIndex()),
					 MsinGUI.ListOfEnvironments.get(listEnvironments.getSelectedIndex()));
			 form.setVisible(true);
			}
		});
		btnViewEdit.setEnabled(false);
		btnViewEdit.setBounds(29, 239, 362, 23);
		contentPane.add(btnViewEdit);
		
		listCharachters = new JList();
		listCharachters.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				CanEditORView();
			}
		});
		listCharachters.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				CanEditORView();
			}
		});
		listCharachters.setBounds(27, 59, 167, 161);
		contentPane.add(listCharachters);
		
		JScrollPane scrollPane = new JScrollPane(listCharachters);
		scrollPane.setBounds(29, 59, 167, 161);
		contentPane.add(scrollPane);
		
		
		listEnvironments = new JList();
		listEnvironments.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CanEditORView();
			}
		});
		listEnvironments.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				CanEditORView();
			}
		});
		listEnvironments.setBounds(231, 59, 167, 161);
		contentPane.add(listEnvironments);
		
		JScrollPane scrollPane_1 = new JScrollPane(listEnvironments);
		scrollPane_1.setBounds(231, 59, 167, 161);
		contentPane.add(scrollPane_1);
		
		//fill list of characters and environments
		updateLists();
		
	}

	private void updateLists() {
		MsinGUI.ListOfCharcters = MsinGUI.hibernateObj.getCharacters();
		Charcaters = new String[MsinGUI.ListOfCharcters.size()];
		
		for (int i =0;i<MsinGUI.ListOfCharcters.size();i++)
		{
			Charcaters[i] = MsinGUI.ListOfCharcters.get(i).getName();
		}
		listCharachters.setModel(new AbstractListModel() {
			public int getSize() {
				return Charcaters.length;
			}
			public Object getElementAt(int index) {
				return Charcaters[index];
			}
		});
		
		MsinGUI.ListOfEnvironments = MsinGUI.hibernateObj.getEnvironments();
		Environments = new String[MsinGUI.ListOfEnvironments.size()];
		for (int i=0;i<MsinGUI.ListOfEnvironments.size();i++)
		{
			Environments[i] = MsinGUI.ListOfEnvironments.get(i).getName();
		}
		listEnvironments.setModel(new AbstractListModel() {
			public int getSize() {
				return Environments.length;
			}
			public Object getElementAt(int index) {
				return Environments[index];
			}
		});
	}

	private void CanEditORView()
	{
		if(listCharachters.getSelectedIndex()>-1&&listEnvironments.getSelectedIndex()>-1)
		{
			btnViewEdit.setEnabled(true);
		}
		else
		{
			btnViewEdit.setEnabled(false);
		}
	}
}
