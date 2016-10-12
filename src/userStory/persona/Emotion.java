package userStory.persona;

public class Emotion extends PersonaltyCriteria {
	private String name;
	
	public Emotion()
	{}
	public Emotion(String name)
	{
		this.name=name;
	}
	public Emotion(String name,int value)
	{
		this(name);
		setCriteriaValue(value);
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return "Emotion[name: '"+name+"' criteriaValue: '"+getCriteriaValue()+"']";
	}
}
