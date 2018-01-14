package examples.gipc.consensus.centralizable;

import examples.gipc.consensus.ExampleMemberLauncher;
import examples.gipc.consensus.Member1;
import util.trace.port.consensus.ConsensusTraceUtility;

public class CentralizableProposer1Launcher extends
		ACentralizableMemberLauncher implements Member1 {

	public CentralizableProposer1Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
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
