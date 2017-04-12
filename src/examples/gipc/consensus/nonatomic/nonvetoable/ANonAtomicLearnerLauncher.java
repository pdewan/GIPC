package examples.gipc.consensus.nonatomic.nonvetoable;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import consensus.Accepted;
import consensus.Acceptor;
import consensus.ConsensusMechanism;
import consensus.Learned;
import consensus.Learner;
import consensus.nonatomic.nonvetoable.ALearnerMechanism;
import consensus.nonatomic.nonvetoable.LearnerMechanism;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyAcceptor;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyProposer;
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
import examples.gipc.consensus.AGreetingConsensusListener;
import examples.gipc.consensus.AGreetingVetoer;
import examples.gipc.consensus.AMeaningOfLifeConsensusListener;
import examples.gipc.consensus.AnExampleConsensusMemberLauncher;
import examples.gipc.counter.layers.AMultiLayeServerReceiveListener;
import examples.gipc.counter.layers.AMultiLayerCounterServer;
import examples.gipc.counter.sessionport.CounterSessionMember;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class ANonAtomicLearnerLauncher extends
		AnExampleConsensusMemberLauncher {
	// protected static TwoPartyAsymmetricAcceptor<String> greetingMechanism;
	// protected static TwoPartyAsymmetricAcceptor<Integer>
	// meaningOfLifeMechanism;
	// protected static Accepted<String> remoteGreetingMechanism;
	// protected static Accepted<Integer> remoteMeaningOfLifeMechanism;
	// protected static GIPCSessionRegistry gipcRegistry;
	// protected static GroupRPCSessionPort groupRPCSessionPort;
	// protected static Integer numMembersToWaitFor = 2;
	// protected static SessionChoice sessionChoice = SessionChoice.P2P;

	public ANonAtomicLearnerLauncher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
		// TODO Auto-generated constructor stub
	}

	// static final int MY_PORT_NUMBER = 7002;
	// static final String MY_NAME = "2";
	@Override
	protected Object lookupMulticastProxy(Class aClass, String aName) {
		return Learned.class;
	}

	@Override
	protected ConsensusMechanism<String> createLocalGreetingMechanism(short anId) {
		return new ALearnerMechanism(groupRPCSessionPort,
				GREETING_CONSENSUS_MECHANISM_NAME, anId);
	}

	@Override
	protected ConsensusMechanism<Integer> createLocalMeaningOfLifeMechanism(
			short anId) {
		return new ALearnerMechanism(groupRPCSessionPort,
				MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId);
	}

	@Override
	protected Class remoteReceiverConsensusClass() {
		return null;
	}
	// no reply needed
	@Override
	protected Class remoteCallerConsensusClass() {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// protected void addListenersAndVetoersToLocalGreetingMechanism() {
	// super.addListenersAndVetoersToLocalGreetingMechanism();
	// greetingMechanism.addConsensusVetoer(new AGreetingVetoer<>());
	//
	// }
	// public static void main (String[] args) {
	// ConsensusTraceUtility.setTracing();
	// new AnAsymmetricMultiPartyLearnerLauncher().init(MY_NAME,
	// MY_PORT_NUMBER);
	//
	// }

}
