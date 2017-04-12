package examples.gipc.consensus;

import consensus.ProposalVetoKind;
import consensus.ProposalVetoer;

public class AGreetingVetoer implements ProposalVetoer<String>{

	@Override
	public ProposalVetoKind acceptProposal(float aProposalNumber, String aState) {
		return aState.length() > 2?ProposalVetoKind.SERVICE_DENIAL:ProposalVetoKind.NO_VETO;
			
	}

}
