package consensus.twoparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import port.trace.consensus.ProposalReceived;
import port.trace.consensus.ProposalSendInitiated;
import inputport.ConnectionType;
import consensus.AnAbstractConsensusMechanism;
import consensus.ConsensusListener;
import consensus.ProposalState;
import consensus.ConsensusState;

public class ATwoPartyConsensusMechanism<StateType> extends AnAbstractConsensusMechanism<StateType>
	implements TwoPartyConsensusMechanism<StateType> {
	RemoteTwoPartyPeer<StateType> peerProxy;

	public ATwoPartyConsensusMechanism(String aName, int aMyId, RemoteTwoPartyPeer<StateType> aPeerProxy) {
		super(aName, aMyId);
		peerProxy = aPeerProxy;
	}
	
//	public void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy) {
//		peerProxy = aPeerProxy;
//	}

//	protected synchronized void accepted(int aProposalNumber, StateType aProposal,
//			boolean accepted) {
//		if (accepted) {
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_ACCEPTED);
//		} else {
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);
//		}
////		notifyAll();
//	}
	public synchronized void accepted(int aProposalNumber, StateType aProposal) {
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_ACCEPTED);
	}
	public synchronized void rejected(int aProposalNumber, StateType aProposal) {
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);
	}
	
	
	@Override
	protected void propose(int aProposalNumber, StateType aProposal) {
		ProposalSendInitiated.newCase(this, getObjectName(), aProposalNumber, aProposal);
		peerProxy.accept(aProposalNumber, aProposal);
	}

	@Override
	public synchronized void accept(int aProposalNumber, StateType aProposal) {
		ProposalReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		if (!myLastProposalIsPending() || aProposalNumber > myLastProposalNumber) {
			peerProxy.accepted(aProposalNumber, aProposal);	
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_ACCEPTED);
			newProposalState(getMyPendingProposalsBefore(aProposalNumber), ProposalState.PROPOSAL_REJECTED);
			removeProposalsLessThanOrEqualTo(aProposalNumber);
		
		} else {
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);
		}
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
		newProposalState(getMyPendingProposals(), ProposalState.COMMUNICATION_FAILURE);			
	}	
}