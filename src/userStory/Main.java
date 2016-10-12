package userStory;

import userStory.persona.Emotion;
import userStory.persona.Value;
import userStory.persona.Goal;
import userStory.persona.GoalRequirement;
import userStory.persona.Hobby;
import userStory.persona.Need;
import userStory.persona.NeedFulfiller;
import userStory.persona.Personality;
import userStory.persona.Skill;
import userStory.persona.storyCharacter;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		storyCharacter persona1 = new storyCharacter("persona1",27);
		Hobby horsebackRiding = new Hobby("Horseback Riding");
		Hobby reading = new Hobby("Reading");
		GoalRequirement passAllCourses = new GoalRequirement("pass all courses",true);
		GoalRequirement FinishThesis = new GoalRequirement("Finish the thesis");
		GoalRequirement[] goal1Requirements = new GoalRequirement[2];
		goal1Requirements[0]= passAllCourses;
		goal1Requirements[1] = FinishThesis;
		Goal goal1 = new Goal("Finish Master", goal1Requirements);
		
		NeedFulfiller apple = new NeedFulfiller("Apple");
		NeedFulfiller orange = new NeedFulfiller("Orange",true);
		NeedFulfiller meat = new NeedFulfiller("Meant",false);
		
		NeedFulfiller[] need1Fulfiller = new NeedFulfiller[3];
		need1Fulfiller[0] = apple;
		need1Fulfiller[1] = orange;
		need1Fulfiller[2] = meat;
		Need need1 = new Need("Hunger", need1Fulfiller);
		
		Personality personality1 = new Personality();
		personality1.addSkill(new Skill("Programming", 15));
		personality1.addSkill(new Skill("Leadership", 10));
		personality1.addEmotion(new Emotion("Disappointment", 7));
		personality1.addEmotion(new Emotion("Optimism", 15));
		personality1.addEmotion(new Emotion("Happy", 13));
		personality1.addValue(new Value("Perfection", 14));
		
		//add details to our persona
		persona1.addHobby(horsebackRiding);
		persona1.addHobby(reading);
		
		persona1.addGoal(goal1);
		
		persona1.addNeed(need1);
		
		persona1.setPersonality(personality1);
		
		System.out.println("our persona is: " + persona1);
	}

}
