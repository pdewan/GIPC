package port.trace;

import java.util.List;

import inputport.datacomm.ReceiveNotifier;
import inputport.rpc.RemoteCall;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.Traceable;
import util.trace.Tracer;
@DisplayToString(true)
@ComponentWidth(1000)
public class SyncReplayEndInfo extends ReplayListInfo {
	

	public SyncReplayEndInfo (Object aFinder, 
			List<MessageWithSource> aMessageList) {
		super ("Finished synchronous replay of messages" , aFinder, aMessageList);	
	}
	
	// should this take message list as an argument
	public static SyncReplayEndInfo newCase(Object aSource, List<MessageWithSource> aReplayList) {
//    	String aMessage = "Finished synchronous replay of messages" ;
    	SyncReplayEndInfo retVal = new SyncReplayEndInfo(aSource, aReplayList);
    	retVal.announce();
    	return retVal;
	}
	static {
//		Tracer.setKeywordDisplayStatus(SyncReplayEndInfo.class, true);
	}

}
