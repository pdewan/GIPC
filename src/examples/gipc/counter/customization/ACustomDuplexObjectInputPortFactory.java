package examples.gipc.counter.customization;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectInputPortFactory;

public class ACustomDuplexObjectInputPortFactory extends ADuplexObjectInputPortFactory{
	public DuplexClientInputPort<Object> createDuplexClientInputPort(DuplexClientInputPort<ByteBuffer> bbClientInputPort) {
		return new ACustomDuplexObjectClientInputPort(bbClientInputPort);
	}
	public DuplexServerInputPort<Object> createDuplexServerInputPort(DuplexServerInputPort<ByteBuffer> bbServerInputPort) {
		return new ACustomDuplexObjectServerInputPort(bbServerInputPort);
	}
}
