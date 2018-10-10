package examples.gipc.consensus.paxos;

import examples.gipc.consensus.Member2;
import util.trace.port.consensus.ConsensusTraceUtility;

public class PaxosAcceptor2Launcher extends
APaxosMemberLauncher
		implements Member2 {

	public PaxosAcceptor2Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}


	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		
		new PaxosAcceptor2Launcher(MY_NAME, MY_PORT_NUMBER);
	}

}
