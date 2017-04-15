package consensus.twoparty.asymmetric;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationReceived;
import port.trace.consensus.ProposalLearnedNotificationSent;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import consensus.Accepted;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learned;
import consensus.Learner;
import consensus.ProposalState;
import consensus.ProposalRejectionKind;
import consensus.asynchronous.ALearnerConsensusMechanism;

public class AnAsymmetricTwoPartyAcceptor<StateType> extends
		ALearnerConsensusMechanism<StateType> implements TwoPartyAsymmetricAcceptor<StateType> {
	protected Accepted<StateType> proposer;
	public AnAsymmetricTwoPartyAcceptor(GIPCSessionRegistry anInputPort, 
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
		ProposalRejectionKind anAgreement = checkWithVetoer(aProposalNumber, aState);
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
