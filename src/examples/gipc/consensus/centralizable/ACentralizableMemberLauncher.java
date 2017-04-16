package examples.gipc.consensus.centralizable;

import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ReplicationSynchrony;
import consensus.central.ACentralizableConsensusMechanismFactory;
import consensus.synchronous.ASynchronousConsensusMechanism;
import consensus.synchronous.ASynchronousConsensusMechanismFactory;
import consensus.synchronous.Accepted;
import consensus.synchronous.Acceptor;
import examples.gipc.consensus.AnExampleConsensusProposerLauncher;

public class ACentralizableMemberLauncher extends AnExampleConsensusProposerLauncher  {

	public ACentralizableMemberLauncher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory() {
		return new ACentralizableConsensusMechanismFactory<>();
	}

	@Override
	protected ConsensusMechanismFactory<String> greetingConsensusMechanismFactory() {
		return new ACentralizableConsensusMechanismFactory<>();	
		}
//	protected  void initMeaningOfLifeConsensusMechanism(short anId) {
//		super.initMeaningOfLifeConsensusMechanism(anId);
////		meaningOfLifeMechanism.setReplicationSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);
//
//	}
	protected void customizeMeaningOfLifeConsensusMechanism(){
		meaningOfLifeMechanism.setCentralized(true);
		meaningOfLifeMechanism.setServerName("1");
		meaningOfLifeMechanism.setIsServer(false);
	}
	
	
//	protected Object lookupSessionProxy(Class aClass, String aName) {
//		return gipcRegistry.lookupAllRemote(aClass, aName);
//	}
	
//	protected ConsensusMechanism<String> createLocalGreetingMechanism(short anId) {
//		return new ASynchronouConsensusMechanism<String>(groupRPCSessionPort, 
//				GREETING_CONSENSUS_MECHANISM_NAME, anId, 
//				(Acceptor<String>) receiversRemoteGreetingMechanism,  (Accepted<String>) callerRemoteGreetingMechanism);
////		return new AnAsymmetricMultiPartyProposer<>(anInputPort, aName, aMyId, anAcceptors, aNumLearners)
//	}
//	protected ConsensusMechanism<Integer> createLocalMeaningOfLifeMechanism(short anId) {
////		return new AnAsymmetricMultiPartyProposerAndAcceptor(groupRPCSessionPort,  MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, (AsymmetricMultiPartyAcceptor) receiversMeaningOfLifeMechanism, (short) (numMembersToWaitFor() - 1));
//		return new ASynchronouConsensusMechanism<Integer>(groupRPCSessionPort, 
//				MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, 
//				(Acceptor<Integer>) receiversMeaningOfLifeMechanism, 
//				(Accepted<Integer>) callerMeaningOfLifeMechanism);
//
//	}
//	@Override
//	protected Class remoteReceiverConsensusClass() {
//		return Acceptor.class;
//	}
//
//
//
//	@Override
//	protected Class remoteCallerConsensusClass() {
//		return Accepted.class;
//	}
	
	
	

}
