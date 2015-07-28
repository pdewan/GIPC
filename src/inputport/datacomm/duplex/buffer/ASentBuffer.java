package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

public class ASentBuffer implements SentBuffer {
	String destination;
	ByteBuffer buffer;
	
	public ASentBuffer(String destination, ByteBuffer buffer) {
		super();
		this.destination = destination;
		this.buffer = buffer;
	}
	protected String getDestination() {
		return destination;
	}
	protected void setDestination(String destination) {
		this.destination = destination;
	}
	protected ByteBuffer getBuffer() {
		return buffer;
	}
	protected void setBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}

}
