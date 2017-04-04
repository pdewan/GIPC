package consensus.multiparty.multilisteners;

import port.trace.consensus.ProposalLearnNotificationtSent;
import port.trace.consensus.ProposalPrepareNotificationSent;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import inputport.ConnectionRegistrar;
import consensus.Learned;
import consensus.Learner;
import consensus.MultiPartyLearner;
import consensus.ProposalState;
import consensus.ProposerOfMultiPartyLearner;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyLearnerConsensusMechanism;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyProposerConsensusMechanism;

public class AnAsymmetricSingleProposerMultiPartyConsensusMechanism<StateType> 
	extends AnAsymmetricTwoPartyProposerConsensusMechanism<StateType> 
	implements ProposerOfMultiPartyLearner<StateType>{
	protected int numLearners;
	
	public AnAsymmetricSingleProposerMultiPartyConsensusMechanism(
			ConnectionRegistrar anInputPort, String aName, short aMyId,
			MultiPartyLearner<StateType> aLearners, short aNumLearners) {
		super(anInputPort, aName, aMyId, aLearners);
		numLearners = aNumLearners;
	}
	protected MultiPartyLearner<StateType> learner() {
		return (MultiPartyLearner<StateType>) learner;
	}
	protected void propose(float aProposalNumber, StateType aProposal) {
		ProposalPrepareNotificationSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		learner().prepare(aProposalNumber, aProposal);		
	}
	@Override
	public void prepared(float aProposalNumber, StateType aProposal, boolean anAgreement) {
		ProposalPreparedNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		if (anAgreement) {
			incrementCount(aProposalNumber, PREPARED_AGREEMENT, 1);
		}
		int aNumAgreements = getCount(aProposalNumber, PREPARED_AGREEMENT);
		if (aNumAgreements == numLearners) {
			sendLearnNotification(aProposalNumber, aProposal);
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);			

		}		
	}

	

}
