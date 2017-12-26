package inputport.datacomm.simplex.buffer.nio;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface ObservableNIOManager /*extends WriteBoundedBufferListener*/ {	
    public void enableAccepts(ServerSocketChannel aChannel, SocketChannelAcceptListener... aListener);
//    public void accept(ServerSocketChannel aChannel, SocketChannelAcceptListener[] listeners);
//	public void connect(SocketChannel aSocketChannel, InetAddress theServerHost, int thePort, SocketChannelConnectListener aListener);
//	public void connect(SocketChannel aSocketChannel, InetAddress theServerHost, int thePort, SocketChannelConnectListener[] listeners);
	public void connect(SocketChannel aSocketChannel, InetAddress aServerHost, int aPort, SocketChannelConnectListener... listeners);

//	public void write(SocketChannel aChannel, ByteBuffer aByteBuffer, SocketChannelWriteListener aListener);
	public void write(SocketChannel aChannel, ByteBuffer aByteBuffer, SocketChannelWriteListener... listeners);
    public void addReadListener(SocketChannel aChannel, SocketChannelReadListener aListener);
    public void addCloseListener(SocketChannel aChannel, SocketChannelCloseListener aListener);
	void enableReads(SocketChannel aChannel);
//	boolean isAllowReads();
//	void setAllowReads(boolean allowReads);
	void addWriteBoundedBufferListener(SocketChannel aSocketChannel,
			WriteBoundedBufferListener aListener);
}
