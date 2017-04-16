package examples.gipc.consensus.centralizable;

import examples.gipc.consensus.ExampleMemberLauncher;
import examples.gipc.consensus.Member1;
import port.trace.consensus.ConsensusTraceUtility;

public class CentralizableProposer1Launcher extends
		ACentralizableMemberLauncher implements Member1 {

	public CentralizableProposer1Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
		// TODO Auto-generated constructor stub
	}
	protected void customizeMeaningOfLifeConsensusMechanism(){
		super.customizeMeaningOfLifeConsensusMechanism();
		meaningOfLifeMechanism.setIsServer(true);
	}
	
	protected void doPropose() {
		proposeValues1();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new CentralizableProposer1Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
