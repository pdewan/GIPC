package consensus.twoparty.asymmetric;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationtReceived;
import port.trace.consensus.ProposalLearnedNotificationtSent;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learned;
import consensus.Learner;
import consensus.ProposalState;

public class ATwoPartyAsymmetricLearnerConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements TwoPartyAsymmetricListenerConsensusMechanism<StateType> {
	Learned<StateType> proposer;
	public ATwoPartyAsymmetricLearnerConsensusMechanism(ConnectionRegistrar anInputPort, 
			String aName, short aMyId, Learned<StateType> aProposer
			) {
		super(anInputPort, aName, aMyId);
		proposer = aProposer;
	}

	@Override
	public void learn(float aProposalNumber, StateType aProposal) {
		ProposalLearnNotificationtReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		proposer.learned(aProposalNumber, aProposal);
		ProposalLearnedNotificationtSent.newCase(this, getObjectName(), aProposalNumber, aProposal);
		ProposalConsensusOccurred.newCase(this, getObjectName(), aProposalNumber, aProposal);
	}

	



	


}
