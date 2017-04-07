package consensus.multiparty.asymmetric;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalAcceptedNotificationReceived;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalPrepareNotificationSent;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import consensus.Accepted;
import consensus.ProposalState;
import consensus.multiparty.asymmetric.listener.AnAsymmetricMultiPartyProposerConsensusMechanism;

public class AnAsymmetricMultiPartyProposer<StateType> 
	extends AnAsymmetricMultiPartyProposerConsensusMechanism<StateType> 
	implements Accepted<StateType>{
	protected int numLearners;
//	protected MultiPartyAcceptor<StateType> acceptors;
	public AnAsymmetricMultiPartyProposer(
			ConnectionRegistrar anInputPort, String aName, short aMyId,
			AsymmetricMultiPartyAcceptor<StateType> anAcceptors, short aNumLearners, boolean anEventualConsistency) {
		super(anInputPort, aName, aMyId, anAcceptors, anEventualConsistency);
		numLearners = aNumLearners;
	}
	protected AsymmetricMultiPartyAcceptor<StateType> acceptors() {
		return  (AsymmetricMultiPartyAcceptor<StateType>) learners;
	}
	protected void propose(float aProposalNumber, StateType aProposal) {
		
			sendAcceptRequest(aProposalNumber, aProposal);
	}
	protected void sendAcceptRequest(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		acceptors().accept(aProposalNumber, aProposal);		
	}
	@Override
	public void accepted(float aProposalNumber, StateType aProposal, boolean anAgreement) {
		ProposalAcceptedNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal, anAgreement);
		if (anAgreement) {
			incrementCount(aProposalNumber, ACCEPT_AGREEMENT, 1);
		} else {
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);
		      sendLearnNotification(aProposalNumber, aProposal, false);

		}
		if (!isPending(aProposalNumber)) {
			return;
		}	
		if (getCount(aProposalNumber, ACCEPT_AGREEMENT) == numLearners) {
	      sendLearnNotification(aProposalNumber, aProposal, true);
//		  newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		}

		}		
	}

	


