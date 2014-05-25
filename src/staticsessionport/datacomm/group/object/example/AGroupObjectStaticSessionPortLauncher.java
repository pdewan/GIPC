package staticsessionport.datacomm.group.object.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.duplex.object.echoer.example.ADuplexObjectClientInputPortLauncher;
import inputport.datacomm.duplex.object.echoer.example.AFrostyObjectConnectionListener;
import inputport.datacomm.group.GroupSender;
import inputport.rpc.duplex.example.DuplexCounterAndSenderAwareSummer;

import java.util.Scanner;

import javax.swing.JOptionPane;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.sessionserver.ASessionParticipantDescription;
import port.sessionserver.SessionParticipantDescription;
import staticsessionport.datacomm.duplex.buffer.AStaticSessionDuplexBufferPortLauncherSupport;
import staticsessionport.datacomm.group.object.GroupObjectStaticSessionPortSelector;


public class AGroupObjectStaticSessionPortLauncher extends ADuplexObjectClientInputPortLauncher {
	protected  static SessionParticipantDescription AliceDescription = new ASessionParticipantDescription("localhost", "9100", "Alice", null);
	protected static  SessionParticipantDescription BobDescription = new ASessionParticipantDescription("localhost", "9101", "Bob", null);
	protected static  SessionParticipantDescription CathyDescription = new ASessionParticipantDescription("localhost", "9102", "Cathy", null);
	protected static final String REMOTE_END_POINT = "Echo Servers" ; 

//	protected  Echoer registerdEchoer;
//	protected  Echoer unregisteredEchoer;
//	protected  Counter counter;
	SessionParticipantDescription[] serverList;
	String id;
	String name;
	DuplexCounterAndSenderAwareSummer adderProxy;
	
	
	public AGroupObjectStaticSessionPortLauncher(SessionParticipantDescription[] aServerList, String anId, String aName) {
		super(aName);
		serverList = aServerList;
		id = anId;
		name = aName;
//		GlobalState.setRelayedCommunicaton(false);

	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new AStaticSessionDuplexBufferPortLauncherSupport();
	}
	
	@Override
	protected InputPort getPort() {
		return GroupObjectStaticSessionPortSelector.createObjectGroupStaticSessionPort(serverList, id, name, REMOTE_END_POINT, ParticipantChoice.SYMMETRIC_JOIN
				);	
	}
	
	public static  void waitForUserToOKConnectionThroughDialogBox() {
		Object[] options = {"Connect to Static Peers"};
		JOptionPane.showOptionDialog(
			    null,
			    "Press button when all of the peers have been started",
			    "Connection dialog",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[0]);
	}
	public  static void waitForUserToOKConnectionThroughConsole(Scanner in) {		
		System.out.println("Press any key to  connect:");
		String message  = in.nextLine();
		Object[] options = {"Connect to Static Peers"};
		JOptionPane.showOptionDialog(
			    null,
			    "Press button when all of the peers have been started",
			    "Connection dialog",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[0]);
	}
	
	@Override
	protected ConnectionListener getConnectionListener(InputPort anInputPort) {
		return new AFrostyObjectConnectionListener((DuplexInputPort<Object>) anInputPort);
	}
	
	@Override
	public void launch() {
		createAndBindConnectablePorts();
		waitForUserToOKConnectionThroughDialogBox();
		mainPort.connect();
		createUI(mainPort);

	}
//	@Override
	protected void send(InputPort anInputPort, String aMessage) {
		GroupSender<Object> aSender = (GroupSender<Object>) anInputPort;
		aSender.sendAll(aMessage);
	}
	

}
