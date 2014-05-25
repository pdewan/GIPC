package sessionport.rpc.group.mvc.flexible.example;

import java.beans.PropertyChangeEvent;

import sessionport.rpc.group.GroupRPCSessionPort;
import util.trace.Tracer;
import examples.mvc.local.duplex.ADuplexFrostyModel;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.duplex.DuplexUpperCaser;


public class ASessionSendingCollaborativeFrostyModel extends ADuplexFrostyModel 
                  implements SessionSendingCollaborativeFrostyModel {
	String userName;
	GroupRPCSessionPort sessionPort;
	public ASessionSendingCollaborativeFrostyModel(DuplexUpperCaser anUpperCaser, 
										    GroupRPCSessionPort aSessionPort, 
											Counter aCounter,
											String aUserName) {
		super(anUpperCaser, aCounter);
		sessionPort = aSessionPort;
		userName = aUserName;
	}
	protected void processUpperCase(String aString) {
		super.processUpperCase(aString);
		sessionPort.sendAllMembers(userName + " said:" + aString);

	}
	public void messageReceived(String aSourceName, Object aMessage) {
		Tracer.info(this, "message Received:" + aMessage);
		String oldOutput = output;
		output = (String) aMessage;
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "output", oldOutput, output));		
	}
}
