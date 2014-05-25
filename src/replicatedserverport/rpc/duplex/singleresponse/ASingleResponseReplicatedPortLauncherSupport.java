package replicatedserverport.rpc.duplex.singleresponse;

import replicatedserverport.datacomm.duplex.AReplicatedPortLauncherSupport;

public class ASingleResponseReplicatedPortLauncherSupport extends AReplicatedPortLauncherSupport {	
	
     public  void setFactories() {
		super.setFactories();
//		SingleResponseReplicatedClientServerUtlity.setRelayedSingleResponseClientFactories();
		SingleResponseReplicatedClientServerUtlity.setSingleResponseClientFactories();



	}
     public  void setTracing() {
//    	 Tracer.setKeywordPrintStatus(this, true);
//    	 Tracer.showInfo(true);
     }
}
