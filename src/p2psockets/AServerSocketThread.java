package p2psockets;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.List;

//import com.sun.corba.se.impl.ior.ByteBuffer;
//import com.sun.corba.se.pept.transport.Selector;

public class AServerSocketThread implements Runnable {
	List<Socket> sockets;
	AbstractSelector socketSelector;
	ServerSocketChannel myServerSocketChannel;
	 // The buffer into which we'll read data when it's available
	ByteBuffer readBuffer = ByteBuffer.allocate(8192);	
	// dunno if need more than one listener
	List<ReadListener> readListeners = new ArrayList();
	ObjectOutputStream objectOutputStream;
	ByteArrayOutputStream byteArrayOutputStream;
	public AServerSocketThread(String[] args) {
		socketSelector = toSelector();
		myServerSocketChannel = toServerSocket(args[0]);		
		register(myServerSocketChannel, socketSelector);
		byteArrayOutputStream = new ByteArrayOutputStream();
		byteArrayOutputStream.toByteArray();
		
		

	}
	
	ObjectOutputStream toObjectOutputStream(ByteArrayOutputStream byteArrayStream ) {
		try {
			return new ObjectOutputStream(byteArrayStream);
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public void run() {
		while (true) {
			//SocketChannel socket = accept(myServerSocketChannel);			
		}		
	}
	
	static void register(ServerSocketChannel theServerSocketChannel, AbstractSelector theSelector) {
		try {
			theServerSocketChannel.register(theSelector, SelectionKey.OP_ACCEPT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
//	static SocketChannel accept (ServerSocketChannel theServerSocketChannel) {
//		try {		
//			return  theServerSocketChannel.accept();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	 void accept(SelectionKey key)  {
		 try {
		    // For an accept to be pending the channel must be a server socket channel.
		    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

		    // Accept the connection and make it non-blocking
		    SocketChannel socketChannel = serverSocketChannel.accept();
		    //Socket socket = socketChannel.socket();
		    socketChannel.configureBlocking(false);

		    // Register the new SocketChannel with our Selector, indicating
		    // we'd like to be notified when there's data waiting to be read
		    socketChannel.register(socketSelector, SelectionKey.OP_READ);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	 }
	 
	 void select() {
		 try {
		 	socketSelector.select();

	        // Iterate over the set of keys for which events are available
		 	for (SelectionKey selectionKey:socketSelector.selectedKeys()) {
	          if (!selectionKey.isValid()) {
	            continue;
	          } 
	          // Check what event is available and deal with it
	          if (selectionKey.isAcceptable()) {
	             accept(selectionKey);
	          } else if (selectionKey.isReadable()) {
	        	  read(selectionKey);
	          }
		 	}
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	      

	 }
	  void read(SelectionKey key)  {
		  
		    SocketChannel socketChannel = (SocketChannel) key.channel();
		    //Socket socket = socketChannel.socket();
		    
		    //ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		 

		    // Clear out our read buffer so it's ready for new data
		    this.readBuffer.clear();
		    
		    // Attempt to read off the channel
		    int numRead;
		    try {
		  
		      numRead = socketChannel.read(this.readBuffer);
		    } catch (Exception e) {
		      // The remote forcibly closed the connection, cancel
		      // the selection key and close the channel.
		    	e.printStackTrace();
		      key.cancel();
		      //socketChannel.close();
		      return;
		    }

		    if (numRead == -1) {
		      // Remote entity shut the socket down cleanly. Do the
		      // same from our end and cancel the channel.
		      //key.channel().close();
		      key.cancel();
		      return;
		    }

		    // Hand the data off to our worker thread
		    notifyListeners(socketChannel.socket(), this.readBuffer.array(), numRead); 
		  } 
	
	static AbstractSelector toSelector() {
		try {
			return SelectorProvider.provider().openSelector();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	static ServerSocketChannel toServerSocket(String portString) {
		try {
			int portNumber = Integer.parseInt(portString);
			return toServerSocket(portNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	static ServerSocketChannel toServerSocket(int portNumber) {
		try {	
			ServerSocketChannel retVal = ServerSocketChannel.open();
			retVal.configureBlocking(false);
		    InetSocketAddress isa = new InetSocketAddress(getHostName(), portNumber);
		    retVal.socket().bind(isa);		    
		    return retVal;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	public static String getHostName() {
    	try {
    		return java.net.InetAddress.getLocalHost().getHostName();
    	} catch (Exception e) {
    		return null;
    	}
	}
	
	public void addReadListener(ReadListener theReadListener) {
		readListeners.add(theReadListener);
	}
	void notifyListeners(Socket socket, byte[] bytes, int theNumRead) {
		for (ReadListener readListener: readListeners) {
			readListener.bytesRead(socket, bytes, theNumRead);
		}
	}
	

}
