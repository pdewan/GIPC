package sessionport.rpc.duplex.direct.counter_example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.simplex.object.ObjectTranslatingIPTrapperSelector;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.AnAbstractDuplexRPCClientPortLauncher;

import java.util.Scanner;

import port.PortAccessKind;
import port.PortKind;
import port.SessionChoice;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import examples.mvc.local.duplex.ACounter;
import examples.mvc.local.duplex.Counter;

public class ASessionPortCounterClientLauncher extends
		AnAbstractDuplexRPCClientPortLauncher {
	public ASessionPortCounterClientLauncher(String aClientName,
			String aClientId, String aServerHost, String aServerId,
			String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);
		DirectedRPCProxyGenerator.setDoShortCircuitLocalCallsToRemotes(false);
		clientId = aClientId;

	}

	String clientId;

	protected String getClientId() {
		return clientId;
	}

	@Override
	protected void registerRemoteObjects() {
		register(new ACounter());
	}

	SessionPortConnectionListener myConnectionListener;

	protected ConnectionListener getConnectionListener(InputPort anInputPort) {
		myConnectionListener = new ASessionPortConnectListener();
		return myConnectionListener;
	}
	@Override
	public PortKind getPortKind() {
		return PortKind.SESSION_PORT;
	}
	@Override
	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.DUPLEX;
	}
	@Override
	protected SessionChoice getSessionChoice() {
		return SessionChoice.P2P;
	}
	@Override
	protected void initPortLaucherSupports() {
		super.initPortLaucherSupports();
		ObjectTranslatingIPTrapperSelector.getTrapperSelector()
				.setReceiveTrapperFactory(
						new ACustomDeserializingForwarderFactory());
		ObjectTranslatingIPTrapperSelector
				.getTrapperSelector()
				.setSendTrapperFactory(new ACustomSerializingForwarderFactory());

	}
	@Override
	protected void createUI(InputPort anInputPort) {
		Scanner aScanner = new Scanner(System.in);
		for (;;) {
			System.out
					.println("Enter user name whose counter is to be incremented:");
			String aName = aScanner.nextLine();
			Counter aCounter = (Counter) DirectedRPCProxyGenerator
					.generateRPCProxy((DuplexRPCSessionPort) mainPort, aName,
							ACounter.class, null);
			aCounter.increment(1);
			System.out.println("New counter value:" + aCounter.getValue());
		}
	}
}
