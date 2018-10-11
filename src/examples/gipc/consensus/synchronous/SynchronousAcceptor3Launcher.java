package examples.gipc.consensus.synchronous;

import examples.gipc.consensus.Member3;
import util.trace.port.consensus.ConsensusTraceUtility;

public class SynchronousAcceptor3Launcher extends ASynchronousMemberLauncher
		implements Member3 {
	public SynchronousAcceptor3Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
		new SynchronousAcceptor3Launcher(MY_NAME, MY_PORT_NUMBER);
	}
}
