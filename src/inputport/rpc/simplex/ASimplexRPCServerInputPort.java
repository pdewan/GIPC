package inputport.rpc.simplex;

import java.util.List;
import java.util.Set;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.rpc.Marshaller;
import inputport.rpc.MarshallerSelector;
import inputport.rpc.RPCRegistry;
import inputport.rpc.RPCRegistrySelector;
import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;





public class ASimplexRPCServerInputPort implements SimplexRPCServerInputPort, ReceiveListener<Object> {
	protected RPCRegistry rpcRegistry;
	SimplexServerInputPort<Object> objectServerInputPort;
	protected ReceiveRegistrarAndNotifier<Object> receiveReceiptRegistrarAndNotifier = new AReceiveRegistrarAndNotifier<Object>();
	protected ReceiveTrapper<Object, Object> serializableCallReceiveTrapper;
	protected Marshaller marshaller;

	public ASimplexRPCServerInputPort(SimplexServerInputPort<Object> anObjectServerInputPort) {
		objectServerInputPort = anObjectServerInputPort;
		rpcRegistry = RPCRegistrySelector.createRPCRegistry(this);
		objectServerInputPort.addReceiveListener(this);
//		serializableCallReceiveTrapper = GlobalState.getSimplexSerializableCallTrapper().createReceiveTrapper(this, receiveReceiptRegistrarAndNotifier);
		initReceiveTrapper();		
//		serializableCallReceiveTrapper = SimplexSerializableCallTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveReceiptRegistrarAndNotifier);
//		setReceiveTrapper(serializableCallReceiveTrapper);
		marshaller = MarshallerSelector.createMarshaller();

	}
	
	protected void initReceiveTrapper() {
		serializableCallReceiveTrapper = SimplexSerializableCallTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveReceiptRegistrarAndNotifier);
		setReceiveTrapper(serializableCallReceiveTrapper);
	}


	@Override
	public void register(Class type, Object server) {
		rpcRegistry.register(type, server);		
	}

	@Override
	public Object getServerObject(String name) {
		return rpcRegistry.getServerObject(name);
	}


	
	@Override
	public void register(String name, Object server) {
		rpcRegistry.register(name, server);
	}
	@Override
	public void register(Object server) {
		rpcRegistry.register(server);		
	}

	
    @Override
	public void notifyPortReceive(String aRemoteEnd, Object aMessage) {
    	receiveReceiptRegistrarAndNotifier.notifyPortReceive(aRemoteEnd, aMessage);
    	
//    	getReceiveTrapper().notifyPortReceive(aRemoteEnd, aMessage);


	}
	
	public void addConnectionListener(ConnectionListener portConnectListener) {
	objectServerInputPort.addConnectionListener(portConnectListener);
}

public void addReceiveListener(ReceiveListener<Object> portReceiveListener) {
	receiveReceiptRegistrarAndNotifier.addReceiveListener(portReceiveListener);
}
public void connect() {
	objectServerInputPort.connect();
}
public void disconnect() {
	objectServerInputPort.disconnect();
}

public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
	objectServerInputPort.notifyConnect(remoteEnd, aConnectionType);
}
public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
	objectServerInputPort.notifyConnectFailure(remoteEnd, message, null);
}
public void notifyDisconnect(String remoteEnd, boolean eof, String closeReason, ConnectionType aConnectionType) {
	objectServerInputPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
}
@Override
public String getLocalName() {
	return objectServerInputPort.getLocalName();
}

public void removeConnectionListener(ConnectionListener portConnectListener) {
	objectServerInputPort.removeConnectionListener(portConnectListener);
}

public void removeReceiveListener(ReceiveListener<Object> portReceiveListener) {
	objectServerInputPort.removeReceiveListener(portReceiveListener);
}
@Override
public void messageReceived(String aSource, Object aMessage) {
//	notifyPortReceive(remoteClientName, message);
	getReceiveTrapper().notifyPortReceive(aSource, aMessage);

}
	
	@Override
	public String getServerId() {
		return objectServerInputPort.getServerId();
	}

	@Override
	public ReceiveTrapper<Object, Object> getReceiveTrapper() {
		return serializableCallReceiveTrapper;
	}

	@Override
	public void setReceiveTrapper(ReceiveTrapper<Object, Object> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(serializableCallReceiveTrapper.getDestination());
			Tracer.warning("send trapper == mull!");
		} else if (newVal.getDestination() == serializableCallReceiveTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, serializableCallReceiveTrapper, newVal, false, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, false));
		}
		
		
//		if (newVal.getDestination() == null)
//			newVal.setDestination(serializableCallReceiveTrapper.getDestination());
		serializableCallReceiveTrapper = newVal;
//		DistEventsBus.newEvent(new AConnectionEvent(this, newVal, false));
	}
	@Override
	public Set<String> getConnections() {
		return objectServerInputPort.getConnections();
	}

	@Override
	public String getSender() {
		return objectServerInputPort.getSender();
	}
	@Override
	public void setSender(String newVal) {
		objectServerInputPort.setSender(newVal);
	}

	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return objectServerInputPort.isConnected(remoteName);
	}

	@Override
	public InputPort getDataInputPort() {
		return objectServerInputPort;
	}



	@Override
	public Marshaller getMarshaller() {
		return marshaller;
	}



	@Override
	public void setDataInputPort(InputPort newVal) {
		objectServerInputPort =  (SimplexServerInputPort<Object>) newVal;
		
	}



	@Override
	public void setMarshaller(Marshaller newVal) {
		marshaller = newVal;
	}



	@Override
	public List<ReceiveListener<Object>> getReceiveListeners() {
		return receiveReceiptRegistrarAndNotifier.getReceiveListeners();
	}



	@Override
	public Set<String> registeredMethodNames() {
		return rpcRegistry.registeredMethodNames();
	}
	public String getLogicalRemoteEndPoint() {
		return objectServerInputPort.getLogicalRemoteEndPoint();
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		return objectServerInputPort.getPhysicalRemoteEndPoint();
	}
	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		objectServerInputPort.setLogicalRemoteEndPoint(newVal);
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		objectServerInputPort.setPhysicalRemoteEndPoint(newVal);
	}
	

}
