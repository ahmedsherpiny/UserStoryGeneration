package hibernate;


import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import userStory.environment.Action;
import userStory.environment.EnvEvent;
import userStory.environment.EnvObject;
import userStory.environment.Environment;
import userStory.persona.Emotion;
import userStory.persona.Goal;
import userStory.persona.GoalRequirement;
import userStory.persona.Hobby;
import userStory.persona.Need;
import userStory.persona.NeedFulfiller;
import userStory.persona.Personality;
import userStory.persona.Skill;
import userStory.persona.Value;
import userStory.persona.storyCharacter;


public class HibernateClass {

	private SessionFactory sessionFactory;
	Session session;	
	public void init()
	{
		sessionFactory = new Configuration()
        .configure() // configures settings from hibernate.cfg.xml
        .buildSessionFactory();
	}
	
	//******************HOBBY************//
	public void addHobby(Hobby hobby)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( hobby );
		session.getTransaction().commit();
		session.close();

	}
	
	public void modifyHobby(Hobby hobby)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update( hobby );
		session.getTransaction().commit();
		session.close();

	}
	public void deleteHobby(Hobby hobby)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete( hobby );
		session.getTransaction().commit();
		session.close();

	}
	
	public ArrayList<Hobby> getHobbies()
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Hobby> result = (ArrayList<Hobby>) session.createQuery( "from Hobby" ).list();
		
        session.getTransaction().commit();
        session.close();
        
        return result;
	}

	//******************GOAL************//
	public void addGoal(Goal goal)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Iterator<GoalRequirement> iterator = goal.getRequirements().iterator(); 
		while(iterator.hasNext())
			{
				session.save(iterator.next());
			}
		session.save(goal);
		session.getTransaction().commit();
		session.close();

	}
	
	public void modifyGoal(Goal goal)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update( goal );
		session.getTransaction().commit();
		session.close();

	}
	public void deleteGoal(Goal goal)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete( goal );
		session.getTransaction().commit();
		session.close();

	}
	
	public ArrayList<Goal> getGoals()
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Goal> result = (ArrayList<Goal>) session.createQuery( "from Goal" ).list();
        /*for(int i=0; i< result.size();i++)
        {
        	ArrayList<GoalRequirement> requirements = (ArrayList<GoalRequirement>) session.createQuery( "select gr.GOAL_REQ_ID, gr.NAME, gr.IS_DONE from Goal_req gr join Goal_requirements gr1 where gr.Goal_REQ_ID = gr1.GOAL_REQ_ID and gr1.Goal_ID = "+ result.get(i).getId() ).list();
        	result.get(i).setRequirements((GoalRequirement[]) requirements.toArray());
        }*/
        session.getTransaction().commit();
        session.close();
        
        return result;
	}
	
	public GoalRequirement[] getGoalRequirements(Goal goal)
	{
		Session session = sessionFactory.openSession();
        session.update(goal);
        GoalRequirement[] result =goal.getRequirementsArray();
        session.close();
        
        return result;
	}

	
	
	//******************NEED************//
	public void addNeed(Need need)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Iterator<NeedFulfiller> iterator = need.getFulfillers().iterator(); 
		while(iterator.hasNext())
			{
				session.save(iterator.next());
			}
		session.save(need);
		session.getTransaction().commit();
		session.close();

	}
	
	public void modifyNeed(Need need)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update( need );
		session.getTransaction().commit();
		session.close();

	}
	public void deleteNeed(Need need)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete( need );
		session.getTransaction().commit();
		session.close();

	}
	
	public ArrayList<Need> getNeeds()
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Need> result = (ArrayList<Need>) session.createQuery( "from Need" ).list();
        /*for(int i=0; i< result.size();i++)
        {
        	ArrayList<GoalRequirement> requirements = (ArrayList<GoalRequirement>) session.createQuery( "select gr.GOAL_REQ_ID, gr.NAME, gr.IS_DONE from Goal_req gr join Goal_requirements gr1 where gr.Goal_REQ_ID = gr1.GOAL_REQ_ID and gr1.Goal_ID = "+ result.get(i).getId() ).list();
        	result.get(i).setRequirements((GoalRequirement[]) requirements.toArray());
        }*/
        session.getTransaction().commit();
        session.close();
        
        return result;
	}
	
	public NeedFulfiller[] getNeedFulfillers(Need need)
	{
		Session session = sessionFactory.openSession();
        session.update(need);
        NeedFulfiller[] result =need.getFulfillersArray();
        session.close();
        
        return result;
	}
	
	
	//******************Personality************//
	public void addPersonality(Personality personality)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Iterator<Skill> skillIterator = personality.getSkills().iterator(); 
		while(skillIterator.hasNext())
			{
				session.save(skillIterator.next());
			}
		Iterator<Emotion> emotionIterator = personality.getEmotions().iterator(); 
		while(emotionIterator.hasNext())
			{
				session.save(emotionIterator.next());
			}
		Iterator<Value> valueIterator = personality.getValues().iterator(); 
		while(valueIterator.hasNext())
			{
				session.save(valueIterator.next());
			}
		session.saveOrUpdate(personality);
		session.getTransaction().commit();
		session.close();

	}
	
	public void modifyPersonality(Personality personality)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Iterator<Skill> skillIterator = personality.getSkills().iterator(); 
		while(skillIterator.hasNext())
			{
				session.update(skillIterator.next());
			}
		Iterator<Emotion> emotionIterator = personality.getEmotions().iterator(); 
		while(emotionIterator.hasNext())
			{
				session.update(emotionIterator.next());
			}
		Iterator<Value> valueIterator = personality.getValues().iterator(); 
		while(valueIterator.hasNext())
			{
				session.update(valueIterator.next());
			}
		session.update( personality );
		session.getTransaction().commit();
		session.close();

	}
	public void addSkill(Skill skill)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( skill );
		session.getTransaction().commit();
		session.close();

	}
	public void addValue(Value value)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( value );
		session.getTransaction().commit();
		session.close();

	}
	public void addEmotion(Emotion emotion)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save( emotion );
		session.getTransaction().commit();
		session.close();

	}
	public void modifySkill(Skill skill)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update( skill );
		session.getTransaction().commit();
		session.close();

	}
	public void modifyValue(Value value)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update( value );
		session.getTransaction().commit();
		session.close();

	}
	public void modifyEmotion(Emotion emotion)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update( emotion );
		session.getTransaction().commit();
		session.close();

	}
	public void deletePersolity(Personality personality)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete( personality );
		session.getTransaction().commit();
		session.close();

	}
	
	public ArrayList<Skill> getSkills()
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Skill> result = (ArrayList<Skill>) session.createQuery( "from Skill" ).list();
        /*for(int i=0; i< result.size();i++)
        {
        	ArrayList<GoalRequirement> requirements = (ArrayList<GoalRequirement>) session.createQuery( "select gr.GOAL_REQ_ID, gr.NAME, gr.IS_DONE from Goal_req gr join Goal_requirements gr1 where gr.Goal_REQ_ID = gr1.GOAL_REQ_ID and gr1.Goal_ID = "+ result.get(i).getId() ).list();
        	result.get(i).setRequirements((GoalRequirement[]) requirements.toArray());
        }*/
        session.getTransaction().commit();
        session.close();
        
        return result;
	}
	public ArrayList<Value> getValues()
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Value> result = (ArrayList<Value>) session.createQuery( "from Value" ).list();
        /*for(int i=0; i< result.size();i++)
        {
        	ArrayList<GoalRequirement> requirements = (ArrayList<GoalRequirement>) session.createQuery( "select gr.GOAL_REQ_ID, gr.NAME, gr.IS_DONE from Goal_req gr join Goal_requirements gr1 where gr.Goal_REQ_ID = gr1.GOAL_REQ_ID and gr1.Goal_ID = "+ result.get(i).getId() ).list();
        	result.get(i).setRequirements((GoalRequirement[]) requirements.toArray());
        }*/
        session.getTransaction().commit();
        session.close();
        
        return result;
	}
	public ArrayList<Emotion> getEmotions()
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Emotion> result = (ArrayList<Emotion>) session.createQuery( "from Emotion" ).list();
        /*for(int i=0; i< result.size();i++)
        {
        	ArrayList<GoalRequirement> requirements = (ArrayList<GoalRequirement>) session.createQuery( "select gr.GOAL_REQ_ID, gr.NAME, gr.IS_DONE from Goal_req gr join Goal_requirements gr1 where gr.Goal_REQ_ID = gr1.GOAL_REQ_ID and gr1.Goal_ID = "+ result.get(i).getId() ).list();
        	result.get(i).setRequirements((GoalRequirement[]) requirements.toArray());
        }*/
        session.getTransaction().commit();
        session.close();
        
        return result;
	}
	
	
	public ArrayList<Skill> getSkills(Personality personality)
	{
		Session session = sessionFactory.openSession();
        session.update(personality);
        ArrayList<Skill> result =personality.getSkillsArrayList();
        session.close();
        
        return result;
	}
	
	public ArrayList<Emotion> getEmotions(Personality personality)
	{
		Session session = sessionFactory.openSession();
        session.update(personality);
        ArrayList<Emotion> result =personality.getEmotionsArrayList();
        session.close();
        
        return result;
	}
	public ArrayList<Value> getValues(Personality personality)
	{
		Session session = sessionFactory.openSession();
        session.update(personality);
        ArrayList<Value> result =personality.getValuesArrayList();
        session.close();
        
        return result;
	}
	
	public Personality getPersonality()
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Personality> personalityList =  (ArrayList<Personality>) session.createQuery( "from Personality" ).list();
        Personality result=null;
        if(personalityList.size()>0)
        {
        	result= personalityList.get(0);
        }
     /*   else 
        {
        	result = new Personality();
        }*/
        /*for(int i=0; i< result.size();i++)
        {
        	ArrayList<GoalRequirement> requirements = (ArrayList<GoalRequirement>) session.createQuery( "select gr.GOAL_REQ_ID, gr.NAME, gr.IS_DONE from Goal_req gr join Goal_requirements gr1 where gr.Goal_REQ_ID = gr1.GOAL_REQ_ID and gr1.Goal_ID = "+ result.get(i).getId() ).list();
        	result.get(i).setRequirements((GoalRequirement[]) requirements.toArray());
        }*/
        session.getTransaction().commit();
        session.close();
        
        return result;
	}
	
	
	//******************ENVIORNMENT************//
	public void addEnvironment(Environment env) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Iterator<EnvObject> ObjectIterator = env.getObjects().iterator(); 
		while(ObjectIterator.hasNext())
			{
				EnvObject object = ObjectIterator.next();
			Iterator<Action> actionIterator = object.getActions().iterator(); 
			while(actionIterator.hasNext())
				{
					session.save(actionIterator.next());
				}
				session.save(object);
			}
		
		Iterator<EnvEvent> eventsIterator = env.getEvents().iterator(); 
		while(eventsIterator.hasNext())
			{
				session.save(eventsIterator.next());
			}
		session.saveOrUpdate(env);
		session.getTransaction().commit();
		session.close();
	}

	public void updateEnvironment(Environment env)
	{
		//hobby.setName(newName);
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Iterator<EnvObject> ObjectIterator = env.getObjects().iterator(); 
			while(ObjectIterator.hasNext())
				{
					
					EnvObject currentObj = ObjectIterator.next();
					Set<Action> actions = currentObj.getActions();	
					Iterator<Action> actionIterator = actions.iterator(); 
					while(actionIterator.hasNext())
						{
							session.update(actionIterator.next());
						}
					session.update(currentObj);	
				}
			
			Iterator<EnvEvent> eventsIterator = env.getEvents().iterator(); 
			while(eventsIterator.hasNext())
				{
					session.update(eventsIterator.next());
				}
			session.update( env);
			session.getTransaction().commit();
		} catch (Exception e) {
	    e.printStackTrace();
	    session.getTransaction().rollback(); 
		} finally {
		    session.flush();
		    session.close();
		  }
	}

	
	public ArrayList<Environment> getEnvironments()
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Environment> EnvironmentList =  (ArrayList<Environment>) session.createQuery( "from Environment" ).list();
        
        session.getTransaction().commit();
        session.close();
        
        return EnvironmentList;
	}

	public ArrayList<EnvObject> getEnvironmentObjects(Environment env)
	{
		Session session = sessionFactory.openSession();
        session.update(env);
        
        ArrayList<EnvObject> result = env.getObjectsArray();

     //   session.getTransaction().commit();
        session.flush();
        session.close();
        
        return result;
	}

	public ArrayList<EnvEvent> getEnvironmentEvents(Environment env)
	{
		Session session = sessionFactory.openSession();
        session.update(env);
        
        ArrayList<EnvEvent> result = env.getEventsArray();

       // session.getTransaction().commit();
        session.close();
        
        return result;
	}
	
	public String getAffectedEmotionName(EnvEvent event)
	{
		Session session = sessionFactory.openSession();
        session.update(event);
        
        String result = event.getAffectedEmotion().getName();

       // session.getTransaction().commit();
        session.close();
        
        return result;
	}

	
	public ArrayList<Action> getObjectActions(EnvObject obj)
	{
		Session session = sessionFactory.openSession();
        session.update(obj);
        
        ArrayList<Action> result = obj.getActionsArray();

     //   session.getTransaction().commit();
        //session.flush();
        session.close();
        
        return result;
	}
	public void deleteEnvironment(Environment env) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete( env );
		session.getTransaction().commit();
		session.close();
		
	}

	
	//******************CHARACTER************//
	public void addCharacter(storyCharacter storyChar) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.openSession();
		//to be able to update already existing character uncomment this block, but adding new characters will raise exception
//		session.update(storyChar);
//		session.close();
//		session = sessionFactory.openSession();
		session.beginTransaction();

		Iterator<Need> needsIterator = storyChar.getNeeds().iterator(); 
		while(needsIterator.hasNext())
			{
				session.save(needsIterator.next());
			}
		Iterator<Hobby> HobbiesIterator = storyChar.getHobbies().iterator(); 
		while(HobbiesIterator.hasNext())
			{
			System.out.println("save hobby");
				session.saveOrUpdate(HobbiesIterator.next());
			}
		//session.save(storyChar.getHobbies());
		Iterator<Action> actionsIterator = storyChar.getActions().iterator(); 
		while(actionsIterator.hasNext())
			{
				session.save(actionsIterator.next());
			}
		
		Iterator<Goal> goalsIterator = storyChar.getGoals().iterator(); 
		while(goalsIterator.hasNext())
			{
				session.save(goalsIterator.next());
			}
	//	if(storyChar.getPersonality()!=null)
	//		session.save(storyChar.getPersonality());
		
		session.saveOrUpdate(storyChar);
		session.getTransaction().commit();
		session.close();
	}

	public ArrayList<storyCharacter> getCharacters()
	{
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<storyCharacter> result = (ArrayList<storyCharacter>) session.createQuery( "from storyCharacter" ).list();
        /*for(int i=0; i< result.size();i++)
        {
        	ArrayList<GoalRequirement> requirements = (ArrayList<GoalRequirement>) session.createQuery( "select gr.GOAL_REQ_ID, gr.NAME, gr.IS_DONE from Goal_req gr join Goal_requirements gr1 where gr.Goal_REQ_ID = gr1.GOAL_REQ_ID and gr1.Goal_ID = "+ result.get(i).getId() ).list();
        	result.get(i).setRequirements((GoalRequirement[]) requirements.toArray());
        }*/
        session.getTransaction().commit();
        session.close();
        
        return result;
	}

	public ArrayList<Goal> getCharacterGoals(storyCharacter character)
	{
		Session session = sessionFactory.openSession();
        session.update(character);
        
        ArrayList<Goal> result = character.getGoalsArray();
        for(int i=0;i<result.size();i++)
        	session.update(result.get(0));

     //   session.getTransaction().commit();
        session.close();
        
        return result;
	}
	
	public ArrayList<Hobby> getCharacterHobbies(storyCharacter character)
	{
		Session session = sessionFactory.openSession();
        session.update(character);
        
        ArrayList<Hobby> result = character.getHobbiesArray();

     //   session.getTransaction().commit();
        session.close();
        
        return result;
	}

	public ArrayList<Action> getCharacterActions(storyCharacter character)
	{
		Session session = sessionFactory.openSession();
        session.update(character);
        
        ArrayList<Action> result = character.getActionsArray();

     //   session.getTransaction().commit();
        session.close();
        
        return result;
	}
	
	public String getCharacterCurrentEnvironmentName(storyCharacter character)
	{
		Session session = sessionFactory.openSession();
        session.update(character);
        
        String result = character.getCurrentEnvironment().getName();

     //   session.getTransaction().commit();
        session.close();
        
        return result;
	}

	
	public void finalize()
	{
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}

	public void updateCharacter(storyCharacter character) {
		Session session = sessionFactory.openSession();
		try{ 
			session.beginTransaction();
			session.update( character );
			session.getTransaction().commit();
		} catch (Exception e) {
		    e.printStackTrace();
		    session.getTransaction().rollback(); 
		} finally {
			session.flush();
			session.close();
		}
	}

	public void updateEnvironmentObject(EnvObject object) {
		Session session = sessionFactory.openSession();
		try{ 
			session.beginTransaction();
			Iterator<Action> actionIterator = object.getActions().iterator(); 
			while(actionIterator.hasNext())
				{
					session.update(actionIterator.next());
				}
				session.update(object);
			session.getTransaction().commit();
		} catch (Exception e) {
		    e.printStackTrace();
		    session.getTransaction().rollback(); 
		} finally {
			session.flush();
			session.close();
		}
		
	}

	public void deleteCharacter(storyCharacter persona) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(persona);
		session.getTransaction().commit();
		session.close();
		
	}
	public ArrayList<Need> getCharacterNeeds(storyCharacter character)
	{
		Session session = sessionFactory.openSession();
        session.update(character);
        
        ArrayList<Need> result = character.getNeedsArray();

     //   session.getTransaction().commit();
        session.close();
        
        return result;
	}

	public Personality getCharacterPersonality(storyCharacter character)
	{
		Session session = sessionFactory.openSession();
        session.update(character);
        
        Personality result = character.getPersonality();
        session.update(result);

     //   session.getTransaction().commit();
        session.close();
        
        return result;
	}
	public ArrayList<Skill> getCharacterSkills(storyCharacter character)
	{
		Session session = sessionFactory.openSession();
        session.update(character);
        
        Personality personality = character.getPersonality();
        session.update(personality);
        
        ArrayList<Skill> result = personality.getSkillsArrayList();

     //   session.getTransaction().commit();
        session.close();
        
        return result;
	}
	public ArrayList<Value> getCharacterValues(storyCharacter character)
	{
		Session session = sessionFactory.openSession();
        session.update(character);
        
        Personality personality = character.getPersonality();
        session.update(personality);
        
        ArrayList<Value> result = personality.getValuesArrayList();

     //   session.getTransaction().commit();
        session.close();
        
        return result;
	}
	public ArrayList<Emotion> getCharacterEmotions(storyCharacter character)
	{
		Session session = sessionFactory.openSession();
        session.update(character);
        
        Personality personality = character.getPersonality();
        session.update(personality);
        
        ArrayList<Emotion> result = personality.getEmotionsArrayList();

     //   session.getTransaction().commit();
        session.close();
        
        return result;
	}

	public void openSession() {
		session = sessionFactory.openSession();
	}
	public void closeSession() {
		session.close();
	}

//	public NeedFulfiller[] getCharacterNeedsFulfillers(
//			Need need) {
//		Session session = sessionFactory.openSession();
//        session.update(need);
//        
//        NeedFulfiller[] result = need.getFulfillersArray();
//
//     //   session.getTransaction().commit();
//        session.close();
//        
//        return result;
//	}
//
	
}
