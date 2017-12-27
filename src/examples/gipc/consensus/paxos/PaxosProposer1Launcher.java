package examples.gipc.consensus.paxos;

import inputport.datacomm.group.GroupSendMessageForwarderSelector;
import inputport.datacomm.group.object.AnAscendingMultipleSendGroupForwarderFactory;
import inputport.datacomm.group.object.MultipleSendGroupForwarderSelector;
import inputport.nio.AConnectCommand;
import inputport.nio.AReadCommand;
import inputport.nio.AWriteBoundedBuffer;
import examples.gipc.consensus.ExampleMemberLauncher;
import examples.gipc.consensus.Member1;
import trace.port.consensus.ConsensusTraceUtility;
import trace.port.nio.NIOTraceUtility;
import util.trace.Tracer;

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
