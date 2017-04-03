package consensus.twoparty.symmetric;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import consensus.AnAbstractConsensusMechanism;
import consensus.ProposalState;

public class ATwoPartySymmetricConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements
		TwoPartySymmetricConsensusMechanism<StateType> {
	RemoteTwoPartyPeer<StateType> peerProxy;

	public ATwoPartySymmetricConsensusMechanism(ConnectionRegistrar anInputPort, String aName, short aMyId,
			RemoteTwoPartyPeer<StateType> aPeerProxy) {
		super(anInputPort, aName, aMyId);
		peerProxy = aPeerProxy;
	}

	// public void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy) {
	// peerProxy = aPeerProxy;
	// }

	// protected synchronized void accepted(int aProposalNumber, StateType
	// aProposal,
	// boolean accepted) {
	// if (accepted) {
	// newProposalState(aProposalNumber, aProposal,
	// ProposalState.PROPOSAL_ACCEPTED);
	// } else {
	// newProposalState(aProposalNumber, aProposal,
	// ProposalState.PROPOSAL_REJECTED);
	// }
	// // notifyAll();
	// }
	public synchronized void learn(float aProposalNumber, StateType aProposal) {
		newProposalState(aProposalNumber, aProposal,
				ProposalState.PROPOSAL_CONSENSUS);
	}

	// public synchronized void rejected(int aProposalNumber, StateType
	// aProposal) {
	// newProposalState(aProposalNumber, aProposal,
	// ProposalState.PROPOSAL_SUPERSEDED);
	// }

	@Override
	protected void propose(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		peerProxy.accept(aProposalNumber, aProposal);
	}

	@Override
	public synchronized void accept(float aProposalNumber, StateType aProposal) {

		ProposalAcceptRequestReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		if (!myLastProposalIsPending()
				|| aProposalNumber > myLastProposalNumber) {
			peerProxy.learn(aProposalNumber, aProposal);
			newProposalState(aProposalNumber, aProposal,
					ProposalState.PROPOSAL_CONSENSUS);
			supersedePendingProposalsBefore(aProposalNumber);
			removeProposalsLessThanOrEqualTo(aProposalNumber);
//			newProposalState(getPendingProposalsBefore(aProposalNumber),
//					ProposalState.PROPOSAL_SUPERSEDED);
//			removeProposalsLessThanOrEqualTo(aProposalNumber);

		} else {
			newProposalState(aProposalNumber, aProposal,
					ProposalState.PROPOSAL_SUPERSEDED);
		}
	}


}
