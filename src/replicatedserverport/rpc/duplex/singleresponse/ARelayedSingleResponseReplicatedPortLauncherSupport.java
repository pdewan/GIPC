package replicatedserverport.rpc.duplex.singleresponse;

import replicatedserverport.datacomm.duplex.AReplicatedPortLauncherSupport;
import util.trace.Tracer;

public class ARelayedSingleResponseReplicatedPortLauncherSupport extends AReplicatedPortLauncherSupport {	
	
     public  void setFactories() {    	 
		super.setFactories();
		SingleResponseReplicatedClientServerUtlity.setRelayedSingleResponseClientFactories();


	}
     
     public  void setTracing() {
//    	 Tracer.showInfo(true);
//    	 Tracer.setKeywordPrintStatus(ASingleResponseReplicatedPortLauncherSupport.class, true);

     }
}
