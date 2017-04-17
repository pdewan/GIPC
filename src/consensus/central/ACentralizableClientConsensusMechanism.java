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
//	protected boolean isClient;
//	protected boolean isServer;
//	protected String serverName;
//	protected boolean isCentralized;
	protected ProposeServer<StateType> proposeServer;
	
	public ACentralizableClientConsensusMechanism(GIPCSessionRegistry aRegistry,
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
	protected void dispatchPropose(float aProposalNumber, StateType aProposal) {
		if (!isCentralized2PC() || isServer()) {
			localPropose(aProposalNumber, aProposal);
		}  else {			
			sendProposeRequest(aProposalNumber, aProposal);
		}
	}
	protected void sendProposeRequest(float aProposalNumber, StateType aProposal) {
		RemoteProposeRequestSent.newCase(this, getObjectName(), aProposalNumber, aProposal);
		server().remotePropose(aProposalNumber, aProposal);
	}
//	@Override
//	public void remotePropose(float aProposalNumber, StateType aProposal) {
//		RemoteProposeRequestReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
//		recordProposalState(aProposalNumber, aProposal);
//		if (lastProposalisPending() && isSerializable()) {
//			sendLearnNotification(aProposalNumber, aProposal, ProposalFeedbackKind.CONCURRENCY_CONFLICT);
//		}
//		localPropose(aProposalNumber, aProposal);
//	}
	protected ProposeServer<StateType> server() {
		return (ProposeServer<StateType>) member(getServerName());
	}

}
