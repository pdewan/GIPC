package examples.gipc.counter.sessionport.twopartyconsensus.asymmetric;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import consensus.Learned;
import consensus.Learner;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyLearnerConsensusMechanism;
import consensus.twoparty.asymmetric.TwoPartyAsymmetricListenerConsensusMechanism;
import consensus.twoparty.symmetric.ASymmetricTwoPartyConsensusMechanism;
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

public class ATwoPartyAsymmetricConsensusSessionListener implements TwoPartyConsensusSessionMember {
	protected static TwoPartyAsymmetricListenerConsensusMechanism<String> greetingMechanism;
	protected static TwoPartyAsymmetricListenerConsensusMechanism<Integer> meaningOfLifeMechanism;
	protected static Learned<String> remoteGreetingMechanism;
	protected static Learned<Integer> remoteMeaningOfLifeMechanism;
	protected static GIPCSessionRegistry gipcRegistry;
	protected static GroupRPCSessionPort groupRPCSessionPort;
	protected static Integer numMembersToWaitFor = 2;
	protected static SessionChoice sessionChoice = SessionChoice.P2P;
	
	static final int MY_PORT_NUMBER = 7002;
	static final String MY_NAME = "2";
	
	
	protected static void initGreetingConsensusMechanism(short anId) {
		remoteGreetingMechanism = (Learned) gipcRegistry.lookupAllRemoteProxy(GREETING_CONSENSUS_MECHANISM_NAME, Learned.class);

		greetingMechanism = new AnAsymmetricTwoPartyLearnerConsensusMechanism<>(groupRPCSessionPort, GREETING_CONSENSUS_MECHANISM_NAME, anId, remoteGreetingMechanism);
		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());	
		gipcRegistry.rebind(GREETING_CONSENSUS_MECHANISM_NAME, greetingMechanism);
	}	
	protected static void initMeaningOfLifeConsensusMechanism(short anId) {
		remoteMeaningOfLifeMechanism = (Learned) gipcRegistry.lookupAllRemoteProxy(MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, Learned.class);

		meaningOfLifeMechanism = new AnAsymmetricTwoPartyLearnerConsensusMechanism<>(groupRPCSessionPort, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, remoteMeaningOfLifeMechanism);
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


	public static void beListener() {
		ConsensusTraceUtility.setTracing();
		init(MY_NAME, MY_PORT_NUMBER);
	}

	public static void main (String[] args) {
		
		beListener();
	}

}
