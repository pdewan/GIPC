package examples.gipc.counter.sessionport.twopartyconsensus.asymmetric;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import consensus.twoparty.asymmetric.ATwoPartyAsymmetricLearnerConsensusMechanism;
import consensus.twoparty.asymmetric.TwoPartyListenerConsensusMechanism;
import consensus.twoparty.symmetric.ATwoPartySymmetricConsensusMechanism;
import consensus.twoparty.symmetric.RemoteTwoPartyPeer;
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
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class ATwoPartyConsensusSessionListener implements TwoPartyConsensusSessionMember {
	protected static TwoPartyListenerConsensusMechanism<String> greetingMechanism;
	protected static TwoPartyListenerConsensusMechanism<Integer> meaningOfLifeMechanism;

	protected static GIPCSessionRegistry gipcRegistry;
	protected static GroupRPCSessionPort groupRPCSessionPort;
	protected static Integer numMembersToWaitFor = 2;
	protected static SessionChoice sessionChoice = SessionChoice.P2P;
	
	static final int MY_PORT_NUMBER = 7002;
	static final String MY_NAME = "2";
	
	
	protected static void initGreetingConsensusMechanism(int anId) {
		greetingMechanism = new ATwoPartyAsymmetricLearnerConsensusMechanism<>(groupRPCSessionPort, GREETING_CONSENSUS_MECHANISM_NAME, anId);
		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());	
		gipcRegistry.rebind(GREETING_CONSENSUS_MECHANISM_NAME, greetingMechanism);
	}	
	protected static void initMeaningOfLifeConsensusMechanism(int anId) {
		meaningOfLifeMechanism = new ATwoPartyAsymmetricLearnerConsensusMechanism<>(groupRPCSessionPort, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId);
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


	public static void beListener() {
		ConsensusTraceUtility.setTracing();
		init(MY_NAME, MY_PORT_NUMBER);
	}

	public static void main (String[] args) {
		
		beListener();
	}

}
