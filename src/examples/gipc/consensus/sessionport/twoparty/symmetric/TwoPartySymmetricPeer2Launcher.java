package examples.gipc.consensus.sessionport.twoparty.symmetric;

import port.trace.consensus.ConsensusTraceUtility;

public class TwoPartySymmetricPeer2Launcher extends ATwoPartySymmetricPeerLauncher{
	static final int MY_PORT_NUMBER = 7002;
	static final String MY_NAME = "2";
	public static final String GREETING_1 = "Ca Va";
	public static final String GREETING_2 = "Bonjour";
	public static int MEANING = 42;


	
	public static void doOperations() {
		float aGreetingProposal1 = greetingMechanism.propose(GREETING_1);
//		greetingMechanism.waitForStateChange(aGreetingProposal1);

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
	}
}
