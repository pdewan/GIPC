package consensus.twoparty.asymmetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationtSent;
import port.trace.consensus.ProposalLearnedNotificationReceived;
import inputport.ConnectionRegistrar;
import inputport.ConnectionType;
import consensus.AnAbstractConsensusMechanism;
import consensus.ConsensusListener;
import consensus.Learner;
import consensus.ProposalState;
import consensus.ConsensusState;

public class AnAsymmetricTwoPartyProposerConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements TwoPartyAsymmetricProposerConsensusMechanism<StateType> {
	protected Learner<StateType> learner;

	public AnAsymmetricTwoPartyProposerConsensusMechanism(ConnectionRegistrar anInputPort, String aName, short aMyId,
			Learner<StateType> aPeerProxy) {
		super(anInputPort, aName, aMyId);
		learner = aPeerProxy;
	}
	protected Learner<StateType> learner() {
		return learner;
	}
//	@Override
//	protected boolean allowConcurrentProposals() {
//		return false;
//	}
	protected void sendLearnNotification(float aProposalNumber, StateType aProposal) {
		ProposalLearnNotificationtSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		learner.learn(aProposalNumber, aProposal);		
	}
	@Override
	protected void propose(float aProposalNumber, StateType aProposal) {
		sendLearnNotification(aProposalNumber, aProposal);		
	}
	@Override
	public void learned(float aProposalNumber, StateType aProposal) {
		ProposalLearnedNotificationReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);			
	}

}
