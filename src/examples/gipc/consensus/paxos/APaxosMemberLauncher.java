package examples.gipc.consensus.paxos;

import consensus.ConcurrencyKind;
import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ProposalState;
import consensus.ReplicationSynchrony;
import consensus.central.ACentralizableConsensusMechanismFactory;
import consensus.paxos.APaxosConsensusMechanismFactory;
import consensus.synchronous.ASynchronousConsensusMechanism;
import consensus.synchronous.ASynchronousConsensusMechanismFactory;
import consensus.synchronous.Accepted;
import consensus.synchronous.Acceptor;
import examples.gipc.consensus.AMeaningConsensusProposerLauncher;
import examples.gipc.consensus.AnExampleGreetingMeaningConsensusProposerLauncher;

public class APaxosMemberLauncher extends AMeaningConsensusProposerLauncher {
	protected boolean overrideRetry = false;
//	protected boolean doNotRetry = true;

	public APaxosMemberLauncher(String aLocalName, int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	protected boolean retry(ProposalState aState) {

		return !overrideRetry &&  (aState == null | super.retry(aState)
				|| aState == ProposalState.PROPOSAL_AGGREGATE_DENIAL);
	}
	protected Long reProposeTime() {
		return overrideRetry?null:super.reProposeTime();
	}


	@Override
	protected ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory() {
		return new APaxosConsensusMechanismFactory();
	}


	@Override
	protected short numMembersToWaitFor() {
		return 3;
	}

	@Override
	protected void customizeConsensusMechanisms() {
		meaningOfLifeMechanism.setCentralized(false);
		meaningOfLifeMechanism.setConcurrencyKind(ConcurrencyKind.SERIALIZABLE);
		meaningOfLifeMechanism
				.setPrepareSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);
		meaningOfLifeMechanism
				.setAcceptSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);
		
	}

}
