package port.relay.mvc.example;

import port.AnAbstractPortLauncher;
import port.relay.RelayerLauncher;




public class ARelayerLauncher extends AnAbstractPortLauncher implements RelayerLauncher{
	public static void main (String args[]) {	
		(new port.relay.ARelayerLauncher(RELAYER_ID, RELAYER_NAME)).launch();
	}	
	
}
