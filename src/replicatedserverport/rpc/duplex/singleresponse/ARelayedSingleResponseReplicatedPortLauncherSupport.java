package replicatedserverport.rpc.duplex.singleresponse;

import replicatedserverport.datacomm.duplex.AReplicatedPortLauncherSupport;

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
