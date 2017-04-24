package examples.gipc.consensus;

import util.misc.ThreadSupport;
import consensus.ProposalState;

public abstract class AMeaningConsensusProposerLauncher extends
		AMeaningConsensusMemberLauncher {

	public AMeaningConsensusProposerLauncher(String aLocalName, int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

	public static int MEANING_1 = 42;
	public static int MEANING_2 = 29;
	public static long INIT_TIME = 6000;
	public static long RE_PROPOSE_TIME = 10000;

	protected boolean retry(ProposalState aState) {
		return aState == ProposalState.PROPOSAL_CONCURRENT_OPERATION
				|| aState == ProposalState.CENTRAL_SERVER_DIED;
	}
	
	protected Long reProposeTime() {
		return RE_PROPOSE_TIME;
	}

	public void proposeMeaning(Integer aValue) {
		while (true) {
			if (meaningOfLifeMechanism.someProposalIsPending()) {
				System.out.println("Waiting for pending proposal");
				meaningOfLifeMechanism.waitForConsensus(meaningOfLifeMechanism
						.lastProposalNumber());
			}
			System.out.println("Making proposal of:" + aValue);
			float aMeaningOfLifeProposal = meaningOfLifeMechanism
					.propose(aValue);
			ProposalState aState = meaningOfLifeMechanism.waitForConsensus(
					aMeaningOfLifeProposal, reProposeTime());
			if (aState == null) {
				System.out.println("timed out waiting for proposal:"
						+ aMeaningOfLifeProposal);
			} 
			if (!retry(aState)) {
				break;
			}
			if (aState != null) { // did not time our
				ThreadSupport.sleep(reProposeTime());
			}
			System.out.println("Retrying proposal");
		}
	}
	
	protected int meaning1() {
		return MEANING_1;
	}
	
	protected int meaning2() {
		return MEANING_2;
	}

	public void proposeValues1() {
		proposeMeaning(meaning1());
	}

	public void proposeValues2() {
		proposeMeaning(meaning2());
	}

	protected void doPropose() {
		proposeValues1();
	}

	public void proposeValues() {
		doPropose();
	}

}
