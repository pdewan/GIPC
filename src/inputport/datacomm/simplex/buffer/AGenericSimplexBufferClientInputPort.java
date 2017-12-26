package inputport.datacomm.simplex.buffer;

import inputport.AConnectRegistrarAndNotifier;
import inputport.ConnectionListener;
import inputport.ConnectionRegistrarAndNotifier;
import inputport.ConnectionType;
import inputport.datacomm.SendToUnconnectedPortException;
import inputport.datacomm.SendTrapper;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import trace.port.AConnectionEvent;
import trace.port.ConnectiontEventBus;
import trace.port.buffer.BufferChannelConnectFailure;
import trace.port.buffer.BufferChannelConnectFinished;
import trace.port.buffer.BufferChannelConnectInitiated;
import trace.port.buffer.BufferChannelDisconnectInitiated;
import trace.port.buffer.BufferChannelDisconnected;
import trace.port.buffer.BufferSendFinished;
import trace.port.buffer.BufferSendInitiated;
import trace.port.buffer.BufferSendToUnconnectedChannelIgnored;
import trace.port.buffer.ClientNameSendInitiated;
import trace.port.buffer.DuplicateBufferChannelConnectIgnored;
import trace.port.buffer.NumberBytesSent;
import trace.port.buffer.TrapperBufferSendFinished;
import util.trace.Tracer;


public class AGenericSimplexBufferClientInputPort<ChannelType>  implements GenericSimplexClientInputPort<ChannelType>  {
	long totalBytesSent = 0;
	ConnectionRegistrarAndNotifier connectNotifier = new AConnectRegistrarAndNotifier();
	protected SendTrapper<ByteBuffer, ByteBuffer> sendTrapper;
	
	SendRegistrarAndNotifier sendNotfier = new ASendRegistrarAndNotifier();	
	String myName;
	protected String serverDescription, serverName;
	protected Set<String> serverNames;
	protected Set<String> emptySet = new HashSet();

	boolean connected = false;
	String[] remoteNames;
	protected SimplexBufferClientInputPortDriver<ChannelType> driver;

	public AGenericSimplexBufferClientInputPort( String theRemoteHostName, String theRemotePort, String aRemoteName, String theMyName) {

		if (theMyName != null)
			myName = theMyName;
		else {
			myName = "Random Name:" + Math.random();
		}
		serverDescription = theRemoteHostName+":"+theRemotePort;
		serverName = aRemoteName;
		serverNames = new HashSet();
		serverNames.add(serverName);
	}
	@Override
	public void setDriver(SimplexBufferClientInputPortDriver<ChannelType> aChannelImplementation) {
		driver = aChannelImplementation;	
//		sendTrapper = SimplexBufferClientIPTrapperSelector.getTrapperSelector().createSendTrapper(this, aChannelImplementation);
		sendTrapper = SimplexBufferClientIPTrapperSelector.getTrapperSelector().createSendTrapper(this, aChannelImplementation);

		setSendTrapper(sendTrapper);
	}
	@Override
	public String getLogicalRemoteEndPoint() {
		return getPhysicalRemoteEndPoint();
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		return serverName;
	}
	@Override
	public String getLocalName() {
		return myName;
	}

	@Override
	public void connect() {
		if (connected) {
			DuplicateBufferChannelConnectIgnored.newCase(this, driver);
			Tracer.info(this, "Ignoring connect call as already connected");
			return;
		}
		BufferChannelConnectInitiated.newCase(this, driver, myName, serverName);
		Tracer.info(this, "Asking driver to connect and changing status");
		connected = true;
		driver.connect();
	}
	void sendClientName() {
		ByteBuffer message =  ByteBuffer.wrap (myName.getBytes());
		Tracer.info(this, "Sending to server my name: " +  myName);
		ClientNameSendInitiated.newCase(this, serverName, myName);
//		BufferChannelSendInitiated.newCase(this, aBufferChannel, aByteBuffer)
		doSend(serverName, message);
	}	
	@Override
	public void send(ByteBuffer message) {
		send (serverName, message);		

	}

	@Override
	public void send(String remoteName, ByteBuffer message) {
		if (!isConnected(remoteName)) {
			BufferSendToUnconnectedChannelIgnored.newCase(this, myName,  this.getPhysicalRemoteEndPoint(), message);

			Tracer.error("Ignoring attempt to send " + message + " to " + remoteName + " before connection completely established");
			return;
		}
		totalBytesSent += message.limit() - message.position();
		doSend(remoteName, message);
//		Tracer.info("Total bytes sent to " + this.getPhysicalRemoteEndPoint() + " " + totalBytesSent);
		NumberBytesSent.newCase(this, getLocalName(), this.getPhysicalRemoteEndPoint(), totalBytesSent);
		
//		if (!connected) throw new SendToUnconnectedPortException();
//		Tracer.info(this, "Forwarding message to send trapper:" + sendTrapper);
//		sendTrapper.send(remoteName, message);		
	}
	 void doSend(String remoteName, ByteBuffer message) {
		if (!connected) throw new SendToUnconnectedPortException(remoteName + " is unconnected");
		Tracer.info(this, "Forwarding message to send trapper:" + sendTrapper);
		BufferSendInitiated.newCase(this, myName,  this.getPhysicalRemoteEndPoint(), message, sendTrapper);
		sendTrapper.send(remoteName, message);		
	}
	
	@Override
	public void disconnect() {
		Tracer.info(this, "Disconnecting driver and notifying listeners");
		BufferChannelDisconnectInitiated.newCase(this, driver, serverName);
		driver.disconnect();
		connected = false;	
		notifyDisconnect(serverName, true, "Local process closed connection to remote end", null);
	}

	public Set<String> getConnections() {
		if (connected)
			return serverNames;
		else
			return emptySet;
	}
	public void addConnectionListener(ConnectionListener portConnectListener) {
		connectNotifier.addConnectionListener(portConnectListener);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		connectNotifier.removeConnectionListener(portConnectListener);
	}
	public void notifyDisconnect(String aRemoteEnd, boolean anExplicitClose,
			String anExplanation, ConnectionType aConnectionType) {
		connectNotifier.notifyDisconnect(aRemoteEnd, anExplicitClose, anExplanation, null);
	}	
	public void notifyConnectFailure(String aRemoteEnd, String anExplanation, ConnectionType aConnectionType) {
		connectNotifier.notifyConnectFailure(aRemoteEnd, anExplanation, null);
	}
	@Override
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		connectNotifier.notifyConnect(remoteEnd, aConnectionType);
		
	}
	public void addSendListener(ByteBufferSendListener portSendListener) {
		sendNotfier.addSendListener(portSendListener);
	}
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		sendNotfier.removeSendListener(portSendListener);
	}
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		sendNotfier.notifyPortSend(aRemoteEnd, message, sendId);
	}
	public SendTrapper<ByteBuffer, ByteBuffer> getSendTrapper() {
		return sendTrapper;
	}
	public void setSendTrapper(SendTrapper<ByteBuffer, ByteBuffer> newVal) {
		Tracer.info(this, "Set my send trapper to:" + newVal);
		sendTrapper = newVal;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
	}
	
	boolean logicallyConnected;
	
	@Override
	public void connected(String aRemoteEnd, ConnectionType aConnectionType) {
		Tracer.info(this, "Received connected notificaton from driver");
		sendClientName();
		logicallyConnected = true;
		BufferChannelConnectFinished.newCase(this, driver, aConnectionType, myName, serverName);
		notifyConnect(aRemoteEnd, aConnectionType);
		
	}
	@Override
	public void notConnected(String aRemoteEnd, String anExplanation, ConnectionType aConnectionType) {
		Tracer.info(this, "Received not connected notification from driver");
		connected = false;
		BufferChannelConnectFailure.newCase(this, driver);
		notifyConnectFailure(aRemoteEnd, anExplanation, null);
	}
	@Override
	public void messageSent(String aRemoteEnd, ByteBuffer message,
			int sendId) {
		Tracer.info(this, "Received sent notification from driver");
		BufferSendFinished.newCase(this, myName, aRemoteEnd, message, driver);
		notifyPortSend(aRemoteEnd, message, sendId);
		
	}
	@Override
	public void  disconnected(String aRemoteEnd, boolean anExplicitDisconnection, String anExplanation, ConnectionType aConnectionType) {
		Tracer.info(this, "Received disconnect notification from driver");
		BufferChannelDisconnected.newCase(this, myName, aRemoteEnd, anExplicitDisconnection, anExplanation, aConnectionType);
		connected = false;
		notifyDisconnect(aRemoteEnd, anExplicitDisconnection, anExplanation, null);
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		Tracer.error("Should not change phsyical remote end point");
		
	}
	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		Tracer.error("Should not change logical remote end point");
		
	}
	
	@Override
	public boolean isConnected(String clientName) {
		return logicallyConnected;
	}

	
}
