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
import consensus.central.ACentralizableConsensusMechanism;
import consensus.synchronous.ASynchronousConsensusMechanism;

public class APreparerConsensusMechanism<StateType> 
	extends ACentralizableConsensusMechanism<StateType>
	implements Preparer<StateType>{

//	protected float maxPreparedProposalNumber;
//	protected StateType maxPreparedProposal;
	protected float maxProposalNumberReceivedInPrepareRequest = -1;
	protected float maxProposalNumberSentInPreparedNotification = -1;
	protected float maxProposalNumberSentInSuccessfulPreparedNotification = -1;

//	protected float maxReceivedAcceptedProposalNumber = -1;
//
//	protected StateType  lastAcceptedProposal = null;
//	protected float lastAcceptedProposalNumber = -1;
//	protected float lastPreparedProposalNumber = -1;

	public APreparerConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
	}
	protected boolean isAcceptConcurrencyConflict (float aProposalNumber, StateType aState )  {
		   return maxProposalNumberSentInSuccessfulPreparedNotification > aProposalNumber;
	}
	protected boolean isPrepareConcurrencyConflict (float aProposalNumber, StateType aState )  {
		   return isAcceptConcurrencyConflict(aProposalNumber, aState);
	}
	 protected synchronized ProposalFeedbackKind checkProposalForPrepare(float aProposalNumber, StateType aProposal ) {
		  return  isPrepareConcurrencyConflict(aProposalNumber, aProposal)?
			    ProposalFeedbackKind.CONCURRENCY_CONFLICT:
			 
				checkWithVetoer(aProposalNumber, aProposal);
	 }
	 protected synchronized ProposalFeedbackKind checkProposalForAccept(float aProposalNumber, StateType aProposal ) {
		   return (isAcceptConcurrencyConflict(aProposalNumber, aProposal))?
			    ProposalFeedbackKind.CONCURRENCY_CONFLICT:
			 ProposalFeedbackKind.SUCCESS;
	 }
//	protected StateType lastAcceptedProposal() {
//		return lastAcceptedProposal;
//	}
	
	protected float maxProposalNumberSentInSuccessfulPreparedNotification() {
		return maxProposalNumberSentInSuccessfulPreparedNotification;
	}
	@Override
	public void prepare(float aProposalNumber, StateType aProposal) {
//		float aLastAcceptedProposalNumber = maxProposalNumberSentInSuccessfulAcceptedNotification;
//		StateType aLastAcceptedProposal = proposal(aLastAcceptedProposalNumber);
		
		ProposalPrepareRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		prepare(maxProposalNumberSentInSuccessfulPreparedNotification, 
				 proposal(maxProposalNumberSentInSuccessfulAcceptedNotification), 
				 aProposalNumber, 
				 aProposal,
				checkProposalForAccept(aProposalNumber, aProposal));		
	}
	
	protected void prepare(float aLastAcceptedProposalNumber, StateType aLastAcceptedProposal, float aPreparedProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		recordReceivedPrepareRequest(aPreparedProposalNumber, aProposal);

		if (!isSuccess(aFeedbackKind)) {
			processPrepareRejection(aLastAcceptedProposalNumber, aLastAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		} else {
			recordAndSendPrepareResponse(aLastAcceptedProposalNumber, aLastAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		}
	}
	protected Prepared<StateType> preparer() {
		return (Prepared<StateType>) caller();
	}
//	protected void recordReceivedPreparedNotification(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
//		maxProposalNumberSentInPreparedNotification = 
//				Math.max(maxProposalNumberSentInPreparedNotification, 
//						aPreparedProposalNumber);
//		if (isSuccess(aFeedbackKind)) {
//			maxProposalNumberSentInSuccessfulPreparedNotification =
//					Math.max(maxProposalNumberSentInSuccessfulPreparedNotification, 
//							aPreparedProposalNumber);
//		}
//	}
	
	protected void processPrepareRejection(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		if (isSendRejectionNotification()) {
			recordAndSendPrepareResponse(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		}
	}
//	protected void recordReceivedPrepareResponse(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind){
//		maxReceivedPreparedNotificationProposalNumber = Math.max(maxReceivedPreparedNotificationProposalNumber, aProposalNumber);
//		incrementCount(aProposalNumber, ACCEPT_NOTIFICATION, 1);			
//
//		if (isSuccess(aFeedbackKind)) {
//			lastAcceptedProposal = aProposal;
//			lastAcceptedProposalNumber = Math.max(aProposalNumber, lastAcceptedProposalNumber);
//			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);			
//		}
//	}
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
//	protected void dispatchPropose(float aProposalNumber, StateType aProposal) {
//		if (isNonAtomic() || isServer()) {
//			localPropose(aProposalNumber, aProposal);
//		}  else {			
//			sendProposeRequest(aProposalNumber, aProposal);
//		}
//	}
	
	protected Preparer<StateType> allListeners() {
		return (Preparer<StateType>) super.all();
	}
	
//	protected void recordAndSendPrepareRequest (float aProposalNumber, StateType aProposal) {
//		
//	}
//    protected void recordPrepareRequest (float aProposalNumber, StateType aProposal) {
//	}
//    protected void sendPrepareRequest (float aProposalNumber, StateType aProposal) {
//		all().prepare(aProposalNumber, aProposal);
//	}	 
	 
	
//	protected void localPropose(float aProposalNumber, StateType aProposal) {	
//		if (isNonAtomic()) {
//			super.localPropose(aProposalNumber, aProposal);
//		}  else {			
//			recordAndSendPrepareRequest(aProposalNumber, aProposal);
//		}
////		if (isAsynchronousConsistency()) {
////			recordAndSendLearnNotification(aProposalNumber, aProposal, ProposalFeedbackKind.SUCCESS);
////		} else {
////			recordAndSendAcceptRequest(aProposalNumber, aProposal);
////		}
//}
//	@Override
//	public void prepared(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
//		if (!isPending(aPreparedProposalNumber)) {
//			return;
//		}
//		if (!isSuccess(aFeedbackKind)) {
//			newProposalState(aPreparedProposalNumber, proposal(aPreparedProposalNumber), toProposalState(aFeedbackKind));
//			return;
//		} 
//			
//	}
//	protected void recordSentPrepareNotification(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind){
//		maxProposalNumberSentInPreparedNotification = Math.max(
//				maxProposalNumberSentInPreparedNotification, aPreparedProposalNumber);
//			
//				
//		
////		maxReceivedAcceptedNotificationProposalNumber = Math.max(maxReceivedAcceptedNotificationProposalNumber, aProposalNumber);
////		incrementCount(aProposalNumber, ACCEPT_NOTIFICATION, 1);			
////
////		if (isSuccess(aFeedbackKind)) {
////			lastAcceptedProposal = aProposal;
////			lastAcceptedProposalNumber = Math.max(aProposalNumber, lastAcceptedProposalNumber);
////			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);			
////		}
//	}
	protected void recordReceivedPrepareRequest(float aProposalNumber, StateType aProposal){
		recordProposalState(aProposalNumber, aProposal);
		maxProposalNumberReceivedInPrepareRequest = Math.max(
				maxProposalNumberReceivedInPrepareRequest, aProposalNumber);
//		maxReceivedAcceptedNotificationProposalNumber = Math.max(maxReceivedAcceptedNotificationProposalNumber, aProposalNumber);
//		incrementCount(aProposalNumber, ACCEPT_NOTIFICATION, 1);			
//
//		if (isSuccess(aFeedbackKind)) {
//			lastAcceptedProposal = aProposal;
//			lastAcceptedProposalNumber = Math.max(aProposalNumber, lastAcceptedProposalNumber);
//			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);			
//		}
	}
//	
//	protected float maxPreparedProposalNumber() {
//		return maxPreparedProposalNumber;
//	}
//	protected StateType maxPreparedProposal() {
//		return maxPreparedProposal;
//	}
//	protected float lastAcceptedProposalNumber() {
//		return lastAcceptedProposalNumber;
//	}

	
}
