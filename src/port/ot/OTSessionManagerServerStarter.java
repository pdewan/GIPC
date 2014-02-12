package port.ot;
import util.session.AServerSentMessageQueuerSelector;
import util.session.ASessionManager;
import util.session.ServerMessageFilterCreator;
import util.trace.Tracer;
public class OTSessionManagerServerStarter {
	static ASessionManager server;
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		ServerMessageFilterCreator serverMessageQueueCreator = new AnOTServerMessageQueuerCreator();
		AServerSentMessageQueuerSelector.setMessageQueuerFactory(serverMessageQueueCreator);
		server = new ASessionManager();	
		server.register();
	}
}
