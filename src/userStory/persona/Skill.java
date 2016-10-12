package userStory.persona;

public class Skill extends PersonaltyCriteria {
	private String name;

	public Skill()
	{
	}
	public Skill(String name)
	{
		this.name=name;
	}
	public Skill(String name,int value)
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
		return "Skill[name: '"+name+"' criteriaValue: '"+getCriteriaValue()+"']";
	}
}
