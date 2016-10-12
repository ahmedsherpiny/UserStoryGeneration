package userStory.persona;

public class NeedFulfiller {
	private int id;
	private String name;
	private boolean found=false;

	public NeedFulfiller() {
	}
	public NeedFulfiller(String name) {
		this.name=name;
	}
	public NeedFulfiller(String name, boolean found) {
		this(name);
		this.found=found;
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
	public void setName(String name)
	{
		this.name=name;
	}
	public void setFulfillerAsfound (boolean found)
	{
		this.found=found;
	}
	public boolean isFound()
	{
		return found;
	}
	public void setFound(boolean found)
	{
		this.found=found;
	}
	public String toString()
	{
		String doneStr = (found)?"found":"not found yet";
		return "NeedFulfiller [name: '"+name+"' and is '"+ doneStr+"']";
	}

}
