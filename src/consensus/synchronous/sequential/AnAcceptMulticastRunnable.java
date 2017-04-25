package consensus.synchronous.sequential;


public class AnAcceptMulticastRunnable<StateType> implements Runnable {
	Acceptor<StateType> callees;
	float proposalNumber;
	StateType proposal;

	public AnAcceptMulticastRunnable(Acceptor<StateType> aCallees,
			float aProposalNumber, StateType aProposal) {
		callees = aCallees;
		proposalNumber = aProposalNumber;
		proposal = aProposal;
	}

	@Override
	public void run() {
		callees.accept(proposalNumber, proposal);

	}

}
