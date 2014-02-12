package niotutexp;

import java.nio.channels.SocketChannel;

public class ServerDataEvent {
	public NioServerExp server;
	public SocketChannel socket;
	public byte[] data;
	
	public ServerDataEvent(NioServerExp server, SocketChannel socket, byte[] data) {
		this.server = server;
		this.socket = socket;
		this.data = data;
	}
}
