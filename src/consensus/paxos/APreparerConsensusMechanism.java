package consensus.paxos;

import bus.uigen.widgets.universal.CentralUniversalWidget;
import sessionport.rpc.group.GIPCSessionRegistry;
import sun.security.util.PendingException;
import trace.port.consensus.ProposalPrepareRequestReceived;
import trace.port.consensus.ProposalPreparedNotificationSent;
import trace.port.consensus.RemoteProposeRequestReceived;
import trace.port.consensus.RemoteProposeRequestSent;
import util.annotations.IsAtomicShape;
import consensus.ConsensusMechanism;
import consensus.ProposalFeedbackKind;
import consensus.ProposalState;
import consensus.ReplicationSynchrony;
import consensus.central.ACentralizableConsensusMechanism;
import consensus.synchronous.sequential.ASynchronousConsensusMechanism;
import consensus.synchronous.sequential.AnAcceptedMulticastRunnable;

public class APreparerConsensusMechanism<StateType> 
	extends ACentralizableConsensusMechanism<StateType>
	implements Preparer<StateType>{

	protected boolean sendPrepardNumberIfNoAccept = true;

	protected float maxProposalNumberReceivedInPrepareOrAcceptRequest = -1;
//	protected float proposalNumberSentInLastPreparedNotification = -1;
//	protected float maxProposalNumberSentInSuccessfulPreparedNotification = -1;



	public APreparerConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
		setPrepareSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);
		setAcceptSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);
	}
	
	protected boolean isAcceptConcurrencyConflict (float aProposalNumber, StateType aState )  {
		   return isPrepareConcurrencyConflict(aProposalNumber, aState);
	}
	protected synchronized ProposalFeedbackKind checkPrepareRequest(float aProposalNumber, StateType aProposal ) {
		  return  isPrepareConcurrencyConflict(aProposalNumber, aProposal)?
			    ProposalFeedbackKind.CONCURRENCY_CONFLICT:			 
				checkWithVetoer(aProposalNumber, aProposal);
	 }
	
	protected boolean isPrepareConcurrencyConflict (float aProposalNumber, StateType aState )  {
		   return maxProposalNumberReceivedInPrepareOrAcceptRequest > aProposalNumber;
	}
	protected boolean isNotPaxos() {
		return (!isSequentialAccess()) && 
				(
//						isAsynchronousReplication() || 
						isNonAtomic() || 
						isCentralizedPropose());
	}
	protected Preparer<StateType> preparers() {
		return (Preparer<StateType>) super.all();
	}
	protected void sendAcceptedNotification(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		if (isNotPaxos()) {
			super.sendAcceptedNotification(aProposalNumber, aProposal,
					aFeedbackKind);
			return;
		}
		sendAcceptedNotificationToLearners(aProposalNumber, aProposal,
				aFeedbackKind);
	}

	protected void sendAcceptedNotificationToLearners(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		if (!isAcceptedInSeparareThread()) {
			preparers().accepted(aProposalNumber, aProposal, aFeedbackKind);
		} else {
			Thread aThread = new Thread(
					new AnAcceptedMulticastRunnable<StateType>(preparers(),
							aProposalNumber, aProposal, aFeedbackKind));
			aThread.setName("Accepted thread " + aProposalNumber);
			aThread.start();
		}
	}
	protected synchronized ProposalFeedbackKind checkAcceptRequest(float aProposalNumber, StateType aProposal ) {
		if (isNotPaxos()) {
			return super.checkAcceptRequest(aProposalNumber, aProposal);			
		}		
		return (isAcceptConcurrencyConflict(aProposalNumber, aProposal))?
			    ProposalFeedbackKind.CONCURRENCY_CONFLICT:
			 ProposalFeedbackKind.SUCCESS;
	 }	
	
	@Override
	public void prepare(float aProposalNumber, StateType aProposal) {		
		ProposalPrepareRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
//		ProposalFeedbackKind aFeedbackKind = checkPrepareRequest(aProposalNumber, aProposal);		
		float aPreparedOrAcceptedProposalNumber = maxProposalNumberSentInSuccessfulAcceptedNotification ;
		StateType anAcceptedState = null;
		if (aPreparedOrAcceptedProposalNumber != -1) {			
			anAcceptedState = proposal(aPreparedOrAcceptedProposalNumber);
		} else if (sendPrepardNumberIfNoAccept()) {
			aPreparedOrAcceptedProposalNumber = maxProposalNumberReceivedInPrepareOrAcceptRequest;
		} 
		
		prepare(aPreparedOrAcceptedProposalNumber,
				anAcceptedState,
				 aProposalNumber, 
				 aProposal,
				checkPrepareRequest(aProposalNumber, aProposal));		
	}
	
	
	protected void sendLearnNotificationToOthers(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind anAgreement) {
		if (isNotPaxos()) {
			super.sendLearnNotificationToOthers(aProposalNumber, aProposal, anAgreement);
		}		
	}
	
	protected void prepare(float aLastPreparedOrAcceptedProposalNumber, StateType aLastAcceptedProposal, float aPreparedProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		recordReceivedPrepareRequest(aPreparedProposalNumber, aProposal);
		if (
				// we accepted this proposal before preparing for it
				aPreparedProposalNumber == aLastPreparedOrAcceptedProposalNumber
				// peparer has started the accept phase
				|| !isPending(aPreparedProposalNumber)) {
			return;			
		}		
		if (!isSuccess(aFeedbackKind)) {
			processPrepareRejection(aLastPreparedOrAcceptedProposalNumber, aLastAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		} else {
			recordAndSendPrepareResponse(aLastPreparedOrAcceptedProposalNumber, aLastAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		}
	}
	protected Prepared<StateType> caller() {
		return (Prepared<StateType>) super.caller();
	}

	
	protected void processPrepareRejection(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		if (isSendRejectionNotification()) {
			recordAndSendPrepareResponse(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		}
	}

	protected void recordSentPreparedNotification(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		maxProposalNumberReceivedInPrepareOrAcceptRequest =
				Math.max(maxProposalNumberReceivedInPrepareOrAcceptRequest, aPreparedProposalNumber );
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
		caller().prepared(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		// do not want to invoke caller in a separate thread as the name of caler can change
////		preparer().prepared(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
//		if (!isPreparedInSeparateThread()) {
//			caller().prepared(anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
//
//		} else {
//			Thread aThread = new Thread(
//					new APreparedMulticastRunnable<StateType>(caller(), 
//							anAcceptedProposalNumber, anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind));
//
//							
//			aThread.setName("Prepared Thread:" + aPreparedProposalNumber);
//			aThread.start();
//		}
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
		maxProposalNumberReceivedInPrepareOrAcceptRequest = Math.max(
				maxProposalNumberReceivedInPrepareOrAcceptRequest, aProposalNumber);

	}
	protected void recordReceivedAcceptRequest(float aProposalNumber,
			StateType aProposal) {
		super.recordReceivedAcceptRequest(aProposalNumber, aProposal);
		maxProposalNumberReceivedInPrepareOrAcceptRequest = Math.max(
				maxProposalNumberReceivedInPrepareOrAcceptRequest, aProposalNumber);
	}

	protected boolean sendPrepardNumberIfNoAccept() {
		return sendPrepardNumberIfNoAccept;
	}
	
}
