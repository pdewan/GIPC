package assignments.util.inputParameters;

import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;

public interface SimulationParametersListener {
	default void atomicBroadcast(boolean newValue) {
		System.out.println("atomicBroadcast called with argument '" + newValue + "' but it has not been implemented.");
	}

	/**
	 * Send programmed input
	 */
	default void experimentInput() {
		System.out.println("experimentInput called but it has not been implemented.");
	}

	default void localProcessingOnly(boolean newValue) {
		System.out.println(
				"localProcessingOnly called with argument '" + newValue + "' but it has not been implemented.");
	}

	default void ipcMechanism(IPCMechanism newValue) {
		System.out.println("ipcMechanism called with argument '" + newValue + "' but it has not been implemented.");
	}

	default void broadcastMetaState(boolean newValue) {
		System.out.println("broadcastMetaState called with argument '" + newValue + "' but it has not been implemented.");
	}

//	default void broadcastBroadcastMode(boolean newValue) {
//		System.out.println("broadcastBroadcastMode called with argument '" + newValue + "' but it has not been implemented.");
//	}
	
	default void delaySends(int aMillisecondDelay) {
		System.out.println("delaySends called with argument '" + aMillisecondDelay + "' but it has not been implemented.");
	}

	default void waitForBroadcastConsensus(boolean newValue) {
		System.out.println("waitForBroadcastConsensus called with argument '" + newValue + "' but it has not been implemented.");
	}

//	 default void broadcastIPCMechanism(boolean newValue) {
//		System.out.println("broadcastIPCMechanism called with argument '" + newValue + "' but it has not been implemented.");
//	}
	 
	default void waitForIPCMechanismConsensus(boolean newValue) {
		System.out.println("waitForIPCMechanismConsensus called with argument '" + newValue
				+ "' but it has not been implemented.");
	}

	default void consensusAlgorithm(ConsensusAlgorithm newValue) {
		System.out.println("consensusAlgorithm called with argument '" + newValue + "' but it has not been implemented.");
	}

	default void quit(int aCode) {
		System.out.println("quit called with argument '" + aCode + "' but it has not been implemented.");
	}

	default void rejectMetaStateChange(boolean newValue) {
		System.out.println("rejectMetaStateChange called with argument '" + newValue + "' but it has not been implemented.");
	}

	/**
	 * simulationCommand
	 */
	default void simulationCommand(String aCommand) {
		System.out.println("simulationCommand called with argument '" + aCommand + "' but it has not been implemented.");
	}
}
