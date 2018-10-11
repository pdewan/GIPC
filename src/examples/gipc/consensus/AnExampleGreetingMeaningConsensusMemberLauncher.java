package examples.gipc.consensus;

import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.sessionport.AConsensusMemberLauncher;

public abstract class AnExampleGreetingMeaningConsensusMemberLauncher extends AConsensusMemberLauncher 
	implements ExampleMemberLauncher {
	protected  ConsensusMechanism<String> greetingMechanism;
	protected  ConsensusMechanism<Integer> meaningOfLifeMechanism;

	
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
	public AnExampleGreetingMeaningConsensusMemberLauncher(String aLocalName, int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	protected void addListenersAndVetoersToLocalGreetingMechanism() {
		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());
	}
	protected void addListenersAndVetoersToLocalMeaningOfLifeMechanism() {
		meaningOfLifeMechanism.addConsensusListener(new AMeaningOfLifeConsensusListener());
	}
	protected  void initGreetingConsensusMechanism(short anId) {

		greetingMechanism = createLocalGreetingMechanism(anId);
		addListenersAndVetoersToLocalGreetingMechanism();

//		gipcRegistry.rebind(GREETING_CONSENSUS_MECHANISM_NAME, greetingMechanism);
	}	
	protected  void initMeaningOfLifeConsensusMechanism(short anId) {


		meaningOfLifeMechanism = createLocalMeaningOfLifeMechanism(anId);
		customizeMeaningOfLifeConsensusMechanism();

		addListenersAndVetoersToLocalMeaningOfLifeMechanism();


	}
	protected void customizeMeaningOfLifeConsensusMechanism(){
		
	}
	protected  void initConsensusMechanisms(short anId) {
		initMeaningOfLifeConsensusMechanism(anId);
	}
	@Override
	protected short numMembersToWaitFor() {
		return 4;
	}



}
