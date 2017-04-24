package consensus;

public interface ConsensusCustomization {
	public ConcurrencyKind getConcurrencyKind();
	public void setConcurrencyKind(ConcurrencyKind consistencyStrength) ;
	public ProposalFeedbackKind getProposalVetoKind();
	public void setProposalVetoKind(ProposalFeedbackKind proposalRejectionKind);
	public ReplicationSynchrony getAcceptSynchrony();
	public void setAcceptSynchrony(ReplicationSynchrony consensusSynchrony);
	public void setSendRejectionInformation(boolean newVal);
	public boolean isSendRejectionNotification();
	public boolean isAllSynchronous();
	public void setAllowVeto(boolean newVal);

	public ConsensusMemberSetKind getConsensusMemberSetKind() ;
	public void setConsensusMemberSetKind(ConsensusMemberSetKind consensusMemberSet) ;
	public boolean isValueSynchrony();	
	public void setValueSynchrony(boolean newVal) ;
	public boolean isSendAcceptReplyForResolvedProposal();
	public void setSendAcceptReplyForResolvedProposal(
			boolean newVal) ;
	public boolean isClient() ;
	public boolean isServer();
	public boolean isCentralized2PC() ;
	public String getServerName() ;
//	public void setIsClient(boolean isClient) ;

	public void setIsServer(boolean isServer) ;

	public void setServerName(String serverName) ;

	public void setCentralized(boolean isCentralized);
	public ReplicationSynchrony getPrepareSynchrony() ;
	public void setPrepareSynchrony(ReplicationSynchrony prepareSynchrony);
	public boolean isSequentialAccess() ;
	public void setSequentialAccess(boolean sequentialAccess) ;
	
	
}
