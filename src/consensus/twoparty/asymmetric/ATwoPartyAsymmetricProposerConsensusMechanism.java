package consensus.twoparty.asymmetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationtSent;
import port.trace.consensus.ProposalLearnedNotificationtReceived;
import inputport.ConnectionRegistrar;
import inputport.ConnectionType;
import consensus.AnAbstractConsensusMechanism;
import consensus.ConsensusListener;
import consensus.Learner;
import consensus.ProposalState;
import consensus.ConsensusState;

public class ATwoPartyAsymmetricProposerConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements TwoPartyAsymmetricProposerConsensusMechanism<StateType> {
	Learner<StateType> peerProxy;

	public ATwoPartyAsymmetricProposerConsensusMechanism(ConnectionRegistrar anInputPort, String aName, short aMyId,
			Learner<StateType> aPeerProxy) {
		super(anInputPort, aName, aMyId);
		peerProxy = aPeerProxy;
	}
	@Override
	protected boolean allowConcurrentProposals() {
		return false;
	}
	@Override
	protected void propose(float aProposalNumber, StateType aProposal) {
		ProposalLearnNotificationtSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		peerProxy.learn(aProposalNumber, aProposal);
		
	}
	@Override
	public void learned(float aProposalNumber, StateType aProposal) {
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		ProposalLearnedNotificationtReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		ProposalConsensusOccurred.newCase(this, getObjectName(),
				aProposalNumber, aProposal);		
	}

}
