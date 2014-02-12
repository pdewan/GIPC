package inputport.datacomm.simplex.buffer.nio;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public interface ObservableNIOManager {	
    public void accept(ServerSocketChannel aChannel, SocketChannelAcceptListener aListener);
    public void accept(ServerSocketChannel aChannel, SocketChannelAcceptListener[] listeners);
	public void connect(SocketChannel aSocketChannel, InetAddress theServerHost, int thePort, SocketChannelConnectListener aListener);
	public void connect(SocketChannel aSocketChannel, InetAddress theServerHost, int thePort, SocketChannelConnectListener[] listeners);
	public void write(SocketChannel aChannel, ByteBuffer aByteBuffer, SocketChannelWriteListener aListener);
	public void write(SocketChannel aChannel, ByteBuffer aByteBuffer, SocketChannelWriteListener[] listeners);
    public void addReadListener(SocketChannel aChannel, SocketChannelReadListener aListener);
    public void addCloseListener(SocketChannel aChannel, SocketChannelCloseListener aListener);
}
