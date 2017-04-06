package examples.gipc.counter.sessionport.multipartyconsensus.asymmetric;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import consensus.Acceptor;
import consensus.ConsensusMechanism;
import consensus.Learner;
import consensus.multiparty.asymmetric.AnAsymmetricMultiPartyProposer;
import consensus.multiparty.asymmetric.AsymmetricMultiPartyAcceptor;
import consensus.multiparty.listener.asymmetric.eventual.AnAsymmetricMultiPartyProposerEventualConsensusMechanism;
import consensus.multiparty.listener.asymmetric.eventual.MultiPartyAsymmetricListenerConsensusMechanism;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyProposer;
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
import examples.gipc.counter.sessionport.twopartyconsensus.asymmetric.ATwoPartyAsymmeticProposerLauncher;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class AMultiPartyAsymmeticProposerLauncher extends ATwoPartyAsymmeticProposerLauncher {
	protected static ConsensusMechanism<String> greetingMechanism;
	protected static ConsensusMechanism<Integer> meaningOfLifeMechanism;
	protected static AsymmetricMultiPartyAcceptor<String> remoteGreetingMechanism;
	protected static AsymmetricMultiPartyAcceptor<Integer> remoteMeaningOfLifeMechanism;
	
	protected static Integer numMembersToWaitFor = 3;
	
	static final int MY_PORT_NUMBER = 7001;
	static final String MY_NAME = "1";
	public static final String GREETING_1 = "Hello";
	public static final String GREETING_2 = "Howdy";
	public static int MEANING = 42;
	
	protected static void initGreetingConsensusMechanism(short anId) {
		remoteGreetingMechanism = (AsymmetricMultiPartyAcceptor) gipcRegistry.lookupAll(AsymmetricMultiPartyAcceptor.class, GREETING_CONSENSUS_MECHANISM_NAME);
		greetingMechanism = new AnAsymmetricMultiPartyProposer(groupRPCSessionPort, GREETING_CONSENSUS_MECHANISM_NAME, anId, remoteGreetingMechanism, (short) (numMembersToWaitFor -1));
		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());
		gipcRegistry.rebind(GREETING_CONSENSUS_MECHANISM_NAME, greetingMechanism);

	}	
	protected static void initMeaningOfLifeConsensusMechanism(short anId) {
		remoteMeaningOfLifeMechanism = (AsymmetricMultiPartyAcceptor) gipcRegistry.lookupAll(AsymmetricMultiPartyAcceptor.class, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME);
		meaningOfLifeMechanism = new AnAsymmetricMultiPartyProposer<>(groupRPCSessionPort, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, remoteMeaningOfLifeMechanism, (short) (numMembersToWaitFor -1));
		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());
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
//		gipcRegistry.connect();
	}



	public static void doOperations() {
		double aGreetingProposal1 = greetingMechanism.propose(GREETING_1);
		double aGreetingProposal2 = greetingMechanism.propose(GREETING_2);
		double aMeaningOfLifeProposal = meaningOfLifeMechanism.propose(MEANING);
		
	}
	public static void beProposer() {
		ConsensusTraceUtility.setTracing();
		init(MY_NAME, MY_PORT_NUMBER);
		doOperations();
	}

	public static void main (String[] args) {
		
		beProposer();
	}

}
