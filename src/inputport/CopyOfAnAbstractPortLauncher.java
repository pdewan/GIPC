package inputport;

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
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.simplex.SimplexRPCInputPortSelector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import port.APortDescription;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortDescription;
import port.PortKind;
import port.PortLauncher;
import port.PortLauncherSupport;
import port.PortMessageKind;
import port.SessionChoice;
import port.trace.ConnectionEventManagerFactory;

public abstract class CopyOfAnAbstractPortLauncher implements PortLauncher, ConnectionListener, Runnable {
//	public final static String SERVER_NAME = "Echo Server";
//	public final static String SERVER_PORT = "9090";
	protected List<PortLauncherSupport> portLauncherSupports;
	protected InputPort mainPort;
	protected InputPort auxilliaryPort;
	protected InputPort tertiaryPort;

	boolean asyncOperationsDone;
	int numPendingConnects;
	
	public final static String SERVER_NAME = "Generic Server";
	public final static String SERVER_ID = "9090";
	public final static String SERVER_HOST = "localhost";
	protected String serverName = SERVER_NAME;
	protected String serverId = SERVER_ID;
	protected String serverHost = SERVER_HOST;
	public static final boolean DO_DELAY = false; 
	public static final boolean DO_CAUSAL = false;
	Map<String, PortLauncherSupport> stringToPortLauncherSupport = new HashMap();
	protected boolean shouldDelay;
	protected boolean doCausal;
//	public static final PortKind PORT_KIND = PortKind.CLIENT_INPUT_PORT;
//	public static final PortAccessKind PORT_ACCESS_KIND = PortAccessKind.DUPLEX;
//	public static final PortMessageKind PORT_MESSAGE_KIND = PortMessageKind.DATA_COMMUNICATION;
	
	SessionChoice sessionChoice;
//	PortKind portKind;
//	PortAccessKind portAccessKind;
//	PortMessageKind portMessageKind;
	PortDescription portDescription;
	Boolean doDelay;
	Boolean doCausual;
	Boolean doJitter;
//	protected String serverId = SERVER_PORT;
	protected String clientName;
	protected String clientId;
	protected String clientHost;
	public CopyOfAnAbstractPortLauncher(String aServerName, String aServerId) {
		if (serverName != null)
		serverName = aServerName;
		if (serverId != null) {
		serverId = aServerId;
		registerDefaultPortLauncherSupport();
//		serverId = aServerPort;
		}
	}
	public CopyOfAnAbstractPortLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		clientName = aClientName;
		serverHost = aServerHost;
		serverId = aServerId;;
		serverName = aServerName;		
		registerDefaultPortLauncherSupport();
	}
	public CopyOfAnAbstractPortLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoCausal, ParticipantChoice aChoice) {
		serverHost = aSessionServerHost;
		serverId = aServerId;;
		serverName = aServerName;	
		serverName = aServerName;
		
				
		
	}
	
	public  PortLauncherSupport getPortLauncherSupport (PortDescription aPortDescription) {
		return stringToPortLauncherSupport.get(aPortDescription.toString());
	}
	
	protected PortDescription getPortDescription() {
		return getDefaultPortDescription();
	}
	protected PortDescription getDefaultPortDescription() {
		PortKind portKind = getPortKind();
		if (portKind == null)
			return null;
		switch (portKind) {
		case CLIENT_INPUT_PORT:
		 return new APortDescription(portKind, PortAccessKind.DUPLEX, PortMessageKind.RPC);
		case SERVER_INPUT_PORT:
			return new APortDescription(portKind, PortAccessKind.GROUP, PortMessageKind.RPC);
			default: return null;
		}
	}
	
	protected PortKind getPortKind() {
		return null;
	}
	
	public  PortLauncherSupport put (PortDescription aPortDescription, PortLauncherSupport aPortLauncherSupport) {
		return stringToPortLauncherSupport.put(aPortDescription.toString(), aPortLauncherSupport);
	}
	public CopyOfAnAbstractPortLauncher(String aClientName) {
		clientName = aClientName;		
	}
	public CopyOfAnAbstractPortLauncher() {
		
	}
	
	protected  PortLauncherSupport getDefaultPortLauncherSupport() {
		PortLauncherSupport portLauncherSupport = null;
		portDescription = getPortDescription();
		if (portDescription != null) {
			 portLauncherSupport = getPortLauncherSupport(portDescription);
		}
		if (portLauncherSupport != null)
			return portLauncherSupport;
		else
		   return new ADuplexRPCInputPortLauncherSupport();		
	}
	
	protected  PortLauncherSupport getPortLauncherSupport() {
		return getDefaultPortLauncherSupport();		
	}

	public static PortLauncherSupport getPortLauncherSupport(
			PortKind aPortKind,
			PortMessageKind aPortMessageKind, 
			PortAccessKind aPortAccessKind) {		
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
		return getDefaultPort(aPortKind, aPortMessageKind, aPortAccessKind);	
	}
	protected InputPort getDefaultPort() {
		portDescription = getPortDescription();
		if (portDescription == null) {
			 return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
					serverHost, serverId, serverName, clientName);
		}
		return getDefaultPort(portDescription.getPortKind(),
				portDescription.getPortMessageKind(),
				portDescription.getPortAccessKind());
	}
	protected  InputPort getDefaultPort(
			PortKind aPortKind,
			PortMessageKind aPortMessageKind, 
			PortAccessKind aPortAccessKind) {		
		switch (aPortKind) {
		case CLIENT_INPUT_PORT:
			return getClientInputPort(aPortMessageKind, aPortAccessKind);
		case SERVER_INPUT_PORT:	
			return getServerInputPort(aPortMessageKind, aPortAccessKind);			
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
	public static PortLauncherSupport getInputPortLauncherSupport(
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
		return null;
	}
	protected PortLauncherSupport getCausalPortLauncherSupport() {
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
	
	public void createAndBindConnectablePorts() {
		portLauncherSupports = getPortLauncherSupports();
		for (PortLauncherSupport support:portLauncherSupports) {
			support.init();
		}
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
//		numPendingConnects++;

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
	
//	protected int getNumberOfConnects() {
//		if (mainPort == null && auxilliaryPort == null)
//			return 0;
//		else if (mainPort != null && auxilliaryPort != null)
//			return 2;
//		else
//			return 1;
////		return 1;
////		return numPendingConnects;
//	}
	protected int getNumberOfConnects() {
		int retVal = 0;
		if (mainPort != null)
			retVal++;
		if (auxilliaryPort != null)
			retVal++;
		return retVal;
//		return 1;
//		return numPendingConnects;
	}
	
	protected void setNumberOfConnects() {
		numPendingConnects = getNumberOfConnects();
	}


	public void launch () {
//		setNumberOfConnects();
		createTracers();
		createAndBindConnectablePorts();
		setStateAfterPortButBeforeConnection();
//		portLauncherSupport = createPortLauncherSupport();
//		portLauncherSupport.init();
//		inputPort = createInputPort();
//		addListeners(inputPort);
//		registerMethods(inputPort);
//		createProxies();
		connectPorts();
//		inputPort.connect();
	}
	
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		numPendingConnects--;
		if (numPendingConnects != 0) return; // if it goes below we do not want this done. We want it done one time only
//		if (asyncOperationsDone) return;
		connectedToAllPorts();
//		Thread thread = new Thread(this);
//		thread.setName("Post Connect Async Operations");
//		thread.start();
//		asyncOperationsDone = true;
	}
	protected void connectedToAllPorts() {
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
	
	protected  void registerDefaultPortLauncherSupport() {
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.SIMPLEX, PortMessageKind.BUFFER),
				new ASimplexBufferInputPortLauncherSupport());
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.SIMPLEX, PortMessageKind.BUFFER),
				new ASimplexBufferInputPortLauncherSupport());
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.SIMPLEX, PortMessageKind.OBJECT),
				new ASimplexObjectInputPortLauncherSupport());		
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.SIMPLEX, PortMessageKind.OBJECT),
				new ASimplexObjectInputPortLauncherSupport());
		
		
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.BUFFER),
				new ADuplexBufferInputPortLauncherSupport());			
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.BUFFER),
				new ADuplexBufferInputPortLauncherSupport());
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.OBJECT),
				new ADuplexObjectInputPortLauncherSupport());
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.OBJECT),
				new ADuplexObjectInputPortLauncherSupport());
		
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.BUFFER),
				new ADuplexBufferInputPortLauncherSupport());			
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.BUFFER),
				new ADuplexBufferInputPortLauncherSupport());
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.OBJECT),
				new ADuplexObjectInputPortLauncherSupport());
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.OBJECT),
				new ADuplexObjectInputPortLauncherSupport());
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.RPC),
				new ADuplexRPCInputPortLauncherSupport());
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.RPC),
				new ADuplexRPCInputPortLauncherSupport());
		
	}
	
	
	

}
