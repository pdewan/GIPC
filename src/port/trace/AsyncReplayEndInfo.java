package port.trace;

import java.util.List;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.annotations.DisplayToString;
@DisplayToString(true)
public class AsyncReplayEndInfo extends ReplayListInfo {
	

	public AsyncReplayEndInfo (String aMessage, Object aFinder, 
			List<MessageWithSource> aMessageList) {
		super (aMessage, aFinder, aMessageList);	
	}
	
	// should this take message list as an argument
	public static AsyncReplayEndInfo newCase(Object aSource, List<MessageWithSource> aReplayList) {
    	String aMessage = "Finished asynchyronous replay of messages" ;
    	AsyncReplayEndInfo retVal =
   	    new AsyncReplayEndInfo(aMessage, aSource, aReplayList);
    	retVal.announce();
    	return retVal;
	}

}
