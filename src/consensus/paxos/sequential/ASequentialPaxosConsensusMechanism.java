package consensus.paxos.sequential;

import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import bus.uigen.widgets.universal.CentralUniversalWidget;
import sessionport.rpc.group.GIPCSessionRegistry;
import sun.security.util.PendingException;
import util.trace.port.consensus.ProposalPreparedNotificationReceived;
import util.trace.port.consensus.ProposalPreparedNotificationSent;
import util.trace.port.consensus.RemoteProposeRequestReceived;
import util.trace.port.consensus.RemoteProposeRequestSent;
import consensus.ConsensusMechanism;
import consensus.ProposalFeedbackKind;
import consensus.ProposalState;
import consensus.ReplicationSynchrony;
import consensus.central.ACentralizableConsensusMechanism;
import consensus.paxos.APaxosConsensusMechanism;
import consensus.synchronous.sequential.ASynchronousConsensusMechanism;

public class ASequentialPaxosConsensusMechanism<StateType> extends
		APaxosConsensusMechanism<StateType> {

	

	public ASequentialPaxosConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
	}
	@Override
	protected StateType preparedProposal(float aPreparedProposalNumber) {
		if (isSequentialAccess()) {
			return proposal(aPreparedProposalNumber);
		}
		return super.preparedProposal(aPreparedProposalNumber);
	}
	

}
