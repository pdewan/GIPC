package examples.gipc.consensus.paxos;

import consensus.ConcurrencyKind;
import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ProposalState;
import consensus.ReplicationSynchrony;
import consensus.central.ACentralizableConsensusMechanismFactory;
import consensus.paxos.APaxosConsensusMechanismFactory;
import consensus.paxos.sequential.ASequentialPaxosConsensusMechanismFactory;
import consensus.synchronous.sequential.ASynchronousConsensusMechanism;
import consensus.synchronous.sequential.ASynchronousConsensusMechanismFactory;
import consensus.synchronous.sequential.Accepted;
import consensus.synchronous.sequential.Acceptor;
import examples.gipc.consensus.AnExampleProposerLauncher;
import examples.gipc.consensus.AnExampleGreetingMeaningConsensusProposerLauncher;

public class APaxosMemberLauncher extends AnExampleProposerLauncher {
	protected boolean overrideRetry;

	public APaxosMemberLauncher(String aLocalName, int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	protected boolean retry(ProposalState aState) {

		return !overrideRetry && 
				(aState == null | super.retry(aState)
				| aState == ProposalState.PROPOSAL_AGGREGATE_DENIAL);
	}
	protected Long reProposeTime() {
		return overrideRetry?null:super.reProposeTime();
	}


	@Override
	protected ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory() {
		return new ASequentialPaxosConsensusMechanismFactory<>();
	}


	@Override
	protected short numMembersToWaitFor() {
		return 3;
	}
	
	protected void simulateNonAtomicAsynchronous() {
		meaningOfLifeMechanism.setAcceptSynchrony(ReplicationSynchrony.ASYNCHRONOUS);
		meaningOfLifeMechanism.setConcurrencyKind(ConcurrencyKind.NON_ATOMIC);
	}
	protected void simulateNonAtomicSynchronous() {
		meaningOfLifeMechanism.setAcceptSynchrony(ReplicationSynchrony.ALL_SYNCHRONOUS);
		meaningOfLifeMechanism.setConcurrencyKind(ConcurrencyKind.NON_ATOMIC);
	}
	protected void simulateCentralized() {
		meaningOfLifeMechanism.setCentralized(true);
	}
	protected void simulateCentralizedSynchronous() {
		simulateNonAtomicSynchronous();
		simulateCentralized();
	}
	protected void simulateCentralizedAsynchronous() {
		simulateNonAtomicAsynchronous();
		simulateCentralized();
	}
	protected void simulateBasicPaxos() {
		overrideRetry = true;
		meaningOfLifeMechanism.setCentralized(false);
		meaningOfLifeMechanism.setConcurrencyKind(ConcurrencyKind.SERIALIZABLE);
		meaningOfLifeMechanism
				.setPrepareSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);
		meaningOfLifeMechanism
				.setAcceptSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);
	}
	protected void simulateSequentialPaxos() {
		simulateBasicPaxos();
		meaningOfLifeMechanism.setSequentialAccess(true);
		overrideRetry = false;
	}	
	protected int meaning1() {
		return super.meaning1();
	}
	
	protected int meaning2() {
		return super.meaning2();
	}
	@Override
	protected void customizeConsensusMechanisms() {
//		simulateNonAtomicAsynchronous();
//		simulateNonAtomicSynchronous();
//		simulateCentralizedAsynchronous();
//		simulateCentralizedSynchronous();
//		simulateBasicPaxos();
		simulateSequentialPaxos();
		
	}

}
