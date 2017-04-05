package consensus.twoparty.asymmetric;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationReceived;
import port.trace.consensus.ProposalLearnedNotificationSent;
import consensus.Accepted;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learned;
import consensus.Learner;
import consensus.ProposalState;

public class AnAsymmetricTwoPartyAcceptorConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements TwoPartyAsymmetricAcceptorConsensusMechanism<StateType> {
	protected Accepted<StateType> proposer;
	public AnAsymmetricTwoPartyAcceptorConsensusMechanism(ConnectionRegistrar anInputPort, 
			String aName, short aMyId, Accepted<StateType> aProposer
			) {
		super(anInputPort, aName, aMyId);
		proposer = aProposer;
	}
	protected Accepted<StateType> proposer() {
		return proposer;
	}

	@Override
	public void accept(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		proposer().accepted(aProposalNumber, aProposal, true);
		ProposalAcceptedNotificationSent.newCase(this, getObjectName(), aProposalNumber, aProposal, true);
		ProposalConsensusOccurred.newCase(this, getObjectName(), aProposalNumber, aProposal);
	}

	



	


}
