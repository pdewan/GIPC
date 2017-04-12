package consensus;

public interface ConsensusMechanismCustomization {
	public ConsistencyStrength getConsistencyStrength();
	public void setConsistencyStrength(ConsistencyStrength consistencyStrength) ;
	public ProposalVetoKind getProposalVetoKind();
	public void setProposalVetoKind(ProposalVetoKind proposalVetoKind);
	public ConsensusSynchrony getConsensusSynchrony();
	public void setConsensusSynchrony(ConsensusSynchrony consensusSynchrony);
	public void setSendVetoInformation(boolean newVal);
	public boolean isSendVetoInformation();

	
}
