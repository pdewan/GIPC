package examples.gipc.consensus;

import consensus.ProposalRejectionKind;
import consensus.ProposalVetoer;

public class AMeaningOfLifeVetoer implements ProposalVetoer<Integer>{
	@Override
	public ProposalRejectionKind acceptProposal(float aProposalNumber, Integer aState) {
		return aState % 2 != 0?	ProposalRejectionKind.SERVICE_DENIAL:ProposalRejectionKind.ACCEPTED;

	}
}
