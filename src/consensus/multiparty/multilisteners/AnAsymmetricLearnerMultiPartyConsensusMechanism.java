package consensus.multiparty.multilisteners;

import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationReceived;
import port.trace.consensus.ProposalLearnedNotificationSent;
import port.trace.consensus.ProposalPrepareNotificationReceived;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import port.trace.consensus.ProposalPreparedNotificationSent;
import inputport.ConnectionRegistrar;
import consensus.Learned;
import consensus.MultiPartyLearner;
import consensus.ProposalState;
import consensus.ProposerOfMultiPartyLearner;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyLearnerConsensusMechanism;

public class AnAsymmetricLearnerMultiPartyConsensusMechanism<StateType> 
	extends AnAsymmetricTwoPartyLearnerConsensusMechanism<StateType> 
	implements MultiPartyLearner<StateType>{

	public AnAsymmetricLearnerMultiPartyConsensusMechanism(
			ConnectionRegistrar anInputPort, String aName, short aMyId,
			ProposerOfMultiPartyLearner<StateType> aProposer) {
		super(anInputPort, aName, aMyId, aProposer);
	}
	protected ProposerOfMultiPartyLearner<StateType> proposer() {
		return (ProposerOfMultiPartyLearner) proposer;
	}

	@Override
	public void prepare(float aProposalNumber, StateType aProposal) {
		ProposalPrepareNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		addProposal(aProposalNumber, aProposal);
		proposer().prepared(aProposalNumber, aProposal, true);
		ProposalPreparedNotificationSent.newCase(this, getObjectName(), aProposalNumber, aProposal);
	}

}
