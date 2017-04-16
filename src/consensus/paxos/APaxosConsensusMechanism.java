package consensus.paxos;

import port.trace.consensus.RemoteProposeRequestReceived;
import port.trace.consensus.RemoteProposeRequestSent;
import bus.uigen.widgets.universal.CentralUniversalWidget;
import sessionport.rpc.group.GIPCSessionRegistry;
import sun.security.util.PendingException;
import consensus.ConsensusMechanism;
import consensus.ProposalRejectionKind;
import consensus.ProposalState;
import consensus.central.ACentralizableConsensusMechanism;
import consensus.synchronous.ASynchronousConsensusMechanism;

public class APaxosConsensusMechanism<StateType> 
	extends ACentralizableConsensusMechanism<StateType>
	implements Prepared<StateType>{

	protected float maxPreparedProposalNumber;
	protected StateType maxPreparedProposal;
	public APaxosConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
	}

	@Override
	public void prepare(float aProposalNumber, StateType aProposal) {
		sendPrepareResponse(lastAcceptedProposalNumber(), lastAcceptedProposal(), aProposalNumber, checkProposal(aProposalNumber, aProposal));
		
	}
	protected Prepared<StateType> caller() {
		return (Prepared<StateType>) caller();
	}
	
	protected void sendPrepareResponse(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalRejectionKind aRejectionKind) {
		caller().prepared(anAcceptedProposalNumber, anAcceptedProposal, anAcceptedProposalNumber, aRejectionKind);
	}
	
	

	@Override
	public void prepared(float anAcceptedProposalNumber, StateType anAcceptedProposal, float aPreparedProposalNumber, ProposalRejectionKind aRejectionKind) {
		if (!isPending(aPreparedProposalNumber)) {
			return;
		}
		if (anAcceptedProposalNumber > aPreparedProposalNumber) {
			newProposalState(aPreparedProposalNumber, proposal(aPreparedProposalNumber), ProposalState.PROPOSAL_CONCURRENT_OPERATION);
			return;
		} 
		StateType anActualProposedState = proposal(aPreparedProposalNumber);

		if (anAcceptedProposal != null) {
//			anActualProposedState =
		}
		
		
	}
	
	protected float maxPreparedProposalNumber() {
		return maxPreparedProposalNumber;
	}
	protected StateType maxPreparedProposal() {
		return maxPreparedProposal;
	}


	
}
