package consensus.twoparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalAcceptedSent;
import inputport.ConnectionRegistrar;
import inputport.ConnectionType;
import consensus.AnAbstractConsensusMechanism;
import consensus.ConsensusListener;
import consensus.ProposalState;
import consensus.ConsensusState;

public class ATwoPartyConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements
		TwoPartyConsensusMechanism<StateType> {
	RemoteTwoPartyPeer<StateType> peerProxy;

	public ATwoPartyConsensusMechanism(ConnectionRegistrar anInputPort, String aName, int aMyId,
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
	public synchronized void accepted(int aProposalNumber, StateType aProposal) {
		newProposalState(aProposalNumber, aProposal,
				ProposalState.PROPOSAL_CONSENSUS);
	}

	// public synchronized void rejected(int aProposalNumber, StateType
	// aProposal) {
	// newProposalState(aProposalNumber, aProposal,
	// ProposalState.PROPOSAL_SUPERSEDED);
	// }

	@Override
	protected void propose(int aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		peerProxy.accept(aProposalNumber, aProposal);
	}

	@Override
	public synchronized void accept(int aProposalNumber, StateType aProposal) {

		ProposalAcceptRequestReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		if (!myLastProposalIsPending()
				|| aProposalNumber > myLastProposalNumber) {
			peerProxy.accepted(aProposalNumber, aProposal);
			newProposalState(aProposalNumber, aProposal,
					ProposalState.PROPOSAL_CONSENSUS);
			newProposalState(getMyPendingProposalsBefore(aProposalNumber),
					ProposalState.PROPOSAL_SUPERSEDED);
//			removeProposalsLessThanOrEqualTo(aProposalNumber);

		} else {
			newProposalState(aProposalNumber, aProposal,
					ProposalState.PROPOSAL_SUPERSEDED);
		}
	}


}
