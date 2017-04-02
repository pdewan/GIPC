package examples.gipc.counter.sessionport.twopartyconsensus;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import consensus.twoparty.ATwoPartyConsensusMechanism;
import consensus.twoparty.RemoteTwoPartyPeer;
import consensus.twoparty.TwoPartyConsensusMechanism;
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
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class ATwoPartyConsensusSessionMember implements TwoPartyCounterSessionMember {
	protected static TwoPartyConsensusMechanism<String> greetingMechanism;
	protected static TwoPartyConsensusMechanism<Integer> meaningOfLifeMechanism;
	protected static RemoteTwoPartyPeer<String> remoteGreetingMechanism;
	protected static RemoteTwoPartyPeer<Integer> remoteMeaningOfLifeMechanism;
	protected static GIPCSessionRegistry gipcRegistry;
	protected static GroupRPCSessionPort groupRPCSessionPort;
	protected static Integer numMembersToWaitFor = 2;
	protected static SessionChoice sessionChoice = SessionChoice.P2P;
	
	protected static void initGreetingConsensusMechanism(int anId) {
		remoteGreetingMechanism = (RemoteTwoPartyPeer) gipcRegistry.lookupAllRemoteProxy(GREETING_CONSENSUS_MECHANISM_NAME, RemoteTwoPartyPeer.class);
		greetingMechanism = new ATwoPartyConsensusMechanism<>(groupRPCSessionPort, GREETING_CONSENSUS_MECHANISM_NAME, anId, remoteGreetingMechanism);
		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());	
		gipcRegistry.rebind(GREETING_CONSENSUS_MECHANISM_NAME, greetingMechanism);
	}	
	protected static void initMeaningOfLifeConsensusMechanism(int anId) {
		remoteMeaningOfLifeMechanism = (RemoteTwoPartyPeer) gipcRegistry.lookupAllRemoteProxy(MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, RemoteTwoPartyPeer.class);
		meaningOfLifeMechanism = new ATwoPartyConsensusMechanism<>(groupRPCSessionPort, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, remoteMeaningOfLifeMechanism);
		meaningOfLifeMechanism.addConsensusListener(new AMeaningOfLifeConsensusListener());
		gipcRegistry.rebind(MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, meaningOfLifeMechanism);		
	}

	protected static void init(String aLocalName, int aPortNumber) {
		gipcRegistry = GIPCLocateSessionRegistry.createSessionRegistry(
				"mysession", "localhost", aPortNumber, aLocalName,
				sessionChoice, 
				numMembersToWaitFor);
		groupRPCSessionPort = gipcRegistry.getSessionPort();
		int anId = Integer.parseInt(aLocalName);
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
