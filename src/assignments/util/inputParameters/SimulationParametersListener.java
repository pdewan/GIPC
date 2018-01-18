package assignments.util.inputParameters;

public interface SimulationParametersListener {
	void atomicBroadcast(boolean newValue);
	void ipcMechanism(IPCMechanism newValue);
	void experimentInput();
	void broadcastBroadcastMode(boolean newValue);
	void waitForBroadcastConsensus(boolean newValue);
	void broadcastIPCMechanism(boolean newValue);
	void waitForIPCMechanismConsensus(boolean newValue);
	void consensusAlgorithm(ConsensusAlgorithm newValue);
}
