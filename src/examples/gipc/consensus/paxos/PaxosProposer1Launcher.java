package examples.gipc.consensus.paxos;

import inputport.datacomm.group.GroupSendMessageForwarderSelector;
import inputport.datacomm.group.object.AnAscendingMultipleSendGroupForwarderFactory;
import inputport.datacomm.group.object.MultipleSendGroupForwarderSelector;
import examples.gipc.consensus.ExampleMemberLauncher;
import examples.gipc.consensus.Member1;
import port.trace.consensus.ConsensusTraceUtility;

public class PaxosProposer1Launcher extends
		APaxosMemberLauncher implements Member1 {

	public PaxosProposer1Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	
	
	protected void doPropose() {
		proposeValues1();
	}

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
//		GroupSendMessageForwarderSelector.setGroupSendMessageForwarderFactory(
//				new APaxosMultiCastFactory());
		new PaxosProposer1Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
