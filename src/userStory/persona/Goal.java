package userStory.persona;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Goal {
	private int id=0;
	private String name;
	private Set<GoalRequirement> requirements;
	private boolean achieved = false;
	
	public Goal()
	{
		requirements = new HashSet<GoalRequirement>(0);
		
	}
	public Goal(String name, GoalRequirement[] requirements)
	{
		this.name=name;
		this.requirements = new HashSet<GoalRequirement> (Arrays.asList(requirements));
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
		this.name = newName;
	}
	public GoalRequirement[] getRequirementsArray()
	{
		GoalRequirement[] reqArray = new GoalRequirement[requirements.size()];
		Iterator<GoalRequirement> iterator = requirements.iterator();
		int i=0;
		while (iterator.hasNext())
		{
			reqArray[i++] =iterator.next();
		}
		return reqArray;
	}
	public Set<GoalRequirement> getRequirements()
	{
		return requirements;
	}
	public void setRequirements (GoalRequirement[] requirements)
	{
		this.requirements = new HashSet<GoalRequirement> (Arrays.asList(requirements));
	}
	public void setRequirements (Set<GoalRequirement> requirements)
	{
		this.requirements = requirements;
	}
	public boolean isAchieved()
	{
		return achieved;	
	}
	public void setAchieved(boolean achieved)
	{
		 this.achieved = achieved;	
	}
	public boolean achiveGoal()
	{
		if(canAchieveGoal()){
			achieved = true;
		}
		return achieved;
	}
	public boolean canAchieveGoal()
	{
		boolean canAchieve = true;
		Iterator<GoalRequirement> iterator = requirements.iterator();
		while (iterator.hasNext())
		{
			if(!iterator.next().isDone())
			{
				canAchieve=false;
				break;
			}
		}
		/*for (int i=0; i < requirements.size();i++)
		{
			if(!requirements.[i].isDone())
			{
				canAchieve=false;
				break;
			}
		}*/
		return canAchieve;
	}
	public String toString()
	{
		String achievedStr = achieved?"achieved":"not achieved yet";
		String requirementsStr = "";
		for (int i =0;i < requirements.size();i++)
		{
			requirementsStr=requirementsStr.concat(requirements.toArray()[i].toString());
			requirementsStr=requirementsStr + ", ";
		}
		requirementsStr=requirementsStr.substring(0,requirementsStr.length()-2);
		return "Goal[name: '"+name+"' and requires <"+requirementsStr+"> and is '"+achievedStr+"']";
	}
}
