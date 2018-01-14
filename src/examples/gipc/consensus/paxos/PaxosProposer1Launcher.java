package examples.gipc.consensus.paxos;

import inputport.datacomm.group.GroupSendMessageForwarderSelector;
import inputport.datacomm.group.object.AnAscendingMultipleSendGroupForwarderFactory;
import inputport.datacomm.group.object.MultipleSendGroupForwarderSelector;
import inputport.nio.manager.AConnectCommand;
import inputport.nio.manager.AReadCommand;
import inputport.nio.manager.AWriteBoundedBuffer;
import examples.gipc.consensus.ExampleMemberLauncher;
import examples.gipc.consensus.Member1;
import util.trace.Tracer;
import util.trace.port.consensus.ConsensusTraceUtility;
import util.trace.port.nio.NIOTraceUtility;

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
		NIOTraceUtility.setTracing();;
		new PaxosProposer1Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
