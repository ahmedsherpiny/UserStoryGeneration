package userStory.persona;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Need {
	private int id;
	private String name;
	private Set<NeedFulfiller> fulfillers;
	private boolean fulfilled = false;
	
	public Need()
	{}
	public Need(String name,  NeedFulfiller[] fulfillers)
	{
		this.name=name;
		this.fulfillers = new HashSet<NeedFulfiller> (Arrays.asList(fulfillers));
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public boolean fulfillNeed()
	{
		if(canFulfillNeed()){
			fulfilled = true;
		}
		return fulfilled;
	}
	public void setFulfillers (NeedFulfiller[] fulfillers)
	{
		this.fulfillers = new HashSet<NeedFulfiller> (Arrays.asList(fulfillers));
	}
	public void setFulfillers (Set<NeedFulfiller> fulfillers)
	{
		this.fulfillers = fulfillers;
	}
	public Set<NeedFulfiller> getFulfillers()
	{
		return fulfillers;
	}
	public NeedFulfiller[] getFulfillersArray()
	{
		NeedFulfiller[] fulfillersArray = new NeedFulfiller[fulfillers.size()];
		Iterator<NeedFulfiller> iterator = fulfillers.iterator();
		int i=0;
		while (iterator.hasNext())
		{
			fulfillersArray[i++] =iterator.next();
		}
		return fulfillersArray;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String newName)
	{
		name=newName;
	}
	public boolean canFulfillNeed()
	{
		boolean canAchieve = false;
		for (int i=0; i < fulfillers.toArray().length;i++)
		{
			if(((NeedFulfiller) fulfillers.toArray()[i]).isFound())
			{
				canAchieve=true;
				break;
			}
		}
		return canAchieve;
	}
	public boolean isFulfilled()
	{
		return fulfilled;
	}
	public void setFulfilled(boolean fufilled)
	{
		this.fulfilled= fufilled;
	}
	public String toString()
	{
		String fulfilledStr = fulfilled?"fulfilled":"not fullfiled yet";
		String fulfillersStr = "";
		for (int i =0;i < fulfillers.size();i++)
		{
			fulfillersStr=fulfillersStr.concat(fulfillers.toArray()[i].toString());
			fulfillersStr=fulfillersStr + ", ";
		}
		fulfillersStr=fulfillersStr.substring(0, fulfillersStr.length()-2);
		
		return "Need[name: '"+name+"' and can be fulfilled by <"+fulfillersStr+"> and is '"+fulfilledStr+"']";
	}

}
