package inputport.datacomm.simplex.buffer;
import inputport.AConnectRegistrarAndNotifier;
import inputport.ConnectionListener;
import inputport.ConnectionRegistrarAndNotifier;
import inputport.ConnectionType;
import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import port.trace.AConnectionEvent;
import port.trace.AReplaceConnectionEvent;
import port.trace.ConnectiontEventBus;
import port.trace.buffer.BufferChannelConnectInitiated;
import port.trace.buffer.BufferChannelConnectFinished;
import port.trace.buffer.BufferChannelDisconnectInitiated;
import port.trace.buffer.BufferChannelDisconnected;
import port.trace.buffer.BufferReceived;
import port.trace.buffer.TrapperBufferReceived;
import port.trace.buffer.ClientNameAssociatedWithPort;
import port.trace.buffer.ClientNameLookedUp;
import port.trace.buffer.DuplicateBufferChannelConnectIgnored;
import util.trace.Tracer;




public class AGenericSimplexBufferServerInputPort<RequestChannelType, MessageChannelType>  implements GenericSimplexServerInputPort<RequestChannelType, MessageChannelType>   {
	protected ReceiveTrapper<ByteBuffer, ByteBuffer> receiveTrapper;
	boolean phsyicallyConnected;
	protected String lastSender = null;
	int totalBytesReceived = 0;

	
	ConnectionRegistrarAndNotifier connectNotifier = new AConnectRegistrarAndNotifier();
	ReceiveRegistrarAndNotifier receiptNotfier = new AReceiveRegistrarAndNotifier();
	SimplexBufferServerInputPortDriver<RequestChannelType, MessageChannelType> driver;

	String myName;
	String myId;
	protected Map<MessageChannelType, String> channelToClientName = new HashMap();
	protected Map<String, MessageChannelType> clientNameToChannel = new HashMap();
	public AGenericSimplexBufferServerInputPort (String aMyName, String anId) {
		myName = aMyName;
		myId = anId;
//		receiveTrapper = GlobalState.getSimplexBufferServerIPTrapperSelector().createReceiveTrapper(this, receiptNotfier);
//		receiveTrapper = SimplexBufferServerIPTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiptNotfier);
		setReceiveTrapper(SimplexBufferServerIPTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiptNotfier));

		Tracer.info(this, "Retrieved from global state receive trapper:" + receiveTrapper);
	}
	
	@Override
	public void connect() {
		if (phsyicallyConnected) {
			Tracer.info(this, "Ignoring duplicate call to connect");
			DuplicateBufferChannelConnectIgnored.newCase(this, driver);
			return;
		}
		Tracer.info(this, "Changing connection status and asking driver to connect");
		phsyicallyConnected = true;
		BufferChannelConnectInitiated.newCase(this, driver);
		driver.connect();
	}
	protected void associate (MessageChannelType aChannelType, String aClientName) {
		Tracer.info(this, "Associating " + aClientName  + " with " + aChannelType);
		channelToClientName.put(aChannelType, aClientName);
		ClientNameAssociatedWithPort.newCase(this, aClientName, aChannelType);
		MessageChannelType oldChannel = clientNameToChannel.put(aClientName, aChannelType);
		if (oldChannel != null) {
			Tracer.error("A second concurrent connection from client: " + aClientName + " to the same server port. All messages from the old connection will be sent back on the new one");
		}
	}
	@Override
	public void messageReceived(MessageChannelType aChannelType,
			ByteBuffer aMessage) {
		Tracer.info(this, "ServerInputPort Received message:" + aMessage + " on channel:" + aChannelType);
		String aClientName = channelToClientName.get(aChannelType);
		if (aClientName == null) {
			int deducedLength = aMessage.limit() - aMessage.position();
			byte[] stringBytes = new byte[deducedLength];			
			aMessage.get(stringBytes);
			aClientName = new String(stringBytes);
			associate(aChannelType, aClientName);
			Tracer.info(this, "ServerInputPort connected to:" + aChannelType);
			notifyConnect(aClientName, ConnectionType.TO_CLIENT);
		} else {
//			Tracer.info(this, "ServerInputPort received message " + aMessage + " from:" + aChannelType );
//			totalBytesReceived += aMessage.limit() - aMessage.position(); 
//			Tracer.info("Total bytes received by server port: " + getLocalName() + " " + totalBytesReceived);
//
//			notifyPortReceive(clientName, aMessage);	
			ClientNameLookedUp.newCase(this, aClientName, aChannelType);
			messageReceived(aClientName, aMessage);
		}		
	}
	/*
	 * Needs to be public as otherwise the other messageReceived will be called
	 * by buffer sender
	 */
	public void messageReceived(String aClientName, ByteBuffer aMessage) {
		Tracer.info(this, "ServerInputPort received message " + aMessage + " from:" + aClientName );
		totalBytesReceived += aMessage.limit() - aMessage.position(); 
		Tracer.info("Total bytes received by server port: " + getLocalName() + " " + totalBytesReceived);
		BufferReceived.newCase(this, aClientName, myName, aMessage, driver);
		notifyPortReceive(aClientName, aMessage);	
	}
	@Override
	public void disconnect() {
		Tracer.info(this, "Disconnecting all message channels");

		for (MessageChannelType socketChannel: channelToClientName.keySet()) {
			String clientName = channelToClientName.get(socketChannel);
			driver.disconnect(socketChannel);
			BufferChannelDisconnectInitiated.newCase(this, driver, clientName);

			notifyDisconnect(clientName, true, "Local process closed connection to destination", null);
		}
		channelToClientName.clear();
		clientNameToChannel.clear();

	}
	public void addConnectionListener(ConnectionListener portConnectListener) {
		connectNotifier.addConnectionListener(portConnectListener);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		connectNotifier.removeConnectionListener(portConnectListener);
	}
	
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		BufferChannelConnectFinished.newCase(this, driver, aConnectionType);

		connectNotifier.notifyConnect(remoteEnd, aConnectionType);
	}

	public void notifyDisconnect(String aRemoteEnd, boolean anExplicitDisconnection,
			String anExplanation, ConnectionType aConnectionType) {
		connectNotifier.notifyDisconnect(aRemoteEnd, anExplicitDisconnection, anExplanation, null);

		
	}

	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDisconnection, String anExplanation, ConnectionType aConnectionType) {
		Tracer.info(this, "Received from channel disconnected message");
		BufferChannelDisconnected.newCase(this, myName, aRemoteEndName, anExplicitDisconnection, anExplanation, aConnectionType);
		notifyDisconnect(aRemoteEndName, anExplicitDisconnection, anExplanation, null);
		MessageChannelType channel = clientNameToChannel.get(aRemoteEndName);
		channelToClientName.remove(channel);
		clientNameToChannel.remove(aRemoteEndName);
		// how did this pass Java's type checking, because MessageType is object?
//		channelToClientName.remove(aRemoteEndName);
	}
	public void notifyConnectFailure(String aRemoteEnd, String aMessage, ConnectionType aConnectionType) {
		connectNotifier.notifyConnectFailure(aRemoteEnd, aMessage, null);
	}
	@Override
	public void addReceiveListener(ReceiveListener<ByteBuffer> portReceiveListener) {
		receiptNotfier.addReceiveListener(portReceiveListener);
	}
	public void removeReceiveListener(ReceiveListener<ByteBuffer> portReceiveListener) {
		receiptNotfier.removeReceiveListener(portReceiveListener);
	}
	@Override
	public String getSender() {
		return lastSender;
	}
	@Override
	public void setSender(String newVal) {
	
		if (newVal.equals(lastSender)) return;
		Tracer.info(this, "Setting reply receiver:" + newVal);
		lastSender = newVal;
	}
	@Override
	public void notifyPortReceive(String remoteEnd, ByteBuffer message) {
		setSender(remoteEnd);
		receiveTrapper.notifyPortReceive(remoteEnd, message);
	}
	@Override
	public void setDriver(SimplexBufferServerInputPortDriver<RequestChannelType, MessageChannelType> aDriver) {
		driver = aDriver;
	}
	@Override
	public String getLocalName() {
		return myName;
	}
	public ReceiveTrapper<ByteBuffer, ByteBuffer> getReceiveTrapper() {
		return receiveTrapper;
	}

	public void setReceiveTrapper(ReceiveTrapper<ByteBuffer, ByteBuffer> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(receiveTrapper.getDestination());
			Tracer.warning("receive trapper == mull!");
		} else if (newVal.getDestination() == receiveTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, receiveTrapper, newVal, false, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, false));
		}
		
		
		Tracer.info(this, "Setting my receive trapper:" + newVal);
		this.receiveTrapper = newVal;
//		DistEventsBus.newEvent(new AConnectionEvent(this, newVal, false));
	}

	@Override
	public String getServerId() {
		return myId;
	}
	@Override
	public Set<String> getConnections() {
		Set<String> keySet = clientNameToChannel.keySet();
		Set<String> retVal = new HashSet();
		for (String name: keySet) {
			retVal.add(name);			
		}
		return retVal;
	}
	@Override
	public MessageChannelType getChannel(String clientName) {
		return  clientNameToChannel.get(clientName);		
	}
	@Override
	public String getClientName(MessageChannelType aChannel) {
		return  channelToClientName.get(aChannel);		
	}
	@Override
	public boolean isConnected(String clientName) {
		return clientNameToChannel.get(clientName) != null;
	}

	@Override
	public List<ReceiveListener<ByteBuffer>> getReceiveListeners() {
		// TODO Auto-generated method stub
		return receiptNotfier.getReceiveListeners();
	}
	String logicalRemoteEndPoint;
	String phyicalRemoteEndPoint;
	@Override
	public String getLogicalRemoteEndPoint() {
		return logicalRemoteEndPoint;
	}

	@Override
	public String getPhysicalRemoteEndPoint() {
		return phyicalRemoteEndPoint;
	}

	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		logicalRemoteEndPoint = newVal;
	}

	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		phyicalRemoteEndPoint = newVal;
	}

	


	
	
}
