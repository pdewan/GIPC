package consensus;

public interface ConsensusCustomization {
	public ConsistencyStrength getConsistencyStrength();
	public void setConsistencyStrength(ConsistencyStrength consistencyStrength) ;
	public ProposalRejectionKind getProposalVetoKind();
	public void setProposalVetoKind(ProposalRejectionKind proposalRejectionKind);
	public ReplicationSynchrony getReplicationSynchrony();
	public void setReplicationSynchrony(ReplicationSynchrony consensusSynchrony);
	public void setSendRejectionInformation(boolean newVal);
	public boolean isSendRejectionNotification();
	public boolean isSynchronous();
	public void setAllowVeto(boolean newVal);

	public ConsensusMemberSetKind getConsensusMemberSetKind() ;
	public void setConsensusMemberSetKind(ConsensusMemberSetKind consensusMemberSet) ;
	public boolean isValueSynchrony();	
	public void setValueSynchrony(boolean newVal) ;
	public boolean isSendAcceptReplyForResolvedProposal();
	public void setSendAcceptReplyForResolvedProposal(
			boolean newVal) ;
	
}
