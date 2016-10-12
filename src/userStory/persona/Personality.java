package userStory.persona;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Personality{
	private int id;
	private Set<Skill> skills;
	private Set<Emotion> emotions;
	private Set<Value> values;

	public Personality()
	{
		skills = new HashSet<Skill>();
		emotions = new HashSet<Emotion>();
		values = new HashSet<Value>();
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}

	public void setSkills(Set<Skill> skills)
	{
		this.skills=skills;
	}
	public void setValues(Set<Value> values)
	{
		this.values=values;
	}
	public void setEmotions(Set<Emotion> emotions)
	{
		this.emotions=emotions;
	}
	public void setSkills(ArrayList<Skill> skills)
	{
		this.skills.addAll(skills);
	}
	public void setValues(ArrayList<Value> values)
	{
		this.values.addAll(values);
	}
	public void setEmotions(ArrayList<Emotion> emotions)
	{
		this.emotions.addAll(emotions);
	}
	public void addSkill (Skill skill)
	{
		skills.add(skill);
	}
	public void addEmotion (Emotion emotion)
	{
		emotions.add(emotion);
	}
	public void addValue (Value value)
	{
		values.add(value);
	}

	public void removeSkill (int skillID)
	{
		skills.remove(skillID);
	}
	public void removeEmotion (int emotionID)
	{
		emotions.remove(emotionID);
	}
	public void removeValue (int ValueID)
	{
		values.remove(ValueID);
	}
	
	public void clearSkills ()
	{
		skills.removeAll(skills);
	}
	public void clearEmotionss ()
	{
		emotions.removeAll(emotions);
	}
	public void clearValues ()
	{
		values.removeAll(values);
	}
	
	public Set<Skill> getSkills()
	{
		return skills;
	}
	
	public Set<Emotion> getEmotions()
	{
		return emotions;
	}
	
	public Set<Value> getValues()
	{
		return values;
	}
	
	public ArrayList<Skill> getSkillsArrayList()
	{
		ArrayList<Skill> skillsList=new ArrayList<Skill>();
		skillsList.addAll(skills);
		return skillsList;
	}
	
	public ArrayList<Emotion> getEmotionsArrayList()
	{
		ArrayList<Emotion> emotionsList = new ArrayList<Emotion>();
		emotionsList.addAll(emotions);
		return emotionsList;
	}
	
	public ArrayList<Value> getValuesArrayList()
	{
		ArrayList<Value> valuesList = new ArrayList<Value>();
		valuesList.addAll(values);
		return valuesList;
	}
	
	public String toString()
	{
		return "Personality: [ Skills <"+skills+">\t Emotions <"+emotions+"> \t Values <"+values
				+"]";
	}
	
}
