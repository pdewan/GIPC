package examples.gipc.consensus.paxos;

import inputport.datacomm.group.GroupSendMessageForwarderSelector;
import inputport.datacomm.group.object.ADescendingMultipleSendGroupForwarder;
import inputport.datacomm.group.object.ADescendingMultipleSendGroupForwarderFactory;
import inputport.datacomm.group.object.AnAscendingMultipleSendGroupForwarderFactory;
import inputport.datacomm.group.object.MultipleSendGroupForwarderSelector;
import examples.gipc.consensus.Member1;
import examples.gipc.consensus.Member2;
import examples.gipc.consensus.Member3;
import util.trace.port.consensus.ConsensusTraceUtility;

public class PaxosProposer3Launcher extends
	APaxosMemberLauncher implements Member3 {

	public PaxosProposer3Launcher(String aLocalName,
			int aPortNumber) {
		super(aLocalName, aPortNumber);
	}
	protected void doPropose() {
	  proposeValues2();
     }

	public static void main(String[] args) {
		ConsensusTraceUtility.setTracing();
//		GroupSendMessageForwarderSelector.setGroupSendMessageForwarderFactory(
//				new APaxosMultiCastFactory());
		new PaxosProposer3Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
