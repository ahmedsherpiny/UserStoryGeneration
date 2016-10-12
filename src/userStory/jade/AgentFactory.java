package userStory.jade;

public class AgentFactory {
	
	//Agent actions (messages)	
		public final static String RUN_ENGINE_BY_SAMART_PHONE = "RUN_ENGINE_BY_SAMART_PHONE";
		public final static String ENGINE_STARTED = "ENGINE_STARTED";	

		public final static String MOVE_TO_ENV = "MOVE_TO_ENV";
		public final static String MOVED_TO_NEW_ENV = "MOVED_TO_NEW_ENV";
		
		public final static String AFFECT_EMOTIONS = "AFFECT_EMOTIONS";
		public final static String SATISFY_GOAL = "SATISFY_GOAL";
	
	//this function is used to create agent class. agentClass is is it road/person...etc and this will be the name of the class

	public void createAgentClass(String agentClass) {
		
		//probably here i will use reflection to create the new class
		
	}
	
	//this will add an agent to the main container
	public void addAgent(String agentClass, String agentName)
	{
		//Instantiate the new agent (that should have been created by createAgentClass) with specific name
		
		
		//then run the agent
	}
	//this function will be used to run the JADE 	
	public void run(){
		//jade.Boot.main(new String[]{"-nomtp"});
		jade.Boot.main(new String[]{"-gui"});
	}
	
	//function needs to be added to start the sequence of messages
	

}
