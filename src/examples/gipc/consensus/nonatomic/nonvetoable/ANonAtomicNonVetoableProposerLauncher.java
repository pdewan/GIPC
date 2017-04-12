package examples.gipc.consensus.nonatomic.nonvetoable;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import com.sun.javafx.event.EventUtil;

import consensus.Acceptor;
import consensus.ConsensusMechanism;
import consensus.Learner;
import consensus.nonatomic.nonvetoable.ANonAtomicProposerAndLearnerMechanism;
import consensus.nonatomic.nonvetoable.LearnerMechanism;
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
import examples.gipc.consensus.AGreetingConsensusListener;
import examples.gipc.consensus.AMeaningOfLifeConsensusListener;
import examples.gipc.consensus.AnExampleConsensusProposerLauncher;
import examples.gipc.counter.layers.AMultiLayeServerReceiveListener;
import examples.gipc.counter.layers.AMultiLayerCounterServer;
import examples.gipc.counter.sessionport.CounterSessionMember;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class ANonAtomicNonVetoableProposerLauncher extends AnExampleConsensusProposerLauncher  {

//	protected static final int MY_PORT_NUMBER = 7001;
//	protected static final String MY_NAME = "1";

	
	public ANonAtomicNonVetoableProposerLauncher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
		// TODO Auto-generated constructor stub
	}
	protected Object lookupMulticastProxy(Class aClass, String aName) {
		return gipcRegistry.lookupAll(aClass, aName);
	}
//	protected boolean eventualConsistency() {
//		return true;
//	}
	protected ConsensusMechanism<String> createLocalGreetingMechanism(short anId) {
		return new ANonAtomicProposerAndLearnerMechanism(groupRPCSessionPort, GREETING_CONSENSUS_MECHANISM_NAME, anId, (Learner) receiversRemoteGreetingMechanism );
	}
	protected ConsensusMechanism<Integer> createLocalMeaningOfLifeMechanism(short anId) {
		return new ANonAtomicProposerAndLearnerMechanism(groupRPCSessionPort, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, (Learner) receiversMeaningOfLifeMechanism);
	}
	@Override
	protected Class remoteReceiverConsensusClass() {
		return Learner.class;
	}
//	@Override
//	protected Integer numMembersToWaitFor() {
//		return 3;
//	}
//	public static void main (String[] args) {
//		ConsensusTraceUtility.setTracing();		
//		(new AnAsymmeticMultiPartyNonVetoableProposerLauncher()).beProposer(MY_NAME, MY_PORT_NUMBER);;
//	}
	
	// no reply has to be sent back, so do not bother with this method
	@Override
	protected Class remoteCallerConsensusClass() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
