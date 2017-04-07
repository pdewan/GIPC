package consensus.multiparty.asymmetric.listener;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalLearnNotificationSent;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learner;
import consensus.ProposalState;

public class AnAsymmetricMultiPartyProposerConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> {
	protected Learner<StateType> learners;

	public AnAsymmetricMultiPartyProposerConsensusMechanism(ConnectionRegistrar anInputPort, String aName, short aMyId,
			Learner<StateType> aPeerProxy, boolean anEventualConsistency) {
		super(anInputPort, aName, aMyId);
		learners = aPeerProxy;
		eventualConsistency = anEventualConsistency;
	}
	protected Learner<StateType> learners() {
		return learners;
	}
	
//	@Override
//	protected boolean allowConcurrentProposals() {
//		return false;
//	}
	protected void sendLearnNotification(float aProposalNumber, StateType aProposal, boolean anAgreement) {
		ProposalLearnNotificationSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal,anAgreement);		
		learners().learn(aProposalNumber, aProposal, anAgreement);		
		
	}
	@Override
	protected void propose(float aProposalNumber, StateType aProposal) {
		sendLearnNotification(aProposalNumber, aProposal, true);
		if (!eventualConsistency()) {
			waitForDisconnection();
		}
		learn(aProposalNumber, aProposal, true);
//		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);			

	}
	

}
