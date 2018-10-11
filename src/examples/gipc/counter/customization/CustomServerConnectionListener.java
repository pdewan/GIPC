package examples.gipc.counter.customization;

import java.util.List;

import inputport.ConnectionListener;

public interface CustomServerConnectionListener extends ConnectionListener{

	public abstract List<String> getClients();

	void waitForClients(int aNumClients);

}