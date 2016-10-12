package userStory.environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import userStory.persona.GoalRequirement;

public class Environment {

	private int id;
	private String name;
	private Set<EnvObject> objects;
	private Set<EnvEvent> events;
	private Set<GoalRequirement> enteranceRequirements;
	private Set<Environment> possibleNextEnvironments;
	
	public Environment() {
		// TODO Auto-generated constructor stub
		this ("");
	}
	public Environment(String name)
	{
		this.name = name;
		objects= new HashSet<EnvObject>();
		events = new HashSet<EnvEvent>();
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
	public void addObject(EnvObject object)
	{
		objects.add(object);
	}
	
	public void removeObject (int objectID)
	{
		objects.remove(objectID);
	}
	
	public void clearObjects()
	{
		objects.removeAll(objects);
	}

	public Set<EnvObject> getObjects()
	{
		return objects;
	}

	public ArrayList<EnvObject> getObjectsArray()
	{
		ArrayList<EnvObject> objectArray = new ArrayList<EnvObject>();
		objectArray.addAll(objects);
		return objectArray;
	}
	
	public void setObjects (Set<EnvObject> objects)
	{
		this.objects = objects;
	}
	public void setObjects (ArrayList<EnvObject> objects)
	{
		this.objects.addAll(objects);
	}
	
	public void addEvent(EnvEvent event)
	{
		events.add(event);
	}
	
	public void removeEvent (int eventnID)
	{
		events.remove(eventnID);
	}
	
	public void clearEvents()
	{
		events.removeAll(events);
	}
	
	public Set<EnvEvent> getEvents()
	{
		return events;
	}
	
	public ArrayList<EnvEvent> getEventsArray()
	{
		ArrayList<EnvEvent> eventArray = new ArrayList<EnvEvent>();
		eventArray.addAll(events);
		return eventArray;
	}
	
	public void setEvents (Set<EnvEvent> events)
	{
		this.events = events;
	}
	public void setEvents (ArrayList<EnvEvent> events)
	{
		this.events.addAll(events);
	}
	
	public void setName(String newName) {
		name=newName;
	}
	
	public void setEnteranceRequirements(GoalRequirement[] enteranceRequirements)
	{
		this.enteranceRequirements= new HashSet<GoalRequirement> (Arrays.asList(enteranceRequirements));
	}
	public GoalRequirement[] getEnteranceRequirementsArray()
	{
		GoalRequirement[] EnteranceReqArray = new GoalRequirement[enteranceRequirements.size()];
		Iterator<GoalRequirement> iterator = enteranceRequirements.iterator();
		int i=0;
		while (iterator.hasNext())
		{
			EnteranceReqArray[i++] =iterator.next();
		}

		return EnteranceReqArray;
	}
	
	public void setPossibleNextEnvironments(Environment[] nextEnvironments)
	{
		this.possibleNextEnvironments= new HashSet<Environment> (Arrays.asList(nextEnvironments));
	}
	public Environment[] getPossibleNextEnvironmentsArray()
	{
		Environment[] possibleNextEnvArray = new Environment[possibleNextEnvironments.size()];
		Iterator<Environment> iterator = possibleNextEnvironments.iterator();
		int i=0;
		while (iterator.hasNext())
		{
			possibleNextEnvArray[i++] =iterator.next();
		}
		return possibleNextEnvArray;
	}
	
	public void setEnteranceRequirements(Set<GoalRequirement> enteranceRequirements)
	{
		this.enteranceRequirements=enteranceRequirements;
	}
	public Set<GoalRequirement> getEnteranceRequirements()
	{
		return enteranceRequirements;
	}
	
	public void setPossibleNextEnvironments(Set<Environment> nextEnvironments)
	{
		this.possibleNextEnvironments=nextEnvironments;
	}
	public Set<Environment> getPossibleNextEnvironments()
	{
		return possibleNextEnvironments;
	}
}
