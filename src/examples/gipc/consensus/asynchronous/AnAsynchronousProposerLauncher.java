package examples.gipc.consensus.asynchronous;

import consensus.ConsensusMechanismFactory;
import consensus.asynchronous.AnAsynchronousConsensusMechanismFactory;
import examples.gipc.consensus.AnExampleConsensusProposerLauncher;

public class AnAsynchronousProposerLauncher extends AnExampleConsensusProposerLauncher  {

//	protected static final int MY_PORT_NUMBER = 7001;
//	protected static final String MY_NAME = "1";

	
	public AnAsynchronousProposerLauncher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory() {
		return new AnAsynchronousConsensusMechanismFactory<>();
	}

	@Override
	protected ConsensusMechanismFactory<String> greetingConsensusMechanismFactory() {
		return new AnAsynchronousConsensusMechanismFactory<>();
	}
//	protected Object lookupMulticastProxy(Class aClass, String aName) {
//		return gipcRegistry.lookupAll(aClass, aName);
//	}
//	protected ConsensusMechanism<String> createLocalGreetingMechanism(short anId) {
//		return new AnAsynchronousConsensusMechanism(groupRPCSessionPort, GREETING_CONSENSUS_MECHANISM_NAME, anId, (Learner) receiversRemoteGreetingMechanism );
//	}
//	protected ConsensusMechanism<Integer> createLocalMeaningOfLifeMechanism(short anId) {
//		return new AnAsynchronousConsensusMechanism(groupRPCSessionPort, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, (Learner) receiversMeaningOfLifeMechanism);
//	}
//	@Override
//	protected Class remoteReceiverConsensusClass() {
//		return Learner.class;
//	}

	
	// no reply has to be sent back, so do not bother with this method
//	@Override
//	protected Class remoteCallerConsensusClass() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	

}
