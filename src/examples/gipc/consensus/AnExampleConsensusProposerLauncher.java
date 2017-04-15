package examples.gipc.consensus;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import consensus.Acceptor;
import consensus.ConsensusMechanism;
import consensus.Learner;
import consensus.asynchronous.AnAsynchronousConsensusMechanism;
import consensus.asynchronous.LearnerMechanism;
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
import util.misc.ThreadSupport;
import examples.gipc.counter.layers.AMultiLayeServerReceiveListener;
import examples.gipc.counter.layers.AMultiLayerCounterServer;
import examples.gipc.counter.sessionport.CounterSessionMember;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public abstract class AnExampleConsensusProposerLauncher extends AnExampleConsensusMemberLauncher{
	
	public AnExampleConsensusProposerLauncher(String aLocalName, int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	public static final String GREETING_1 = "Hello";
	public static final String GREETING_2 = "Hi";
	public static int MEANING_1 = 42;
	public static int MEANING_2 = 29;
	public static long INIT_TIME = 6000;
	
	public  void proposeValues1() {
//		double aGreetingProposal1 = greetingMechanism.propose(GREETING_1);
		float aMeaningOfLifeProposal = meaningOfLifeMechanism.propose(MEANING_1);		
	}
	public  void proposeValues2() {
//		double aGreetingProposal1 = greetingMechanism.propose(GREETING_2);
		float aMeaningOfLifeProposal = meaningOfLifeMechanism.propose(MEANING_2);		
	}
	
	protected void doPropose() {
		proposeValues1();
	}

	public  void proposeValues() {
//		ThreadSupport.sleep(INIT_TIME);
		doPropose();
		
	}
	

	

}
