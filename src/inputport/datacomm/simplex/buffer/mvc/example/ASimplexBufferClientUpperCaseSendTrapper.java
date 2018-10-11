package inputport.datacomm.simplex.buffer.mvc.example;

import java.io.NotSerializableException;
import java.nio.ByteBuffer;

import inputport.datacomm.ImplicitSender;
import serialization.Serializer;

public class ASimplexBufferClientUpperCaseSendTrapper  implements ImplicitSender {
	ImplicitSender<ByteBuffer> byteBufferSender;
	Serializer serializer = new APrintUpperCaseCallSerializer();
	public ASimplexBufferClientUpperCaseSendTrapper(ImplicitSender<ByteBuffer> aByteBufferSender) {
		byteBufferSender = aByteBufferSender;
	}

	@Override
	public void send(Object message) {
		try {
		byteBufferSender.send(serializer.outputBufferFromObject(message)); 
		} catch (NotSerializableException e) {
			e.printStackTrace();
		}
	}

}
