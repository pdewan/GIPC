package consensus.multiparty.asymmetric;

import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationReceived;
import port.trace.consensus.ProposalLearnedNotificationSent;
import port.trace.consensus.ProposalPrepareNotificationReceived;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import port.trace.consensus.ProposalPreparedNotificationSent;
import inputport.ConnectionRegistrar;
import consensus.Accepted;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learned;
import consensus.ProposalState;
import consensus.multiparty.asymmetric.listener.AnAsymmetricMultiPartyLearnerConsensusMechanism;

public class AnAsymmetricMultiPartyAcceptor<StateType> 
	extends AnAbstractConsensusMechanism<StateType> 
	implements AsymmetricMultiPartyAcceptor<StateType>{
	Accepted<StateType> proposer;

	public AnAsymmetricMultiPartyAcceptor(
			ConnectionRegistrar anInputPort, String aName, short aMyId,
			Accepted<StateType> aProposer) {
		super(anInputPort, aName, aMyId);
		proposer = aProposer;
	}
	protected Accepted proposer() {
		return proposer;
	}
	

	@Override
	public void accept(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		addProposal(aProposalNumber, aProposal);
		boolean anAgreement = checkWithVetoers(aProposalNumber, aProposal);
		proposer().accepted(aProposalNumber, aProposal, anAgreement );
		ProposalAcceptedNotificationSent.newCase(this, getObjectName(), aProposalNumber, aProposal, anAgreement);
	}
	@Override
	public void learn(float aProposalNumber, StateType aState, boolean anAgreement) {
		if (anAgreement)
			newProposalState(aProposalNumber, aState, ProposalState.PROPOSAL_CONSENSUS);
		else
			newProposalState(aProposalNumber, aState, ProposalState.PROPOSAL_REJECTED);
		
	}

}
