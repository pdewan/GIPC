package examples.nio.manager.server;

import assignments.util.mainArgs.ServerPort;
import inputport.nio.manager.listeners.SocketChannelAcceptListener;


public interface MeaningOfLifeNIOServer extends ServerPort, SocketChannelAcceptListener {
	public void initialize(int aServerPort);
}
