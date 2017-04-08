package examples.gipc.counter.sessionport.multiparty.asymmetric.listener;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import consensus.Acceptor;
import consensus.ConsensusMechanism;
import consensus.Learner;
import consensus.multiparty.asymmetric.AnAsymmetricMultiPartyProposer;
import consensus.multiparty.asymmetric.AsymmetricMultiPartyAcceptor;
import consensus.multiparty.asymmetric.listener.AnAsymmetricMultiPartyProposerConsensusMechanism;
import consensus.multiparty.asymmetric.listener.MultiPartyAsymmetricListenerConsensusMechanism;
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
import examples.gipc.counter.sessionport.twoparty.asymmetric.AGreetingConsensusListener;
import examples.gipc.counter.sessionport.twoparty.asymmetric.AMeaningOfLifeConsensusListener;
import examples.gipc.counter.sessionport.twoparty.asymmetric.ATwoPartyAsymmeticProposerLauncher;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class AMultiPartyNonVetoableAsymmeticProposerLauncher extends ATwoPartyAsymmeticProposerLauncher {
	

}
