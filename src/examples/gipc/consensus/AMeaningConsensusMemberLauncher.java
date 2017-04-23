package examples.gipc.consensus;

import port.sessionserver.SessionServerSelector;
import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ConsensusMechanismSelector;
import consensus.ConcurrencyKind;
import consensus.ReplicationSynchrony;
import consensus.sessionport.AConsensusMemberLauncher;

public abstract class AMeaningConsensusMemberLauncher extends AConsensusMemberLauncher 
	implements ExampleMemberLauncher {
	protected  ConsensusMechanism<Integer> meaningOfLifeMechanism;
	
	protected abstract ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory();


	protected  ConsensusMechanism<Integer> createConsensusMechanisms(short anId) {
		meaningOfLifeMechanism = meaningConsensusMechanismFactory().createConsensusMechanism(
				SESSION_MANAGER_HOST,
				EXAMPLE_SESSION,
				memberId, portNumber, 
				MEANING_OF_LIFE_CONSENSUS_MECHANISM_NAME, 
				sessionChoice, 
				numMembersToWaitFor());
		return meaningOfLifeMechanism;
	}
	public AMeaningConsensusMemberLauncher(String aLocalName, int aPortNumber) {
		super(aLocalName, aPortNumber);
	}	
	protected void addListenersAndVetoersToConsensusMechanisms() {
		meaningOfLifeMechanism.addConsensusListener(new AMeaningOfLifeConsensusListener());
	}
	@Override
	protected short numMembersToWaitFor() {
		return 4;
	}
}
