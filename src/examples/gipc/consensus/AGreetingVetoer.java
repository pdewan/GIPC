package examples.gipc.consensus;

import consensus.ProposalRejectionKind;
import consensus.ProposalVetoer;

public class AGreetingVetoer implements ProposalVetoer<String>{

	@Override
	public ProposalRejectionKind acceptProposal(float aProposalNumber, String aState) {
		return aState.length() > 2?ProposalRejectionKind.SERVICE_DENIAL:ProposalRejectionKind.ACCEPTED;
			
	}

}
