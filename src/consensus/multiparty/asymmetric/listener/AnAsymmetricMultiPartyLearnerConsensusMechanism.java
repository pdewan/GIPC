package consensus.multiparty.asymmetric.listener;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationReceived;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalLearnedNotificationSent;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learned;
import consensus.Learner;
import consensus.ProposalState;

public class AnAsymmetricMultiPartyLearnerConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements MultiPartyAsymmetricListenerConsensusMechanism<StateType> {
	public AnAsymmetricMultiPartyLearnerConsensusMechanism(ConnectionRegistrar anInputPort, 
			String aName, short aMyId
			) {
		super(anInputPort, aName, aMyId);
	}
	

	@Override
	public void learn(float aProposalNumber, StateType aProposal, boolean anAgreement) {
		ProposalLearnNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal, anAgreement);
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		ProposalConsensusOccurred.newCase(this, getObjectName(), aProposalNumber, aProposal);
	}

	



	


}
