package assignments.util.inputParameters;

import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;

public interface SimulationParametersListener {
	void atomicBroadcast(boolean newValue);
	/**
	 * Send programmed input
	 */
	void experimentInput();
	void localProcessingOnly(boolean newValue);
	void ipcMechanism(IPCMechanism newValue);
	void broadcastBroadcastMode(boolean newValue);
	void waitForBroadcastConsensus(boolean newValue);
	void broadcastIPCMechanism(boolean newValue);
	void waitForIPCMechanismConsensus(boolean newValue);
	void consensusAlgorithm(ConsensusAlgorithm newValue);
	void quit(int aCode);
}
