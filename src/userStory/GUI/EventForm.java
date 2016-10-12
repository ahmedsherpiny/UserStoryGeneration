package userStory.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import userStory.environment.EnvEvent;
import userStory.persona.Emotion;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class EventForm extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;

	private EnvEvent m_event;
	private JTextField textFieldEmotionName;
	private JLabel lblValue;

	private JSlider slider;
	/**
	 * Create the frame.
	 */
	public EventForm(EnvEvent action,final EnvironmentForm parent) {
		m_event = action;
		setTitle("Event");
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
				m_event.setName(textFieldName.getText());
				m_event.setAffectedEmotion(new Emotion(textFieldEmotionName.getText()));
				m_event.setEmotionEffect(slider.getValue());
			//	notify();
				parent.addEvent(m_event);
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
		
		JLabel lblAffectedEmotion = new JLabel("Affected Emotion");
		lblAffectedEmotion.setBounds(10, 69, 97, 33);
		contentPane.add(lblAffectedEmotion);
		
		textFieldEmotionName = new JTextField();
		textFieldEmotionName.setBounds(136, 75, 86, 20);
		contentPane.add(textFieldEmotionName);
		textFieldEmotionName.setColumns(10);
		
		
		JLabel lblEffectValue = new JLabel("Effect value");
		lblEffectValue.setBounds(10, 102, 79, 14);
		contentPane.add(lblEffectValue);
		
		lblValue = new JLabel("value");
		lblValue.setEnabled(true);
		lblValue.setBounds(103, 102, 26, 14);
		contentPane.add(lblValue);
		
		slider = new JSlider();
		slider.setValue(10);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblValue.setText(Integer.toString(slider.getValue()));
			}
		});
		slider.setMinimum(Emotion.min-Emotion.max);
		slider.setMaximum(Emotion.max-Emotion.min);
		slider.setBounds(136, 102, 86, 23);
		contentPane.add(slider);
		
		
		
		updateForm();
	}
	
	private void updateForm()
	{
			textFieldName.setText(m_event.getName());
			textFieldEmotionName.setText(m_event.getAffectedEmotion().getName());
			slider.setValue(m_event.getEmotionEffect());
		
	}
}
