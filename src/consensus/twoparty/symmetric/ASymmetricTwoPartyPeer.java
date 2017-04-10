package consensus.twoparty.symmetric;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalLearnNotificationSent;
import consensus.Acceptor;
import consensus.AnAbstractConsensusMechanism;
import consensus.ProposalState;
import consensus.ProposalVetoKind;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyProposer;

public class ASymmetricTwoPartyPeer<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements
		TwoPartySymmetricConsensusMechanism<StateType> {
	Acceptor<StateType> peer;

	public ASymmetricTwoPartyPeer(ConnectionRegistrar anInputPort, String aName, short aMyId,
			SymmetricTwoPartyPeer<StateType> aPeer) {
		super(anInputPort, aName, aMyId);
		peer = aPeer;
	}

	public synchronized void learn(float aProposalNumber, StateType aProposal) {
		newProposalState(aProposalNumber, aProposal,
				ProposalState.PROPOSAL_CONSENSUS);
	}
	protected void sendAcceptRequest(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		peer.accept(aProposalNumber, aProposal);		
	}
	@Override
	protected void propose(float aProposalNumber, StateType aProposal) {
		sendAcceptRequest(aProposalNumber, aProposal);
	}

	@Override
	public synchronized void accept(float aProposalNumber, StateType aProposal) {

		ProposalAcceptRequestReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		if (!myLastProposalIsPending()
				|| aProposalNumber > myLastProposalNumber) {
			peer.accept(aProposalNumber, aProposal);
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

	@Override
	public void learn(float aProposalNumber, StateType aProposal,
			ProposalVetoKind anAgreement) {
		// TODO Auto-generated method stub
		
	}


}
