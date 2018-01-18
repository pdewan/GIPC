package assignments.util.inputParameters;

public class AnExampleSimulationParametersListener implements SimulationParametersListener {
/*
 * Run this program see how the following inputs are translated to method calls:
 * p
 * a t
 * a f
 * i t
 * atomic t
 * e
 * l f
 */
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


	@Override
	public void broadcastBroadcastMode(boolean newValue) {
		System.out.println("broadcastBroadcastMode " + newValue);
		
	}

	@Override
	public void waitForBroadcastConsensus(boolean newValue) {
		
		
	}

	@Override
	public void broadcastIPCMechanism(boolean newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForIPCMechanismConsensus(boolean newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void consensusAlgorithm(ConsensusAlgorithm newValue) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		SimulationParametersListener anExampleParameterListener = 
				new AnExampleSimulationParametersListener();
		SimulationParametersController aSimulationParametersController = 
				new ASimulationParametersController();
		aSimulationParametersController.addSimulationParameterListener(anExampleParameterListener);
		aSimulationParametersController.processCommands();
		
	}

	
}
