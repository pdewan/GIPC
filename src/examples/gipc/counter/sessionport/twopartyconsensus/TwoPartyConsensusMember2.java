package examples.gipc.counter.sessionport.twopartyconsensus;

import port.trace.consensus.ConsensusTraceUtility;

public class TwoPartyConsensusMember2 extends ATwoPartyConsensusSessionMember{
	static final int MY_PORT_NUMBER = 7002;
	static final String MY_NAME = "2";
	public static final String GREETING_1 = "Ca Va";
	public static final String GREETING_2 = "Bonjour";
	public static int MEANING = 42;


	
	public static void doOperations() {
		int aGreetingProposal1 = greetingMechanism.propose(GREETING_1);
		greetingMechanism.waitForStateChange(aGreetingProposal1);

		int aGreetingProposal2 = greetingMechanism.propose(GREETING_2);

		int aMeaningOfLifeProposal = meaningOfLifeMechanism.propose(MEANING);
		greetingMechanism.waitForStateChange(aGreetingProposal2);
		meaningOfLifeMechanism.waitForStateChange(aMeaningOfLifeProposal);		
		
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
