package inputport.datacomm.simplex.buffer.nio;

import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexInputPortFactory;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.AGenericSimplexBufferClientInputPort;
import inputport.datacomm.simplex.buffer.AGenericSimplexBufferServerInputPort;
import inputport.datacomm.simplex.buffer.GenericSimplexClientInputPort;
import inputport.datacomm.simplex.buffer.GenericSimplexServerInputPort;
import inputport.datacomm.simplex.buffer.SimplexBufferClientInputPortDriver;
import inputport.datacomm.simplex.buffer.SimplexBufferServerInputPortDriver;
import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.factories.SelectionManagerFactory;
import util.trace.Tracer;


public class AnNIOSimplexBufferInputPortFactory  implements SimplexInputPortFactory<ByteBuffer> {

	@Override
	public SimplexServerInputPort<ByteBuffer> createSimplexServerInputPort(String theServerId,
			String theServerName) {	
		Tracer.info(this, "Creating and linking NIO simplex server skeleton and driver");
		GenericSimplexServerInputPort<ServerSocketChannel, SocketChannel> skeleton = new AGenericSimplexBufferServerInputPort(theServerName, theServerId);
		SelectionManager selectionManager = SelectionManagerFactory.getSelectionManager();
		SimplexBufferServerInputPortDriver<ServerSocketChannel, SocketChannel> driver = new AnNIOSimplexBufferServerInpuPorttDriver(selectionManager, theServerId);
		skeleton.setDriver(driver);
		driver.setSkeleton(skeleton);
		return skeleton;
	}
	@Override
	public SimplexClientInputPort<ByteBuffer> createSimplexClientInputPort(String theServerHost,
			String theServerId, String aServerName, String theClientName) {
		Tracer.info(this, "Creating and linking simplex client skeleton and driver");
		SelectionManager selectionManager = SelectionManagerFactory.getSelectionManager();
		GenericSimplexClientInputPort skeleton = new AGenericSimplexBufferClientInputPort(theServerHost, theServerId, aServerName, theClientName);
		SimplexBufferClientInputPortDriver<SocketChannel> implementation = new AnNIOSimplexBufferClientInputPortDriver(selectionManager, theServerHost, theServerId, aServerName);
		skeleton.setDriver(implementation);
		implementation.setSkeleton(skeleton);
		return skeleton;
	}
}
