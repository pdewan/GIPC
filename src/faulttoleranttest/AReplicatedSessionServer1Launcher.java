package faulttoleranttest;

public class AReplicatedSessionServer1Launcher {
	
	public static void main (String args[]) {
		AReplicatedSessionServerLauncher.launchServer(
				AFaultTolerantSessionPortLauncher.server1Description.getName(), 
				AFaultTolerantSessionPortLauncher.server1Description.getID());
	}
	
	

}
