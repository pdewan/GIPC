package inputport.datacomm.duplex.buffer.nio;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.AGenericDuplexBufferClientInputPort;
import inputport.datacomm.duplex.buffer.AGenericDuplexBufferServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferGenericClientInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferGenericServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexClientInputPortDriver;
import inputport.datacomm.duplex.buffer.DuplexServerInputPortDriver;
import inputport.nio.manager.SelectionManagerFactory;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import util.trace.Tracer;


public class AnNIODuplexBufferInputPortFactory extends SelectionManagerFactory implements DuplexInputPortFactory {

	@Override
	public DuplexServerInputPort createDuplexServerInputPort(String theServerId,
			String theServerName) {
		Tracer.info(this, "Creating and linking NIO duplex server skeleton and driver and selecting thread");
		DuplexBufferGenericServerInputPort<ServerSocketChannel, SocketChannel> skeleton = new AGenericDuplexBufferServerInputPort(theServerName, theServerId);
		getSelectionManager();
		DuplexServerInputPortDriver<ServerSocketChannel,SocketChannel> driver = new AnNIODuplexBufferServerInputPortDriver(selectionManager, theServerId);
		skeleton.setDriver(driver);
		driver.setSkeleton(skeleton);
		return skeleton;
	}
	@Override
	public DuplexClientInputPort createDuplexClientInputPort(String theServerHost,
			String theServerId, String aServerName, String theClientName) {
		Tracer.info(this, "Creating and linking NIO duplex client skeleton and driver and selecting thread");
		DuplexBufferGenericClientInputPort skeleton = new AGenericDuplexBufferClientInputPort(theServerHost, theServerId, aServerName, theClientName);
		if (theClientName.equals(aServerName)) {
			return skeleton;
		}
		
		getSelectionManager();
		DuplexClientInputPortDriver<SocketChannel> implementation = new AnNIODuplexBufferClientInputPortDriver(selectionManager, theServerHost, theServerId, aServerName);
		skeleton.setDriver(implementation);
		implementation.setSkeleton(skeleton);
//		return new AMonolithicNIOClientInputPort(selectingRunnable, theServerHost, theServerId, theClientName);
		return skeleton;
	}
}
