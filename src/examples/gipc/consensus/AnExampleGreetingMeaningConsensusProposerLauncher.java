package examples.gipc.consensus;

import util.misc.ThreadSupport;
import consensus.ProposalState;

public abstract class AnExampleGreetingMeaningConsensusProposerLauncher extends
		AnExampleGreetingMeaningConsensusMemberLauncher {

	public AnExampleGreetingMeaningConsensusProposerLauncher(String aLocalName, int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
//
//	public static final String GREETING_1 = "Hello";
//	public static final String GREETING_2 = "Hi";
	public static int MEANING_1 = 42;
	public static int MEANING_2 = 29;
	public static long INIT_TIME = 6000;
	public static long RE_PROPOSE_TIME = 10000;
	
	protected boolean retry(ProposalState aState) {
		
		return aState == ProposalState.PROPOSAL_CONCURRENT_OPERATION ||
				aState == ProposalState.CENTRAL_SERVER_DIED;
	}
	protected boolean waitForLastProposal() {
		return false;
	}
	public void proposeMeaning(Integer aValue) {
		while (true) {
			if (waitForLastProposal() && meaningOfLifeMechanism.someProposalIsPending()) {
				meaningOfLifeMechanism.waitForConsensus(meaningOfLifeMechanism.lastProposalNumber());
			}
			float aMeaningOfLifeProposal = meaningOfLifeMechanism
					.propose(aValue);			
			ProposalState aState = meaningOfLifeMechanism.waitForConsensus(aMeaningOfLifeProposal, RE_PROPOSE_TIME);
			if (aState  == null) {
				System.out.println ("timed out waiting for proposal:" + aMeaningOfLifeProposal);
			}
			if (!retry(aState)) {
				break;
			}
			if (aState != null) { // did not time our
				ThreadSupport.sleep(RE_PROPOSE_TIME);
			}
		}
	}

	public void proposeValues1() {		
		proposeMeaning(MEANING_1);
	}

	public void proposeValues2() {
		proposeMeaning(MEANING_2);	
	}

	protected void doPropose() {
		proposeValues1();
	}

	public void proposeValues() {
		doPropose();
	}

}
