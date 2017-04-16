package examples.gipc.consensus.centralizable;

import examples.gipc.consensus.Member1;
import examples.gipc.consensus.Member2;
import port.trace.consensus.ConsensusTraceUtility;

public class CentralizableProposer2Launcher extends
	ACentralizableMemberLauncher implements Member2 {

	public CentralizableProposer2Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}

//	public void proposeValues() {
//		proposeValues2();
//	}
	
	protected void doPropose() {
	  proposeValues2();
     }

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new CentralizableProposer2Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
