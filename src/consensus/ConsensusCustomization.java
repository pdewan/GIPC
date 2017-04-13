package consensus;

public interface ConsensusCustomization {
	public ConsistencyStrength getConsistencyStrength();
	public void setConsistencyStrength(ConsistencyStrength consistencyStrength) ;
	public ProposalRejectionKind getProposalVetoKind();
	public void setProposalVetoKind(ProposalRejectionKind proposalRejectionKind);
	public ConsensusSynchrony getConsensusSynchrony();
	public void setConsensusSynchrony(ConsensusSynchrony consensusSynchrony);
	public void setSendRejectionInformation(boolean newVal);
	public boolean isSendRejectionInformation();
	public boolean isAllowVeto();
	public void setAllowVeto(boolean newVal);

	public ConsensusMemberSetKind getConsensusMemberSetKind() ;
	public void setConsensusMemberSetKind(ConsensusMemberSetKind consensusMemberSet) ;

	
}
