package userStory.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import userStory.environment.Action;


public class ActionForm extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;

	private Action m_action;

	/**
	 * Create the frame.
	 */
	public ActionForm(Action obj,final ObjectForm parent) {
		m_action = obj;
		setTitle("Action");
		setResizable(false);
		setBounds(100, 100, 375, 198);
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
				m_action.setName(textFieldName.getText());
			//	notify();
				parent.addAction(m_action);
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
		
		updateForm();
	}
	
	private void updateForm()
	{
			textFieldName.setText(m_action.getName());
		
	}

}
