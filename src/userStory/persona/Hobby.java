package userStory.persona;

public class Hobby {
	private int id;
	private String name;
	
	public Hobby()
	{}
	public Hobby (String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		this.name = newName;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String toString()
	{
		return "Hobby[name: '"+name+"']";
	}
}
