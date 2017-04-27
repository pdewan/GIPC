package examples.gipc.consensus.paxos;

import inputport.datacomm.group.GroupSendMessageForwarderSelector;
import consensus.ConcurrencyKind;
import consensus.ConsensusMechanismFactory;
import consensus.ProposalState;
import consensus.ReplicationSynchrony;
import consensus.paxos.sequential.ASequentialPaxosConsensusMechanismFactory;
import examples.gipc.consensus.AnExampleProposerLauncher;

public class APaxosMemberLauncher extends AnExampleProposerLauncher {
	protected boolean overrideRetry;

	public APaxosMemberLauncher(String aLocalName, int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	protected boolean retry(ProposalState aState) {

		return !overrideRetry && 
				(aState == null | super.retry(aState)
				|| aState == ProposalState.PROPOSAL_AGGREGATE_DENIAL);
	}
	protected Long reProposeTime() {
		return overrideRetry?null:super.reProposeTime();
	}
	@Override

	protected  void createConsensusMechanisms(short anId) {
		super.createConsensusMechanisms(anId);
	}
	protected void addListenersAndVetoersToConsensusMechanisms() {
		super.addListenersAndVetoersToConsensusMechanisms();
	}
	public void proposeValues() {
		super.proposeValues();
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
		meaningOfLifeMechanism.setSequentialAccess(false);
	}
	protected void simulateNonAtomicSynchronous() {
		meaningOfLifeMechanism.setAcceptSynchrony(ReplicationSynchrony.ALL_SYNCHRONOUS);
		meaningOfLifeMechanism.setConcurrencyKind(ConcurrencyKind.NON_ATOMIC);
		meaningOfLifeMechanism.setSequentialAccess(false);
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
		simulateBasicPaxos();
//		simulateSequentialPaxos();		
	}


}
