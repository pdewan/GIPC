package inputport.rpc.duplex;

public class RemoteInvocationException extends GIPCRemoteException {

	public RemoteInvocationException(String aMessage) {
		super(aMessage);
		// TODO Auto-generated constructor stub
	}
	public RemoteInvocationException(Throwable aCause) {
		super();
		initCause(aCause);
		
	}

}
