package consensus.twoparty.asymmetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalConsensusReceived;
import port.trace.consensus.ProposalConsensusSent;
import port.trace.consensus.ProposalLearnNotificationtSent;
import inputport.ConnectionRegistrar;
import inputport.ConnectionType;
import consensus.AnAbstractConsensusMechanism;
import consensus.ConsensusListener;
import consensus.ProposalState;
import consensus.ConsensusState;
import consensus.twoparty.symmetric.RemoteTwoPartyLearner;

public class ATwoPartyAsymmetricProposerConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType>  {
	RemoteTwoPartyLearner<StateType> peerProxy;

	public ATwoPartyAsymmetricProposerConsensusMechanism(ConnectionRegistrar anInputPort, String aName, int aMyId,
			RemoteTwoPartyLearner<StateType> aPeerProxy) {
		super(anInputPort, aName, aMyId);
		peerProxy = aPeerProxy;
	}
	@Override
	protected boolean allowConcurrentProposals() {
		return false;
	}
	@Override
	protected void propose(int aProposalNumber, StateType aProposal) {
		ProposalLearnNotificationtSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		peerProxy.learn(aProposalNumber, aProposal);
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		ProposalConsensusReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
	}

	


}
