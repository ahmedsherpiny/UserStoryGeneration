package userStory.environment;

public class Action {
	
	private int id;
	private String name;
	
	public Action (String name)
	{
		this.name=name;
	}
	public Action ()
	{
	}
	
	public void setName(String newName)
	{
		name = newName;
	}
	public String getName()
	{
		return name;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}


}
