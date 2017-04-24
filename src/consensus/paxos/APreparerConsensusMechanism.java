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
import consensus.synchronous.sequential.ASynchronousConsensusMechanism;

public class APreparerConsensusMechanism<StateType> 
	extends ACentralizableConsensusMechanism<StateType>
	implements Preparer<StateType>{

	protected boolean sendPrepardNumberIfNoAccept = true;

	protected float maxProposalNumberReceivedInPrepareRequest = -1;
//	protected float proposalNumberSentInLastPreparedNotification = -1;
//	protected float maxProposalNumberSentInSuccessfulPreparedNotification = -1;



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
//		   return maxProposalNumberSentInSuccessfulPreparedNotification > aProposalNumber;
		   return maxProposalNumberReceivedInPrepareRequest > aProposalNumber;

	}
	protected synchronized ProposalFeedbackKind checkAcceptRequest(float aProposalNumber, StateType aProposal ) {
		   return (isAcceptConcurrencyConflict(aProposalNumber, aProposal))?
			    ProposalFeedbackKind.CONCURRENCY_CONFLICT:
			 ProposalFeedbackKind.SUCCESS;
	 }

	
	
	
	@Override
	public void prepare(float aProposalNumber, StateType aProposal) {

		
		ProposalPrepareRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		ProposalFeedbackKind aFeedbackKind = checkPrepareRequest(aProposalNumber, aProposal);
		
		float aPreparedOrAcceptedProposalNumber = maxProposalNumberSentInSuccessfulAcceptedNotification ;
		StateType anAcceptedState = null;
		if (aPreparedOrAcceptedProposalNumber != -1) {			
			anAcceptedState = proposal(aPreparedOrAcceptedProposalNumber);
		} else if (sendPrepardNumberIfNoAccept()) {
			aPreparedOrAcceptedProposalNumber = maxProposalNumberReceivedInPrepareRequest;
		} 
		
		prepare(aPreparedOrAcceptedProposalNumber,
//				maxProposalNumberSentInSuccessfulAcceptedNotification,
//				maxProposalNumberSentInSuccessfulPreparedNotification, 
//				 proposal(maxProposalNumberSentInSuccessfulAcceptedNotification), 
				anAcceptedState,
				 aProposalNumber, 
				 aProposal,
				checkPrepareRequest(aProposalNumber, aProposal));		
	}
	
	protected void sendLearnNotificationToOthers(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind anAgreement) {
		
	}
	
	protected void prepare(float aLastPreparedOrAcceptedProposalNumber, StateType aLastAcceptedProposal, float aPreparedProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		recordReceivedPrepareRequest(aPreparedProposalNumber, aProposal);
		if (!isPending(aPreparedProposalNumber)) {
			return;
		}
		if (!isSuccess(aFeedbackKind)) {
			processPrepareRejection(aLastPreparedOrAcceptedProposalNumber, aLastAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		} else {
			recordAndSendPrepareResponse(aLastPreparedOrAcceptedProposalNumber, aLastAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
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
		maxProposalNumberReceivedInPrepareRequest =
				Math.max(maxProposalNumberReceivedInPrepareRequest, aPreparedProposalNumber );
//		if (isSuccess(aFeedbackKind)) {
//		maxProposalNumberSentInSuccessfulPreparedNotification =
//				Math.max(maxProposalNumberSentInSuccessfulPreparedNotification, aPreparedProposalNumber );
//		}
	}

	protected void recordAndSendPrepareResponse(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		recordSentPreparedNotification(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		ProposalPreparedNotificationSent.newCase(this, getObjectName(), anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		sendPrepareResponse(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
	}	
	protected void sendPrepareResponse(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		preparer().prepared(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
	}
	/*
	 * Cannot decide between sending last prepared number or not.
	 */
	protected StateType successProposedState(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
//		return anAcceptedProposal == null?proposal(aPreparedProposalNumber):anAcceptedProposal;
		return  proposal(anAcceptedProposalNumber);

	}

	protected Preparer<StateType> allListeners() {
		return (Preparer<StateType>) super.all();
	}
	
	
	protected void recordReceivedPrepareRequest(float aProposalNumber, StateType aProposal){
		recordProposalState(aProposalNumber, aProposal);
		maxProposalNumberReceivedInPrepareRequest = Math.max(
				maxProposalNumberReceivedInPrepareRequest, aProposalNumber);

	}

	protected boolean sendPrepardNumberIfNoAccept() {
		return sendPrepardNumberIfNoAccept;
	}
	
}
