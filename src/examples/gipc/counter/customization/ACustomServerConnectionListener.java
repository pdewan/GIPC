package examples.gipc.counter.customization;

import java.util.ArrayList;
import java.util.List;

import inputport.ConnectionListener;
import inputport.ConnectionType;

public class ACustomServerConnectionListener implements ConnectionListener, CustomServerConnectionListener{
	protected List<String> clients = new ArrayList<>();
	
	/* (non-Javadoc)
	 * @see examples.gipc.counter.customization.CustomServerConnectionListener#getClients()
	 */
	@Override
	public synchronized List<String> getClients() {
		return clients;
	}
	@Override
	public synchronized void waitForClients(int aNumClients) {
		while (clients.size() < aNumClients) {
			try {
				System.out.println("Waiting for client connection");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public synchronized void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		clients.add(aRemoteEndName);
		notify();
	}

	@Override
	public void notConnected(String aRemoteEndName, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

}
