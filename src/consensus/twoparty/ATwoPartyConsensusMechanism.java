package consensus.twoparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inputport.ConnectionType;
import consensus.AnAbstractConsensusMechanism;
import consensus.ConsensusListener;
import consensus.ProposalState;
import consensus.ConsensusState;

public class ATwoPartyConsensusMechanism<StateType> extends AnAbstractConsensusMechanism<StateType>
	implements TwoPartyConsensusMechanism<StateType> {
	RemoteTwoPartyPeer<StateType> peerProxy;

	public ATwoPartyConsensusMechanism(int aMyId, RemoteTwoPartyPeer<StateType> aPeerProxy ) {
		super(aMyId);
		peerProxy = aPeerProxy;
	}

	@Override
	public synchronized void accepted(int aProposalNumber, StateType aProposal,
			boolean accepted) {
		if (accepted) {
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_ACCEPTED);
		} else {
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);
		}
//		notifyAll();
	}
	
	@Override
	protected void propose(int aProposalNumber, StateType aProposal) {
		peerProxy.accept(aProposalNumber, aProposal);
	}

	@Override
	public synchronized void accept(int aProposalNumber, StateType aProposal) {
		boolean retValue = false;
		if (!myLastProposalIsPending()) {
			retValue = true;
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_ACCEPTED);

		} else if (aProposalNumber > myLastProposalNumber) {
			retValue = true;
			newProposalState(myLastProposalNumber, myLastState, ProposalState.PROPOSAL_REJECTED);
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_ACCEPTED);
			notifyAll();			
		} else {
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);
			retValue = false;
		}		
		peerProxy.accepted(aProposalNumber, aProposal, retValue);		
	}

	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notConnected(String aRemoteEndName, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType aConnectionType) {		
		if (myLastProposalIsPending()) {
			newProposalState(myLastProposalNumber, myLastState, ProposalState.COMMUNICATION_FAILURE);			
		}		
	}	
}
