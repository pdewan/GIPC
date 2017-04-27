package consensus.paxos;

import consensus.ProposalFeedbackKind;


public class APreparedMulticastRunnable<StateType> implements Runnable {
	Prepared<StateType> callees;
	float acceptedProposalNumber, preparedProposalNumber;	
	StateType acceptedProposal;
	ProposalFeedbackKind feedbackKind;

	public APreparedMulticastRunnable(Prepared<StateType> aCallees,
			float anAcceptedProposalNumber, StateType anAcceptedProposal, 
			float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		callees = aCallees;
		acceptedProposalNumber = anAcceptedProposalNumber;
		acceptedProposal = anAcceptedProposal;
		preparedProposalNumber = aPreparedProposalNumber;
		feedbackKind = aFeedbackKind;
		
	}

	@Override
	public void run() {
		callees.prepared(acceptedProposalNumber, 
				acceptedProposal, preparedProposalNumber, feedbackKind);

	}

}
