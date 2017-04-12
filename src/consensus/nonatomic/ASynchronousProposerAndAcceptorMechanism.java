package consensus.nonatomic;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalAcceptedNotificationReceived;
import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalPrepareNotificationSent;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import consensus.Accepted;
import consensus.Acceptor;
import consensus.ConsensusSynchrony;
import consensus.ProposalState;
import consensus.ProposalVetoKind;
import consensus.nonatomic.nonvetoable.AnAsynchronousProposerAndLearnerMechanism;

public class ASynchronousProposerAndAcceptorMechanism<StateType> 
	extends AnAsynchronousProposerAndLearnerMechanism<StateType> 
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
	public ASynchronousProposerAndAcceptorMechanism(
			InputPort anInputPort, String aName, short aMyId,
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
	protected void sendAcceptedNotification(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind){
		proposer().accepted(aProposalNumber, aProposal, aVetoKind );
		ProposalAcceptedNotificationSent.newCase(this, getObjectName(), aProposalNumber, aProposal, aVetoKind);
	}
	protected void recordSentAcceptRequest(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind){
		maxSentAcceptRequestProposalNumber = Math.max(maxSentAcceptRequestProposalNumber, aProposalNumber);
//		if (isAgreement(aVetoKind)) {
//			maxReceivedAccepProposalNumber = Math.max(maxSentAcceptProposalNumber, aProposalNumber);
//			lastAcceptedProposal = aProposal;
//		}
	}
	protected void recordReceivedAcceptRequest(float aProposalNumber, StateType aProposal){
		maxReceivedAcceptRequestNumber = Math.max(maxReceivedAcceptRequestNumber, aProposalNumber);
//		if (isAgreement(aVetoKind)) {
//			maxReceivedAcceptProposalNumber = Math.max(maxSentAcceptProposalNumber, aProposalNumber);
//			lastAcceptedProposal = aProposal;
//		}
	}
	protected void recordSentAcceptedNotification(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind){
		maxSentAcceptedNotificationProposalNumber = Math.max(maxSentAcceptedNotificationProposalNumber, aProposalNumber);
//		if (isAgreement(aVetoKind)) {
//			maxReceivedAccepProposalNumber = Math.max(maxSentAcceptProposalNumber, aProposalNumber);
//			lastAcceptedProposal = aProposal;
//		}
	}
	protected void recordReceivedAcceptedNotification(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind){
		maxReceivedAcceptedNotificationProposalNumber = Math.max(maxReceivedAcceptedNotificationProposalNumber, aProposalNumber);
		if (isAgreement(aVetoKind)) {
			lastAcceptedProposal = aProposal;
			lastAcceptedProposalNumber = Math.max(aProposalNumber, lastAcceptedProposalNumber);
		}
	}
	
	protected float maxAcceptProposalNumber() {
		return maxSentAcceptRequestProposalNumber;
	}
	protected float maxNonVetoedProposalNumber() {
		return maxReceivedAcceptRequestNumber;
	}
	protected StateType lastAcceptedProposal() {
		return lastAcceptedProposal;
	}
	@Override
	public void accept(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		recordReceivedAcceptRequest(aProposalNumber, aProposal);
//		recordProposal(aProposalNumber, aProposal);
		ProposalVetoKind aVetoKind = checkAcceptRequest(aProposalNumber, aProposal);
		recordSentAcceptedNotification(aProposalNumber, aProposal, aVetoKind);
		sendAcceptedNotification(aProposalNumber, aProposal, aVetoKind);
		
	}
	protected void propose(float aProposalNumber, StateType aProposal) {	
			if (isAsynchronousConsistency()) {
				sendLearnNotification(aProposalNumber, aProposal, ProposalVetoKind.NO_VETO);
			} else {
				sendAcceptRequest(aProposalNumber, aProposal);
			}
	}
	protected void sendAcceptRequest(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		acceptors().accept(aProposalNumber, aProposal);		
	}
	protected boolean customSufficientAcceptors(short aMaxAcceptors, short aCurrentAcceptors) {
		return false;
	}
	protected boolean sufficientAcceptors(short aMaxAcceptors, short aCurrentAcceptors) {
		switch (getConsensusSynchrony()){
		case ALL_SYNCHRONOUS:
			return aMaxAcceptors == aCurrentAcceptors;
		case MAJORITY_SYNCHRONOUS:
			return ((float) aMaxAcceptors)/2 > aCurrentAcceptors;
		case ONE_SYNCHRONOUS:
			return aCurrentAcceptors > 0;
		case ASYNCHRONOUS:
			return true;
		case CUSTOM_SYNCHRONOUS:
			return customSufficientAcceptors(aMaxAcceptors, aCurrentAcceptors);
		
		}	
		return false;
	}

	protected Boolean sufficientAcceptors(short aMaxAcceptors, short aCurrentAcceptors, short anAcceptNotifications) {
		if (aMaxAcceptors != aCurrentAcceptors)
			return false;
		if (anAcceptNotifications != aCurrentAcceptors)
			return null;
		return true;
	}
	protected Boolean sufficientAcceptors() {
		return sufficientAcceptors(maxPeers(), numCurrentPeers());
	}
	
	protected void recordAcceptedInformation(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind) {
		if (isAgreement(aVetoKind)) {
			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);
		};
	}
	protected void processVetoedProposal(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind) {
	      sendLearnNotification(aProposalNumber, aProposal, aVetoKind);
	}
	@Override
	public void accepted(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind) {
		ProposalAcceptedNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal, aVetoKind);
		recordSentAcceptRequest(aProposalNumber, aProposal, aVetoKind);
//		if (!isAgreement(aVetoKind)) {
//			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);
//		} else {
////			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);
//		      sendLearnNotification(aProposalNumber, aProposal, aVetoKind);
//
//		}
//		if (!isAgreement(aVetoKind)) {
//			processVetoedProposal(aProposalNumber, aProposal, aVetoKind);
////			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);
//		} 
		if (!isPending(aProposalNumber)) {
			return;
		}	
		if (!isAgreement(aVetoKind)) {
			if (isSendVetoInformation()) {
				 sendLearnNotification(aProposalNumber, aProposal, aVetoKind);
			}
			return;
		}
		
		Boolean isSufficientAcceptors = sufficientAcceptors();
		if (isSufficientAcceptors != null && isSufficientAcceptors) {		 
	      sendLearnNotification(aProposalNumber, aProposal, ProposalVetoKind.NO_VETO);
		}
	
	}
}

	


