package examples.gipc.consensus.asynchronous;

import consensus.ConsensusMechanism;
import consensus.Learned;
import consensus.asynchronous.ALearnerMechanism;
import examples.gipc.consensus.AnExampleConsensusMemberLauncher;

public class AnAsynchronousLearnerLauncher extends
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

	public AnAsynchronousLearnerLauncher(String aLocalName,
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
	// protected void addListenersAndRejectionersToLocalGreetingMechanism() {
	// super.addListenersAndRejectionersToLocalGreetingMechanism();
	// greetingMechanism.addConsensusRejectioner(new AGreetingRejectioner<>());
	//
	// }
	// public static void main (String[] args) {
	// ConsensusTraceUtility.setTracing();
	// new AnAsymmetricMultiPartyLearnerLauncher().init(MY_NAME,
	// MY_PORT_NUMBER);
	//
	// }

}
