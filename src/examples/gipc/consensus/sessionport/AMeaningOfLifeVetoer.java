package examples.gipc.consensus.sessionport;

import consensus.ProposalVetoKind;
import consensus.ProposalVetoer;

public class AMeaningOfLifeVetoer implements ProposalVetoer<Integer>{
	@Override
	public ProposalVetoKind acceptProposal(float aProposalNumber, Integer aState) {
		return aState % 2 != 0?	ProposalVetoKind.SERVICE_DENIAL:ProposalVetoKind.NO_VETO;

	}
}
