package examples.gipc.counter.sessionport.twoparty.asymmetric;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import consensus.Accepted;
import consensus.Learned;
import consensus.Learner;
import consensus.multiparty.asymmetric.listener.AnAsymmetricMultiPartyLearnerConsensusMechanism;
import consensus.multiparty.asymmetric.listener.MultiPartyAsymmetricListenerConsensusMechanism;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyAcceptor;
import consensus.twoparty.asymmetric.TwoPartyAsymmetricAcceptor;
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
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class ATwoPartyAsymmetricAcceptorLauncher implements TwoPartyMember {
	protected static TwoPartyAsymmetricAcceptor<String> greetingMechanism;
	protected static TwoPartyAsymmetricAcceptor<Integer> meaningOfLifeMechanism;
	protected static Accepted<String> remoteGreetingMechanism;
	protected static Accepted<Integer> remoteMeaningOfLifeMechanism;
	protected static GIPCSessionRegistry gipcRegistry;
	protected static GroupRPCSessionPort groupRPCSessionPort;
	protected static Integer numMembersToWaitFor = 2;
	protected static SessionChoice sessionChoice = SessionChoice.P2P;
	
	static final int MY_PORT_NUMBER = 7002;
	static final String MY_NAME = "2";
	
	
	protected static void initGreetingConsensusMechanism(short anId) {
		remoteGreetingMechanism = (Accepted) gipcRegistry.lookupAllRemote(Accepted.class, GREETING_CONSENSUS_MECHANISM_NAME);

		greetingMechanism = new AnAsymmetricTwoPartyAcceptor(groupRPCSessionPort, GREETING_CONSENSUS_MECHANISM_NAME, anId, remoteGreetingMechanism);
		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());	
		greetingMechanism.addConsensusVetoer(new AConsensusVetoer<String>());
		gipcRegistry.rebind(GREETING_CONSENSUS_MECHANISM_NAME, greetingMechanism);
	}	
	protected static void initMeaningOfLifeConsensusMechanism(short anId) {
		remoteMeaningOfLifeMechanism = (Accepted) gipcRegistry.lookupAllRemote(Accepted.class, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME);
		meaningOfLifeMechanism = new AnAsymmetricTwoPartyAcceptor<>(groupRPCSessionPort, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, remoteMeaningOfLifeMechanism);
		meaningOfLifeMechanism.addConsensusListener(new AMeaningOfLifeConsensusListener());
		meaningOfLifeMechanism.addConsensusVetoer(new AConsensusVetoer<Integer>());

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
