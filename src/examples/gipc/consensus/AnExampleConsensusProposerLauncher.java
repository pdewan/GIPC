package examples.gipc.consensus;


public abstract class AnExampleConsensusProposerLauncher extends AnExampleConsensusMemberLauncher{
	
	public AnExampleConsensusProposerLauncher(String aLocalName, int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	public static final String GREETING_1 = "Hello";
	public static final String GREETING_2 = "Hi";
	public static int MEANING_1 = 42;
	public static int MEANING_2 = 29;
	public static long INIT_TIME = 6000;
	
	public  void proposeValues1() {
//		double aGreetingProposal1 = greetingMechanism.propose(GREETING_1);
		float aMeaningOfLifeProposal = meaningOfLifeMechanism.propose(MEANING_1);		
	}
	public  void proposeValues2() {
//		double aGreetingProposal1 = greetingMechanism.propose(GREETING_2);
		float aMeaningOfLifeProposal = meaningOfLifeMechanism.propose(MEANING_2);		
	}
	
	protected void doPropose() {
		proposeValues1();
	}

	public  void proposeValues() {
//		ThreadSupport.sleep(INIT_TIME);
		doPropose();
		
	}
	

	

}
