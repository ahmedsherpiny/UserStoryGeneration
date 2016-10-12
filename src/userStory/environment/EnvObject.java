package userStory.environment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EnvObject {

	private int id;
	private String name;
	private Set<Action> actions;

	public EnvObject ()
	{
	}

	public EnvObject (String name)
	{
		this.name = name;
		actions = new HashSet<Action>();
	}
	
	public EnvObject(String name,ArrayList<Action> actions)
	{
		this.name = name;
		this.actions = new HashSet<Action>();
		this.actions.addAll(actions);
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
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public ArrayList<Action> getActionsArray()
	{
		ArrayList<Action> actionArray = new ArrayList<Action>();
		actionArray.addAll(actions);
		return actionArray;
	}
	public Set<Action> getActions()
	{
		return actions;
	}
	
	public void setActions(Set<Action> actions)
	{
		this.actions = actions;
	}
	
	public void setActions(ArrayList<Action> actions)
	{
		this.actions.addAll(actions);
	}
	
	public void addAction(Action action)
	{
		if (actions ==null)
			actions = new HashSet<Action>();
		actions.add(action);
	}
	public void removeAction(int actionID)
	{
		actions.remove(actionID);
	}
	public void clearActions()
	{
		actions.removeAll(actions);
	}
}
