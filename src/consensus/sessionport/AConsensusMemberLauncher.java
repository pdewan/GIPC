package consensus.sessionport;

import port.SessionChoice;
import sessionport.rpc.group.GIPCLocateSessionRegistry;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;

public abstract class AConsensusMemberLauncher  {
//	protected  ConsensusMechanism<String> greetingMechanism;
//	protected  ConsensusMechanism<Integer> meaningOfLifeMechanism;
//	protected  Object remoteGreetingMechanism;
//	protected  Object remoteMeaningOfLifeMechanism;
	protected  GIPCSessionRegistry gipcRegistry;
	protected  GroupRPCSessionPort groupRPCSessionPort;
//	protected  Integer numMembersToWaitFor = 2;
	protected  SessionChoice sessionChoice = SessionChoice.P2P;
	
//	protected  final int MY_PORT_NUMBER = 7001;
//	protected  final String MY_NAME = "1";
//	public static final String GREETING_1 = "Hello";
//	public static final String GREETING_2 = "Howdy";
//	public static int MEANING = 42;
	
	protected abstract Class remoteReceiverConsensusClass();
	protected abstract Class remoteCallerConsensusClass();

	
	protected abstract short numMembersToWaitFor() ;
//	protected abstract Object lookupSessionProxy(Class aClass, String aName) ;

	protected abstract void initConsensusMechanisms(short anId) ;
	protected Object lookupMulticastProxy(Class aClass, String aName) {
		return gipcRegistry.lookupAll(aClass, aName);
	}

	public  void init(String aLocalName, int aPortNumber) {
//		ConsensusTraceUtility.setTracing();

		gipcRegistry = GIPCLocateSessionRegistry.createSessionRegistry(
				"mysession", "localhost", aPortNumber, aLocalName,
				sessionChoice, 
				numMembersToWaitFor());
		groupRPCSessionPort = gipcRegistry.getSessionPort();
		short anId = Short.parseShort(aLocalName);
		initConsensusMechanisms(anId);	
	}
	

}
