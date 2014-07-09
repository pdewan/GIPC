package port.ot;
import util.session.ServerSentMessageFilterSelector;
import util.session.ASessionManager;
import util.session.ServerMessageFilterCreator;
public class OTSessionManagerServerStarter {
	static ASessionManager server;
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		ServerMessageFilterCreator serverMessageQueueCreator = new AnOTServerMessageQueuerCreator();
		ServerSentMessageFilterSelector.setMessageFilterFactory(serverMessageQueueCreator);
		server = new ASessionManager();	
		server.register();
	}
}
