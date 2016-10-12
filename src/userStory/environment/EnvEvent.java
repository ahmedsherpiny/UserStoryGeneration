package userStory.environment;

import userStory.persona.Emotion;

public class EnvEvent {
	private int id;
	private String name;
	private int emotionEffect; // here should be the +ve or -ve value that should be added or removed from current emotion (Happy,surprise,...etc) value when this action performed 
	private Emotion affectedEmotion; //the mode that this action affects (happy/surprise,...etc) - mode could be a new class 

	public EnvEvent ()
	{}

	public EnvEvent (String name, Emotion affectedEmotion, int effect)
	{
		this.name = name;
		this.affectedEmotion=affectedEmotion;
		this.emotionEffect = effect;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	public Emotion getAffectedEmotion()
	{
		return affectedEmotion;
	}
	public int getEmotionEffect()
	{
		return emotionEffect;
	}

	public void setName(String newName) {
		name=newName;
	}
	
	public void setAffectedEmotion(Emotion e)
	{
		affectedEmotion=e;
	}
	public void setEmotionEffect(int effect)
	{
		 emotionEffect=effect;
	}
}
