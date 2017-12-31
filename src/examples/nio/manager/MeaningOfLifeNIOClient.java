package examples.nio.manager;

import java.io.IOException;

import inputport.nio.manager.SocketChannelConnectListener;

public interface MeaningOfLifeNIOClient extends 
		SocketChannelConnectListener{
	public void createModel();
	public void createUI();
	public void initiateConnection(String aServerHost, int aServerPort);
}
