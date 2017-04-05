package examples.gipc.counter.sessionport.twopartyconsensus.symmetric;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import consensus.twoparty.symmetric.ASymmetricTwoPartyPeer;
import consensus.twoparty.symmetric.SymmetricTwoPartyPeer;
import consensus.twoparty.symmetric.TwoPartySymmetricConsensusMechanism;
import bus.uigen.visitors.AddListenersAdapterVisitor;
import inputport.rpc.GIPCRegistry;
import port.ATracingConnectionListener;
import port.SessionChoice;
import port.trace.consensus.ConsensusTraceUtility;
import sessionport.rpc.group.GIPCLocateSessionRegistry;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import examples.gipc.counter.layers.AMultiLayeServerReceiveListener;
import examples.gipc.counter.layers.AMultiLayerCounterServer;
import examples.gipc.counter.sessionport.CounterSessionMember;
import examples.gipc.counter.sessionport.twopartyconsensus.asymmetric.AGreetingConsensusListener;
import examples.gipc.counter.sessionport.twopartyconsensus.asymmetric.AMeaningOfLifeConsensusListener;
import examples.gipc.counter.sessionport.twopartyconsensus.asymmetric.TwoPartyMember;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class ATwoPartySymmetricPeerLauncher implements TwoPartyMember {
	protected static TwoPartySymmetricConsensusMechanism<String> greetingMechanism;
	protected static TwoPartySymmetricConsensusMechanism<Integer> meaningOfLifeMechanism;
	protected static SymmetricTwoPartyPeer<String> remoteGreetingMechanism;
	protected static SymmetricTwoPartyPeer<Integer> remoteMeaningOfLifeMechanism;
	protected static GIPCSessionRegistry gipcRegistry;
	protected static GroupRPCSessionPort groupRPCSessionPort;
	protected static Integer numMembersToWaitFor = 2;
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
