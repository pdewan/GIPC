package consensus.central;

import inputport.ConnectionType;
import port.trace.consensus.RemoteProposeRequestReceived;
import port.trace.consensus.RemoteProposeRequestSent;
import bus.uigen.widgets.universal.CentralUniversalWidget;
import sessionport.rpc.group.GIPCSessionRegistry;
import consensus.ConsensusMechanism;
import consensus.ProposalFeedbackKind;
import consensus.ProposalState;
import consensus.synchronous.sequential.ASynchronousConsensusMechanism;

public class ACentralizableClientConsensusMechanism<StateType> 
	extends ASynchronousConsensusMechanism<StateType>
	{
	protected String lastServerName;

	protected ProposeServer<StateType> proposeServer;
	
	public ACentralizableClientConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
	}
	public String getServerName() {
		if (serverName == null) {
			return getSortedMembers().get(0); // at least this one is alive
		}
		return serverName;
	}

	protected void dispatchPropose(float aProposalNumber, StateType aProposal) {
//		if (!isCentralized2PC() || isServer()) {
		if (!isCentralizedPropose() ) {
			localPropose(aProposalNumber, aProposal);
		}  else {			
			sendProposeRequest(aProposalNumber, aProposal);
		}
	}
	protected void sendProposeRequest(float aProposalNumber, StateType aProposal) {
		RemoteProposeRequestSent.newCase(this, getObjectName(), aProposalNumber, aProposal);
		lastServerName = getServerName();
		server().remotePropose(aProposalNumber, aProposal);
	}

	protected ProposeServer<StateType> server() {
		return (ProposeServer<StateType>) member(getServerName());
	}
	public synchronized void disconnected(String aRemoteEndName,
			boolean anExplicitDisconnection, String anExplanation,
			ConnectionType aConnectionType) {
		super.disconnected(aRemoteEndName, anExplicitDisconnection, anExplanation, aConnectionType);
		if (aRemoteEndName.equals(lastServerName)) {
			newProposalState(pendingProposals(),
			ProposalState.CENTRAL_SERVER_DIED);
		}
	}

}
