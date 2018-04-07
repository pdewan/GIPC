package examples.gipc.counter.customization;

import inputport.ConnectionListener;

import java.util.List;

public interface CustomServerConnectionListener extends ConnectionListener{

	public abstract List<String> getClients();

	void waitForClients(int aNumClients);

}