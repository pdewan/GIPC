package inputport.datacomm.group.object;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupNamingSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import util.trace.Tracer;




// cannot inherit AnAbstractGroupSendTrapper
public class AnAscendingMultipleSendGroupForwarder implements GroupNamingSender<Object>{
//	GroupNamingSender<ByteBuffer> destination;
	NamingSender<Object> destination;
//	protected BufferSerializationSupport bufferSerializationSupport;
	InputPort inputPort;
	public AnAscendingMultipleSendGroupForwarder(InputPort anInputPort, NamingSender<Object>  aDestination) {
		destination = aDestination;
	}
	@Override
	public void send(Set<String> clientNames, Object message) {
		Tracer.info(this, this + " multiply sending  message " + message + " to " + clientNames);
		List<String> aSortedList = new ArrayList(clientNames);
		Collections.sort(aSortedList);
		for (String clientName:aSortedList) {
			destination.send(clientName, message);
		}
	}
	@Override
	public void send(String clientName, Object message) {
		Tracer.info(this, this + " a single send  message " + message + " to " + clientName);
		destination.send(clientName, message);
		
	}

}
