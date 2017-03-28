package sessionport.rpc.group;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import examples.mvc.local.simplex.SimplexUpperCaser;
import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.simplex.SimplexRPCServerInputPort;
import port.ATracingConnectionListener;
import port.AnAbstractPortLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import replicatedserverport.rpc.group.ReplicatedServerSessionPortSelector;
import replicatedserverport.rpc.group.flexibleresponse.flexible.AnEarliestReponseReplicatedSessionServerLauncher;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import sessionport.rpc.duplex.DuplexRPCSessionPortSelector;
import sessionport.rpc.duplex.direct.example.AModularDuplexRPCDirectSessionPortLauncher;
import sessionport.rpc.duplex.relayed.example.ACallingConnectListener;
import sessionport.rpc.duplex.relayed.example.Adder;
import sessionport.rpc.duplex.relayed.example.AnAdder;
import sessionport.rpc.group.direct.example.AGroupCallingConnectListener;


public class AGroupRPCSessionPortLauncher extends
	AModularDuplexRPCDirectSessionPortLauncher implements GroupRPCSessionPortLauncher
//	extends AnAbstractPortLauncher
	{
	protected Map<String, Object> allRemoteProxy = new HashMap();
	protected Map<String, Object> allRemoteButCallerProxy = new HashMap();
	protected Map<String, Object> allRemoteAndMeProxy = new HashMap();
	protected Map<String, Map<String, Object>> allMembersProxy = new HashMap();
	protected Map<String, Object> myObject = new HashMap();

	protected Integer numMembersToWaitFor;
	
	
	protected Integer getNumberOfMemberConnects() {
		return numMembersToWaitFor;
	}

	public AGroupRPCSessionPortLauncher (String aSessionServerHost, 
			String aServerId, 
			String aServerName, 
			String aMyId, 
			String aMyName, 
			String aSessionName,
			SessionChoice aSessionChoice, // not used
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice,
			Integer aNumMembersToWaitFor) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
		Map<String, Object> aMembersProxy = new HashMap();
		numMembersToWaitFor = aNumMembersToWaitFor;
	}

//	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
//		return new AGroupCallingConnectListener((GroupRPCSessionPort) mainPort);
//	}

	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.GROUP;
	}	
//	protected SessionChoice getSessionChoice() {
//		return SessionChoice.P2P;
//	}
	
//	protected int getNumberOfConnects() {
//		return super.getNumberOfConnects() + numMembersToWaitFor;
//
//	}

	@Override
	public void rebind(String aProxyName, Object anObject) {
		myObject.put(aProxyName, anObject);
		getSessionPort().register(aProxyName, anObject);
	}

	protected Class getMyClass(String aProxyName) {
			Object aMyObject = myObject.get(aProxyName);
			if (aMyObject == null) {
				System.err.println (aProxyName + " not registered in rebind");
				return null;
			}
			Class aMyClass = aMyObject.getClass();
			return aMyClass;
	}
//		protected Object lookupProxy(String aProxyName, Map<String, Object> aProxyMap) {
//			Object retVal = aProxyMap.get(aProxyName);
//			if (retVal == null) {
//				Object aMyObject = myObject.get(aProxyName);
//				if (aMyObject == null) {
//					System.err.println (aProxyName + " not registered in rebind");
//					return null;
//				}
//				Class aMyClass = aMyObject.getClass();
//				retVal = GroupRPCProxyGenerator.generateOthersRPCProxy((GroupRPCSessionPort) getSessionPort(),  aMyClass, aProxyName);
//				aProxyMap.put(aProxyName, retVal);
//			}
//			return retVal;
//		}
	@Override
	public Object lookupAllRemoteButCallerProxy(String aProxyName) {
		Object retVal = allRemoteButCallerProxy.get(aProxyName);
		if (retVal == null) {
//			Object aMyObject = myObject.get(aProxyName);
//			if (aMyObject == null) {
//				System.err.println (aProxyName + " not registered in rebind");
//				return null;
//			}
//			Class aMyClass = aMyObject.getClass();
			retVal = GroupRPCProxyGenerator.generateOthersRPCProxy((GroupRPCSessionPort) getSessionPort(),  getMyClass(aProxyName), aProxyName);
			allRemoteButCallerProxy.put(aProxyName, retVal);
		}
		return retVal;
	}

	@Override
	public Object lookupMemberProxy(String aRemoteName) {
		return null;
	}

	@Override
	public Object lookupAllRemoteProxy(String aProxyName) {
		Object retVal = allRemoteProxy.get(aProxyName);
		if (retVal == null) {
			retVal = GroupRPCProxyGenerator.generateAllRPCProxy((GroupRPCSessionPort) getSessionPort(),  getMyClass(aProxyName), aProxyName);
			allRemoteProxy.put(aProxyName, retVal);
		}
		return retVal;
	}

	@Override
	public Object lookupAllRemoteAndMeProxy(String aProxyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupRPCSessionPort getSessionPort() {
		return (GroupRPCSessionPort) getInputPort();
	}
	
}
