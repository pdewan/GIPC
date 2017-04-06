package consensus.twoparty.asymmetric;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalAcceptedNotificationReceived;
import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalLearnedNotificationReceived;
import consensus.Acceptor;
import consensus.AnAbstractConsensusMechanism;
import consensus.Acceptor;
import consensus.ProposalState;

public class AnAsymmetricTwoPartyProposer<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements TwoPartyAssymetricProposer<StateType> {
	protected Acceptor<StateType> acceptor;

	public AnAsymmetricTwoPartyProposer(ConnectionRegistrar anInputPort, String aName, short aMyId,
			Acceptor<StateType> aPeerProxy) {
		super(anInputPort, aName, aMyId);
		acceptor = aPeerProxy;
	}
	protected Acceptor<StateType> acceptor() {
		return acceptor;
	}
//	@Override
//	protected boolean allowConcurrentProposals() {
//		return false;
//	}
	protected void sendAcceptRequest(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		acceptor.accept(aProposalNumber, aProposal);		
	}
	@Override
	protected void propose(float aProposalNumber, StateType aProposal) {
		sendAcceptRequest(aProposalNumber, aProposal);		
	}
	@Override
	public void accepted(float aProposalNumber, StateType aProposal, boolean anAgreement) {
		ProposalAcceptedNotificationReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal, anAgreement);
		learn(aProposalNumber, aProposal, anAgreement);
//		if (anAgreement)
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);	
//		else
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);	

	}

}
