package userStory.persona;

public class GoalRequirement {

	private int id;
private String name;
private boolean done=false;

public GoalRequirement() {
}
public GoalRequirement(String name, boolean done) {
	this(name);
	this.done=done;
}
public int getId()
{
	return id;
}
public void setId(int id)
{
	this.id = id;
}
public GoalRequirement(String name) {
	this.name=name;
}
public void setDone (boolean done)
{
	this.done=done;
}
public boolean isDone()
{
	return done;
}
public String getName()
{
	return name;
}
public void setName(String newName)
{
	name=newName;
}
public String toString()
{
	String doneStr = (done)?"done":"not done yet";
	return "GoalRequirement [name: '"+name+"' and is '"+ doneStr+"']";
}

}
