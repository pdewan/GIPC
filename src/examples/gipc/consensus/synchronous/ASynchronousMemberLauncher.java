package examples.gipc.consensus.synchronous;

import consensus.ConsensusMechanismFactory;
import consensus.ReplicationSynchrony;
import consensus.synchronous.sequential.ASynchronousConsensusMechanismFactory;
import examples.gipc.consensus.AnExampleProposerLauncher;

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
