package replicatedserverport.rpc.duplex.singleresponse;

public class AControlMessage implements ControlMessage {	
	String serverChoice;
	int serverMessageCount;
//	public AControlMessage() {
//	}
	public AControlMessage(String aServerChoice, int aServerMessageCount) {
		serverChoice = aServerChoice;
		serverMessageCount = aServerMessageCount;
	}
	public int getServerMessageCount() {
		return serverMessageCount;
	}
	public void setServerMessageCount(int serverMessageCount) {
		this.serverMessageCount = serverMessageCount;
	}	
	public String getServerChoice() {
		return serverChoice;
	}
	public void setServerChoice(String serverChosen) {
		this.serverChoice = serverChosen;
	}
	
	@Override
	public String toString() {
		return "Server " + serverChoice +  " count " + serverMessageCount;
	}
	

}
