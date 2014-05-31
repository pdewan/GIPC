package port.sessionserver.example;

import inputport.ConnectionType;
import inputport.InputPort;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ATracingConnectionListener;
import port.sessionserver.SessionServer;

public abstract class ASessionParticipatingConnectListener extends ATracingConnectionListener implements Runnable {
	
	protected SessionServer sessionServerProxy;
	protected String sessionName;

//	UICreator uiCreator;

//	SessionObserver sessionObserver;
//	ServerPortDescription serverPortDescription;

	public ASessionParticipatingConnectListener(InputPort anInputPort, String aSessionName, Class aSessionServerClass)
			 {
		super(anInputPort);
//		sessionObserver = aSessionObserver;
		sessionName = aSessionName;
		try {
			 sessionServerProxy = (SessionServer) DirectedRPCProxyGenerator.generateRPCProxy((DuplexRPCClientInputPort) anInputPort, null,
					 aSessionServerClass, null);
		

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
//	protected Class sessionServerClass () {
//		return ARelayerSupportingSessionsServer.class;
//	}
	@Override
	// creating own thread so we do not need the thread created by launcher
	public void connected(String aRemoteEnd, ConnectionType aConnectionType) {
		(new Thread(this)).start();
		
	}
	@Override
	public void run() {
		participateInSession();
	}
	protected abstract void participateInSession() ;
	
	

}
