package faulttoleranttest;

public class AReplicatedSessionServer2Launcher {
	
	public static void main (String args[]) {
		AReplicatedSessionServerLauncher.launchServer(
				AFaultTolerantSessionPortLauncher.server2Description.getName(), 
				AFaultTolerantSessionPortLauncher.server2Description.getID());
	}
	
	

}
