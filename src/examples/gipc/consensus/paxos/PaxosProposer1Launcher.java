package examples.gipc.consensus.paxos;

import examples.gipc.consensus.Member1;

public class PaxosProposer1Launcher extends
		APaxosMemberLauncher implements Member1 {

	public PaxosProposer1Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	
	
	protected void doPropose() {
//		proposeGreeting("Hello");
		proposeValues1();
	}

	public static void main(String[] args) {
//		ConsensusTraceUtility.setTracing();
//		NIOTraceUtility.setTracing();;
		new PaxosProposer1Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
