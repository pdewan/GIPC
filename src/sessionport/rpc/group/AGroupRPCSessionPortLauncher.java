package sessionport.rpc.group;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.group.GroupRPCProxyGenerator;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import sessionport.rpc.duplex.direct.example.AModularDuplexRPCDirectSessionPortLauncher;


public class AGroupRPCSessionPortLauncher extends
	AModularDuplexRPCDirectSessionPortLauncher implements GroupRPCSessionPortLauncher
//	extends AnAbstractPortLauncher
	{
	protected Map<String, Object> allRemoteProxy = new HashMap();
	protected Map<String, Object> allRemoteButCallerProxy = new HashMap();
	protected Map<String, Object> allRemoteAndMeProxy = new HashMap();
	protected Map<String, Map<String, Object>> allMembersProxy = new HashMap();
	protected Map<String, Object> myObject = new HashMap();

	protected short numMembersToWaitFor;
	
	
	protected short getNumberOfMemberConnects() {
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
			short aNumMembersToWaitFor) {
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
	public Object lookupAllRemoteButCaller(Class aProxyClass, String aProxyName) {
		Object retVal = allRemoteButCallerProxy.get(aProxyName);
		if (retVal == null) {

			retVal = GroupRPCProxyGenerator.generateOthersRPCProxy((GroupRPCSessionPort) getSessionPort(),  aProxyClass, aProxyName);
			allRemoteButCallerProxy.put(aProxyName, retVal);
		}
		return retVal;
	}
	@Override
	public Object lookupAllRemoteButCaller(String aProxyName) {
		return lookupAllRemoteButCaller(getMyClass(aProxyName), aProxyName);		
	}
//	@Override
//	public Object lookupAllRemoteButCaller(Class anInterface, String aProxyName) {
//		return lookupAllRemoteButCaller(anInterface, aProxyName);		
//	}
	@Override
	public Object lookup(String aRemoteName, Class aProxyClass, String aProxyName) {
		Map<String, Object> aMembersProxy = allMembersProxy.get(aRemoteName);
		if (aMembersProxy == null) {
			aMembersProxy = new HashMap<String, Object>();
			allMembersProxy.put(aRemoteName, aMembersProxy );
		}
		Object retVal = aMembersProxy.get(aProxyName);
		if (retVal == null) {
			if (aRemoteName.equals(getInputPort().getLocalName())) {
				retVal = myObject.get(aProxyName);
			} else {
			retVal = DirectedRPCProxyGenerator.generateRPCProxy((GroupRPCSessionPort) getSessionPort(), 
					aRemoteName, aProxyClass, aProxyName);
			}
			aMembersProxy.put(aProxyName, retVal);
		}
		return retVal;
	}

	@Override
	public Object lookup(String aRemoteName, String aProxyName) {
		return lookup(aRemoteName, getMyClass(aProxyName), aProxyName);
	}
	@Override
	public Object lookupCaller(String aProxyName) {
		return lookupCaller(getMyClass(aProxyName), aProxyName);
	}
	
	@Override
	public Object lookupAllRemote(Class aProxyClass, String aProxyName) {
		Object retVal = allRemoteProxy.get(aProxyName);
		if (retVal == null) {
			retVal = GroupRPCProxyGenerator.generateAllRPCProxy((GroupRPCSessionPort) getSessionPort(),  aProxyClass, aProxyName);
			allRemoteProxy.put(aProxyName, retVal);
		}
		return retVal;
	}

	@Override
	public Object lookupAllRemote(String aProxyName) {
		return lookupAllRemote(getMyClass(aProxyName), aProxyName);

	}
	@Override
	public Set<String> getAllRemoteMembers() {
		Set<String> retVal = new HashSet( getSessionPort().getMemberConnections());
		return retVal;
	}
	@Override
	public Set<String> getAllMembers() {
		Set<String> retVal = getAllRemoteMembers();
		retVal.add(getSessionPort().getLocalName());
		return retVal;
	}

	@Override
	public Object lookupAll(String aProxyName) {
		
		return lookupAll(getMyClass(aProxyName), aProxyName);
	}
	
	
	public Object lookupAll(Class aProxyClass, String aProxyName) {
		Object retVal = allRemoteAndMeProxy.get(aProxyName);
		if (retVal == null) {
			retVal = GroupRPCProxyGenerator.generateAllAndMeRPCProxy((GroupRPCSessionPort) getSessionPort(),  aProxyClass, aProxyName);
			allRemoteAndMeProxy.put(aProxyName, retVal);
		}
		return retVal;
	}

	@Override
	public GroupRPCSessionPort getSessionPort() {
		return (GroupRPCSessionPort) getInputPort();
	}

	
	
}
