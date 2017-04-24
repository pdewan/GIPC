package inputport.datacomm.group;

import inputport.datacomm.group.object.AnAscendingMultipleSendGroupForwarder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import util.trace.Tracer;


public class AnAscendingGroupSendMessageForwarder<InMessageType> extends AGroupSendMessageForwarder<InMessageType>{
	public AnAscendingGroupSendMessageForwarder(GroupNamingSender<InMessageType>  aDestination) {
		 super (aDestination);
	}
	public static List<String> sort (Collection<String> aStrings) {
		List<String> aSortedList = new ArrayList(aStrings);
		Collections.sort(aSortedList);
		return aSortedList;
		
	}
	@Override
	public void send(Collection<String> clientNames, InMessageType message) {
		Tracer.info(this, this + "group forwarding sent message:" + message +  " to: " + clientNames);
		destination.send(sort(clientNames),  message);
	}
	
//	@Override
//	public void send(String aRemoteEnd, InMessageType aMessage) {
//		destination.send(aRemoteEnd, aMessage);
//	}
}
