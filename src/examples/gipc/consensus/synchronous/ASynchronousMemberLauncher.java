package examples.gipc.consensus.synchronous;

import consensus.ConsensusMechanism;
import consensus.ConsensusMechanismFactory;
import consensus.ReplicationSynchrony;
import consensus.synchronous.sequential.ASynchronousConsensusMechanism;
import consensus.synchronous.sequential.ASynchronousConsensusMechanismFactory;
import consensus.synchronous.sequential.Accepted;
import consensus.synchronous.sequential.Acceptor;
import examples.gipc.consensus.AnExampleProposerLauncher;
import examples.gipc.consensus.AnExampleGreetingMeaningConsensusProposerLauncher;

public class ASynchronousMemberLauncher extends AnExampleProposerLauncher  {

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
