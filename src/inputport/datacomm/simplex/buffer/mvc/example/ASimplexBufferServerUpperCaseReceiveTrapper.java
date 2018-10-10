package inputport.datacomm.simplex.buffer.mvc.example;

import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;

import examples.mvc.local.simplex.ASimplexUpperCaser;
import examples.mvc.local.simplex.SimplexUpperCaser;
import inputport.datacomm.ReceiveListener;
import serialization.Serializer;

public class ASimplexBufferServerUpperCaseReceiveTrapper  implements ReceiveListener<ByteBuffer>{
//	protected InputPort inputPort;
	SimplexUpperCaser simplexUpperCaser = new ASimplexUpperCaser();
	Serializer serializer = new APrintUpperCaseCallSerializer();
	ReceiveListener objectReceiveListener;
	public ASimplexBufferServerUpperCaseReceiveTrapper(ReceiveListener<Object> anObjectReceiveListener) {
		objectReceiveListener = anObjectReceiveListener;
	}
	@Override
	public void messageReceived(String aRemoteEnd, ByteBuffer aMessage) {
		try {
			objectReceiveListener.messageReceived(aRemoteEnd, serializer.objectFromInputBuffer(aMessage));
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		}
	}
	
}
