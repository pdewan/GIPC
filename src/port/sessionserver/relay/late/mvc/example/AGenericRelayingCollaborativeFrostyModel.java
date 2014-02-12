package port.sessionserver.relay.late.mvc.example;

import java.beans.PropertyChangeEvent;

import port.relay.Relayer;
import port.relay.mvc.example.GenericRelayingCollaborativeFrostyModel;
import sessionport.datacomm.duplex.object.relayed.AMessageWithSource;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.trace.Tracer;
import examples.mvc.local.duplex.ADuplexFrostyModel;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.duplex.DuplexUpperCaser;


public class AGenericRelayingCollaborativeFrostyModel extends ADuplexFrostyModel implements GenericRelayingCollaborativeFrostyModel {
	String userName;
	Relayer relayer;
	public AGenericRelayingCollaborativeFrostyModel(DuplexUpperCaser anUpperCaser, 
											Relayer aRelayer, 
											Counter aCounter,
											String aUserName) {
		super(anUpperCaser, aCounter);
		relayer = aRelayer;
		userName = aUserName;
	}
	protected void processUpperCase(String aString) {
		super.processUpperCase(aString);
		relayer.relayOthers(new AMessageWithSource(userName, userName + " said:" + aString));
	}
	public void messageReceived(String aSourceName, Object aMessage) {
		Tracer.info(this, "message Received:" + aMessage);
//		System.out.println("Message:" + aMessage);
		String oldOutput = output;
		MessageWithSource messageWithSource = (MessageWithSource) aMessage;
		output = (String) messageWithSource.getMessage();
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "output", oldOutput, output));
		
	}
	@Override
	public void setRelayer(Relayer aRelayer) {
		relayer = aRelayer;		
	}
}
