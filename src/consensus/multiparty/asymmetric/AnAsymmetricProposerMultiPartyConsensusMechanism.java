package consensus.multiparty.asymmetric;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalPrepareNotificationSent;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import consensus.ProposalState;
import consensus.multiparty.listener.asymmetric.eventual.AnAsymmetricMultiPartyProposerEventualConsensusMechanism;

public class AnAsymmetricProposerMultiPartyConsensusMechanism<StateType> 
	extends AnAsymmetricMultiPartyProposerEventualConsensusMechanism<StateType> 
	implements ProposerOfMultiPartyAcceptor<StateType>{
	protected int numLearners;
//	protected MultiPartyAcceptor<StateType> acceptors;
	public AnAsymmetricProposerMultiPartyConsensusMechanism(
			ConnectionRegistrar anInputPort, String aName, short aMyId,
			MultiPartyAcceptor<StateType> anAcceptors, short aNumLearners) {
		super(anInputPort, aName, aMyId, anAcceptors);
		numLearners = aNumLearners;
	}
	protected MultiPartyAcceptor<StateType> acceptors() {
		return  (MultiPartyAcceptor<StateType>) learners;
	}
	protected void propose(float aProposalNumber, StateType aProposal) {
		ProposalPrepareNotificationSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
			
	}
	protected void sendAcceptRequest(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		acceptors().accept(aProposalNumber, aProposal);		
	}
	@Override
	public void accepted(float aProposalNumber, StateType aProposal, boolean anAgreement) {
		ProposalPreparedNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		if (anAgreement) {
			incrementCount(aProposalNumber, ACCEPT_AGREEMENT, 1);
		} else {
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);
		}
		if (isPending(aProposalNumber)) {
			return;
		}
		
	    sendLearnNotification(aProposalNumber, aProposal, true);
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);	

		}		
	}

	


