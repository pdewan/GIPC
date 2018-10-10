package examples.gipc.consensus.paxos.scenarios;

import examples.gipc.consensus.Member3;
import util.trace.port.consensus.ConsensusTraceUtility;

public class PaxosScenariosProposer3Launcher extends
	APaxosScenariosMemberLauncher implements Member3 {

	public PaxosScenariosProposer3Launcher(String aLocalName,
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
		new PaxosScenariosProposer3Launcher(MY_NAME, MY_PORT_NUMBER)
				.proposeValues();
	}

}
