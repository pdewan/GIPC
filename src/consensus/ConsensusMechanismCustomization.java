package consensus;

public interface ConsensusMechanismCustomization {
	public ConsistencyStrength getConsistencyStrength();
	public void setConsistencyStrength(ConsistencyStrength consistencyStrength) ;
	public ProposalRejectionKind getProposalVetoKind();
	public void setProposalVetoKind(ProposalRejectionKind proposalRejectionKind);
	public ConsensusSynchrony getConsensusSynchrony();
	public void setConsensusSynchrony(ConsensusSynchrony consensusSynchrony);
	public void setSendRejectionInformation(boolean newVal);
	public boolean isSendRejectionInformation();

	
}
