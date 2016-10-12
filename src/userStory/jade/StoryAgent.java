package userStory.jade;

import java.util.ResourceBundle.Control;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class StoryAgent extends Agent{
	
	private AgentContainer ac = null;

//Agent actions (messages)	
	public final static String RUN_ENGINE_BY_SAMART_PHONE = "RUN_ENGINE_BY_SAMART_PHONE";
	public final static String ENGINE_STARTED = "ENGINE_STARTED";	

	public final static String MOVE_TO_ENV = "MOVE_TO_ENV";
	public final static String MOVED_TO_NEW_ENV = "MOVED_TO_NEW_ENV";
	
	public final static String AFFECT_EMOTIONS = "AFFECT_EMOTIONS";
	public final static String SATISFY_GOAL = "SATISFY_GOAL";
	
	//Agent properties
	char gender = 'M'; // or 'F' for female
	String goal = "go to the beach";
	
	String currentEnvironment;
	//
	
	String PersonalPronoun = (gender=='M')? "he":"she";
	String singularPossessivePronouns  = (gender=='M')? "his":"her"; 
	
	protected void setup() {
		System.out.println(getLocalName()+" STARTED");
		//Object[] args = getArguments();
		
		 DFAgentDescription dfdEnv = new DFAgentDescription();
         ServiceDescription sd  = new ServiceDescription();
         sd.setType( "starting_environment" );
         dfdEnv.addServices(sd);
			//currentEnvironmentID = new AID("examples.storyGen.EnvironmentAgent", AID.ISLOCALNAME);
		try {
			DFAgentDescription[] result;
			int i=0;
			do
			{
				i++;
				result = DFService.search(this, dfdEnv);
			} while (result.length<=0 && i<10);
			if(i==10) System.out.println("Can not find initial environment");
			if(result.length>0)
				currentEnvironment = result[0].getName().getLocalName();

		
			// create the agent descrption of itself
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			// register the description with the DF
			DFService.register(this, dfd);
			System.out.println(getLocalName()+" REGISTERED WITH THE DF");
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		
		try {
			// create agent t1 on the same container of the creator agent
			AgentContainer container = (AgentContainer)getContainerController(); // get a container controller for creating new agents
		} catch (Exception any) {
			any.printStackTrace();
		}
		
		
		//Static story
		
		//Introduction
		System.out.println(getLocalName()+" will start now "+singularPossessivePronouns+" unique experience.");
		System.out.println("The story begins when "+getLocalName()+ " was at "+currentEnvironment);
		System.out.println(PersonalPronoun + " wants to "+ goal);

		//
			// send them a GREETINGS message
			ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
			msg.setContent(RUN_ENGINE_BY_SAMART_PHONE);

			msg.addReceiver(new AID(currentEnvironment, AID.ISLOCALNAME));
			send(msg);
			
			System.out.println(getLocalName()+" uses the car administration app on "+singularPossessivePronouns+" smart phone to run the car engine "); 

			ACLMessage msg2 = new ACLMessage(ACLMessage.PROPOSE);
			msg2.setContent(MOVE_TO_ENV);

			msg2.addReceiver(new AID(currentEnvironment, AID.ISLOCALNAME));
			send(msg2);
			
			System.out.println(getLocalName()+ " moves down to the Road ");


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
				addBehaviour(new CyclicBehaviour(this) {
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
								try {
									ac.kill();
								} catch (StaleProxyException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

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
				});
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

}