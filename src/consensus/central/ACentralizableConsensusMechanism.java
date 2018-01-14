package consensus.central;

import inputport.ConnectionType;
import bus.uigen.widgets.universal.CentralUniversalWidget;
import sessionport.rpc.group.GIPCSessionRegistry;
import util.trace.port.RemoteEndDisconnected;
import util.trace.port.consensus.RemoteProposeRequestReceived;
import util.trace.port.consensus.RemoteProposeRequestSent;
import consensus.ConsensusMechanism;
import consensus.ProposalFeedbackKind;
import consensus.synchronous.sequential.ASynchronousConsensusMechanism;

public class ACentralizableConsensusMechanism<StateType> 
	extends ACentralizableClientConsensusMechanism<StateType>
	implements ProposeServer<StateType>{
//	protected boolean isClient;
//	protected boolean isServer;
//	protected String serverName;
//	protected boolean isCentralized;
	protected ProposeServer<StateType> proposeServer;
	
	public ACentralizableConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
	}
	
//	public boolean isClient() {
//		return isClient;		
//	}
//	public boolean isServer() {
//		return isServer;
//	}
//	public boolean isCentralized() {
//		return isCentralized;
//	}
//	public String getServerName() {
//		return serverName;
//	}
//	public void setClient(boolean isClient) {
//		this.isClient = isClient;
//	}
//
//	public void setServer(boolean isServer) {
//		this.isServer = isServer;
//	}
//
//	public void setServerName(String serverName) {
//		this.serverName = serverName;
//	}
//
//	public void setCentralized(boolean isCentralized) {
//		this.isCentralized = isCentralized;
//	}

	//	public void remotePropose(float aProposalNumber, StateType aProposal) {
//		propose(aProposalNumber, aProposal);
//	}
//	protected boolean isAcceptConcurrencyConflict (float aProposalNumber, StateType aProposal )  {
//		   return isSerializable() && someProposalIsPending() ;
//	   }
//	   protected synchronized ProposalFeedbackKind checkProposalForAccept(float aProposalNumber, StateType aProposal ) {
//		   if (isAcceptConcurrencyConflict(aProposalNumber, aProposal))
//			   return ProposalFeedbackKind.CONCURRENCY_CONFLICT;
//			return 
//				checkWithVetoer(aProposalNumber, aProposal);
//	  }
	
	@Override
	public void remotePropose(float aProposalNumber, StateType aProposal) {
		RemoteProposeRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		recordProposalState(aProposalNumber, aProposal);
		if (someOtherProposalIsPending(aProposalNumber) && isSerializable()) {
			sendLearnNotification(aProposalNumber, aProposal, ProposalFeedbackKind.CONCURRENCY_CONFLICT);
			return;
		} 
		localPropose(aProposalNumber, aProposal);		
	}

	

}
