package port.sessionserver.asymmetricexample;

import inputport.InputPort;
import inputport.datacomm.simplex.buffer.example.UICreator;

import java.util.Scanner;

import port.ParticipantChoice;


public abstract class AnAbstractInteractiveSessionServerClientLauncher extends AnAbstractSessionServerClientLauncher implements UICreator{

	public AnAbstractInteractiveSessionServerClientLauncher(String aSessionServerHost, String aServerId, 
			String aServerName, String aMyId, String aMyName, ParticipantChoice aChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aChoice);

			
	}
	
	
//	protected void doPostConnectsAsyncOperations() {
//		createUI(mainPort);
//	}
	
	protected abstract void sendMessage(Object message);
	
	public void createUI (InputPort anInputPort) {
		if (participantChoice == ParticipantChoice.SERVER_ONLY)
			return;
//		GroupAllSender<Object> sessionPort = (GroupAllSender) anInputPort;
//		if (greetOnReadingInput) {			
		       Scanner in = new Scanner(System.in);		  
//		       String message  = in.nextLine();
//		       sessionPort.sendAll(aName + " says hi to all");
		       while (true) {
		    	   System.out.println("Please enter  next input");
//					  in = new Scanner(System.in);		  
				     String message  = in.nextLine();
				     sendMessage(message);
//				     myServerPort.sendAll(message);
//				      sessionPort.sendAll(message);
				}
		}
		
//	}

}
