package replicatedserverport.rpc.duplex.fixedresponse;

import inputport.datacomm.simplex.object.example.AliceClientLauncher;
import inputport.datacomm.simplex.object.example.BobClientLauncher;
import inputport.datacomm.simplex.object.example.CathyClientLauncher;

import java.util.HashMap;
import java.util.Map;

import replicatedserverport.rpc.group.flexibleresponse.flexible.Server1Launcher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server2Launcher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server3Launcher;

public class ClientServerMapping {
	 static final Map<String,String> clientToServer = new HashMap();
	 public static boolean isServer(String client, String server) {
		 return server.equals(clientToServer.get(client));
	 }
	 public static String getServer(String client) {
		 return clientToServer.get(client);
	 }
	 public static String setServer(String client, String server) {
		 return clientToServer.put(client, server);
	 }
	 
	 static {
		 // these are the settings for local mapping, can for a particular application
		 clientToServer.put(AliceClientLauncher.ALICE, Server1Launcher.SERVER1);
		 clientToServer.put(BobClientLauncher.BOB, Server2Launcher.SERVER2);
		 clientToServer.put(CathyClientLauncher.CATHY, Server3Launcher.SERVER3);

	 }

}
