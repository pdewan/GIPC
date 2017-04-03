package consensus.twoparty.asymmetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalConsensusSent;
import port.trace.consensus.ProposalLearnNotificationtReceived;
import port.trace.consensus.ProposalLearnNotificationtSent;
import inputport.ConnectionRegistrar;
import inputport.ConnectionType;
import consensus.AnAbstractConsensusMechanism;
import consensus.ConsensusListener;
import consensus.ProposalState;
import consensus.ConsensusState;
import consensus.twoparty.symmetric.RemoteTwoPartyLearner;

public class ATwoPartyAsymmetricLearnerConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements TwoPartyListenerConsensusMechanism<StateType> {

	public ATwoPartyAsymmetricLearnerConsensusMechanism(ConnectionRegistrar anInputPort, String aName, int aMyId
			) {
		super(anInputPort, aName, aMyId);
	}

	@Override
	public Object learn(int aProposalNumber, StateType aProposal) {
		ProposalLearnNotificationtReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		ProposalConsensusSent.newCase(this, getObjectName(), aProposalNumber, aProposal);
		return 0; // message can fail but that means no one will use old value (proposer is disconnected)
	}

	



	


}
