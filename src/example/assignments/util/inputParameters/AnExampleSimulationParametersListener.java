package example.assignments.util.inputParameters;

import assignments.util.inputParameters.SimulationParametersListener;
import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;
/**
 * Methods in this class are called when corresponding user commands are invoked.
 * These method simply print the method name and parameters.
 */
public class AnExampleSimulationParametersListener implements SimulationParametersListener {

	@Override
	public void atomicBroadcast(boolean newValue) {
		System.out.println("atomicBroadcast " + newValue);		
	}

	@Override
	public void ipcMechanism(IPCMechanism newValue) {
		System.out.println("ipcMechanism " + newValue);	
	}

	@Override
	public void experimentInput() {
		System.out.println("experimentInput");			
	}
	@Override
	public void localProcessingOnly(boolean newValue) {
		System.out.println("localProcessingOnly " + newValue);			
	}


//	@Override
	public void broadcastBroadcastMode(boolean newValue) {
		System.out.println("broadcastBroadcastMode " + newValue);		
	}

	@Override
	public void waitForBroadcastConsensus(boolean newValue) {
		System.out.println("waitForBroadcastConsensus " + newValue);

		
	}

//	@Override
	public void broadcastIPCMechanism(boolean newValue) {
		System.out.println("broadcastIPCMechanism " + newValue);		
	}

	@Override
	public void waitForIPCMechanismConsensus(boolean newValue) {
		System.out.println("waitForIPCMechanismConsensus " + newValue);		
	}

	@Override
	public void consensusAlgorithm(ConsensusAlgorithm newValue) {
		System.out.println("consensusAlgorithm " + newValue);		
	}

	@Override
	public void quit(int aCode) {
		System.out.println("Quitting with code " + aCode);
		System.exit(aCode);
	}

	@Override
	public void simulationCommand(String aCommand) {
		System.out.println("Simulation command: " + aCommand);
		
	}

	@Override
	public void broadcastMetaState(boolean newValue) {
		System.out.println("broadcastMetaState " + newValue);		

		
	}

	@Override
	public void delaySends(int aMilliseconcDelay) {
		System.out.println("delaySends " + aMilliseconcDelay);		

		
	}

	@Override
	public void rejectMetaStateChange(boolean newValue) {
		System.out.println("rejectMeataStateChange " + newValue);			
	}
	@Override
	public void trace(boolean newValue) {
		System.out.println(
				"trace '" + newValue );
	}

	
	
}
