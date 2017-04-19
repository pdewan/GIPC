package consensus.central;

import port.trace.consensus.RemoteProposeRequestReceived;
import port.trace.consensus.RemoteProposeRequestSent;
import bus.uigen.widgets.universal.CentralUniversalWidget;
import sessionport.rpc.group.GIPCSessionRegistry;
import consensus.ConsensusMechanism;
import consensus.ProposalFeedbackKind;
import consensus.synchronous.ASynchronousConsensusMechanism;

public class ACentralizableClientConsensusMechanism<StateType> 
	extends ASynchronousConsensusMechanism<StateType>
	{

	protected ProposeServer<StateType> proposeServer;
	
	public ACentralizableClientConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
	}
	

	protected void dispatchPropose(float aProposalNumber, StateType aProposal) {
//		if (!isCentralized2PC() || isServer()) {
		if (!isCentralized2PC() ) {
			localPropose(aProposalNumber, aProposal);
		}  else {			
			sendProposeRequest(aProposalNumber, aProposal);
		}
	}
	protected void sendProposeRequest(float aProposalNumber, StateType aProposal) {
		RemoteProposeRequestSent.newCase(this, getObjectName(), aProposalNumber, aProposal);
		server().remotePropose(aProposalNumber, aProposal);
	}

	protected ProposeServer<StateType> server() {
		return (ProposeServer<StateType>) member(getServerName());
	}

}
