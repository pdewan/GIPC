package oldrpcip;

import java.io.Serializable;
import java.lang.reflect.Method;

import oldtypedip.PrintingReplyingTypedReceiver;
import oldtypedip.TypedReceiveListener;



public class RPCReceiver extends PrintingReplyingTypedReceiver {
	protected GroupRPCServerInputPort port;
	Method method;
	public RPCReceiver(GroupRPCServerInputPort thePort) {
		super(thePort);
		port = thePort;
		try {
			Class[] parameterTypes = {String.class, Serializable.class};
			method = TypedReceiveListener.class.getMethod("messageReceived", parameterTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
	}
	@Override
	public void messageReceived(String senderName, Serializable message) {
		super.messageReceived(senderName, message);	
		Serializable[] args = {senderName, "hello from " + senderName};
		port.callOthers(TypedReceiveListener.class, method, args);
	}

}
