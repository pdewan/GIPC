package inputport.rpc;

public class RPCOnUnregisteredObjectException extends RuntimeException{
	public RPCOnUnregisteredObjectException(String message) {
		super(message);
	}

}
