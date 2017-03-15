package examples.gipc.counter.customport;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectInputPortFactory;

import java.nio.ByteBuffer;

public class ACustomDuplexObjectInputPortFactory extends ADuplexObjectInputPortFactory{
	public DuplexClientInputPort<Object> createDuplexClientInputPort(DuplexClientInputPort<ByteBuffer> bbClientInputPort) {
		return new ACustomDuplexObjectClientInputPort(bbClientInputPort);
	}
	public DuplexServerInputPort<Object> createDuplexServerInputPort(DuplexServerInputPort<ByteBuffer> bbServerInputPort) {
		return new ACustomDuplexObjectServerInputPort(bbServerInputPort);
	}
}
