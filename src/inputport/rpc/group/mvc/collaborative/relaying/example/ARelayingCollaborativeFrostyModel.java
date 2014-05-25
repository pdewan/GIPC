package inputport.rpc.group.mvc.collaborative.relaying.example;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import examples.mvc.local.duplex.ADuplexFrostyModel;
import examples.mvc.local.duplex.Counter;
import examples.mvc.rmi.collaborative.relaying.Echoer;


public class ARelayingCollaborativeFrostyModel extends ADuplexFrostyModel implements PropertyChangeListener {
	String userName;
	public ARelayingCollaborativeFrostyModel(RelayingCollaborativeUpperCaser anUpperCaser, 
											Echoer anEchoer, 
											Counter aCounter,
											String aUserName) {
		super(anUpperCaser, aCounter);
		userName = aUserName;
		anUpperCaser.addListener(anEchoer);			
		anEchoer.addPropertyChangeListener(this);
	}
	protected void processUpperCase(String aString) {
//		MVCTraceableInfo.newInfo("Processing Upper Case", this);
		super.processUpperCase(aString);
		MVCTraceableInfo.newInfo("Client Requesting Relay", this);
		((RelayingCollaborativeUpperCaser) upperCaser).relayToOthers(userName + " said:" + aString);		
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		String oldOutput = output;
		output =  (String) evt.getNewValue();
		MVCTraceableInfo.newInfo("Displaying Remote Output", this);
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this, "output", oldOutput, output));
	}
}
