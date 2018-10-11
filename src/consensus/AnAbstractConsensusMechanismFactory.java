package consensus;

import port.SessionChoice;
import sessionport.rpc.group.GIPCLocateSessionRegistry;
import sessionport.rpc.group.GIPCSessionRegistry;

public abstract class AnAbstractConsensusMechanismFactory<StateType> implements ConsensusMechanismFactory<StateType>{
//	protected  GIPCSessionRegistry gipcRegistry;
//	protected  GroupRPCSessionPort groupRPCSessionPort;
//	protected  Integer numMembersToWaitFor = 2;
//	protected  SessionChoice sessionChoice = SessionChoice.P2P;
	
	protected abstract ConsensusMechanism instantiateConsensusMehanism(GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) ;
	
	@Override
	public ConsensusMechanism createConsensusMechanism(String aSessionServerHost, 
			String aSessionName,
			short aMemberId,
			int aPortNumber, String anObjectName, SessionChoice aSessionChoice,
			short aNumMembersToWaitFor) {
		String aMemberName = aMemberId + "";
		GIPCSessionRegistry gipcRegistry = GIPCLocateSessionRegistry.createSessionRegistryWithoutConnecting(
				aSessionName, aSessionServerHost, aPortNumber, aMemberName,
				aSessionChoice, 
				aNumMembersToWaitFor);
//		groupRPCSessionPort = gipcRegistry.getSessionPort();
		short anId = Short.parseShort(""+ aMemberId);
		ConsensusMechanism aConsensusMechanism = instantiateConsensusMehanism(gipcRegistry, anObjectName, anId);
		gipcRegistry.rebind(anObjectName, aConsensusMechanism);	
		gipcRegistry.connect();
		return aConsensusMechanism;
	}
	public ConsensusMechanism createConsensusMechanism(
			String aSessionName,
			short aMemberId, String anObjectName) {
		String aMemberName = aMemberId + "";
		GIPCSessionRegistry gipcRegistry = GIPCLocateSessionRegistry.createSessionRegistryWithoutConnecting(
				aSessionName);
//		groupRPCSessionPort = gipcRegistry.getSessionPort();
		short anId = Short.parseShort(""+ aMemberId);
		ConsensusMechanism aConsensusMechanism = instantiateConsensusMehanism(gipcRegistry, anObjectName, anId);
		gipcRegistry.rebind(anObjectName, aConsensusMechanism);	
//		gipcRegistry.connect();
		return aConsensusMechanism;
	}

}
