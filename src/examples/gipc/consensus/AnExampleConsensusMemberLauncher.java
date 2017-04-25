package examples.gipc.consensus;

import port.sessionserver.SessionServerSelector;
import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ConsensusMechanismSelector;
import consensus.ConcurrencyKind;
import consensus.ReplicationSynchrony;
import consensus.asynchronous.sequential.AnAsynchronousConsensusMechanism;
import consensus.asynchronous.sequential.AnAsynchronousConsensusMechanismFactory;
import consensus.paxos.sequential.ASequentialPaxosConsensusMechanismFactory;
import consensus.sessionport.AConsensusMemberLauncher;

public abstract class AnExampleConsensusMemberLauncher extends AConsensusMemberLauncher 
	implements ExampleMemberLauncher {
	protected  ConsensusMechanism<Integer> meaningOfLifeMechanism;
	protected  ConsensusMechanism<String> greetingMechanism;

	
	protected abstract ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory();
	protected  ConsensusMechanismFactory<String> greetingConsensusMechanismFactory() {
		return new AnAsynchronousConsensusMechanismFactory<>();
	}
	
	protected void createMeaningConsensusMechanism() {
		meaningOfLifeMechanism = meaningConsensusMechanismFactory().createConsensusMechanism(
				SESSION_MANAGER_HOST,
				EXAMPLE_SESSION,
				memberId, portNumber, 
				MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, 
				sessionChoice, 
				numMembersToWaitFor());
	}
	protected void createGreetingConsensusMechanism() {
		greetingMechanism = greetingConsensusMechanismFactory().createConsensusMechanism(
				EXAMPLE_SESSION, memberId, GREETING_CONSENSUS_MECHANISM_NAME);
	}

	protected  void createConsensusMechanisms(short anId) {
		createMeaningConsensusMechanism();
		createGreetingConsensusMechanism();
//		meaningOfLifeMechanism = meaningConsensusMechanismFactory().createConsensusMechanism(
//				SESSION_MANAGER_HOST,
//				EXAMPLE_SESSION,
//				memberId, portNumber, 
//				MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, 
//				sessionChoice, 
//				numMembersToWaitFor());
//		greetingMechanism = greetingConsensusMechanismFactory().createConsensusMechanism(
//				EXAMPLE_SESSION, memberId, GREETING_CONSENSUS_MECHANISM_NAME);
	}
	public AnExampleConsensusMemberLauncher(String aLocalName, int aPortNumber) {
		super(aLocalName, aPortNumber);
	}	
	protected void addListenersAndVetoersToConsensusMechanisms() {
		meaningOfLifeMechanism.addConsensusListener(new AMeaningOfLifeConsensusListener());
		greetingMechanism.addConsensusListener(new AGreetingConsensusListener());
	}
	@Override
	protected short numMembersToWaitFor() {
		return 4;
	}
}
