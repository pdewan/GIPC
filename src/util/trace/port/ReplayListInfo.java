package util.trace.port;

import java.util.List;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.trace.ObjectInfo;

public class ReplayListInfo extends ObjectInfo {
	
	List<MessageWithSource> replayMessageList;
	public ReplayListInfo (String aMessage, Object aFinder, List<MessageWithSource> aReplayList) {
		super (aMessage, aReplayList,  aFinder);
		replayMessageList = aReplayList;
	}
	
//	public Object getSource() {
//		return  getFinder();
//	}
	
	public List<MessageWithSource> getReplayMessageList() {
		return replayMessageList;
	}
	
//	public static void newCase(Object aSource, List<MessageWithSource> aReplayList) {
//    	String aMessage = "Ended replay of messages" ;
//   	    new ReplayListInfo(aMessage, aSource, aReplayList);
//	}

}
