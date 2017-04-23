package examples.gipc.consensus.paxos;

import inputport.datacomm.group.ADescendingMultipleSendGroupForwarder;
import inputport.datacomm.group.ADescendingMultipleSendGroupForwarderFactory;
import inputport.datacomm.group.AnAscendingMultipleSendGroupForwarderFactory;
import inputport.datacomm.group.object.MultipleSendGroupForwarderSelector;
import examples.gipc.consensus.Member1;
import examples.gipc.consensus.Member2;
import examples.gipc.consensus.Member3;
import port.trace.consensus.ConsensusTraceUtility;

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
		MultipleSendGroupForwarderSelector.setMultipleSendGroupForwarderFactory(
				new APaxosMultiCastFactory());
//		MultipleSendGroupForwarderSelector.setMultipleSendGroupForwarderFactory(new ADescendingMultipleSendGroupForwarderFactory());

		new PaxosProposer3Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
