package consensus.synchronous.sequential;

import consensus.ProposalFeedbackKind;


public class AnAcceptedMulticastRunnable<StateType> implements Runnable {
	Accepted<StateType> callees;
	float proposalNumber;
	StateType proposal;
	ProposalFeedbackKind feedbackKind;

	public AnAcceptedMulticastRunnable(Accepted<StateType> aCallees,
			float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		callees = aCallees;
		proposalNumber = aProposalNumber;
		proposal = aProposal;
		feedbackKind = aFeedbackKind;
	}

	@Override
	public void run() {
		callees.accepted(proposalNumber, proposal, feedbackKind);

	}

}
