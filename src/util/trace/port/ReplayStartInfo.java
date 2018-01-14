package util.trace.port;

import java.util.List;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.annotations.ComponentWidth;
import util.annotations.DisplayToString;
import util.trace.Tracer;
@DisplayToString(true)
@ComponentWidth(1000)
public class ReplayStartInfo extends ReplayListInfo {
	

	public ReplayStartInfo (Object aFinder, 
			List<MessageWithSource> aMessageList) {
		super ("Started replay of messages", aFinder, aMessageList);	
	}
	
	// should this take message list as an argument
	public static ReplayStartInfo newCase(Object aSource, List<MessageWithSource> aReplayList) {
    	String aMessage = "Started replay of messages" ;
    	ReplayStartInfo retVal = new ReplayStartInfo(aSource, aReplayList);
    	retVal.announce();
    	return retVal;
	}
	
	static {
		Tracer.setKeywordDisplayStatus(ReplayStartInfo.class, true);
	}

}
