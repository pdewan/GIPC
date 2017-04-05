package consensus.multiparty.asymmetric;

import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationReceived;
import port.trace.consensus.ProposalLearnedNotificationSent;
import port.trace.consensus.ProposalPrepareNotificationReceived;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import port.trace.consensus.ProposalPreparedNotificationSent;
import inputport.ConnectionRegistrar;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learned;
import consensus.ProposalState;
import consensus.multiparty.listener.asymmetric.eventual.AnAsymmetricMultiPartyLearnerConsensusMechanism;

public class AnAsymmetricAcceptorMultiPartyConsensusMechanism<StateType> 
	extends AnAbstractConsensusMechanism<StateType> 
	implements MultiPartyAcceptor<StateType>{
	ProposerOfMultiPartyAcceptor<StateType> proposer;

	public AnAsymmetricAcceptorMultiPartyConsensusMechanism(
			ConnectionRegistrar anInputPort, String aName, short aMyId,
			ProposerOfMultiPartyAcceptor<StateType> aProposer) {
		super(anInputPort, aName, aMyId);
		proposer = aProposer;
	}
	protected ProposerOfMultiPartyAcceptor proposer() {
		return proposer;
	}
	

	@Override
	public void accept(float aProposalNumber, StateType aProposal) {
		ProposalPrepareNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		addProposal(aProposalNumber, aProposal);
		proposer().accepted(aProposalNumber, aProposal, true);
		ProposalAcceptedNotificationSent.newCase(this, getObjectName(), aProposalNumber, aProposal, true);
	}
	@Override
	public void learn(float aProposalNumber, StateType aState, boolean anAgreement) {
		if (anAgreement)
			newProposalState(aProposalNumber, aState, ProposalState.PROPOSAL_CONSENSUS);
		else
			newProposalState(aProposalNumber, aState, ProposalState.PROPOSAL_REJECTED);
		
	}

}
