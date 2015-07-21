package port;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrar;
import inputport.datacomm.duplex.buffer.ADuplexBufferInputPortLauncherSupport;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.duplex.object.ADuplexObjectInputPortLauncherSupport;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.group.buffer.AGroupBufferInputPortLauncherSupport;
import inputport.datacomm.group.object.AGroupObjectInputPortLauncherSupport;
import inputport.datacomm.group.object.GroupObjectInputPortSelector;
import inputport.datacomm.simplex.buffer.ASimplexBufferInputPortLauncherSupport;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.datacomm.simplex.buffer.SendRegistrar;
import inputport.datacomm.simplex.buffer.SimplexBufferInputPortSelector;
import inputport.datacomm.simplex.object.ASimplexObjectInputPortLauncherSupport;
import inputport.datacomm.simplex.object.SimplexObjectInputPortSelector;
import inputport.rpc.RPCProxyGenerator;
import inputport.rpc.RPCProxyGeneratorHolder;
import inputport.rpc.RPCRegistry;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.duplex.ReplyRPCProxyGenerator;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.group.GroupRPCServerInputPort;
import inputport.rpc.simplex.SimplexRPCInputPortSelector;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import port.causal.ACausalGroupSessionPortLauncherSupport;
import port.delay.AClientDelayingPortLauncherSupport;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import port.trace.ConnectionEventManagerFactory;
import sessionport.datacomm.duplex.buffer.BufferDuplexSessionPortSelector;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.group.buffer.BufferGroupSessionPortSelector;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.rpc.duplex.DuplexRPCSessionPortSelector;
import sessionport.rpc.group.GroupRPCSessionPortSelector;
import staticsessionport.datacomm.duplex.buffer.BufferDuplexStaticSessionPortSelector;
import staticsessionport.datacomm.duplex.object.ObjectDuplexStaticSessionPortSelector;
import staticsessionport.datacomm.group.buffer.BufferGroupStaticSessionPortSelector;
import staticsessionport.datacomm.group.object.GroupObjectStaticSessionPortSelector;
import staticsessionport.rpc.duplex.DuplexRPCStaticSessionPortSelector;
import staticsessionport.rpc.group.GroupRPCStaticSessionPortSelector;

public abstract class AnAbstractPortLauncher implements PortLauncher, ConnectionListener, Runnable {
;
	public static final SessionChoice DEFAULT_SESSION_CHOICE = SessionChoice.P2P; 
	public static final ParticipantChoice DEFAULT_PARTICIPANT_CHOICE = ParticipantChoice.MEMBER; 
	public static final String DEFAULT_SESSION_NAME = "Session"; 


	protected List<PortLauncherSupport> portLauncherSupports;
	protected InputPort mainPort;
	protected InputPort auxilliaryPort;
	protected InputPort tertiaryPort;
	protected SessionParticipantDescription[] serverList;
	protected ParticipantBindTime participantBindTime = ParticipantBindTime.DYNAMIC;

	boolean asyncOperationsDone;
	int numPendingConnects;
	Integer numPendingServerConnects;
	
	public final static String SERVER_NAME = "Generic Server";
	public final static String SERVER_ID = "9090";
	public final static String SERVER_HOST = "localhost";
	protected String serverName = SERVER_NAME;
	protected String serverId = SERVER_ID;
	protected String serverHost = SERVER_HOST;

	protected boolean shouldDelay;
	protected boolean doCausal;
	protected PortLauncherSupport delaysSupport;
	private String sessionName;
	

	
	private SessionChoice sessionChoice;

	protected PortDescription portDescription;

	protected boolean doJitter;
	protected String clientName;
	protected String clientId;
	protected String clientHost;
	protected ServerPortDescription clientPortDescription;
	private ParticipantChoice participantChoice;


	public AnAbstractPortLauncher(String aServerName, String aServerId) {
		if (serverName != null)
		serverName = aServerName;
		if (serverId != null) {
		serverId = aServerId;

		}
	}
	// need init routines
	public AnAbstractPortLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		clientName = aClientName;
		serverHost = aServerHost;
		serverId = aServerId;;
		serverName = aServerName;		
	}
	public AnAbstractPortLauncher(String aSessionServerHost, 
			String aServerId, 
			String aServerName, 
			String aMyId, 
			String aMyName, 
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		serverHost = aSessionServerHost;
		serverId = aServerId;;
		serverName = aServerName;	
		serverName = aServerName;
		clientId = aMyId;
		clientName = aMyName;
		try {
			clientHost = InetAddress.getLocalHost().getCanonicalHostName();
			} catch (Exception e) {
				e.printStackTrace();
			}
		sessionChoice = aSessionChoice;
		shouldDelay = aShouldDelay;
		delaysSupport = aDelaysSupport;
		doCausal = aDoCausal;
		clientPortDescription = new AServerPortDescription(clientHost,clientId, clientName);
		participantChoice = aChoice;
		sessionName = aSessionName;
		doJitter = aDoJitter;
				
		
	}
	public AnAbstractPortLauncher(SessionParticipantDescription[] aServerList, String aMyId, String aMyName,

			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		clientId = aMyId;
		clientName = aMyName;	
		serverList = aServerList;
		sessionChoice = SessionChoice.P2P;
		participantBindTime = ParticipantBindTime.STATIC;
		try {
			clientHost = InetAddress.getLocalHost().getCanonicalHostName();
			} catch (Exception e) {
				e.printStackTrace();
			}
		sessionChoice = aSessionChoice;
		shouldDelay = aShouldDelay;
		delaysSupport = aDelaysSupport;
		doCausal = aDoCausal;
		clientPortDescription = new AServerPortDescription(clientHost,clientId, clientName);
		participantChoice = aChoice;
		sessionName = aSessionName;
		doJitter = aDoJitter;
	}

	protected void register (InputPort aPort, Object anObject) {
		((RPCRegistry) aPort).register(anObject);			
	}
	protected void register (Object anObject) {
		register (mainPort, anObject);
	}
	protected void register (InputPort aPort, String aName, Object anObject) {
		((RPCRegistry) aPort).register(aName, anObject);			
	}
	protected void register (String aName, Object anObject) {
		register (mainPort, aName, anObject);
	}
	protected Object createProxy ( Class aProxyClass, String aName) {
		return createProxy(mainPort, aProxyClass, aName);
	}
	
	protected Object createProxy (InputPort aPort, Class aProxyClass, String aName) {
		RPCProxyGenerator rpcProxyGenerator = ((RPCProxyGeneratorHolder) aPort).getRPCProxyGenerator();
		return rpcProxyGenerator.generateRPCProxy( aProxyClass, aName);		
			
	}
	protected Object createProxy ( Class aProxyClass) {
		return createProxy(mainPort, aProxyClass);
	}
	protected Object createReplyProxy (Class aProxyClass) {
		return createReplyProxy(mainPort, aProxyClass)		;	
	}
	protected Object createReplyProxy (InputPort aPort, Class aProxyClass) {
		return ReplyRPCProxyGenerator.generateReplyRPCProxy((DuplexRPCServerInputPort) aPort, aProxyClass);				
	}
	protected Object createOthersProxy (Class aProxyClass) {
		return createOthersProxy(mainPort, aProxyClass)		;	
	}
	protected Object createOthersProxy (InputPort aPort, Class aProxyClass) {
		return GroupRPCProxyGenerator.generateOthersRPCProxy((GroupRPCServerInputPort) aPort, aProxyClass);
	}
	protected Object createAllProxy (Class aProxyClass) {
		return createOthersProxy(mainPort, aProxyClass)		;	
	}
	
	
    protected Object createAllProxy (InputPort aPort, Class aProxyClass) {
    	return GroupRPCProxyGenerator.generateAllRPCProxy((GroupRPCServerInputPort) aPort, aProxyClass);
	}
	protected Object createProxy (InputPort aPort, Class aProxyClass) {
		RPCProxyGenerator rpcProxyGenerator = ((RPCProxyGeneratorHolder) aPort).getRPCProxyGenerator();
		return rpcProxyGenerator.generateRPCProxy( aProxyClass);			

			
	}
	
	public  PortLauncherSupport getPortLauncherSupport (PortDescription aPortDescription) {
		return PortLauncherSupportRegistry.get(aPortDescription);
	}
	
	protected PortDescription getPortDescription() {
		return getDefaultPortDescription();
	}
	protected SessionChoice getDefaultSessionChoice() {
		return DEFAULT_SESSION_CHOICE;
	}
	protected ParticipantChoice getDefaultParticipantChoice() {
		return DEFAULT_PARTICIPANT_CHOICE;
	}
	protected SessionChoice getSessionChoice() {
		if (sessionChoice == null)
			sessionChoice =  getDefaultSessionChoice();
		return sessionChoice;
		
	}
	protected ParticipantChoice getParticipantChoice() {
		if (participantChoice == null)
			participantChoice =  getDefaultParticipantChoice();
		return participantChoice;
		
	}
	protected void initSessionChoice(SessionChoice aSessionChoice) {
		sessionChoice = aSessionChoice;
		
	}
	protected void initParticipantChoice(ParticipantChoice aChoice) {
		participantChoice = aChoice;
		
	}
	
	protected String getSessionName() {
		if (sessionName == null)
			sessionName =  DEFAULT_SESSION_NAME;
		return sessionName;
	}
	
	protected void initSessionName (String aChoice) {
		sessionName = aChoice;
		
	}

	protected PortDescription getDefaultPortDescription() {
		
		 return new APortDescription(getPortKind(), getPortAccessKind(), getPortMessageKind(), getSessionChoice());
//		 return new APortDescription(getPortKind(), getPortAccessKind(), getPortMessageKind(), sessionChoice);

		
	}
	
	protected PortKind getPortKind() {
		return PortKind.CLIENT_INPUT_PORT;
	}
	protected PortAccessKind getPortAccessKind() {
		PortKind portKind = getPortKind();
		switch (portKind) {
		case CLIENT_INPUT_PORT:
		 return PortAccessKind.DUPLEX;
		case SERVER_INPUT_PORT:
		case SESSION_PORT:
			return  PortAccessKind.GROUP;
			default: return null;
		}
	}
	protected PortMessageKind getPortMessageKind() {
		return PortMessageKind.RPC;
	}
	

	public AnAbstractPortLauncher(String aClientName) {
		clientName = aClientName;		
	}
	public AnAbstractPortLauncher() {
		
	}
	
	protected  PortLauncherSupport getDefaultPortLauncherSupport() {
		PortLauncherSupport portLauncherSupport = null;
		portDescription = getPortDescription();
		if (portDescription != null) {
			 portLauncherSupport = PortLauncherSupportRegistry.get(portDescription);
		}
		if (portLauncherSupport != null)
			return portLauncherSupport;
		else
		   return new ADuplexRPCInputPortLauncherSupport();		
	}
	
	protected  PortLauncherSupport getPortLauncherSupport() {
		return getDefaultPortLauncherSupport();		
	}

	public  PortLauncherSupport getPortLauncherSupport(
			PortKind aPortKind,
			PortMessageKind aPortMessageKind, 
			PortAccessKind aPortAccessKind, ParticipantBindTime aParticipantParticipantBindTime) {		
		switch (aPortKind) {
		case CLIENT_INPUT_PORT:
		case SERVER_INPUT_PORT:	
			return getInputPortLauncherSupport(aPortMessageKind, aPortAccessKind);

			
		default: return null;	
		}			
	}
	protected  InputPort getPort(
			PortKind aPortKind,
			PortMessageKind aPortMessageKind, 
			PortAccessKind aPortAccessKind) {		
		return getDefaultPort(aPortKind, aPortMessageKind, aPortAccessKind, ParticipantBindTime.DYNAMIC);	
	}
	protected InputPort getDefaultPort() {
		portDescription = getPortDescription();
		if (portDescription == null) {
			 return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
					serverHost, serverId, serverName, clientName);
		}
		return getDefaultPort(portDescription.getPortKind(),
				portDescription.getPortMessageKind(),
				portDescription.getPortAccessKind(), participantBindTime);
	}
	protected  InputPort getDefaultPort(
			PortKind aPortKind,
			PortMessageKind aPortMessageKind, 
			PortAccessKind aPortAccessKind, ParticipantBindTime aParticipantBindTime) {		
		switch (aPortKind) {
		case CLIENT_INPUT_PORT:
			return getClientInputPort(aPortMessageKind, aPortAccessKind);
		case SERVER_INPUT_PORT:	
			return getServerInputPort(aPortMessageKind, aPortAccessKind);
		case SESSION_PORT:
			return getSesssionPort(aPortMessageKind, aPortAccessKind, aParticipantBindTime);
		default: return null;	
		}			
	}
	
	protected  InputPort getClientInputPort(
			PortMessageKind aPortMessageKind, 
			PortAccessKind aPortAccessKind		
			) {
		switch (aPortMessageKind) {
		case BUFFER:
			return null;			
		case OBJECT:
			return getObjectClientInputPort(aPortAccessKind);			
		case RPC:
			return getRPCClientInputPort(aPortAccessKind);
		default: return null;
		}
	}
	protected  InputPort getServerInputPort(
			PortMessageKind aPortMessageKind, 
			PortAccessKind aPortAccessKind
		
			) {
		switch (aPortMessageKind) {
		case BUFFER:
			return null;			
		case OBJECT:
			return getObjectServerInputPort(aPortAccessKind);			
		case RPC:
			return getRPCServerInputPort(aPortAccessKind);
		default: return null;
		}
	}
	protected  InputPort getSesssionPort(
			PortMessageKind aPortMessageKind, 
			PortAccessKind aPortAccessKind, ParticipantBindTime aParticipantBindTime
		
			) {
		switch (aPortMessageKind) {
		case BUFFER:
			return null;			
		case OBJECT:
			return getObjectSessionPort(aPortAccessKind, aParticipantBindTime);			
		case RPC:
			return getRPCSessionPort(aPortAccessKind, aParticipantBindTime);
		default: return null;
		}
	}
	public  PortLauncherSupport getInputPortLauncherSupport(
			PortMessageKind aPortMessageKind, 
			PortAccessKind aPortAccessKind		
			) {
		switch (aPortMessageKind) {
		case BUFFER:
			return getBufferInputPortLauncherSupport(aPortAccessKind);			
		case OBJECT:
			return getObjectInputPortLauncherSupport(aPortAccessKind);			
		case RPC:
			return getRPCInputPortLauncherSupport(aPortAccessKind);
		default: return null;
		}
	}

	
	
	protected  InputPort getBufferClientInputPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {	
		case SIMPLEX:
			return SimplexBufferInputPortSelector.createSimplexClientInputPort(
					serverHost, serverId, serverName, clientName);
		case DUPLEX:
			return DuplexBufferInputPortSelector.createDuplexClientInputPort(
					
					serverHost, serverId, serverName, clientName);
		
		default: return null;	
		}	
	}
	
	
	protected  InputPort getBufferServerInputPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {	
		case SIMPLEX:
			return SimplexObjectInputPortSelector.createSimplexServerInputPort (serverId, serverName);
		case DUPLEX:
			return DuplexObjectInputPortSelector.createDuplexServerInputPort(
					serverId, serverName);
		case GROUP:
			return GroupObjectInputPortSelector.createGroupServerInputPort(
					 serverId, serverName);
		
		default: return null;	
		}	
	}
	protected  InputPort getObjectClientInputPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {	
		case SIMPLEX:
			return SimplexObjectInputPortSelector.createSimplexClientInputPort(
					serverHost, serverId, serverName, clientName);
		case DUPLEX:
			return DuplexObjectInputPortSelector.createDuplexClientInputPort(
					serverHost, serverId, serverName, clientName);
		
		default: return null;	
		}	
	}
	
	protected  InputPort getObjectServerInputPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {	
		case SIMPLEX:
			return SimplexObjectInputPortSelector.createSimplexServerInputPort (serverId, serverName);
		case DUPLEX:
			return DuplexObjectInputPortSelector.createDuplexServerInputPort(
					serverId, serverName);
		case GROUP:
			return GroupObjectInputPortSelector.createGroupServerInputPort(
					 serverId, serverName);
		
		default: return null;	
		}	
	}
	protected  InputPort getBufferSessionPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {	
		case SIMPLEX:
			return null;
		case DUPLEX:
			return BufferDuplexSessionPortSelector.createBufferDuplexSessionPort(serverHost, 
					serverId, serverName, getSessionName(), clientId, clientName, getParticipantChoice());
		case GROUP:
			return BufferGroupSessionPortSelector.createBufferGroupSessionPort(serverHost, 
					serverId, serverName, getSessionName(), clientId, clientName, getParticipantChoice());
		
		default: return null;	
		}	
	}
	protected  InputPort getBufferStaticSessionPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {	
		case SIMPLEX:
			return null;
		case DUPLEX:
			return BufferDuplexStaticSessionPortSelector.createBufferDuplexStaticSessionPort(serverList, 
					  clientId, clientName, getSessionName(),getParticipantChoice());
		case GROUP:
			return BufferGroupStaticSessionPortSelector.createBufferGroupStaticSessionPort(serverList, 
					clientId, clientName, getSessionName(), getParticipantChoice());
		
		default: return null;	
		}	
	}
	protected  InputPort getObjectSessionPort(PortAccessKind aPortAccessKind, ParticipantBindTime aParticipantBindTime) {
		switch (aParticipantBindTime) {
		case DYNAMIC:
			return getObjectSessionPort(aPortAccessKind);
		case STATIC:
			return getObjectStaticSessionPort(aPortAccessKind);
		
		default: return null;
		}
	
	
	}
	protected  InputPort getObjectSessionPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {	
		case SIMPLEX:
			return null;
		case DUPLEX:
			return ObjectDuplexSessionPortSelector.createObjectDuplexSessionPort(serverHost, 
					serverId, serverName, getSessionName(), clientId, clientName, getParticipantChoice());
		case GROUP:
			return ObjectGroupSessionPortSelector.createObjectGroupSessionPort(serverHost, 
					serverId, serverName, getSessionName(), clientId, clientName, getParticipantChoice());
		
		default: return null;	
		}	
	}
	protected  InputPort getObjectStaticSessionPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {	
		case SIMPLEX:
			return null;
		case DUPLEX:
			return ObjectDuplexStaticSessionPortSelector.createObjectDuplexStaticSessionPort(serverList,  clientId, clientName, getParticipantChoice());
		case GROUP:
			return GroupObjectStaticSessionPortSelector.createObjectGroupStaticSessionPort(
					serverList, clientId, clientName, getSessionName(), getParticipantChoice());
		
		default: return null;	
		}	
	}
	protected  InputPort getRPCSessionPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {	
		case SIMPLEX:
			return null;
		case DUPLEX:
			return DuplexRPCSessionPortSelector.createDuplexRPCSessionPort(serverHost, 
					serverId, serverName, getSessionName(), clientId, clientName, getParticipantChoice());
		case GROUP:
			return GroupRPCSessionPortSelector.createGroupRPCSessionPort(serverHost, 
					serverId, serverName, getSessionName(), clientId, clientName,   getParticipantChoice());
		
		default: return null;	
		}	
	}
	protected  InputPort getRPCStaticSessionPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {	
		case SIMPLEX:
			return null;
		case DUPLEX:
			return DuplexRPCStaticSessionPortSelector.createDuplexRPCStaticSessionPort(
					serverList, clientId, clientName, getParticipantChoice()
					);	
		case GROUP:
			return GroupRPCStaticSessionPortSelector.createGroupRPCStaticSessionPort(serverList, clientId, clientName, getSessionName(), getParticipantChoice()
					);	
			
		
		default: return null;	
		}	
	}
	protected  InputPort getRPCSessionPort(PortAccessKind aPortAccessKind, ParticipantBindTime aParticipantBindTime) {
		switch (aParticipantBindTime) {
		case DYNAMIC:
			return getRPCSessionPort(aPortAccessKind);
		case STATIC:
			return getRPCStaticSessionPort(aPortAccessKind);
			default: return null;
		}

	}
	
	protected  InputPort getRPCClientInputPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {
		case SIMPLEX:
			return SimplexRPCInputPortSelector.createSimplexRPCClientInputPort(
					serverHost, serverId, serverName, clientName);		
		case DUPLEX:
			return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
					serverHost, serverId, serverName, clientName);
		
		default: return null;	
		}	
	}
	public  InputPort getRPCServerInputPort(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {		
		case DUPLEX:
			return DuplexRPCInputPortSelector.createDuplexRPCServerInputPort(serverId, serverName);
		case GROUP: 
			return GroupRPCInputPortSelector.createGroupRPCServerInputPort(serverId, serverName);		
		default: return null;	
		}	
	}
	public  static PortLauncherSupport getBufferInputPortLauncherSupport(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {
		case SIMPLEX:
			return new ASimplexBufferInputPortLauncherSupport();
		case DUPLEX:
			return new ADuplexBufferInputPortLauncherSupport();
		case GROUP:
			return new AGroupBufferInputPortLauncherSupport();			
		default: return null;	
		}		
	}
	
	public static PortLauncherSupport getObjectInputPortLauncherSupport(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {
		case SIMPLEX:
			return new ASimplexObjectInputPortLauncherSupport();
		case DUPLEX:
			return new ADuplexObjectInputPortLauncherSupport();
		case GROUP:
			return new AGroupObjectInputPortLauncherSupport();			
		default: return null;	
		}	
	}	

	public static PortLauncherSupport getRPCInputPortLauncherSupport(PortAccessKind aPortAccessKind) {
		switch (aPortAccessKind) {
		case SIMPLEX:
			return new ASimplexObjectInputPortLauncherSupport();
		case DUPLEX:
			return new ADuplexRPCInputPortLauncherSupport();
		case GROUP:
			return new AGroupRPCInputPortLauncherSupport();			
		default: return null;	
		}	
	}
	protected PortLauncher getPortLauncherSupportPortMessageKind (PortMessageKind aPortMessageKind) {
		return null;
		
	}
	
	protected PortLauncherSupport getAuxilliaryPortLauncherSupport() {
		return new ASimplexBufferInputPortLauncherSupport();
	}
	protected PortLauncherSupport getTertiaryPortLauncherSupport() {
		return new ASimplexBufferInputPortLauncherSupport();
	}
	
	protected List<PortLauncherSupport> getPortLauncherSupports() {
		List<PortLauncherSupport> retVal = new ArrayList();
		PortLauncherSupport support = getPortLauncherSupport();
		if (support != null)
			retVal.add(support);
		PortLauncherSupport auxSupport = getAuxilliaryPortLauncherSupport();
		if (auxSupport != null)
			retVal.add(auxSupport);
		PortLauncherSupport teriarySupport = getTertiaryPortLauncherSupport();
		if (teriarySupport != null)
			retVal.add(teriarySupport);
		PortLauncherSupport shouldDelaySupport = getDelayPortLauncherSupport();
		if (shouldDelaySupport != null) {
			retVal.add(shouldDelaySupport);
		}
		PortLauncherSupport delays = getPortLauncherDelaysSupport();
		if (delays != null) {
			retVal.add(delays);
		}
	
		PortLauncherSupport causalSupport = getCausalPortLauncherSupport();
		if (causalSupport != null) {
			retVal.add(causalSupport);
		}
		// unwrap time stamped message before replicated trappers get message
		PortLauncherSupport jitterSupport = getJitterPortLauncherSupport();
		if (jitterSupport != null) {
			retVal.add(jitterSupport);
		}
		PortLauncherSupport replicatedSupport = getReplicatedPortLauncherSupport();
		if (replicatedSupport != null) {
			retVal.add(replicatedSupport);
		}
		PortLauncherSupport replicatedServerSupport = getReplicatedAuxilliaryPortLauncherSupport();
		if (replicatedServerSupport != null) {
			retVal.add(replicatedServerSupport);
		}
		
		return retVal;		
	}
	protected PortLauncherSupport getJitterPortLauncherSupport() {
		return null;
	}

	protected PortLauncherSupport getDelayPortLauncherSupport() {
		
		if (shouldDelay)
			return new AClientDelayingPortLauncherSupport();
		else
			return null;
	}

	protected PortLauncherSupport getCausalPortLauncherSupport() {
//		if (sessionChoice == SessionChoice.P2P && doCausal)
		if (getSessionChoice() == SessionChoice.P2P && doCausal)
		return new ACausalGroupSessionPortLauncherSupport();
		else
			return null;
	}
	// this was originally just PortLauncherSupport
	// so some server support is in this method, to be fixed at some point
	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
		return null;
	}
	protected PortLauncherSupport getReplicatedAuxilliaryPortLauncherSupport() {
		return null;
	}
	
	protected PortLauncherSupport getPortLauncherDelaysSupport() {
//		if (shouldDelay && sessionChoice == SessionChoice.P2P)
		if (shouldDelay && getSessionChoice() == SessionChoice.P2P)

		return delaysSupport;
		else
			return null;
	}
	

	
	protected  InputPort getPort() {
		return getDefaultPort();
	}
	protected  InputPort getAuxilliaryPort() {
		return null;
	}
	protected  InputPort getTertiaryPort() {
		return null;
	}

	protected  void addListeners () {
		if (mainPort == null) return;
		addConnectListeners();
		addReceiveListeners();
		addSendListeners();
		
	}
	
	protected  void addAuxilliaryListeners () {
		if (auxilliaryPort == null) return;
		addAuxilliaryConnectListeners();
		addAuxilliaryReceiveListeners();
		addAuxilliarySendListeners();
		
	}
	
	protected  void addTertiaryListeners () {
		if (tertiaryPort == null) return;
		addTertiaryConnectListeners();
		addTertiaryReceiveListener();
		addTertiarySendListeners();
		
	}
	
	protected ConnectionListener connectionListener;
	protected void addConnectListeners() {
		connectionListener = getConnectionListener(mainPort);
		if (connectionListener != null)
			mainPort.addConnectionListener(connectionListener);
	}
	protected ReceiveListener receiveListener;
	protected void addReceiveListeners() {
		receiveListener = getReceiveListener (mainPort);
		if (receiveListener != null)
			((ReceiveRegistrar) mainPort).addReceiveListener(receiveListener);
	}
	protected ByteBufferSendListener sendListener;
	protected void addSendListeners() {
		sendListener = getSendListener(mainPort);
		if (sendListener != null)
			((SendRegistrar) mainPort).addSendListener(sendListener);		
	}
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return null;
	}
	protected  ReceiveListener getReceiveListener (InputPort anInputPort) {
		return null;
	}
	protected  ByteBufferSendListener getSendListener (InputPort anInputPort) {
		return null;
	}
	protected ConnectionListener auxiliaryConnectionListener;
	protected void addAuxilliaryConnectListeners() {
		if (auxilliaryPort == null) return;
		auxiliaryConnectionListener = getAuxilliaryConnectionListener(auxilliaryPort);
		if (auxiliaryConnectionListener != null)
			auxilliaryPort.addConnectionListener(auxiliaryConnectionListener);
	}
	protected ReceiveListener auxiliaryReceiveListener;
	protected void addAuxilliaryReceiveListeners() {
		if (auxilliaryPort == null) return;
		auxiliaryReceiveListener = getAuxilliaryReceiveListener (auxilliaryPort);
		if (auxiliaryReceiveListener != null)
			((ReceiveRegistrar) auxilliaryPort).addReceiveListener(auxiliaryReceiveListener);
	}
	protected ByteBufferSendListener auxiliarySendListener;
	protected void addAuxilliarySendListeners() {
		if (auxilliaryPort == null) return;
		auxiliarySendListener = getAuxilliarySendListener(auxilliaryPort);
		if (auxiliarySendListener != null)
			((SendRegistrar) auxilliaryPort).addSendListener(auxiliarySendListener);		
	}
	protected  ConnectionListener getAuxilliaryConnectionListener (InputPort anInputPort) {
		return null;
	}
	protected  ReceiveListener getAuxilliaryReceiveListener (InputPort anInputPort) {
		return null;
	}
	protected  ByteBufferSendListener getAuxilliarySendListener (InputPort anInputPort) {
		return null;
	}
	// tertiary start
	protected ConnectionListener tertiaryConnectionListener;
	protected void addTertiaryConnectListeners() {
		if (tertiaryPort == null) return;
		tertiaryConnectionListener = getTertiaryConnectionListener(tertiaryPort);
		if (tertiaryConnectionListener != null)
			tertiaryPort.addConnectionListener(tertiaryConnectionListener);
	}
	protected ReceiveListener tertiaryReceiveListener;
	protected void addTertiaryReceiveListener() {
		if (tertiaryPort == null) return;
		tertiaryReceiveListener = getTertiaryReceiveListener (tertiaryPort);
		if (tertiaryReceiveListener != null)
			((ReceiveRegistrar) tertiaryPort).addReceiveListener(tertiaryReceiveListener);
	}
	protected ByteBufferSendListener tertiarySendListener;
	protected void addTertiarySendListeners() {
		if (tertiaryPort == null) return;
		tertiarySendListener = getTertiarySendListener(tertiaryPort);
		if (tertiarySendListener != null)
			((SendRegistrar) tertiaryPort).addSendListener(tertiarySendListener);		
	}
	protected  ConnectionListener getTertiaryConnectionListener (InputPort anInputPort) {
		return null;
	}
	protected  ReceiveListener getTertiaryReceiveListener (InputPort anInputPort) {
		return null;
	}
	protected  ByteBufferSendListener getTertiarySendListener (InputPort anInputPort) {
		return null;
	}
	
	
	// teriary end
	 

	
	protected void registerRemoteObjects() {
		
	}
	
	protected  void createProxies() {
		
	}
	
	protected void initPortLaucherSupports() {
		portLauncherSupports = getPortLauncherSupports();
		for (PortLauncherSupport support:portLauncherSupports) {
			support.init();
		}
	}
	
	public void createAndBindConnectablePorts() {
//		portLauncherSupports = getPortLauncherSupports();
//		for (PortLauncherSupport support:portLauncherSupports) {
//			support.init();
//		}
		initPortLaucherSupports();
		setStateBeforePortCreation();
		setPorts();
		// moved it up so that listeners can take proxies as arguments
		// as remote objects may need proxies, we creat them first
		createProxies();
		registerRemoteObjects();
//		createProxies();
//		setStateAfterPortButBeforeConnection();
		setNumberOfConnects();
//		inputPort = createPort();
		setStateBeforeAddingListeners();
		// listeners can be defined in terms of remote objects and proxies
		addListeners();
		addAuxilliaryListeners();
		addTertiaryListeners();
//		registerMethods();
//		createProxies();
	}
	protected void createTracers() {
		ConnectionEventManagerFactory.getConnectionManager();
		
	}
	protected void setPorts() {
		mainPort = getPort();
		auxilliaryPort = getAuxilliaryPort();
		tertiaryPort = getTertiaryPort();
	}
	
	protected void setStateBeforePortCreation() {
		
	}
    protected void setStateBeforeAddingListeners() {
		
	}
	protected void setStateAfterPortButBeforeConnection() {
		
	}
	protected void connect(InputPort anInputPort) {
		if (anInputPort == null) return;
		anInputPort.addConnectionListener(this);

		anInputPort.connect();

	}
	
	protected void connectPorts() {
		connect(mainPort);
		connect(auxilliaryPort);
		connect(tertiaryPort);

	}
	
	
	protected void doPostConnectsAsyncOperations() {
		createUI(mainPort);
	}
	
	protected void createUI(InputPort anInputPort){
		
	}
	

	protected Integer getNumberOfServerParticipantConnects() {
		return null;
	}
	protected int getNumberOfConnects() {
		if (serverList != null)
			return serverList.length;
		int retVal = 0;
		if (mainPort != null)
			retVal++;
		if (auxilliaryPort != null)
			retVal++;
		return retVal;

	}
	
	protected void setNumberOfConnects() {
		numPendingConnects = getNumberOfConnects();
		numPendingServerConnects = getNumberOfServerParticipantConnects();
	}


	public void launch () {
		createTracers();
		createAndBindConnectablePorts();
		setStateAfterPortButBeforeConnection();
;
		connectPorts();
	}
	
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		if (connectedToAllPorts)
			return;
		numPendingConnects--;
		if (aConnectionType == ConnectionType.SERVER_TO_SESSION && numPendingServerConnects != null) {
			numPendingServerConnects --;
		}
		if (numPendingConnects > 0) return; // we have a boolean to ensure we do not doubly connect


		if (numPendingServerConnects != null &&  numPendingServerConnects != 0) return; 
		connectedToAllPorts();

	}
	boolean connectedToAllPorts;
	protected void connectedToAllPorts() {
		connectedToAllPorts = true;
		Thread thread = new Thread(this);
		thread.setName("Post Connect Async Operations");
		thread.start();
	}
	public void notConnected(String aRemoteEndName, String anExplanation, ConnectionType aConnectionType) {
		
	}
	
	public void  disconnected(String aRemoteEndName, boolean anExplicitDsconnection, String anExplanation, ConnectionType aConnectionType) {
		
	}
	@Override
	public void run() {
		doPostConnectsAsyncOperations();
		
	}
	

	
	
	

}
