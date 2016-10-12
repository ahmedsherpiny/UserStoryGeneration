package userStory.persona;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import userStory.environment.Action;
import userStory.environment.EnvObject;
import userStory.environment.Environment;

public class storyCharacter{
	
	private int id;
	private String name;
	private int age;
	private char gender; // M for Male and F for Female
	private String profession;
	private String behavior;
	private String picture; 
	private Set <Goal> goals;
	private Set<Need> needs;
	private Set<Hobby> hobbies;
	private Personality personality;
	
	private Set<Action> actions;
	private Environment currentEnvironment;
	
	public storyCharacter () 
	{
	}
	public storyCharacter (String name, int age) 
	{
		this.name=name;
		this.age=age;
		
		hobbies = new HashSet<Hobby>();
		needs = new HashSet<Need>();
		goals = new HashSet<Goal>();
		actions = new HashSet<Action>();
	}
	public storyCharacter (String name, int age, String profession) 
	{
		this (name,age);
		this.profession=profession;
	}
	public void addAction (Action action)
	{
		actions.add(action);
	}
	public void addGoal (Goal goal)
	{
		goals.add(goal);
	}
	public void addNeed (Need need)
	{
		needs.add(need);
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void addHobby (Hobby hobby)
	{
		hobbies.add(hobby);
	}

	public void removeAction (int actionID)
	{
		actions.remove(actionID);
	}
	public void removeGoal (int goalID)
	{
		goals.remove(goalID);
	}
	public void removeNeed (int needID)
	{
		needs.remove(needID);
	}
	public void removeHobby (int hobbyID)
	{
		hobbies.remove(hobbyID);
	}
	
	public void clearActions ()
	{
		actions.removeAll(actions);
	}
	public void clearGoals ()
	{
		goals.removeAll(goals);
	}
	public void clearNeeds ()
	{
		needs.removeAll(needs);
	}
	public void clearHobbies ()
	{
		hobbies.removeAll(hobbies);
	}
	
	public ArrayList<Goal> getGoalsArray()
	{
		ArrayList<Goal> goalsArray = new ArrayList<Goal>();
		goalsArray.addAll(goals);

		return goalsArray;
	}
	public Set<Goal> getGoals()
	{
		return goals;
	}
	
	public void setGoals(Set<Goal> goals)
	{
		this.goals = goals;
	}
	
//	public void setGoals (ArrayList<Goal> goals)
//	{
//		this.goals.addAll(goals);
//	}

	public ArrayList<Need> getNeedsArray()
	{
		ArrayList<Need> needsArray = new ArrayList<Need>();
		needsArray.addAll(needs);		
		return needsArray;
	}
	public Set<Need> getNeeds()
	{
		return needs;
	}

	public void setNeeds(Set<Need> needs)
	{
		this.needs = needs;
	}
//	public void setNeeds (ArrayList<Need> needs)
//	{
//		this.needs.addAll(needs);
//	}
	
	public ArrayList<Hobby> getHobbiesArray()
	{
		ArrayList<Hobby> hobbiesArray = new ArrayList<Hobby>();
		hobbiesArray.addAll(hobbies);
		return hobbiesArray;
	}
	public Set<Hobby> getHobbies()
	{
		return hobbies;
	}
	
	public void setHobbies(Set<Hobby> hobbies)
	{
		this.hobbies = hobbies;
	}
	
	public void setName(String newName)
	{
		this.name = newName;
	}
	public String getName()
	{
		return name;
	}
	
	public void setAge(int newAge)
	{
		this.age = newAge;
	}
	public int getAge()
	{
		return age;
	}
	
	public void setProfession(String newProfession)
	{
		this.profession = newProfession;
	}
	public String getProfession()
	{
		return profession;
	}
	
	public void setPersonality(Personality personality)
	{
		this.personality = personality;
	}
	public Personality getPersonality()
	{
		return personality;
	}

	public void setBehavior(String newBehavior)
	{
		this.behavior = newBehavior;
	}
	public String getBehavior()
	{
		return behavior;
	}
	
	public void setPicture(String newPicturePath)
	{
		this.picture = newPicturePath;
	}
	public String getPicture()
	{
		return picture;
	}
	
	public void setCurrentEnvironment(Environment currentEnvironment)
	{
		this.currentEnvironment=currentEnvironment;
	}
	public Environment getCurrentEnvironment()
	{
		return currentEnvironment;
	}

	public String toString()
	{
		return "storyCharacter: [ Name: '" +name+"' - Age: '"+age+"' - Profession: '"+profession
				+"' Behavior: '"+behavior+ "' \n\t Hobbies <"+hobbies+">\n\t Goals <"+goals+"> \n\t Needs <"+needs
				+"> \n\t Personality <" +personality+ "> "
				+ " picture path '"+picture+"'"
				+"]";
	}

	public Set<Action> getActions() {		
		return actions;
	}
	
	public ArrayList<Action> getActionsArray() {
		// TODO Auto-generated method stub
		ArrayList<Action> actionsArray = new ArrayList<Action>();
		actionsArray.addAll(actions);		
		return actionsArray;
	}
	
	public void setActions(Set<Action> actions)
	{
		this.actions = actions;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	} 
	public boolean isFemale()
	{
		if (gender=='F')
			return true;
		return false;
	}
}
