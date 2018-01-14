package examples.nio.manager.server;

import inputport.nio.manager.SocketChannelAcceptListener;
import inputport.nio.manager.SocketChannelReadListener;
import inputport.nio.manager.WriteBoundedBufferListener;


public interface MeaningOfLifeNIOServer extends ServerPort, SocketChannelAcceptListener , WriteBoundedBufferListener {
	public void initialize(int aServerPort);
}
