package consensus.synchronous;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import inputport.datacomm.simplex.buffer.nio.AnAcceptCommand;
import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalAcceptedNotificationReceived;
import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalPrepareNotificationSent;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import sessionport.rpc.group.GroupRPCSessionPort;
import consensus.Accepted;
import consensus.Acceptor;
import consensus.ReplicationSynchrony;
import consensus.ProposalState;
import consensus.ProposalRejectionKind;
import consensus.asynchronous.AnAsynchronousConsensusMechanism;

public class ASynchronouConsensusMechanism<StateType> 
	extends AnAsynchronousConsensusMechanism<StateType> 
	implements Accepted<StateType>{
	protected int numAcceptors;
	protected Accepted proposer;
	protected float maxSentAcceptRequestProposalNumber = -1;
	protected float maxReceivedAcceptRequestNumber = -1;
	protected float maxReceivedAcceptedNotificationProposalNumber = -1;
	protected float maxSentAcceptedNotificationProposalNumber = -1;	

	protected StateType  lastAcceptedProposal = null;
	protected float lastAcceptedProposalNumber = -1;
//	Accepted<StateType> proposer;
//	protected MultiPartyAcceptor<StateType> acceptors;
	public ASynchronouConsensusMechanism(
			GroupRPCSessionPort anInputPort, String aName, short aMyId,
			Acceptor<StateType> anAcceptors, 
			Accepted<StateType> aProposer) {
		super(anInputPort, aName, aMyId, anAcceptors);
		proposer = aProposer;

	}
	protected Acceptor<StateType> acceptors() {
		return  (Acceptor<StateType>) learners;
	}
	protected Accepted proposer() {
		return (Accepted) proposer;
	}
	protected void sendAcceptedNotification(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind){
		proposer().accepted(aProposalNumber, aProposal, aRejectionKind );
		ProposalAcceptedNotificationSent.newCase(this, getObjectName(), aProposalNumber, aProposal, aRejectionKind);
	}
	protected void recordSentAcceptRequest(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind){
		maxSentAcceptRequestProposalNumber = Math.max(maxSentAcceptRequestProposalNumber, aProposalNumber);
//		if (isAgreement(aRejectionKind)) {
//			maxReceivedAccepProposalNumber = Math.max(maxSentAcceptProposalNumber, aProposalNumber);
//			lastAcceptedProposal = aProposal;
//		}
	}
	protected void recordReceivedAcceptRequest(float aProposalNumber, StateType aProposal){
		maxReceivedAcceptRequestNumber = Math.max(maxReceivedAcceptRequestNumber, aProposalNumber);
//		if (isAgreement(aRejectionKind)) {
//			maxReceivedAcceptProposalNumber = Math.max(maxSentAcceptProposalNumber, aProposalNumber);
//			lastAcceptedProposal = aProposal;
//		}
	}
	protected void recordSentAcceptedNotification(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind){
		maxSentAcceptedNotificationProposalNumber = Math.max(maxSentAcceptedNotificationProposalNumber, aProposalNumber);
//		if (isAgreement(aRejectionKind)) {
//			maxReceivedAccepProposalNumber = Math.max(maxSentAcceptProposalNumber, aProposalNumber);
//			lastAcceptedProposal = aProposal;
//		}
	}
	protected void recordReceivedAcceptedNotification(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind){
		maxReceivedAcceptedNotificationProposalNumber = Math.max(maxReceivedAcceptedNotificationProposalNumber, aProposalNumber);
		incrementCount(aProposalNumber, ACCEPT_NOTIFICATION, 1);			

		if (isAgreement(aRejectionKind)) {
			lastAcceptedProposal = aProposal;
			lastAcceptedProposalNumber = Math.max(aProposalNumber, lastAcceptedProposalNumber);
			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);			
		}
	}
	
	protected float maxAcceptProposalNumber() {
		return maxSentAcceptRequestProposalNumber;
	}
	protected float maxNonRejectionedProposalNumber() {
		return maxReceivedAcceptRequestNumber;
	}
	protected StateType lastAcceptedProposal() {
		return lastAcceptedProposal;
	}
	@Override
	public void accept(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		recordReceivedAcceptRequest(aProposalNumber, aProposal);
		if (!isPending(aProposalNumber)) {
			if (!isSendAcceptReplyForResolvedProposal()) {
				return;
			}
		}
		ProposalRejectionKind aRejectionKind = checkAcceptRequest(aProposalNumber, aProposal);
		recordSentAcceptedNotification(aProposalNumber, aProposal, aRejectionKind);
		sendAcceptedNotification(aProposalNumber, aProposal, aRejectionKind);		
	}
	protected void propose(float aProposalNumber, StateType aProposal) {	
			if (isAsynchronousConsistency()) {
				sendLearnNotification(aProposalNumber, aProposal, ProposalRejectionKind.ACCEPTED);
			} else {
				sendAcceptRequest(aProposalNumber, aProposal);
			}
	}
	protected void sendAcceptRequest(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		acceptors().accept(aProposalNumber, aProposal);		
	}
	protected boolean customSufficientAcceptors(short aMaxAcceptors, int aCurrentAcceptors) {
		return false;
	}
	protected Boolean sufficientAcceptors(short aMaxAcceptors, short aCurrentAcceptors, int anAcceptNotifications, int anAgreements) {
		if (anAcceptNotifications != aCurrentAcceptors) {
			return null;
		}
		switch (getConsensusSynchrony()){
		case ALL_SYNCHRONOUS:
			return aMaxAcceptors == anAcceptNotifications;
		case MAJORITY_SYNCHRONOUS:
			return ((float) aMaxAcceptors)/2 > anAcceptNotifications;
		case ONE_SYNCHRONOUS:
			return aCurrentAcceptors > 0;
		case ASYNCHRONOUS:
			return true;
		case CUSTOM_SYNCHRONOUS:
			return customSufficientAcceptors(aMaxAcceptors, anAcceptNotifications);
		
		}	
		return false;
	}

	protected Boolean sufficientAcceptors(float aProposalNumber) {
		return sufficientAcceptors(numMaximumMembers(), numCurrentMembers(), getCount(aProposalNumber, ACCEPT_NOTIFICATION), getCount(aProposalNumber, ACCEPT_SUCCESS));
	}
	
	protected void recordAcceptedInformation(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind) {
		if (isAgreement(aRejectionKind)) {
			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);
		};
	}
	protected void processRejectionedProposal(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind) {
	      sendLearnNotification(aProposalNumber, aProposal, aRejectionKind);
	}
	protected boolean heardFromAll() {
		return false;
	}
	protected void processProposalRejection(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind) {
		
			if (isSendRejectionNotification()) {
				 recordSentLearnNotification(aProposalNumber, aProposal, aRejectionKind);
				 sendLearnNotification(aProposalNumber, aProposal, aRejectionKind);
			} else {
				learn(aProposalNumber, aProposal, aRejectionKind);
			}
		
	}
	@Override
	public void accepted(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind) {
		ProposalAcceptedNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal, aRejectionKind);
		recordReceivedAcceptedNotification(aProposalNumber, aProposal, aRejectionKind);
		if (!isPending(aProposalNumber)) {
			return;
		}
		if (isSynchronous() && !isAgreement(aRejectionKind)) {
			processProposalRejection(aProposalNumber, aProposal, aRejectionKind);
			return;
		}		
		Boolean isSufficientAcceptors = sufficientAcceptors(aProposalNumber);
		if (isSufficientAcceptors == null)
			return;
		if (isSufficientAcceptors) {
		  aRejectionKind = ProposalRejectionKind.ACCEPTED;
		  recordSentLearnNotification(aProposalNumber, aProposal, aRejectionKind);
	      sendLearnNotification(aProposalNumber, aProposal, aRejectionKind);
		} else {
			processProposalRejection(aProposalNumber, aProposal, ProposalRejectionKind.NOT_ENOUGH_ACCEPTS);
			
		}		
	}
}

	


