package userStory.jade;

import java.io.CharConversionException;

import userStory.GUI.MsinGUI;
import userStory.environment.Action;
import userStory.persona.storyCharacter;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.StaleProxyException;

public class CharacterAgent extends Agent {
	private storyCharacter characterObject;
	//private AID initiator = null;	
	String PersonalPronoun;
	String singularPossessivePronouns; 
	public void setCharacterObject(storyCharacter characterObject)
	{
		this.characterObject = characterObject;
		PersonalPronoun = (characterObject.getGender()=='M')? "he":"she";
		singularPossessivePronouns  = (characterObject.getGender()=='M')? "his":"her";
	}
	
	public CharacterAgent(storyCharacter characterObject)
	{
		this.characterObject = characterObject;
	}

public void sendMessage(int messageType, String content, String receiver)
{
	ACLMessage msg = new ACLMessage(messageType);
	msg.setContent(content);

	msg.addReceiver(new AID(receiver, AID.ISLOCALNAME));
	send(msg);
	
	System.out.println(getLocalName()+" sent "+content+" to " + receiver); 

	}
	
	protected void setup() {
		System.out.println(getLocalName()+" STARTED");
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			setCharacterObject((storyCharacter) args[0]);
		}

//		Search for an agent
//		DFAgentDescription dfdEnv = new DFAgentDescription();
//         ServiceDescription sd  = new ServiceDescription();
//         sd.setType( "starting_environment" );
//         dfdEnv.addServices(sd);
			//initiator = new AID(characterObject.getName(), AID.ISLOCALNAME);
		try {
//			DFAgentDescription[] result;
//			int i=0;
//			do
//			{
//				i++;
//				result = DFService.search(this, dfdEnv);
//			} while (result.length<=0 && i<10);
//			if(i==10) System.out.println("Can not find initial environment");
//			currentEnvironment = result[0].getName().getLocalName();

		
			// create the agent descrption of itself
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			// register the description with the DF
			DFService.register(this, dfd);
			System.out.println(getLocalName()+" REGISTERED WITH THE DF");
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		
		
		
		//Static story
		
//		//Introduction
		System.out.println(getLocalName()+" will start now "+singularPossessivePronouns+" unique experience.");
		String currentEnvironment = "NO WHERE";
		if(characterObject.getCurrentEnvironment()!=null)
		{
			currentEnvironment=MsinGUI.hibernateObj.getCharacterCurrentEnvironmentName(characterObject);
		}
		System.out.println("The story begins when "+getLocalName()+ " was at "+currentEnvironment);
		MsinGUI.hibernateObj.openSession();
		System.out.println(PersonalPronoun + " wants to "+ MsinGUI.hibernateObj.getCharacterGoals(characterObject).toString());
		MsinGUI.hibernateObj.closeSession();
//
//		//
			// send them a GREETINGS message
		// example of message
		//sendMessage(ACLMessage.PROPOSE,RUN_ENGINE_BY_SAMART_PHONE,currentEnvironment)
			
		//example of logging
		//System.out.println(getLocalName()+" uses the car administration app on "+singularPossessivePronouns+" smart phone to run the car engine "); 

		
		////move to new environment
		//sendMessage(ACLMessage.PROPOSE,MOVE_TO_ENV,currentEnvironment)
		//System.out.println(getLocalName()+ " moves down to the Road ");


		  /* IF YOU COMMENTED OUT THIS ELSE CLAUSE, THEN YOU WOULD GENERATE
	      AN INTERESTING INFINITE LOOP WITH INFINTE AGENTS AND AGENT 
	      CONTAINERS BEING CREATED 
	      else {
	      IAmTheCreator = true;
	      doWait(2000); // wait two seconds
		 
		}*/

		// add a Behaviour that listen if a greeting message arrives
				// and sends back an ANSWER.
				// if an ANSWER to a greetings message is arrived 
				// then send a THANKS message
				/*addBehaviour(new CyclicBehaviour(this) {
					public void action() {
						// listen if a greetings message arrives
						ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL));
						if (msg != null) {
							if (ENGINE_STARTED.equalsIgnoreCase(msg.getContent())) {
								// if a greetings message is arrived then send an ANSWER
								System.out.println(myAgent.getLocalName()+" can hear the engine "); 
							} 
							else if (MOVED_TO_NEW_ENV.equalsIgnoreCase(msg.getContent())) {
								// if an ANSWER to a greetings message is arrived 
								// then send a THANKS message
								System.out.println(myAgent.getLocalName()+" RECEIVED CONFIRMATION MESSAGE FROM "+msg.getSender().getLocalName());
								currentEnvironment = "Road";
								System.out.println(myAgent.getLocalName()+" is now in the "+ currentEnvironment);
							}
							else if (SATISFY_GOAL.equalsIgnoreCase(msg.getContent())) {
								System.out.println(myAgent.getLocalName()+" RECEIVED SATISFY GOAL MESSAGE FROM "+msg.getSender().getLocalName());
								System.out.println(myAgent.getLocalName()+ " has achieved "+singularPossessivePronouns+" goal");
								// Kill the created container
								

							}
							else {
								System.out.println(myAgent.getLocalName()+" Unexpected message received from "+msg.getSender().getLocalName()); 
							}					
						}
						else {
							// if no message is arrived, block the behaviour
							block();
						}
					}
				});*/
			}

			protected void takeDown() {
				// Deregister with the DF
				try {
					
					System.out.println("----THE END----");
					
					DFService.deregister(this);
					System.out.println(getLocalName()+" DEREGISTERED WITH THE DF");
				} catch (FIPAException e) {
					e.printStackTrace();
				}
			}
			
			public void performAction(Action action, String receiver)
			{
				sendMessage(ACLMessage.PROPOSE,action.getName(),receiver);
			}

	

}
