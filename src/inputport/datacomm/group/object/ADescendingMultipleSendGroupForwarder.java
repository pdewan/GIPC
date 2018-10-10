package inputport.datacomm.group.object;

import java.util.Collection;
import java.util.List;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupNamingSender;
import util.trace.Tracer;




// cannot inherit AnAbstractGroupSendTrapper
public class ADescendingMultipleSendGroupForwarder implements GroupNamingSender<Object>{
//	GroupNamingSender<ByteBuffer> destination;
	NamingSender<Object> destination;
//	protected BufferSerializationSupport bufferSerializationSupport;
	InputPort inputPort;
	public ADescendingMultipleSendGroupForwarder(InputPort anInputPort, NamingSender<Object>  aDestination) {
		destination = aDestination;
	}
	@Override
	public void send(Collection<String> clientNames, Object message) {
		Tracer.info(this, this + " multiply sending  message " + message + " to " + clientNames);
		List<String> aSortedList = AnAscendingMultipleSendGroupForwarder.sort(clientNames);
		for (int i = aSortedList.size() -1; i>= 0; i--) {
			destination.send(aSortedList.get(i), message);
		}
	}
	@Override
	public void send(String clientName, Object message) {
		Tracer.info(this, this + " a single send  message " + message + " to " + clientName);
		destination.send(clientName, message);
		
	}

}
