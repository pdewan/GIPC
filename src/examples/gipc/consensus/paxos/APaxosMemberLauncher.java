package examples.gipc.consensus.paxos;

import consensus.ConcurrencyKind;
import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ReplicationSynchrony;
import consensus.central.ACentralizableConsensusMechanismFactory;
import consensus.paxos.APaxosConsensusMechanismFactory;
import consensus.synchronous.ASynchronousConsensusMechanism;
import consensus.synchronous.ASynchronousConsensusMechanismFactory;
import consensus.synchronous.Accepted;
import consensus.synchronous.Acceptor;
import examples.gipc.consensus.AnExampleConsensusProposerLauncher;

public class APaxosMemberLauncher extends AnExampleConsensusProposerLauncher  {

	public APaxosMemberLauncher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory() {
		return new APaxosConsensusMechanismFactory();
	}

	@Override
	protected ConsensusMechanismFactory<String> greetingConsensusMechanismFactory() {
		return new APaxosConsensusMechanismFactory<>();	
		}

	protected void customizeMeaningOfLifeConsensusMechanism(){
		meaningOfLifeMechanism.setCentralized(false);
		meaningOfLifeMechanism.setConcurrencyKind(ConcurrencyKind.SERIALIZABLE);

	}	
	@Override
	protected short numMembersToWaitFor() {
		return 3;
	}

	
	

}
