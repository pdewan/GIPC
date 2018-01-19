package consensus.synchronous.sequential;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import inputport.nio.manager.commands.classes.AnAcceptCommand;
import inputport.rpc.duplex.MaybeProcessReturnValue;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import util.trace.port.consensus.ProposalAcceptRequestReceived;
import util.trace.port.consensus.ProposalAcceptRequestSent;
import util.trace.port.consensus.ProposalAcceptedNotificationReceived;
import util.trace.port.consensus.ProposalAcceptedNotificationSent;
import util.trace.port.consensus.ProposalLearnNotificationSent;
import util.trace.port.consensus.ProposalPrepareRequestSent;
import util.trace.port.consensus.ProposalPreparedNotificationReceived;
import util.trace.port.consensus.SufficientAgreementsChecked;
import consensus.ConsensusMemberSetKind;
import consensus.ProposalVetoer;
import consensus.ReplicationSynchrony;
import consensus.ProposalState;
import consensus.ProposalFeedbackKind;
import consensus.asynchronous.sequential.AnAsynchronousConsensusMechanism;

public class AnAcceptorConsensusMechanism<StateType> extends
		AnAsynchronousConsensusMechanism<StateType> implements
		Acceptor<StateType> {

	protected float maxProposalNumberSentInAcceptedNotification = -1;
	protected float maxProposalNumberReceivedInAcceptRequest = -1;
	protected float maxProposalNumberSentInSuccessfulAcceptedNotification = -1;

	public AnAcceptorConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);

	}

	protected Accepted proposer() {
		return (Accepted) caller();
	}

	protected void sendAcceptedNotificationToRequester(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		proposer().accepted(aProposalNumber, aProposal, aFeedbackKind);
	}

	protected void sendAcceptedNotification(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		sendAcceptedNotificationToRequester(aProposalNumber, aProposal,
				aFeedbackKind);
	}

	protected void recordReceivedAcceptRequest(float aProposalNumber,
			StateType aProposal) {
		maxProposalNumberReceivedInAcceptRequest = Math.max(
				maxProposalNumberReceivedInAcceptRequest, aProposalNumber);
	}

	protected void recordSentAcceptedNotification(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		maxProposalNumberSentInAcceptedNotification = Math.max(
				maxProposalNumberSentInAcceptedNotification, aProposalNumber);
		if (isSuccess(aFeedbackKind)) {
			maxProposalNumberSentInSuccessfulAcceptedNotification = Math.max(
					maxProposalNumberSentInSuccessfulAcceptedNotification,
					aProposalNumber);
		}
	}

	protected void recordAndSendAcceptedNotification(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		recordSentAcceptedNotification(aProposalNumber, aProposal,
				aFeedbackKind);
		ProposalAcceptedNotificationSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal, aFeedbackKind);
		

		sendAcceptedNotification(aProposalNumber, aProposal, aFeedbackKind);
	}

	protected synchronized ProposalFeedbackKind checkAcceptRequest(
			float aProposalNumber, StateType aProposal) {
		return checkWithVetoer(aProposalNumber, aProposal);
	}

	protected synchronized ProposalFeedbackKind checkWithVetoer(
			float aProposalNumber, StateType aProposal) {
		for (ProposalVetoer<StateType> aConsensusVetoer : consensusVetoers) {
			ProposalFeedbackKind aVetoKind = aConsensusVetoer.acceptProposal(
					aProposalNumber, aProposal);
			if (aVetoKind != ProposalFeedbackKind.SUCCESS) {
				return aVetoKind;
			}
		}
		return ProposalFeedbackKind.SUCCESS;
	}

	@Override
	public void accept(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		ProposalFeedbackKind aFeedbackKind = checkAcceptRequest(aProposalNumber, aProposal);

		recordReceivedAcceptRequest(aProposalNumber, aProposal);
		if (!isPending(aProposalNumber)
				&& !isSendAcceptReplyForResolvedProposal()) {
			return;
		}
//		ProposalFeedbackKind aFeedbackKind = checkAcceptRequest(aProposalNumber, aProposal);
		
		if (!isSuccess(aFeedbackKind) && !isSendRejectionNotification()) {
			return;
		}
		recordAndSendAcceptedNotification(aProposalNumber, aProposal,
				aFeedbackKind);
	}

}
