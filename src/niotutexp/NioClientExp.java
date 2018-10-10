package niotutexp;



import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import extraip.ASingleBufferSerializationSupport;
import extraip.SingleBufferSerializationSupport;
import util.misc.SeekableByteArrayInputStream;
import util.misc.SeekableByteArrayOutputStream;




public class NioClientExp implements Runnable {
	// The host:port combination to connect to
	private InetAddress hostAddress;
	private int port;

	// The selector we'll be monitoring
	private Selector selector;

	// The buffer into which we'll read data when it's available
	private ByteBuffer readBuffer = ByteBuffer.allocate(8192);
	private byte[] readByteArray = new byte[8192];
	private byte[] headerBytes = SeekableByteArrayOutputStream.getHeaderBytes();
	private byte[] dummyBytes = new byte[headerBytes.length];

	// A list of PendingChange instances
	private List pendingChanges = new LinkedList();

	// Maps a SocketChannel to a list of ByteBuffer instances
	private Map pendingData = new HashMap();
	
	// Maps a SocketChannel to a RspHandler
	private Map rspHandlers = Collections.synchronizedMap(new HashMap());
	RspHandler initRspHandler;
	
	SingleBufferSerializationSupport  portSerializationSupport = new ASingleBufferSerializationSupport();
	
	public NioClientExp(InetAddress hostAddress, int port, RspHandler theHandler) throws IOException {
		this.hostAddress = hostAddress;
		this.port = port;
		this.selector = this.initSelector();
		initRspHandler = theHandler;
	}

	public void send(SocketChannel socketChannel, byte[] data) throws IOException {
//		// Start a new connection
//		SocketChannel socket = this.initiateConnection();
//		
//		// Register the response handler
//		this.rspHandlers.put(socket, handler);
		
		// And queue the data we want written
		synchronized (this.pendingData) {
			List queue = (List) this.pendingData.get(socketChannel);
			if (queue == null) {
				queue = new ArrayList();
				this.pendingData.put(socketChannel, queue);
			}
			queue.add(ByteBuffer.wrap(data));
		}

		// Finally, wake up our selecting thread so it can make the required changes
		this.selector.wakeup();
	}
	public SocketChannel connect(RspHandler handler) throws IOException {
		// Start a new connection
		SocketChannel socket = this.initiateConnection();
		
		// Register the response handler
		this.rspHandlers.put(socket, handler);
		return socket;
		
		

		// Finally, wake up our selecting thread so it can make the required changes
		//this.selector.wakeup();
	}
	
	public SocketChannel syncConnect(RspHandler handler)  {
		// Start a new connection
		// Create a non-blocking socket channel
		try {
		SocketChannel socketChannel = SocketChannel.open();
		globalSocketChannel = socketChannel;
		//socketChannel.configureBlocking(false);
		//socketChannel.configureBlocking(true);	
		// Kick off connection establishment
		socketChannel.connect(new InetSocketAddress(this.hostAddress, this.port));
		Socket socket = socketChannel.socket();
		System.out.println("Connected client socket, port:" + socket.getPort() + " local port:" + socket.getLocalPort() + "remote socket address:" +  socket.getRemoteSocketAddress());
		if (!socketChannel.isConnected()) {
			System.out.println("not connected");
			socketChannel.finishConnect();
		}
		//boolean retVal = socketChannel.finishConnect();
		
		//socketChannel.configureBlocking(true);
	
		
		socketChannel.configureBlocking(false);
		//selector.wakeup();
		//selector.wakeup();
		//socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);	
		
		this.rspHandlers.put(socketChannel, handler);
		register = true;
		selector.wakeup();
		return socketChannel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		

		// Finally, wake up our selecting thread so it can make the required changes
		//this.selector.wakeup();
	}
	SocketChannel globalSocketChannel;
	boolean register = false;

	public void run() {
		SelectionKey writeKey = null;
		try {
			
				
//		SocketChannel socketChannel = syncConnect(initRspHandler);
//		globalSocketChannel = socketChannel;
//		send(socketChannel, "Hello World".getBytes());
//	     send(socketChannel, "Goodbye World".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				if (register) {
					//globalSocketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
					globalSocketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
					register = false;
				}
				// process synchronous write
//				if (writeKey != null) {
//					//writeKey.cancel();
//					syncWrite(writeKey);
//					writeKey = null;
//				}
				// Process any pending changes
				synchronized (this.pendingChanges) {
					Iterator changes = this.pendingChanges.iterator();
					while (changes.hasNext()) {
						ChangeRequest change = (ChangeRequest) changes.next();
						switch (change.type) {
						case ChangeRequest.CHANGEOPS:
							SelectionKey key = change.socket.keyFor(this.selector);
							key.interestOps(change.ops);
							break;
						case ChangeRequest.REGISTER:
							//change.socket.configureBlocking(true);
							SocketChannel sockChannel = change.socket;
							int ops = change.ops;
							Selector theSelector = selector;
							sockChannel.register(theSelector, ops);
							//sockChannel.register(theSelector, ops);

							//change.socket.register(this.selector, change.ops);						

							break;
						}
					}
					this.pendingChanges.clear();
				}

				// Wait for an event one of the registered channels
				this.selector.select();

				// Iterate over the set of keys for which events are available
				Iterator selectedKeys = this.selector.selectedKeys().iterator();
				while (selectedKeys.hasNext()) {
					SelectionKey key = (SelectionKey) selectedKeys.next();
					selectedKeys.remove();

					if (!key.isValid()) {
						continue;
					}

					// Check what event is available and deal with it
					if (key.isConnectable()) {
						this.finishConnection(key);
					}
					if (key.isReadable()) {
						this.read(key);
					}
					if (key.isWritable()) {
						writeKey = key;
						//this.write(key);
						this.serializedWrite(key);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	SeekableByteArrayInputStream byteArrayInputStream = new SeekableByteArrayInputStream(readByteArray, 0, 0);
	ByteBuffer wrappedReadBuffer = ByteBuffer.wrap(readByteArray);
	ObjectInputStream objectInputStream;
	
	void maybeCreateObjectInputStream () {
		if (objectInputStream != null)
			return;		
		try {
		objectInputStream = new ObjectInputStream(byteArrayInputStream);
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	boolean firstRead = false;
//	void initializeInitialBytes() {
//		if (!firstRead)
//			return;
//		int initCount = byteArrayInputStream.getBufCount();
//		initialBytes = new byte[initCount];
//		wrappedReadBuffer.get(initialBytes, 0, initCount);
//		firstRead = false;
//		
//	}
	boolean containsHeader() {
		if (byteArrayInputStream.getCount() - byteArrayInputStream.getPosition() < headerBytes.length)
			return false;
		for (int i=0; i < headerBytes.length; i++) {
			if (headerBytes[i] != wrappedReadBuffer.get(i + byteArrayInputStream.getPosition()))
				return false;
		}
		return true;
		
	}

	static int numReads;
	boolean firstTime = true;	
	private void read(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		

		// Clear out our read buffer so it's ready for new data
		this.readBuffer.clear();
		wrappedReadBuffer.clear();
		byteArrayInputStream.setCount(0);
//		byteArrayInputStream.mark(0);
//		byteArrayInputStream.reset();
		byteArrayInputStream.setPosition(0);

		// Attempt to read off the channel
		int numRead;
		try {
			//numRead = socketChannel.read(this.readBuffer);
			numRead = socketChannel.read(portSerializationSupport.getInputBuffer());
			if (numRead == -1) {
				// Remote entity shut the socket down cleanly. Do the
				// same from our end and cancel the channel.
				key.channel().close();
				key.cancel();
				return;
			}
			System.out.println("Bytes Read:" + numRead);
			List<Serializable> objectsInBuffer = portSerializationSupport.getSerializablesFromInputBuffer();
			for (Serializable readObject:objectsInBuffer) {
				System.out.println("Next Read:" + readObject);				
			}

		} catch (IOException e) {
			// The remote forcibly closed the connection, cancel
			// the selection key and close the channel.
			key.cancel();
			socketChannel.close();
			return;
		}
		if (numReads < 50)
		serializedWrite(socketChannel, "looping back" + numReads++);

//		if (numRead == -1) {
//			// Remote entity shut the socket down cleanly. Do the
//			// same from our end and cancel the channel.
//			key.channel().close();
//			key.cancel();
//			return;
//		}
//
//		// Handle the response
//		this.handleResponse(socketChannel, this.readBuffer.array(), numRead);
	}
	private void readManual(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();

		// Clear out our read buffer so it's ready for new data
		this.readBuffer.clear();
		wrappedReadBuffer.clear();
		byteArrayInputStream.setCount(0);
//		byteArrayInputStream.mark(0);
//		byteArrayInputStream.reset();
		byteArrayInputStream.setPosition(0);

		// Attempt to read off the channel
		int numRead;
		try {
			//numRead = socketChannel.read(this.readBuffer);
			numRead = socketChannel.read(wrappedReadBuffer);
			if (numRead == -1) {
				// Remote entity shut the socket down cleanly. Do the
				// same from our end and cancel the channel.
				key.channel().close();
				key.cancel();
				return;
			}
			System.out.println("Bytes Read:" + numRead);
			byteArrayInputStream.setCount(numRead);
			maybeCreateObjectInputStream();
//			if (firstTime) {
//				firstTime = false;
//			} else if (containsHeader()) {
//					byteArrayInputStream.read(dummyBytes);
//			}
		
			while (true) {
				try {
					
					//System.out.println("Contains header bytes:" + containsHeader());
					if (firstTime) {
						firstTime = false;
					} else if (containsHeader()) {
							byteArrayInputStream.advancePosition(headerBytes.length);
					}
				Object readObject = objectInputStream.readObject();
				System.out.println(readObject);
				} catch (EOFException eof) {					
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// The remote forcibly closed the connection, cancel
			// the selection key and close the channel.
			key.cancel();
			socketChannel.close();
			return;
		}
		if (numReads < 50)
		serializedWrite(socketChannel, "looping back" + numReads++);

//		if (numRead == -1) {
//			// Remote entity shut the socket down cleanly. Do the
//			// same from our end and cancel the channel.
//			key.channel().close();
//			key.cancel();
//			return;
//		}
//
//		// Handle the response
//		this.handleResponse(socketChannel, this.readBuffer.array(), numRead);
	}

	private void handleResponse(SocketChannel socketChannel, byte[] data, int numRead) throws IOException {
		// Make a correctly sized copy of the data before handing it
		// to the client
		byte[] rspData = new byte[numRead];
		System.arraycopy(data, 0, rspData, 0, numRead);
		
		// Look up the handler for this channel
		RspHandler handler = (RspHandler) this.rspHandlers.get(socketChannel);
		handler.handleResponse(rspData);
		// And pass the response to it
//		if (handler.handleResponse(rspData)) {
//			// The handler has seen enough, close the connection
//			socketChannel.close();
//			socketChannel.keyFor(this.selector).cancel();
//		}
	}

	private void write(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();

		synchronized (this.pendingData) {
			List queue = (List) this.pendingData.get(socketChannel);

			// Write until there's not more data ...
			while (!queue.isEmpty()) {
				ByteBuffer buf = (ByteBuffer) queue.get(0);
				socketChannel.write(buf);
   			     

				//socketChannel.register(null, 0);
				//socketChannel.configureBlocking(true);
//				OutputStream socketOutputStream = socketChannel.socket().getOutputStream();				
//				socketOutputStream.write(buf.array());
//				socketChannel.configureBlocking(false);
//				socketChannel.register(this.selector, ChangeRequest.CHANGEOPS);

				if (buf.remaining() > 0) {
					// ... or the socket's buffer fills up
					break;
				}
				queue.remove(0);
			}

			if (queue.isEmpty()) {
				// We wrote away all data, so we're no longer interested
				// in writing on this socket. Switch back to waiting for
				// data.
				key.interestOps(SelectionKey.OP_READ);
			}
		}
	}
	SeekableByteArrayOutputStream byteArrayOutputStream = new SeekableByteArrayOutputStream();
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
	int initCount = byteArrayOutputStream.getCount();
	ByteBuffer buf = ByteBuffer.wrap(byteArrayOutputStream.getBuffer(), 0, byteArrayOutputStream.getBuffer().length);

	
	private void serializedWriteManual(SocketChannel socketChannel, Object object) throws IOException {
//		byteArrayOutputStream = new MyByteArrayOutputStream();
//		objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
		
		//ByteBuffer buf2 = ByteBuffer.allocate(byteArrayOutputStream.getBufCount()*2);
		//ByteBuffer buf2 = ByteBuffer.allocate(8192);			;
		//buf.put(byteArrayOutputStream.getBufReference(), 0, byteArrayOutputStream.getBufCount());
		buf.limit(byteArrayOutputStream.getCount());
		//ByteBuffer buf = ByteBuffer.wrap(byteArrayOutputStream.getBufReference(), 0, byteArrayOutputStream.getBufCount());
		System.out.println("Writing: " + object + " with bufferof size:" + byteArrayOutputStream.getCount());
		//while (buf.remaining() > 0) {
		socketChannel.write(buf);
		byteArrayOutputStream.reset();
		buf.clear();
		
		//}
		if (buf.remaining() > 0) {
			// ... or the socket's buffer fills up
			return;
		}
		
	}
	private void serializedWrite(SocketChannel socketChannel, Object object) throws IOException {
		portSerializationSupport.putSerializableInOutputBuffer((Serializable) object);
		socketChannel.write(portSerializationSupport.getOutputBuffer());	
		//}
		if (portSerializationSupport.getOutputBuffer().remaining() > 0) {
			// ... or the socket's buffer fills up
			return;
		}
		
	}

	private void serializedWrite(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();

		synchronized (this.pendingData) {
			List queue = (List) this.pendingData.get(socketChannel);

			// Write until there's not more data ...
			while (queue != null && !queue.isEmpty()) {
				ByteBuffer originalBuf = (ByteBuffer) queue.get(0);
				String bufToString = new String(originalBuf.array());				
				//byteArrayOutputStream.reset();
				serializedWrite(socketChannel, bufToString);
//				objectOutputStream.writeObject(bufToString);
//				objectOutputStream.flush();
//				
//				ByteBuffer buf = ByteBuffer.wrap(byteArrayOutputStream.getBufReference(), 0, byteArrayOutputStream.getBufCount());
//				socketChannel.write(buf);
//				byteArrayOutputStream.reset();
				//objectOutputStream.reset();
   			     

				//socketChannel.register(null, 0);
				//socketChannel.configureBlocking(true);
//				OutputStream socketOutputStream = socketChannel.socket().getOutputStream();				
//				socketOutputStream.write(buf.array());
//				socketChannel.configureBlocking(false);
//				socketChannel.register(this.selector, ChangeRequest.CHANGEOPS);

//				if (buf.remaining() > 0) {
//					// ... or the socket's buffer fills up
//					break;
//				}
				queue.remove(0);
			}

			if (queue == null || queue.isEmpty()) {
				// We wrote away all data, so we're no longer interested
				// in writing on this socket. Switch back to waiting for
				// data.
				key.interestOps(SelectionKey.OP_READ);
			}
		}
	}
	private void syncWrite(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		int ops = key.interestOps();
		key.cancel();
		//socketChannel.register(null, 0);
		SelectableChannel retChannel = socketChannel.configureBlocking(true);
		OutputStream socketOutputStream = socketChannel.socket().getOutputStream();			


		//synchronized (this.pendingData) {
			List queue = (List) this.pendingData.get(socketChannel);

			// Write until there's not more data ...
			while (!queue.isEmpty()) {
				ByteBuffer buf = (ByteBuffer) queue.get(0);
				//socketChannel.write(buf);

				
				//OutputStream socketOutputStream = socketChannel.socket().getOutputStream();				
				socketOutputStream.write(buf.array());
				
				//socketChannel.register(this.selector, ChangeRequest.CHANGEOPS);
// this seems to fil up
//				if (buf.remaining() > 0) {
//					// ... or the socket's buffer fills up
//					break;
//				}
				queue.remove(0);
			}
			retChannel = socketChannel.configureBlocking(false);
			//initSelector();
			Selector theSelector = this.selector;
			//int ops = ChangeRequest.CHANGEOPS;
		    socketChannel.register(theSelector, ops);
			if (queue.isEmpty()) {
				// We wrote away all data, so we're no longer interested
				// in writing on this socket. Switch back to waiting for
				// data.
				key.interestOps(SelectionKey.OP_READ);
			}
		}
	

	private void finishConnection(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
	
		// Finish the connection. If the ion operation failed
		// this will raise an IOException.
		try {
			socketChannel.finishConnect();
		} catch (IOException e) {
			// Cancel the channel's registration with our selector
			System.out.println(e);
			key.cancel();
			return;
		}
	
		// Register an interest in writing on this channel
		key.interestOps(SelectionKey.OP_WRITE);
	}

	private SocketChannel initiateConnection() throws IOException {
		// Create a non-blocking socket channel
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
	
		// Kick off connection establishment
		socketChannel.connect(new InetSocketAddress(this.hostAddress, this.port));
		//socketChannel.configureBlocking(true);
	
		// Queue a channel registration since the caller is not the 
		// selecting thread. As part of the registration we'll register
		// an interest in connection events. These are raised when a channel
		// is ready to complete connection establishment.
		synchronized(this.pendingChanges) {
			this.pendingChanges.add(new ChangeRequest(socketChannel, ChangeRequest.REGISTER, SelectionKey.OP_CONNECT));
		}
		
		return socketChannel;
	}

	private Selector initSelector() throws IOException {
		// Create a new selector
		return SelectorProvider.provider().openSelector();
	}

//	public static void main(String[] args) {
//		try {
//			NioClient client = new NioClient(InetAddress.getByName("www.google.com"), 80);
//			//NioClient client = new NioClient(InetAddress.getByName("localhost"), 9090);
//			Thread t = new Thread(client);
//			t.setDaemon(true);
//			t.start();
//			RspHandler handler = new RspHandler();
//			client.send("GET / HTTP/1.0\r\n\r\n".getBytes(), handler);
//			handler.waitForResponse();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public static void main(String[] args) {
	    try {
		  RspHandler handler = new RspHandler();

	      NioClientExp client = new NioClientExp(InetAddress.getByName("localhost"), 9090, handler);
	      Thread t = new Thread(client);
	      t.setDaemon(true);
	      t.start();
	      //RspHandler handler = new RspHandler();
//	      SocketChannel socketChannel = client.connect(handler);
	      SocketChannel socketChannel = client.syncConnect(handler);
	      for (int i = 0; i < 5; i++)
	    	  client.serializedWrite(socketChannel, "Hello World" + i);
//	      client.serializedWrite(socketChannel, "Goodbye World".getBytes());
//	      client.serializedWrite(client.globalSocketChannel, "Hello World".getBytes());
//	      client.serializedWrite(client.globalSocketChannel, "Goodbye World".getBytes());
	      //client.send(socketChannel, "Goodbye World".getBytes());
	      handler.waitForResponse();
	      //handler.waitForResponse();
//	      socketChannel.close();
//		  socketChannel.keyFor(client.selector).cancel();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}

