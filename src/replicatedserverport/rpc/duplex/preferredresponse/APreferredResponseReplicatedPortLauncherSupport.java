package replicatedserverport.rpc.duplex.preferredresponse;

import replicatedserverport.datacomm.duplex.AReplicatedPortLauncherSupport;

public class APreferredResponseReplicatedPortLauncherSupport extends AReplicatedPortLauncherSupport {	
	String name;
	public APreferredResponseReplicatedPortLauncherSupport(String aName) {
		name = aName;
		
	}
     public  void setFactories() {
		super.setFactories();
		PreferredResponseReplicatedClientServerUtlity.setPreferredResponseClientFactories(name);


	}
}
