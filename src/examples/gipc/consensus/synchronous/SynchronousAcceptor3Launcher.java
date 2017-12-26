package examples.gipc.consensus.synchronous;

import trace.port.buffer.BufferTraceUtility;
import trace.port.consensus.ConsensusTraceUtility;
import examples.gipc.consensus.AGreetingVetoer;
import examples.gipc.consensus.Member3;

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
