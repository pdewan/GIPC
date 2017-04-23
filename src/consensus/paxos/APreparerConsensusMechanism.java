package consensus.paxos;

import port.trace.consensus.ProposalPrepareRequestReceived;
import port.trace.consensus.ProposalPreparedNotificationSent;
import port.trace.consensus.RemoteProposeRequestReceived;
import port.trace.consensus.RemoteProposeRequestSent;
import bus.uigen.widgets.universal.CentralUniversalWidget;
import sessionport.rpc.group.GIPCSessionRegistry;
import sun.security.util.PendingException;
import consensus.ConsensusMechanism;
import consensus.ProposalFeedbackKind;
import consensus.ProposalState;
import consensus.ReplicationSynchrony;
import consensus.central.ACentralizableConsensusMechanism;
import consensus.synchronous.ASynchronousConsensusMechanism;

public class APreparerConsensusMechanism<StateType> 
	extends ACentralizableConsensusMechanism<StateType>
	implements Preparer<StateType>{


	protected float maxProposalNumberReceivedInPrepareRequest = -1;
	protected float maxProposalNumberSentInPreparedNotification = -1;
	protected float maxProposalNumberSentInSuccessfulPreparedNotification = -1;



	public APreparerConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
		setPrepareSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);
		setAcceptSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);
	}
	
	protected boolean isPrepareConcurrencyConflict (float aProposalNumber, StateType aState )  {
		   return isAcceptConcurrencyConflict(aProposalNumber, aState);
	}
	protected synchronized ProposalFeedbackKind checkPrepareRequest(float aProposalNumber, StateType aProposal ) {
		  return  isPrepareConcurrencyConflict(aProposalNumber, aProposal)?
			    ProposalFeedbackKind.CONCURRENCY_CONFLICT:			 
				checkWithVetoer(aProposalNumber, aProposal);
	 }
	protected boolean isAcceptConcurrencyConflict (float aProposalNumber, StateType aState )  {
		   return maxProposalNumberSentInSuccessfulPreparedNotification > aProposalNumber;
	}
	protected synchronized ProposalFeedbackKind checkAcceptRequest(float aProposalNumber, StateType aProposal ) {
		   return (isAcceptConcurrencyConflict(aProposalNumber, aProposal))?
			    ProposalFeedbackKind.CONCURRENCY_CONFLICT:
			 ProposalFeedbackKind.SUCCESS;
	 }

	protected float maxProposalNumberSentInSuccessfulPreparedNotification() {
		return maxProposalNumberSentInSuccessfulPreparedNotification;
	}
	@Override
	public void prepare(float aProposalNumber, StateType aProposal) {

		
		ProposalPrepareRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		prepare(maxProposalNumberSentInSuccessfulAcceptedNotification,
//				maxProposalNumberSentInSuccessfulPreparedNotification, 
				 proposal(maxProposalNumberSentInSuccessfulAcceptedNotification), 
				 aProposalNumber, 
				 aProposal,
				checkPrepareRequest(aProposalNumber, aProposal));		
	}
	
	protected void sendLearnNotificationToOthers(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind anAgreement) {
		
	}
	
	protected void prepare(float aLastAcceptedProposalNumber, StateType aLastAcceptedProposal, float aPreparedProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		recordReceivedPrepareRequest(aPreparedProposalNumber, aProposal);
		if (!isPending(aPreparedProposalNumber)) {
			return;
		}
		if (!isSuccess(aFeedbackKind)) {
			processPrepareRejection(aLastAcceptedProposalNumber, aLastAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		} else {
			recordAndSendPrepareResponse(aLastAcceptedProposalNumber, aLastAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		}
	}
	protected Prepared<StateType> preparer() {
		return (Prepared<StateType>) caller();
	}

	
	protected void processPrepareRejection(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		if (isSendRejectionNotification()) {
			recordAndSendPrepareResponse(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		}
	}

	protected void recordSentPreparedNotification(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		maxProposalNumberSentInPreparedNotification =
				Math.max(maxProposalNumberSentInPreparedNotification, aPreparedProposalNumber );
		if (isSuccess(aFeedbackKind)) {
		maxProposalNumberSentInSuccessfulPreparedNotification =
				Math.max(maxProposalNumberSentInSuccessfulPreparedNotification, aPreparedProposalNumber );
		}
	}

	protected void recordAndSendPrepareResponse(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		recordSentPreparedNotification(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		ProposalPreparedNotificationSent.newCase(this, getObjectName(), anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		sendPrepareResponse(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
	}	
	protected void sendPrepareResponse(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		preparer().prepared(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
	}
	protected StateType successProposedState(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		return anAcceptedProposal == null?proposal(aPreparedProposalNumber):anAcceptedProposal;
	}

	protected Preparer<StateType> allListeners() {
		return (Preparer<StateType>) super.all();
	}
	
	
	protected void recordReceivedPrepareRequest(float aProposalNumber, StateType aProposal){
		recordProposalState(aProposalNumber, aProposal);
		maxProposalNumberReceivedInPrepareRequest = Math.max(
				maxProposalNumberReceivedInPrepareRequest, aProposalNumber);

	}


	
}
