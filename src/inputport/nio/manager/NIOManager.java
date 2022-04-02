package inputport.nio.manager;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import inputport.nio.manager.listeners.SocketChannelAcceptListener;
import inputport.nio.manager.listeners.SocketChannelCloseListener;
import inputport.nio.manager.listeners.SocketChannelConnectListener;
import inputport.nio.manager.listeners.SocketChannelReadListener;
import inputport.nio.manager.listeners.SocketChannelWriteListener;
import inputport.nio.manager.listeners.WriteBoundedBufferListener;

public interface NIOManager {
	/**
	 * Requests that  an accept operation be invoked each time it can be executed (an incoming
	 * connect request is received) on the ServerSocketChannel and registers 
	 * one or more listeners to be invoked 	each time the operation completes execution.
	 * Specifies the initialInterestOps of the 
	 * @param aChannel
	 * @param anInitialInterestOps
	 * @param aListeners
	 */
	void enableListenableAccepts(ServerSocketChannel aChannel, Integer anInitialInterestOps,
			SocketChannelAcceptListener[] aListeners);
	/**
	 * Requests that  an accept operation be invoked each time it can be executed (an incoming
	 * connect request is received) on the ServerSocketChannel and registers 
	 * one or more listeners to be invoked 	each time the operation completes execution.
	 * 
	 * @param aChannel
	 * @param aListener
	 */
    public void enableListenableAccepts(ServerSocketChannel aChannel, SocketChannelAcceptListener... aListener);
    /**
     * Requests an asynchronous invocation of the connect NIO operation and 
     * registers one or more listeners to be invoked when the operation completes execution
     * later.
     * @param aSocketChannel
     * @param aServerHost
     * @param aPort
     * @param listeners
     */
	public void connect(SocketChannel aSocketChannel, InetAddress aServerHost, int aPort, SocketChannelConnectListener... listeners);
	/**
	 * Requests that a byte buffer be written on the socketchannel and registers
	 * a list of listener to be invoked when the write completes (so that it can
	 * use the ByteBuffer for something else, for instance)
	 * @param aSocketChannel
	 * @param aByteBuffer
	 * @param listeners
	 */
	public void write(SocketChannel aSocketChannel, ByteBuffer aByteBuffer, 
			SocketChannelWriteListener... listeners);
	/**
	 * Registers a listener to be invoked each time a read completes. 
	 * {@link #enableReads(SocketChannel) enableReads}
	 * must be used to enable reads (usually at connect or accept time or after
	 * all writes in {@link WriteBoundedBufferListener} registered through
	 * {@link #addWriteBoundedBufferListener(SocketChannel, WriteBoundedBufferListener) addWriteBoundedBufferListener}
	 */
    public void addReadListener(SocketChannel aSocketChannel, SocketChannelReadListener aListener);
    /**
	 * Registers a listener to be invoked when the channel closes. 
	 */
    public void addCloseListener(SocketChannel aSocketChannel, SocketChannelCloseListener aListener);
    /**
     * Sets the interestop of the channel to read.
     */
	void enableReads(SocketChannel aChannel);
	/**
	 * Registers a listener to be invoked when all pending writes complete. While
	 * there are pending writes, it is advisable to allow only writes to the socket
	 * channel (and ignore reads). The listener can enable reads when it is executed,
	 * if the channel is both readable and readable (or enable some other operation).
	 * @param aSocketChannel
	 * @param aListener
	 */
	void addWriteBoundedBufferListener(SocketChannel aSocketChannel,
			WriteBoundedBufferListener aListener);
	
	void connect(SocketChannel aChannel, InetAddress aServerHost, int aPort, Integer anInitialInterestOps,
			SocketChannelConnectListener[] aListeners);
}
