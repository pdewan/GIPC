package consensus.twoparty.asymmetric;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationReceived;
import port.trace.consensus.ProposalLearnedNotificationSent;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learned;
import consensus.Learner;
import consensus.ProposalState;

public class AnAsymmetricTwoPartyLearnerConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements TwoPartyAsymmetricListenerConsensusMechanism<StateType> {
	protected Learned<StateType> proposer;
	public AnAsymmetricTwoPartyLearnerConsensusMechanism(ConnectionRegistrar anInputPort, 
			String aName, short aMyId, Learned<StateType> aProposer
			) {
		super(anInputPort, aName, aMyId);
		proposer = aProposer;
	}
	protected Learned<StateType> proposer() {
		return proposer;
	}

	@Override
	public void learn(float aProposalNumber, StateType aProposal) {
		ProposalLearnNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		proposer().learned(aProposalNumber, aProposal);
		ProposalLearnedNotificationSent.newCase(this, getObjectName(), aProposalNumber, aProposal);
		ProposalConsensusOccurred.newCase(this, getObjectName(), aProposalNumber, aProposal);
	}

	



	


}
