package consensus.paxos;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import port.trace.consensus.ProposalPreparedNotificationReceived;
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

public class APaxosConsensusMechanism<StateType> 
	extends APreparerConsensusMechanism<StateType>
	implements Prepared<StateType>{

//	protected float maxPreparedProposalNumber;
//	protected StateType maxPreparedProposal;
	protected float maxProposalNumberSentInPrepareRequest = -1;

	protected float maxProposalNumberReceivedInPreparedNotification;
	protected float maxProposalNumberReceivedInSuccessfulPreparedNotification = -1;
	
	protected float maxAcceptedProposalNumberReceivedInPreparedNotification;
//
//	protected StateType  lastAcceptedProposal = null;
//	protected float lastAcceptedProposalNumber = -1;
//	protected float lastPreparedProposalNumber = -1;

	public APaxosConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
	}
//	protected boolean isConcurrencyConflict (float aProposalNumber, StateType aState )  {
//		   return lastAcceptedProposalNumber() > aProposalNumber;
//	}
//	protected StateType lastAcceptedProposal() {
//		return lastAcceptedProposal;
//	}
	

//	@Override
//	public void prepare(float aProposalNumber, StateType aProposal) {
//		prepare(lastAcceptedProposalNumber(), lastAcceptedProposal(), aProposalNumber, 
//				checkProposal(aProposalNumber, aProposal));		
//	}
//	
//	protected void prepare(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
//		if (!isSuccess(aFeedbackKind)) {
//			processPrepareRejection(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
//		} else {
//			recordAndSendPrepareResponse(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
//		}
//	}
//	protected Prepared<StateType> caller() {
//		return (Prepared<StateType>) caller();
//	}
	protected void recordReceivedPreparedNotification(
			float anAcceptedProposalNumber, StateType anAcceptedProposal, 
			float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		maxProposalNumberReceivedInPreparedNotification = Math.max(
				maxProposalNumberReceivedInPreparedNotification, aPreparedProposalNumber);
		maxAcceptedProposalNumberReceivedInPreparedNotification = Math.max(
				maxAcceptedProposalNumberReceivedInPreparedNotification,anAcceptedProposalNumber );
		incrementCount(aPreparedProposalNumber, PREPARE_NOTIFICATION, 1);

//		if (isSuccess(aFeedbackKind)) {
//			maxProposalNumberReceivedInSuccessfulPreparedNotification =
//					Math.max(maxProposalNumberReceivedInSuccessfulPreparedNotification, aPreparedProposalNumber);
//			incrementCount(aPreparedProposalNumber, anAttribute, anIncrement)
//		}
				
//		caller().prepared(anAcceptedProposalNumber, anAcceptedProposal, anAcceptedProposalNumber, aFeedbackKind);
	}
	private Boolean sufficientPeparers(ReplicationSynchrony aReplicationSynchrony, 
			float aPreparedProposalNumber) {
		int aNumPrepareNotifications = getCount(aPreparedProposalNumber, PREPARE_NOTIFICATION);
		return sufficientAgreements(aReplicationSynchrony, aPreparedProposalNumber, proposal(aPreparedProposalNumber), 
				numConsensusMembers(), numCurrentMembers(), 
				aNumPrepareNotifications, 
				aNumPrepareNotifications);
	}
	protected StateType preparedState(float aPreparedProposalNumber) {
		float aChosenProposalNumber = 
				maxAcceptedProposalNumberReceivedInPreparedNotification <= 0?
						aPreparedProposalNumber:maxAcceptedProposalNumberReceivedInPreparedNotification	;
		return proposal(aChosenProposalNumber);
	}
	protected void aggregatePreparedNotification (
			float anAcceptedProposalNumber, StateType anAcceptedProposal, 
			float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		Boolean isSufficientPreparers = sufficientPeparers(getPrepareSynchrony(), aPreparedProposalNumber);
		if (isSufficientPreparers == null)
			return;
		recordAndSendAcceptRequest(aPreparedProposalNumber, preparedState(aPreparedProposalNumber));		
	}
//	protected boolean isConcurrencyConflict (float aProposalNumber, StateType aProposal )  {
//		   return aProposalNumber < maxProposalNumberSentInPreparedNotification;
//	}
	
//	protected void processPrepareRejection(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
//		if (isSendRejectionNotification()) {
//			recordAndSendPrepareResponse(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
//		}
//	}
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
//	protected void recordAndSendPrepareResponse(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
//		recordReceivedPreparedNotification(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
//		if (!isSuccess(aFeedbackKind)) {
//			processPrepareRejection(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
//		}
//		sendPrepareResponse(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
//	}	
//	protected void sendPrepareResponse(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
//		caller().prepared(anAcceptedProposalNumber, anAcceptedProposal, anAcceptedProposalNumber, aFeedbackKind);
//	}
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
	
	protected ProposalState toProposalState(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		ProposalState result = toProposalState(aFeedbackKind);	
		if (result == ProposalState.PROPOSAL_CONSENSUS && aProposal != proposal(aProposalNumber)) { // we were overridden
			return ProposalState.PROPOSAL_CONCURRENT_OPERATION;
		}
		return result;
	}
	
	protected Preparer<StateType> all() {
		return (Preparer<StateType>) super.all();
	}
	
	protected void recordAndSendPrepareRequest (float aProposalNumber, StateType aProposal) {
		recordPrepareRequest(aProposalNumber, aProposal);
		sendPrepareRequest(aProposalNumber, aProposal);		
	}
    protected void recordPrepareRequest (float aProposalNumber, StateType aProposal) {
    	maxProposalNumberSentInPrepareRequest =
    			Math.max(maxProposalNumberSentInPrepareRequest, aProposalNumber);    	
	}
    protected void sendPrepareRequest (float aProposalNumber, StateType aProposal) {
		all().prepare(aProposalNumber, aProposal);
	}	
	protected void localPropose(float aProposalNumber, StateType aProposal) {	
		if (isNonAtomic()) {
			super.localPropose(aProposalNumber, aProposal);
		}  else {			
			recordAndSendPrepareRequest(aProposalNumber, aProposal);
		}
//		if (isAsynchronousConsistency()) {
//			recordAndSendLearnNotification(aProposalNumber, aProposal, ProposalFeedbackKind.SUCCESS);
//		} else {
//			recordAndSendAcceptRequest(aProposalNumber, aProposal);
//		}
}
//	protected StateType toAcceptState(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber) {
//		
//	}
	protected void sendAcceptedNotification(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind){
		sendAcceptedNotificationToLearners(aProposalNumber, aProposal, aFeedbackKind);
	}
	protected void sendAcceptedNotificationToLearners(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind){
		all().accepted(aProposalNumber, aProposal, aFeedbackKind);
	}
	
	@Override
	public void prepared(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		ProposalPreparedNotificationReceived.newCase(this, getObjectName(), anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);

		recordReceivedPreparedNotification(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		if (!isPending(aPreparedProposalNumber)) {
			return;
		}
		if (!isSuccess(aFeedbackKind)) {
			newProposalState(aPreparedProposalNumber, proposal(aPreparedProposalNumber), toProposalState(aPreparedProposalNumber, anAcceptedProposal, aFeedbackKind));
			return;
		} 
		aggregatePreparedNotification(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		
			
	}
//	protected void recordReceivedPrepareNotification(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind){
//		recordProposalState(anAcceptedProposalNumber, anAcceptedProposal);
//		ma
//		

//		maxReceivedAcceptedNotificationProposalNumber = Math.max(maxReceivedAcceptedNotificationProposalNumber, aProposalNumber);
//		incrementCount(aProposalNumber, ACCEPT_NOTIFICATION, 1);			
//
//		if (isSuccess(aFeedbackKind)) {
//			lastAcceptedProposal = aProposal;
//			lastAcceptedProposalNumber = Math.max(aProposalNumber, lastAcceptedProposalNumber);
//			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);			
//		}
//	}
	
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
