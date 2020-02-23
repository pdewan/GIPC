package assignments.util.inputParameters;

import util.interactiveMethodInvocation.ConsensusAlgorithm;
import util.interactiveMethodInvocation.IPCMechanism;
import util.trace.port.consensus.ProposalMade;
import util.trace.port.consensus.ProposedStateSet;
import util.trace.port.consensus.communication.CommunicationStateNames;

public abstract class AnAbstractSimulationParametersBean implements SimulationParametersBean{
	protected boolean localProcessingOnly = false;
	protected boolean broadcastBroadcastMode = false;
	protected boolean broadcastIPCMechanism = false;
	protected boolean broadcastMetaState = false;


	
	protected boolean waitForBroadcastConsensus = false;
	protected boolean waitForIPCMechanismConsensus = false;
	protected ConsensusAlgorithm consensusAlgorithm = ConsensusAlgorithm.CENTRALIZED_ASYNCHRONOUS;
	protected boolean rejectMetaStateChange = false;

	
	protected Boolean atomicBroadcast = false;
	
	protected int delay = 0;
	
	protected boolean trace = false;
	
	
	@Override
	public synchronized Boolean isAtomicBroadcast() {
		return atomicBroadcast;
	}
	@Override
	public boolean isLocalProcessingOnly() {
		return localProcessingOnly;
	}
	@Override
	public void setLocalProcessingOnly(boolean localProcessingOnly) {
		this.localProcessingOnly = localProcessingOnly;
	}
	public boolean isBroadcastBroadcastMode() {
		return broadcastBroadcastMode;
	}
	public void setBroadcastBroadcastMode(boolean broadcastBroadcastMode) {
		this.broadcastBroadcastMode = broadcastBroadcastMode;
	}
	public boolean isBroadcastIPCMechanism() {
		return broadcastIPCMechanism;
	}
	public void setBroadcastIPCMechanism(boolean broadcastIPCMechanism) {
		this.broadcastIPCMechanism = broadcastIPCMechanism;
	}
	public boolean isWaitForBroadcastConsensus() {
		return waitForBroadcastConsensus;
	}
	public void setWaitForBroadcastConsensus(boolean waitForBroadcastConsensus) {
		this.waitForBroadcastConsensus = waitForBroadcastConsensus;
	}
	public boolean isWaitForIPCMechanismConsensus() {
		return waitForIPCMechanismConsensus;
	}
	public void setWaitForIPCMechanismConsensus(boolean waitForIPCMechanismConsensus) {
		this.waitForIPCMechanismConsensus = waitForIPCMechanismConsensus;
	}
	public ConsensusAlgorithm getConsensusAlgorithm() {
		return consensusAlgorithm;
	}
	public void setConsensusAlgorithm(ConsensusAlgorithm consensusAlgorithm) {
		this.consensusAlgorithm = consensusAlgorithm;

	}
	@Override
	public synchronized void setAtomicBroadcast(Boolean newValue) {
		ProposedStateSet.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue);
		atomicBroadcast = newValue;
	}
	@Override
	public void atomicBroadcast(boolean newValue) {
		ProposalMade.newCase(this, CommunicationStateNames.BROADCAST_MODE, -1, newValue);
		setAtomicBroadcast(newValue);
	}
	@Override
	public synchronized void setIPCMechanism(IPCMechanism newValue) {
		ProposedStateSet.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue);
		ipcMechanism = newValue;
	}
	protected IPCMechanism ipcMechanism = IPCMechanism.NIO;
	@Override
	public synchronized IPCMechanism getIPCMechanism() {
		return ipcMechanism;
	}

	@Override
	public void ipcMechanism(IPCMechanism newValue) {
		ProposalMade.newCase(this, CommunicationStateNames.IPC_MECHANISM, -1, newValue);
		setIPCMechanism(newValue);
	}
	
	@Override
	public void localProcessingOnly(boolean newValue) {
		setLocalProcessingOnly(newValue);
		
	}
//	@Override
	public void broadcastBroadcastMode(boolean newValue) {
		setBroadcastBroadcastMode(newValue);		
	}
	@Override
	public void broadcastMetaState(boolean newValue) {
		setBroadcastMetaState(newValue);		
	}
	@Override
	public void waitForBroadcastConsensus(boolean newValue) {
		setWaitForBroadcastConsensus(newValue);		
	}
//	@Override
	public void broadcastIPCMechanism(boolean newValue) {
		setBroadcastIPCMechanism(newValue);		
	}
	@Override
	public boolean isBroadcastMetaState() {
		return broadcastMetaState;
	}
	@Override
	public void setBroadcastMetaState(boolean broadcastMetaState) {
		this.broadcastMetaState = broadcastMetaState;
		broadcastBroadcastMode(broadcastMetaState);
		broadcastIPCMechanism(broadcastMetaState);
	}
	@Override
	public void waitForIPCMechanismConsensus(boolean newValue) {
		setWaitForIPCMechanismConsensus(newValue);		
	}
	@Override
	public void consensusAlgorithm(ConsensusAlgorithm newValue) {
		setConsensusAlgorithm(newValue);		
	}
	public void simulationCommand(String aCommand) {
		ProposalMade.newCase(this, CommunicationStateNames.COMMAND, -1, aCommand);
	}
	@Override
	public void delaySends(int aMillisecondDelay) {
		setDelay(aMillisecondDelay);		
	}
	@Override
	public int getDelay() {
		return delay;
	}
	@Override
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public boolean isRejectMetaStateChange() {
		return rejectMetaStateChange;
	}
	public void setRejectMetaStateChange(boolean rejectMetaStateChange) {
		this.rejectMetaStateChange = rejectMetaStateChange;
	}
	public void rejectMetaStateChange(boolean newValue) {
		setRejectMetaStateChange(newValue);
	}
	@Override
	public void trace(boolean newValue) {
		setTrace(newValue);
	}
	@Override
	public boolean isTrace() {
		return trace;
	}
	@Override
	public void setTrace(boolean trace) {
		this.trace = trace;
	}
	
}
