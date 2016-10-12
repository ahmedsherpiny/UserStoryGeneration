package userStory.jade;

import jade.core.Agent;

import java.util.ArrayList;

import userStory.UserStory;
import userStory.GUI.MsinGUI;
import userStory.persona.Goal;
import userStory.persona.Need;
import userStory.persona.storyCharacter;

public class AuthorAgent extends Agent{

	private ArrayList<storyCharacter> storyCharacters;
	private UserStory story;
	String PersonalPronoun;
	String singularPossessivePronouns; 
	public AuthorAgent(ArrayList<storyCharacter> selectedCharactersList) {
		storyCharacters = selectedCharactersList;
		story = new UserStory();
	}
	public void startGeneratingTheStory() {
		// TODO Auto-generated method stub
		story.addSentence("Story title");
		for(int i =0; i<storyCharacters.size();i++)
		{
			PersonalPronoun = (storyCharacters.get(i).getGender()=='M')? "he":"she";
			singularPossessivePronouns  = (storyCharacters.get(i).getGender()=='M')? "his":"her";
			story.addSentence(storyCharacters.get(i).getName() + " is in "+MsinGUI.hibernateObj.getCharacterCurrentEnvironmentName(storyCharacters.get(i)));
			ArrayList<Goal> goals =MsinGUI.hibernateObj.getCharacterGoals(storyCharacters.get(i));
			for(int j=0;j<goals.size();j++)
				story.addSentence(/*storyCharacters.get(i).getName()*/ PersonalPronoun + " wants to "+MsinGUI.hibernateObj.getCharacterGoals(storyCharacters.get(i)).get(j).getName());
			story.addSentence(/*storyCharacters.get(i).getName()*/ PersonalPronoun + " goes to "+ "Road and uses Car");
			ArrayList<Need> needs =MsinGUI.hibernateObj.getCharacterNeeds(storyCharacters.get(i));
			for(int j =0;j<needs.size();j++)
			{
				Need need =MsinGUI.hibernateObj.getCharacterNeeds(storyCharacters.get(i)).get(j);
				story.addSentence("To fulfill the need for "+ need.getName() + " the "+MsinGUI.hibernateObj.getNeedFulfillers(need)[0].getName()+" switched on");
			}
			story.addSentence(storyCharacters.get(i).getName() + " achieved the goal and "+ MsinGUI.hibernateObj.getCharacterGoals(storyCharacters.get(i)).get(0).getName());
		}	
	}
	public void printStory()
	{
		for (int i=0;i<story.getSentences().size();i++)
		{
			System.out.println(story.getSentences().get(i));
		}
	}

}
