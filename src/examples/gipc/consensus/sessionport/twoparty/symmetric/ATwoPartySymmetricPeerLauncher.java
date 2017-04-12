package examples.gipc.consensus.sessionport.twoparty.symmetric;

import port.SessionChoice;
import sessionport.rpc.group.GIPCLocateSessionRegistry;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import consensus.twoparty.symmetric.ASymmetricTwoPartyPeer;
import consensus.twoparty.symmetric.SymmetricTwoPartyPeer;
import consensus.twoparty.symmetric.TwoPartySymmetricConsensusMechanism;
import examples.gipc.consensus.AGreetingConsensusListener;
import examples.gipc.consensus.AMeaningOfLifeConsensusListener;
import examples.gipc.consensus.ExampleMember;

public class ATwoPartySymmetricPeerLauncher implements ExampleMember{
	protected static TwoPartySymmetricConsensusMechanism<String> greetingMechanism;
	protected static TwoPartySymmetricConsensusMechanism<Integer> meaningOfLifeMechanism;
	protected static SymmetricTwoPartyPeer<String> remoteGreetingMechanism;
	protected static SymmetricTwoPartyPeer<Integer> remoteMeaningOfLifeMechanism;
	protected static GIPCSessionRegistry gipcRegistry;
	protected static GroupRPCSessionPort groupRPCSessionPort;
	protected static short numMembersToWaitFor = 2;
	protected static SessionChoice sessionChoice = SessionChoice.P2P;
	
	protected static void initGreetingConsensusMechanism(short anId) {
		remoteGreetingMechanism = (SymmetricTwoPartyPeer) gipcRegistry.lookupAllRemote(SymmetricTwoPartyPeer.class, GREETING_CONSENSUS_MECHANISM_NAME);
		greetingMechanism = new ASymmetricTwoPartyPeer<>(groupRPCSessionPort, GREETING_CONSENSUS_MECHANISM_NAME, anId, remoteGreetingMechanism);
		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());	
		gipcRegistry.rebind(GREETING_CONSENSUS_MECHANISM_NAME, greetingMechanism);
	}	
	protected static void initMeaningOfLifeConsensusMechanism(short anId) {
		remoteMeaningOfLifeMechanism = (SymmetricTwoPartyPeer) gipcRegistry.lookupAllRemote(SymmetricTwoPartyPeer.class, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME);
		meaningOfLifeMechanism = new ASymmetricTwoPartyPeer<>(groupRPCSessionPort, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, remoteMeaningOfLifeMechanism);
		meaningOfLifeMechanism.addConsensusListener(new AMeaningOfLifeConsensusListener());
		gipcRegistry.rebind(MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, meaningOfLifeMechanism);		
	}

	protected static void init(String aLocalName, int aPortNumber) {
		gipcRegistry = GIPCLocateSessionRegistry.createSessionRegistry(
				"mysession", "localhost", aPortNumber, aLocalName,
				sessionChoice, 
				numMembersToWaitFor);
		groupRPCSessionPort = gipcRegistry.getSessionPort();
		short anId = Short.parseShort(aLocalName);
		initGreetingConsensusMechanism(anId);
		initMeaningOfLifeConsensusMechanism(anId);		
	}


//	public static void doOperations(String aGreeting, int aMeaning) {
//		int aGreetingProposal = greetingMechanism.propose(aGreeting);
//		int aMeaningOfLifeProposal = meaningOfLifeMechanism.propose(aMeaning);
//		greetingMechanism.waitForConsensus(aGreetingProposal);
//		meaningOfLifeMechanism.waitForConsensus(aMeaningOfLifeProposal);		
//		
//	}
//	public static void beSessionMember(String aMemberName, int aMemberPort, String aGreeting, int aMeaning) {
//		ConsensusTraceUtility.setTracing();
//		init(aMemberName, aMemberPort);
////		initGreetingConsensusMechanism(aMemberName);
////		initMeaningOfLifeConsensusMechanism(aMemberName);
//		doOperations(aGreeting, aMeaning);
//	}
	public static void main(String[] args) {
		
//		addListeners();
//		sendByteBuffers();
//		sendObjects();
//		doOperations();
	}

}
