package faulttoleranttest;

public class AReplicatedSessionServer3Launcher {
	
	public static void main (String args[]) {
		AReplicatedSessionServerLauncher.launchServer(
				AFaultTolerantSessionPortLauncher.server3Description.getName(), 
				AFaultTolerantSessionPortLauncher.server3Description.getID());
	}
	
	

}
