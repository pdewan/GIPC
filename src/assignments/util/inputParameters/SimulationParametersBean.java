package assignments.util.inputParameters;

import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;

/**
 * 
 * @author Dewan
 * Keeps state, in the form of getters and setters, of global parameters changed by methods
 * of SimulationParametersListener
 *
 */
public interface SimulationParametersBean extends SimulationParametersListener {
	Boolean isAtomicBroadcast();

	void setAtomicBroadcast(Boolean newValue);

	void setIPCMechanism(IPCMechanism newValue);

	IPCMechanism getIPCMechanism();
	public boolean isBroadcastBroadcastMode();
	public void setBroadcastBroadcastMode(boolean broadcastBroadcastMode) ;
	public boolean isWaitForBroadcastConsensus() ;
	public void setWaitForBroadcastConsensus(boolean waitForBroadcastConsensus) ;
	public boolean isWaitForIPCMechanismConsensus() ;
	public void setWaitForIPCMechanismConsensus(boolean waitForIPCMechanismConsensus) ;
	public ConsensusAlgorithm getConsensusAlgorithm() ;
	public void setConsensusAlgorithm(ConsensusAlgorithm consensusAlgorithm) ;

	boolean isLocalProcessingOnly();

	void setLocalProcessingOnly(boolean localProcessingOnly);

	boolean isBroadcastMetaState();

	void setBroadcastMetaState(boolean broadcastMetaState);

	int getDelay();

	void setDelay(int delay);
	public void setRejectMetaStateChange(boolean rejectMetaStateChange);
	public boolean isRejectMetaStateChange();



}
