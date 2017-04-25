package consensus.paxos;


public class APrepareMulticastRunnable<StateType> implements Runnable {
	Preparer<StateType> callees;
	float proposalNumber;
	StateType proposal;

	public APrepareMulticastRunnable(Preparer<StateType> aCallees,
			float aProposalNumber, StateType aProposal) {
		callees = aCallees;
		proposalNumber = aProposalNumber;
		proposal = aProposal;
	}

	@Override
	public void run() {
		callees.prepare(proposalNumber, proposal);

	}

}
