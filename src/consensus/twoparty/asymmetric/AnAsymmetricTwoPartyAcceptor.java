package consensus.twoparty.asymmetric;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
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
import consensus.ProposalVetoKind;
import consensus.nonatomic.nonvetoable.ALearnerMechanism;

public class AnAsymmetricTwoPartyAcceptor<StateType> extends
		ALearnerMechanism<StateType> implements TwoPartyAsymmetricAcceptor<StateType> {
	protected Accepted<StateType> proposer;
	public AnAsymmetricTwoPartyAcceptor(InputPort anInputPort, 
			String aName, short aMyId, Accepted<StateType> aProposer
			) {
		super(anInputPort, aName, aMyId);
		proposer = aProposer;
	}
	protected Accepted<StateType> proposer() {
		return proposer;
	}

	@Override
	public void accept(float aProposalNumber, StateType aState) {
		ProposalAcceptRequestReceived.newCase(this, getObjectName(), aProposalNumber, aState);
		ProposalVetoKind anAgreement = checkWithVetoers(aProposalNumber, aState);
		proposer().accepted(aProposalNumber, aState, anAgreement);
		ProposalAcceptedNotificationSent.newCase(this, getObjectName(), aProposalNumber, aState, anAgreement);
		learn(aProposalNumber, aState, anAgreement);
//		if (anAcceptResult) {
//			newProposalState(aProposalNumber, aState, ProposalState.PROPOSAL_CONSENSUS);
//		} else {
//			newProposalState(aProposalNumber, aState, ProposalState.PROPOSAL_REJECTED);
//		}
	}

	



	


}
