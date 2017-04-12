package examples.gipc.consensus.nonatomic;

import consensus.Accepted;
import consensus.Acceptor;
import consensus.ConsensusMechanism;
import consensus.nonatomic.ANonAtomicProposerAndAcceptor;
import examples.gipc.consensus.AnExampleConsensusProposerLauncher;

public class ANonAtomicProposerAndAcceptorLauncher extends AnExampleConsensusProposerLauncher  {

	public ANonAtomicProposerAndAcceptorLauncher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
		// TODO Auto-generated constructor stub
	}

	
	
//	protected Object lookupSessionProxy(Class aClass, String aName) {
//		return gipcRegistry.lookupAllRemote(aClass, aName);
//	}
	
	protected ConsensusMechanism<String> createLocalGreetingMechanism(short anId) {
		return new ANonAtomicProposerAndAcceptor<String>(groupRPCSessionPort, 
				GREETING_CONSENSUS_MECHANISM_NAME, anId, 
				(Acceptor<String>) receiversRemoteGreetingMechanism,  (Accepted<String>) callerRemoteGreetingMechanism);
//		return new AnAsymmetricMultiPartyProposer<>(anInputPort, aName, aMyId, anAcceptors, aNumLearners)
	}
	protected ConsensusMechanism<Integer> createLocalMeaningOfLifeMechanism(short anId) {
//		return new AnAsymmetricMultiPartyProposerAndAcceptor(groupRPCSessionPort,  MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, (AsymmetricMultiPartyAcceptor) receiversMeaningOfLifeMechanism, (short) (numMembersToWaitFor() - 1));
		return new ANonAtomicProposerAndAcceptor<Integer>(groupRPCSessionPort, 
				MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, 
				(Acceptor<Integer>) receiversMeaningOfLifeMechanism, 
				(Accepted<Integer>) callerMeaningOfLifeMechanism);

	}
	@Override
	protected Class remoteReceiverConsensusClass() {
		return Acceptor.class;
	}



	@Override
	protected Class remoteCallerConsensusClass() {
		return Accepted.class;
	}
	
	
	

}
