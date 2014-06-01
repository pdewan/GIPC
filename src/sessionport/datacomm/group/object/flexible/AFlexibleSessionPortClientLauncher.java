package sessionport.datacomm.group.object.flexible;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.group.GroupAllSender;
import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.causal.ACausalGroupSessionPortLauncherSupport;
import port.delay.AClientDelayingPortLauncherSupport;
import port.delay.example.AnEchoingObjectReceiveListener;
import port.sessionserver.SessionObserver;
import port.sessionserver.example.APrintingSessionObserver;
import port.sessionserver.example.ASessionServerClientLauncher;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.direct.AnObjectGroupSessionPortDirectLauncherSupport;
import sessionport.datacomm.group.object.flexible.example.AFrostySessionConnectionListener;
import sessionport.datacomm.group.object.flexible.example.ATracingSessionConnectionListener;
import sessionport.datacomm.group.object.relayed.AnObjectGroupSessionPortRelayedLauncherSupport;
import sessionport.datacomm.group.object.relayed.latecomer.AnObjectGroupSessionPortLatecomerLauncherSupport;

public class AFlexibleSessionPortClientLauncher extends ASessionServerClientLauncher{

//	public static final boolean P2P = false; 
//	public static final SessionChoice SESSION_CHOICE = SessionChoice.RELAYED;
//	public static final SessionChoice SESSION_CHOICE = SessionChoice.P2P;
	public static final SessionChoice SESSION_CHOICE = SessionChoice.LATECOMER_RELAYED;


	public static final boolean DO_DELAY = false; 
	public static final boolean DO_CAUSAL = false; 
	PortLauncherSupport delaysSupport;
//	protected String id, name;
	protected SessionChoice sessionChoice ;
	protected boolean shouldDelay;
	protected boolean doCausal;
	public AFlexibleSessionPortClientLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoCausal, ParticipantChoice aChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aChoice);
		sessionChoice = aSessionChoice;
		shouldDelay = aShouldDelay;
		delaysSupport = aDelaysSupport;
		doCausal = aDoCausal;
	}

	
	protected SessionObserver createSessionObserver() {
		return new APrintingSessionObserver();
	}
//	@Override
//	protected void setStateAfterPortButBeforeConnection() {
//		ACausalGroupSessionPortLauncherSupport.doCausalBroadcast((GroupServerInputPort) inputPort);
//	}	
	
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
//		return new ATracingConnectionListener(anInputPort);
//		return new ATracingSessionConnectionListener(SESSION_NAME);
		if (!(sessionChoice == SessionChoice.P2P) || !doCausal)
		return new AFrostySessionConnectionListener(SESSION_NAME, (DuplexInputPort) anInputPort);

		else
			return new ATracingSessionConnectionListener(SESSION_NAME, (DuplexInputPort)anInputPort);

	}
//	protected void connectPorts() {
//		connect(mainPort);
//	}
//	
	protected  InputPort getPort() {
		return ObjectGroupSessionPortSelector.createObjectGroupSessionPort(serverHost, 
				serverId, serverName, SESSION_NAME, myId, myName, participantChoice);		
		
	}
	protected  InputPort getAuxilliaryPort() {
		return null;
	}

//	protected PortLauncherSupport getPortLauncherSupport() {
//		switch (sessionChoice) {
//		case P2P: 
//			return new AnObjectGroupSessionPortDirectLauncherSupport();
//		case RELAYED:
//			return new AnObjectGroupSessionPortRelayedLauncherSupport();
//		case LATECOMER_RELAYED:
//			return  new AnObjectGroupSessionPortLatecomerLauncherSupport();
//		
//
//		}
//		return null;
//	}
	protected PortLauncherSupport getPortLauncherSupport() {
		switch (sessionChoice) {
		case P2P: 
			return getP2PPortLauncherSupport();
		case RELAYED:
			return getRelayedPortLauncherSupport();
		case LATECOMER_RELAYED:
			return getLatecomerRelayedPortLauncherSupport();

		}
		return null;
	}
	
	protected PortLauncherSupport getP2PPortLauncherSupport() {
		return new AnObjectGroupSessionPortDirectLauncherSupport();

	}
	
	protected PortLauncherSupport getRelayedPortLauncherSupport() {
		return new AnObjectGroupSessionPortRelayedLauncherSupport();

	}
	
	protected PortLauncherSupport getLatecomerRelayedPortLauncherSupport() {
		return  new AnObjectGroupSessionPortLatecomerLauncherSupport();

	}
	@Override
	protected PortLauncherSupport getPortLauncherDelaysSupport() {
		if (shouldDelay && sessionChoice == SessionChoice.P2P)
		return delaysSupport;
		else
			return null;
	}
	@Override
	protected PortLauncherSupport getCausalPortLauncherSupport() {
		if (sessionChoice == SessionChoice.P2P && doCausal)
		return new ACausalGroupSessionPortLauncherSupport();
		else
			return null;
	}
	
	@Override
	protected PortLauncherSupport getDelayPortLauncherSupport() {
		if (shouldDelay)
			return new AClientDelayingPortLauncherSupport();
		else
			return null;
	}
//	@Override
//	protected ReceiveListener getReceiveListener() {
//		return new AnEchoingObjectReceiveListener();
//	}
	protected  ReceiveListener getReceiveListener (InputPort anInputPort) {
		return new AnEchoingObjectReceiveListener();
	}
	
//	@Override
//	protected int getNumberOfConnects() {
//		return 1;
//	}
	@Override
	protected void sendMessage(Object aMessage) {
	     ((GroupAllSender) mainPort).sendAll(aMessage);

	}
	protected void doPostConnectsAsyncOperations() {
		createUI(mainPort);
	}
	
	
//	@Override
//	public void createUI (InputPort anInputPort) {
//		GroupAllSender<Object> sessionPort = (GroupAllSender) anInputPort;
////		if (greetOnReadingInput) {			
//		       Scanner in = new Scanner(System.in);		  
////		       String message  = in.nextLine();
////		       sessionPort.sendAll(aName + " says hi to all");
//		       while (true) {
//		    	   System.out.println("Please enter  next input");
////					  in = new Scanner(System.in);		  
//				     String message  = in.nextLine();
//				      sessionPort.sendAll(message);
//				}
//		}
		
//	}

}
