package examples.gipc.consensus.paxos;

import inputport.datacomm.group.object.ADescendingMultipleSendGroupForwarder;
import inputport.datacomm.group.object.ADescendingMultipleSendGroupForwarderFactory;
import inputport.datacomm.group.object.AnAscendingMultipleSendGroupForwarderFactory;
import inputport.datacomm.group.object.MultipleSendGroupForwarderSelector;
import examples.gipc.consensus.Member1;
import examples.gipc.consensus.Member2;
import port.trace.consensus.ConsensusTraceUtility;

public class PaxosProposer2Launcher extends
	APaxosMemberLauncher implements Member2 {

	public PaxosProposer2Launcher(String aLocalName,
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
		MultipleSendGroupForwarderSelector.setMultipleSendGroupForwarderFactory(new ADescendingMultipleSendGroupForwarderFactory());

		new PaxosProposer2Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
