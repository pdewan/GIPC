package examples.gipc.consensus;

import consensus.ConsensusMechanism;
import consensus.ConsistencyStrength;
import consensus.ReplicationSynchrony;
import consensus.sessionport.AConsensusMemberLauncher;

public abstract class AnExampleConsensusMemberLauncher extends AConsensusMemberLauncher 
	implements ExampleMemberLauncher {
	protected  ConsensusMechanism<String> greetingMechanism;
	protected  ConsensusMechanism<Integer> meaningOfLifeMechanism;
	protected  Object receiversRemoteGreetingMechanism;
	protected  Object receiversMeaningOfLifeMechanism;
	protected  Object callerRemoteGreetingMechanism;
	protected  Object callerMeaningOfLifeMechanism;
//	protected  GIPCSessionRegistry gipcRegistry;
//	protected  GroupRPCSessionPort groupRPCSessionPort;
////	protected  Integer numMembersToWaitFor = 2;
//	protected  SessionChoice sessionChoice = SessionChoice.P2P;
	
//	protected  final int MY_PORT_NUMBER = 7001;
//	protected  final String MY_NAME = "1";
	
	
	
	
//	protected abstract Class remoteConsensusClass();	
//	protected abstract Integer numMembersToWaitFor() ;
//	protected abstract Object lookupSessionProxy(Class aClass, String aName) ;
	protected abstract ConsensusMechanism<String> createLocalGreetingMechanism(short anId) ;
	protected abstract ConsensusMechanism<Integer> createLocalMeaningOfLifeMechanism(short anId) ;
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
		receiversRemoteGreetingMechanism = lookupMulticastProxy(remoteReceiverConsensusClass(), GREETING_CONSENSUS_MECHANISM_NAME);
		callerRemoteGreetingMechanism = gipcRegistry.lookupCaller(remoteCallerConsensusClass(), GREETING_CONSENSUS_MECHANISM_NAME);
		greetingMechanism = createLocalGreetingMechanism(anId);
		addListenersAndRejectionersToLocalGreetingMechanism();
//		greetingMechanism = new AnAsymmetricTwoPartyProposer<>(groupRPCSessionPort, GREETING_CONSENSUS_MECHANISM_NAME, anId, remoteGreetingMechanism);
//		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());
		gipcRegistry.rebind(GREETING_CONSENSUS_MECHANISM_NAME, greetingMechanism);
	}	
	protected  void initMeaningOfLifeConsensusMechanism(short anId) {
//		remoteMeaningOfLifeMechanism = (Acceptor) gipcRegistry.lookupAllRemote(Acceptor.class, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME);
		receiversMeaningOfLifeMechanism =  lookupMulticastProxy(remoteReceiverConsensusClass(), MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME);
		callerMeaningOfLifeMechanism
		= gipcRegistry.lookupCaller(remoteCallerConsensusClass(), MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME);

//		meaningOfLifeMechanism = new AnAsymmetricTwoPartyProposer<>(groupRPCSessionPort, MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, anId, remoteMeaningOfLifeMechanism);
		meaningOfLifeMechanism = createLocalMeaningOfLifeMechanism(anId);

//		meaningOfLifeMechanism.addConsensusListener(new AMeaningOfLifeConsensusListener());
		addListenersAndRejectionersToLocalMeaningOfLifeMechanism();
		gipcRegistry.rebind(MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, meaningOfLifeMechanism);	
		meaningOfLifeMechanism.setReplicationSynchrony(ReplicationSynchrony.MAJORITY_SYNCHRONOUS);

	}
	protected  void initConsensusMechanisms(short anId) {
		initGreetingConsensusMechanism(anId);
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
