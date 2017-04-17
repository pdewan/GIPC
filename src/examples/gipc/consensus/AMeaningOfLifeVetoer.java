package examples.gipc.consensus;

import consensus.ProposalFeedbackKind;
import consensus.ProposalVetoer;

public class AMeaningOfLifeVetoer implements ProposalVetoer<Integer>{
	@Override
	public ProposalFeedbackKind acceptProposal(float aProposalNumber, Integer aState) {
		return aState % 2 != 0?	ProposalFeedbackKind.SERVICE_DENIAL:ProposalFeedbackKind.SUCCESS;

	}
}
