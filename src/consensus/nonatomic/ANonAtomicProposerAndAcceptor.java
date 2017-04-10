package consensus.nonatomic;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalAcceptedNotificationReceived;
import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalPrepareNotificationSent;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import consensus.Accepted;
import consensus.Acceptor;
import consensus.ProposalState;
import consensus.ProposalVetoKind;
import consensus.nonatomic.nonvetoable.ALearnedMechanism;

public class ANonAtomicProposerAndAcceptor<StateType> 
	extends ALearnedMechanism<StateType> 
	implements Accepted<StateType>{
	protected int numAcceptors;
	protected Accepted proposer;
//	Accepted<StateType> proposer;
//	protected MultiPartyAcceptor<StateType> acceptors;
	public ANonAtomicProposerAndAcceptor(
			ConnectionRegistrar anInputPort, String aName, short aMyId,
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
	// duplicated from multiparty acceptor
	@Override
	public void accept(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		addProposal(aProposalNumber, aProposal);
		ProposalVetoKind anAgreement = checkWithVetoers(aProposalNumber, aProposal);
		proposer().accepted(aProposalNumber, aProposal, anAgreement );
		ProposalAcceptedNotificationSent.newCase(this, getObjectName(), aProposalNumber, aProposal, anAgreement);
	}
	protected void propose(float aProposalNumber, StateType aProposal) {
		
			sendAcceptRequest(aProposalNumber, aProposal);
	}
	protected void sendAcceptRequest(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		acceptors().accept(aProposalNumber, aProposal);		
	}
	protected boolean sufficientAcceptors(short aMaxAcceptors, short aCurrentAcceptors) {
		return aMaxAcceptors == aCurrentAcceptors;
	}
	protected Boolean sufficientAcceptors(short aMaxAcceptors, short aCurrentAcceptors, short anAcceptNotifications) {
		if (aMaxAcceptors != aCurrentAcceptors)
			return false;
		if (anAcceptNotifications != aCurrentAcceptors)
			return null;
		return true;
	}
	
	@Override
	public void accepted(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind) {
		ProposalAcceptedNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal, aVetoKind);
		
		if (isAgreement(aVetoKind)) {
			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);
		} else {
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);
		      sendLearnNotification(aProposalNumber, aProposal, aVetoKind);

		}
		if (!isPending(aProposalNumber)) {
			return;
		}	
		Integer aCount = getCount(aProposalNumber, ACCEPT_SUCCESS); 
		if (sufficientAcceptors(maxPeers(), numCurrentPeers)) {
	      sendLearnNotification(aProposalNumber, aProposal, ProposalVetoKind.NO_VETO);
		}

		}		
	}

	


