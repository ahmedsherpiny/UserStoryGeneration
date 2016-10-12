package userStory.persona;

public class Value extends PersonaltyCriteria {

	private String name;
	
	public Value()
	{}
	public Value(String name)
	{
		this.name=name;
	}
	public Value(String name,int value)
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
		return "Value[name: '"+name+"' criteriaValue: '"+getCriteriaValue()+"']";
	}
}
