package examples.gipc.consensus.sessionport.twoparty.symmetric;

import port.trace.consensus.ConsensusTraceUtility;

public class TwoPartySymmetricPeer1Launcher extends ATwoPartySymmetricPeerLauncher{
	static final int MY_PORT_NUMBER = 7001;
	static final String MY_NAME = "1";
	public static final String GREETING_1 = "Hello";
	public static final String GREETING_2 = "Howdy";
	public static int MEANING = 29;


	
	public static void doOperations() {
		float aGreetingProposal1 = greetingMechanism.propose(GREETING_1);
		greetingMechanism.waitForConsensus(aGreetingProposal1);
		float aGreetingProposal2 = greetingMechanism.propose(GREETING_2);
		float aMeaningOfLifeProposal = meaningOfLifeMechanism.propose(MEANING);
		greetingMechanism.waitForConsensus(aGreetingProposal2);
		meaningOfLifeMechanism.waitForConsensus(aMeaningOfLifeProposal);		
		
	}
	public static void beSessionMember() {
		ConsensusTraceUtility.setTracing();
		init(MY_NAME, MY_PORT_NUMBER);
		doOperations();
	}

	public static void main (String[] args) {
		
		beSessionMember();
//		doOperations();
	}
}
