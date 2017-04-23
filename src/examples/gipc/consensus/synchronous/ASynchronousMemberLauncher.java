package examples.gipc.consensus.synchronous;

import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ReplicationSynchrony;
import consensus.synchronous.ASynchronousConsensusMechanism;
import consensus.synchronous.ASynchronousConsensusMechanismFactory;
import consensus.synchronous.Accepted;
import consensus.synchronous.Acceptor;
import examples.gipc.consensus.AMeaningConsensusProposerLauncher;
import examples.gipc.consensus.AnExampleGreetingMeaningConsensusProposerLauncher;

public class ASynchronousMemberLauncher extends AMeaningConsensusProposerLauncher  {

	public ASynchronousMemberLauncher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	@Override
	protected ConsensusMechanismFactory<Integer> meaningConsensusMechanismFactory() {
		return new ASynchronousConsensusMechanismFactory<>();
	}
	@Override
	protected void customizeConsensusMechanisms() {
		meaningOfLifeMechanism.setAcceptSynchrony(ReplicationSynchrony.TWO_SYNCHRONOUS);
		
	}
}
