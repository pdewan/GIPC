package inputport;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import port.ATracingConnectionListener;

public class ASynchronizingConnectionListener extends ATracingConnectionListener implements SynchronizingConnectionListener{
	
	boolean hasReceivedFeedback;
	public ASynchronizingConnectionListener (InputPort aPort) {
		super (aPort);
	}
	protected synchronized  void setReceivedConnectionStatus() {
		hasReceivedFeedback = true;
		System.out.println("Notifying on " + this);

		this.notify();
		
		System.out.println("Finished Notifying:" + Thread.currentThread());
	

	}
	public synchronized void waitForConnectionStatus() {
		System.out.println("wait for connection callede on:" + this);

		if (hasReceivedFeedback)
			return;
		try {
			System.out.println("Waiting:" + Thread.currentThread());
			
			this.wait();
			System.out.println("finished Waiting");
			

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public  void connected(String arg0, ConnectionType arg1) {
		System.out.println("connected");

		super.connected(arg0, arg1);
		setReceivedConnectionStatus();
		
		
		
	}

	@Override
	public  void notConnected(String arg0, String arg1, ConnectionType arg2) {
		System.out.println("not connected");

		super.notConnected(arg0, arg1, arg2);
		setReceivedConnectionStatus();

		
		
	}

	@Override
	public void disconnected(String arg0, boolean arg1, String arg2,
			ConnectionType arg3) {
		System.out.println("discconnected");

		super.disconnected(arg0, arg1, arg2, arg3);
		setReceivedConnectionStatus();	
		
	}

}
