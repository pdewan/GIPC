package examples.nio.manager.server;

import assignments.util.mainArgs.ServerPort;
import inputport.nio.manager.listeners.SocketChannelAcceptListener;
import inputport.nio.manager.listeners.SocketChannelReadListener;
import inputport.nio.manager.listeners.WriteBoundedBufferListener;


public interface MeaningOfLifeNIOServer extends ServerPort, SocketChannelAcceptListener {
	public void initialize(int aServerPort);
}
