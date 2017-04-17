package examples.gipc.consensus;

import consensus.ProposalFeedbackKind;
import consensus.ProposalVetoer;

public class AGreetingVetoer implements ProposalVetoer<String>{

	@Override
	public ProposalFeedbackKind acceptProposal(float aProposalNumber, String aState) {
		return aState.length() > 2?ProposalFeedbackKind.SERVICE_DENIAL:ProposalFeedbackKind.SUCCESS;
			
	}

}
