package examples.gipc.consensus;

import port.sessionserver.SessionServerSelector;
import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ConsensusMechanismSelector;
import consensus.ConsistencyStrength;
import consensus.ReplicationSynchrony;
import consensus.sessionport.AConsensusMemberLauncher;

public abstract class AnExampleConsensusMemberLauncher extends AConsensusMemberLauncher 
	implements ExampleMemberLauncher {
	protected  ConsensusMechanism<String> greetingMechanism;
	protected  ConsensusMechanism<Integer> meaningOfLifeMechanism;
//	protected  Object receiversRemoteGreetingMechanism;
//	protected  Object receiversMeaningOfLifeMechanism;
//	protected  Object callerRemoteGreetingMechanism;
//	protected  Object callerMeaningOfLifeMechanism;
	
	protected abstract ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory();
	protected abstract ConsensusMechanismFactory<String> greetingConsensusMechanismFactory();


	protected ConsensusMechanism<String> createLocalGreetingMechanism(short anId) {
		return greetingConsensusMechanismFactory().createConsensusMechanism(
				SESSION_MANAGER_HOST,
				EXAMPLE_SESSION,
				memberId, portNumber, GREETING_CONSENSUS_MECHANISM_NAME, sessionChoice, numMembersToWaitFor());
		
	}
	protected  ConsensusMechanism<Integer> createLocalMeaningOfLifeMechanism(short anId) {
		return meaningConsensusMechanismFactory().createConsensusMechanism(
				SESSION_MANAGER_HOST,
				EXAMPLE_SESSION,
				memberId, portNumber, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, sessionChoice, numMembersToWaitFor());
	}
	public AnExampleConsensusMemberLauncher(String aLocalName, int aPortNumber) {
		init(aLocalName, aPortNumber);
	}
	protected void addListenersAndRejectionersToLocalGreetingMechanism() {
		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());
	}
	protected void addListenersAndRejectionersToLocalMeaningOfLifeMechanism() {
		meaningOfLifeMechanism.addConsensusListener(new AMeaningOfLifeConsensusListener());
	}
	protected  void initGreetingConsensusMechanism(short anId) {
//		receiversRemoteGreetingMechanism = lookupMulticastProxy(remoteReceiverConsensusClass(), GREETING_CONSENSUS_MECHANISM_NAME);
//		callerRemoteGreetingMechanism = gipcRegistry.lookupCaller(remoteCallerConsensusClass(), GREETING_CONSENSUS_MECHANISM_NAME);
		greetingMechanism = createLocalGreetingMechanism(anId);
		addListenersAndRejectionersToLocalGreetingMechanism();

//		gipcRegistry.rebind(GREETING_CONSENSUS_MECHANISM_NAME, greetingMechanism);
	}	
	protected  void initMeaningOfLifeConsensusMechanism(short anId) {
//		receiversMeaningOfLifeMechanism =  lookupMulticastProxy(remoteReceiverConsensusClass(), MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME);
//		callerMeaningOfLifeMechanism
//		= gipcRegistry.lookupCaller(remoteCallerConsensusClass(), MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME);

		meaningOfLifeMechanism = createLocalMeaningOfLifeMechanism(anId);

		addListenersAndRejectionersToLocalMeaningOfLifeMechanism();
//		gipcRegistry.rebind(MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, meaningOfLifeMechanism);	
//		meaningOfLifeMechanism.setReplicationSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);

	}
	protected  void initConsensusMechanisms(short anId) {
//		initGreetingConsensusMechanism(anId);
		initMeaningOfLifeConsensusMechanism(anId);
	}
	@Override
	protected short numMembersToWaitFor() {
		return 4;
	}

//	protected  void init(String aLocalName, int aPortNumber) {
////		ConsensusTraceUtility.setTracing();
//
//		gipcRegistry = GIPCLocateSessionRegistry.createSessionRegistry(
//				"mysession", "localhost", aPortNumber, aLocalName,
//				sessionChoice, 
//				numMembersToWaitFor());
//		groupRPCSessionPort = gipcRegistry.getSessionPort();
//		short anId = Short.parseShort(aLocalName);
//		initGreetingConsensusMechanism(anId);
//		initMeaningOfLifeConsensusMechanism(anId);		
//	}
//	public  void proposeValues() {
//		double aGreetingProposal1 = greetingMechanism.propose(GREETING_1);
//		double aGreetingProposal2 = greetingMechanism.propose(GREETING_2);
//		double aMeaningOfLifeProposal = meaningOfLifeMechanism.propose(MEANING);
//		
//	}
	
//	public void beProposer(String aLocalName, int aPortNumber) {
//		init(aLocalName, aPortNumber);
//		proposeValues();
//	}

}
