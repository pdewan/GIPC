package consensus.synchronous;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import inputport.datacomm.simplex.buffer.nio.AnAcceptCommand;
import inputport.rpc.duplex.MaybeProcessReturnValue;
import port.trace.consensus.SufficientAgreementsChecked;
import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalAcceptResponseReceived;
import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalPrepareRequestSent;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import consensus.ConsensusMemberSetKind;
import consensus.ProposalVetoer;
import consensus.ReplicationSynchrony;
import consensus.ProposalState;
import consensus.ProposalFeedbackKind;
import consensus.asynchronous.AnAsynchronousConsensusMechanism;

public class AnAcceptorConsensusMechanism<StateType> 
	extends AnAsynchronousConsensusMechanism<StateType> 
	implements Acceptor<StateType>{
//	protected int numAcceptors;
//	protected Accepted proposer;
//	protected float maxSentAcceptRequestProposalNumber = -1;
	protected float maxProposalNumberSentInAcceptedNotification = -1;
//	protected float maxReceivedAcceptedNotificationProposalNumber = -1;
	protected float maxProposalNumberReceivedInAcceptRequest = -1;	
	protected float maxProposalNumberSentInSuccessfulAcceptedNotification = -1;
//
//	protected StateType  lastAcceptedProposal = null;
//	protected float lastAcceptedProposalNumber = -1;
//	Accepted<StateType> proposer;
//	protected MultiPartyAcceptor<StateType> acceptors;
	public AnAcceptorConsensusMechanism(
			GIPCSessionRegistry aRegistry, String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
//		proposer = aProposer;

	}
//	protected Acceptor<StateType> acceptors() {
//		return  (Acceptor<StateType>) learners();
//	}
	protected Accepted proposer() {
		return (Accepted) caller();
	}
	protected void sendAcceptedNotificationToRequester(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind){
		proposer().accepted(aProposalNumber, aProposal, aFeedbackKind);
	}

	protected void sendAcceptedNotification(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind){
		sendAcceptedNotificationToRequester(aProposalNumber, aProposal, aFeedbackKind);
	}
//	protected void recordSentAcceptRequest(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind){
//		maxSentAcceptRequestProposalNumber = Math.max(maxSentAcceptRequestProposalNumber, aProposalNumber);
////		if (isAgreement(aFeedbackKind)) {
////			maxReceivedAccepProposalNumber = Math.max(maxSentAcceptProposalNumber, aProposalNumber);
////			lastAcceptedProposal = aProposal;
////		}
//	}
	protected void recordReceivedAcceptRequest(float aProposalNumber, StateType aProposal){
//		maxProposalNumberSentInAccepted = Math.max(maxProposalNumberSentInAccepted, aProposalNumber);
		maxProposalNumberReceivedInAcceptRequest = Math.max(maxProposalNumberReceivedInAcceptRequest,  aProposalNumber);
		
//		if (isAgreement(aFeedbackKind)) {
//			maxReceivedAcceptProposalNumber = Math.max(maxSentAcceptProposalNumber, aProposalNumber);
//			lastAcceptedProposal = aProposal;
//		}
	}
	protected void recordSentAcceptedNotification(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind){
		maxProposalNumberSentInAcceptedNotification = Math.max(maxProposalNumberSentInAcceptedNotification, aProposalNumber);
		if (isSuccess(aFeedbackKind)) {
			maxProposalNumberSentInSuccessfulAcceptedNotification = Math.max(maxProposalNumberSentInSuccessfulAcceptedNotification, aProposalNumber);
		}
//		if (isAgreement(aFeedbackKind)) {
//			maxReceivedAccepProposalNumber = Math.max(maxSentAcceptProposalNumber, aProposalNumber);
//			lastAcceptedProposal = aProposal;
//		}
	}
	protected void recordAndSendAcceptResponse(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind){
		recordSentAcceptedNotification(aProposalNumber, aProposal, aFeedbackKind);
		ProposalAcceptedNotificationSent.newCase(this, getObjectName(), aProposalNumber, aProposal, aFeedbackKind);

		sendAcceptedNotification(aProposalNumber, aProposal, aFeedbackKind);
	}
//	protected void recordReceivedAcceptResponse(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind){
////		maxReceivedAcceptedNotificationProposalNumber = Math.max(maxReceivedAcceptedNotificationProposalNumber, aProposalNumber);
//		incrementCount(aProposalNumber, ACCEPT_NOTIFICATION, 1);			
//
//		if (isSuccess(aFeedbackKind)) {
////			lastAcceptedProposal = aProposal;
////			lastAcceptedProposalNumber = Math.max(aProposalNumber, lastAcceptedProposalNumber);
//			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);			
//		}
//	}
	
//	protected float maxAcceptProposalNumber() {
//		return maxSentAcceptRequestProposalNumber;
//	}
//	protected float maxNonRejectionedProposalNumber() {
//		return maxReceivedAcceptRequestNumber;
//	}
//	protected StateType lastAcceptedProposal() {
//		return lastAcceptedProposal;
//	}
//	  protected boolean isConcurrencyConflict (float aProposalNumber, StateType aProposal )  {
//		   return false;
//	   }
	   protected synchronized ProposalFeedbackKind checkProposalForAccept(float aProposalNumber, StateType aProposal ) {
//		   if (isConcurrencyConflict(aProposalNumber, aProposal))
//			   return ProposalFeedbackKind.CONCURRENCY_CONFLICT;
//			return 
				return checkWithVetoer(aProposalNumber, aProposal);
	  }
	   protected synchronized ProposalFeedbackKind checkWithVetoer(float aProposalNumber, StateType aProposal ) {		
			for (ProposalVetoer<StateType> aConsensusRejectioner:consensusRejectioners){
				ProposalFeedbackKind aRejectionKind = aConsensusRejectioner.acceptProposal(aProposalNumber, aProposal);
				if (aRejectionKind != ProposalFeedbackKind.SUCCESS) {
					return aRejectionKind;
				}			
			}		
			return ProposalFeedbackKind.SUCCESS;
	   }
	 
	@Override
	public void accept(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		recordReceivedAcceptRequest(aProposalNumber, aProposal);
		if (!isPending(aProposalNumber) && !isSendAcceptReplyForResolvedProposal()) {			
				return;			
		}
		recordAndSendAcceptResponse(aProposalNumber, aProposal, checkProposalForAccept(aProposalNumber, aProposal));
//		ProposalFeedbackKind aFeedbackKind = checkProposal(aProposalNumber, aProposal);
//		recordSentAcceptedNotification(aProposalNumber, aProposal, aFeedbackKind);
//		sendAcceptedNotification(aProposalNumber, aProposal, aFeedbackKind);		
	}
	
//	protected void localPropose(float aProposalNumber, StateType aProposal) {	
//			if (isAsynchronousReplication()) {
//				recordAndSendLearnNotification(aProposalNumber, aProposal, ProposalFeedbackKind.SUCCESS);
//			} else {
//				recordAndSendAcceptRequest(aProposalNumber, aProposal);
//			}
//	}
//	
//    protected void recordAndSendAcceptRequest (float aProposalNumber, StateType aProposal){
//		recordAcceptRequest(aProposalNumber, aProposal);
//		sendAcceptRequest(aProposalNumber, aProposal);
//	}
//	protected void recordAcceptRequest (float aProposalNumber, StateType aProposal){
//		
//	}
//	protected void sendAcceptRequest(float aProposalNumber, StateType aProposal) {
//		ProposalAcceptRequestSent.newCase(this, getObjectName(),
//				aProposalNumber, aProposal);
//		acceptors().accept(aProposalNumber, aProposal);		
//	}
//	protected boolean customSufficientAgrements(float aProposalNumber, StateType aProposal,short aMaxAcceptors, short aCurrentAcceptors, int anAcceptNotifications, int anAgreements) {
//		return true;
//	}
//	protected Boolean synchronousAgreements(float aProposalNumber, StateType aProposal,short aMaxAcceptors, short aCurrentAcceptors, int anAcceptNotifications, int anAgreements) {
//		   return sufficientAgreements(aProposalNumber, aProposal, aMaxAcceptors, aCurrentAcceptors, aMaxAcceptors, anAcceptNotifications, anAgreements);
//
//	}
//	protected Boolean sufficientAgreements(float aProposalNumber, StateType aProposal, short aMaxAcceptors, short aCurrentAcceptors, float aRequiredAgreements, int anAcceptNotifications, int anAgreements) {
//		   Boolean retVal;
//		   if (anAgreements >= aRequiredAgreements ) {
//			   retVal = true;
//		   } else if (aCurrentAcceptors == anAcceptNotifications) {
//				retVal = false;
//			} else {
//				retVal = null;
//			}
//			SufficientAgreementsChecked.newCase(this, getObjectName(), aProposalNumber, aProposal, aMaxAcceptors, aCurrentAcceptors, aRequiredAgreements, anAcceptNotifications, anAgreements, retVal);
//			return retVal;
//	}
//	
//	protected Boolean majorityAgreements(float aProposalNumber, StateType aProposal,short aMaxAcceptors, short aCurrentAcceptors, int anAcceptNotifications, int anAgreements) {
//		   return sufficientAgreements(aProposalNumber, aProposal, aMaxAcceptors, aCurrentAcceptors, (float) (((float) aMaxAcceptors)/(2.0)), anAcceptNotifications, anAgreements);
//	}
//	protected Boolean oneAgreement(float aProposalNumber, StateType aProposal,short aMaxAcceptors, short aCurrentAcceptors, int anAcceptNotifications, int anAgreements) {
//		   return sufficientAgreements(aProposalNumber, aProposal, aMaxAcceptors, aCurrentAcceptors, 1, anAcceptNotifications, anAgreements);
//	}
//	protected Boolean asynchronousAgreements(float aProposalNumber, StateType aProposal,short aMaxAcceptors, short aCurrentAcceptors, int anAcceptNotifications, int anAgreements) {
//		   return true;
//	}
//
//	protected Boolean sufficientAgreements(float aProposalNumber, StateType aProposal, short aMaxAcceptors, short aCurrentAcceptors, int anAcceptNotifications, int anAgreements) {
//		
//		switch (getReplicationSynchrony()){
//		case ALL_SYNCHRONOUS:
//			return synchronousAgreements(aProposalNumber, aProposal,aMaxAcceptors, aCurrentAcceptors, anAcceptNotifications, anAgreements);
//		case MAJORITY_SYNCHRONOUS:
//			return majorityAgreements(aProposalNumber, aProposal,aMaxAcceptors, aCurrentAcceptors, anAcceptNotifications, anAgreements);
//		case ONE_SYNCHRONOUS:
//			return oneAgreement(aProposalNumber, aProposal,aMaxAcceptors, aCurrentAcceptors, anAcceptNotifications, anAgreements);
//		case ASYNCHRONOUS:
//			return asynchronousAgreements(aProposalNumber, aProposal,aMaxAcceptors, aCurrentAcceptors, anAcceptNotifications, anAgreements);
//		case CUSTOM_SYNCHRONOUS:
//			return customSufficientAgrements(aProposalNumber, aProposal, aMaxAcceptors, aCurrentAcceptors, anAcceptNotifications, anAgreements);
//		
//		}	
//		return false;
//	}
//	private Boolean sufficientAcceptors(float aProposalNumber, StateType aProposal) {
//		return sufficientAgreements(aProposalNumber, aProposal, numConsensusMembers(), numCurrentMembers(), getCount(aProposalNumber, ACCEPT_NOTIFICATION), getCount(aProposalNumber, ACCEPT_SUCCESS));
//	}
//
//
//	protected short numConsensusMembers() {
//		
//		switch (getConsensusMemberSetKind()) {
//		case INITIAL_MEMBERS: return numInitialMembers();
//		case MAXIMUM_MEMBERS: return numMaximumMembers();
//		case CURRENT_MEMBERS: return numCurrentMembers;
//		}
//		
//		return 0;
//	}
//	protected void recordAcceptedNotification(float aProposalNumber, 
//			StateType aProposal, 
//			ProposalFeedbackKind aFeedbackKind) {
//		recordNotificationCount(aProposalNumber, aProposal, aFeedbackKind, ACCEPT_SUCCESS);
////		if (isAgreement(aFeedbackKind)) {
////			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);
////		};
//	}
	
//	protected void recordNotificationCount(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind, String aNotificationKind) {
//		if (isSuccess(aFeedbackKind)) {
//			incrementCount(aProposalNumber, aNotificationKind, 1);
//		};
//	}
//	protected void processRejectionedProposal(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
//	      recordAndSendLearnNotification(aProposalNumber, aProposal, aFeedbackKind);
//	}
//	protected boolean heardFromAll() {
//		return false;
//	}
//	protected void processAcceptRejection(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
//		
//			if (isSendRejectionNotification()) {
//				 recordAndSendLearnNotification(aProposalNumber, aProposal, aFeedbackKind);
//			} else {
//				learn(aProposalNumber, aProposal, aFeedbackKind);
//			}
//		
//	}
//	protected boolean maybeProcessRejection(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
//		if (!isSuccess(aFeedbackKind)) {
//
////		if (isSynchronous() && !isSuccess(aFeedbackKind)) {
//			processAcceptRejection(aProposalNumber, aProposal, aFeedbackKind);
//			return true;
//		}
//		return false;
//	}
	
//	protected void aggregateAcceptReponse (float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
//		Boolean isSufficientAcceptors = sufficientAcceptors(aProposalNumber, aProposal);
//		if (isSufficientAcceptors == null)
//			return;
//		if (isSufficientAcceptors) {
//			recordAndSendLearnNotification(aProposalNumber, aProposal, ProposalFeedbackKind.SUCCESS);
////		  aFeedbackKind = ProposalFeedbackKind.SUCCESS;
////		  recordSentLearnNotification(aProposalNumber, aProposal, aFeedbackKind);
////	      sendLearnNotification(aProposalNumber, aProposal, aFeedbackKind);
//		} else {
//			processAcceptRejection(aProposalNumber, aProposal, ProposalFeedbackKind.NOT_ENOUGH_SUCCESSES);
//			
//		}		
//	}
//	@Override
//	public void accepted(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
//		ProposalAcceptResponseReceived.newCase(this, getObjectName(), aProposalNumber, aProposal, aFeedbackKind);
//		recordReceivedAcceptResponse(aProposalNumber, aProposal, aFeedbackKind);
//		if (!isPending(aProposalNumber)) {
//			return;
//		}
//		if (!isSuccess(aFeedbackKind)) {
//
//				processAcceptRejection(aProposalNumber, aProposal, aFeedbackKind);
//				return ;
//		}
////		if (maybeProcessRejection(aProposalNumber, aProposal, aFeedbackKind)) {
////			return;
////		}
////		if (isSynchronous() && !isAgreement(aFeedbackKind)) {
////			processProposalRejection(aProposalNumber, aProposal, aFeedbackKind);
////			return;
////		}		
////		Boolean isSufficientAcceptors = sufficientAcceptors(aProposalNumber, aProposal);
////		if (isSufficientAcceptors == null)
////			return;
////		if (isSufficientAcceptors) {
////		  aFeedbackKind = ProposalFeedbackKind.SUCCESS;
////		  recordSentLearnNotification(aProposalNumber, aProposal, aFeedbackKind);
////	      sendLearnNotification(aProposalNumber, aProposal, aFeedbackKind);
////		} else {
////			processProposalRejection(aProposalNumber, aProposal, ProposalFeedbackKind.NOT_ENOUGH_SUCCESSES);
////			
////		}	
//		aggregateAcceptReponse(aProposalNumber, aProposal, aFeedbackKind);
//	}
	
}

	


