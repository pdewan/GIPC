package examples.mvc.rmi.collaborative.relaying;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegistrar;
import examples.mvc.rmi.collaborative.ACollaborativeRMIFrostyModel;
import examples.mvc.rmi.duplex.DistributedRMICounter;

public class ARelayingCollaborativeRMIFrostyModel extends
		ACollaborativeRMIFrostyModel implements PropertyChangeListener {
	DistributedRMIEchoer echoer;
	public ARelayingCollaborativeRMIFrostyModel(
			RelayingCollaborativeRMIUpperCaser anUpperCaser,
			DistributedRMIEchoer anEchoer, DistributedRMICounter aCounter,
			String aUserName) {
		super(anUpperCaser, aCounter, aUserName);
		echoer = anEchoer;
		try {
			anUpperCaser.addListener(aUserName, anEchoer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		((PropertyListenerRegistrar) echoer).addPropertyChangeListener(this);
	}
	protected void processUpperCase(String aString) {
		super.processUpperCase(aString);
		try {
			((RelayingCollaborativeRMIUpperCaser) upperCaser).relayToOthers(
					userName + " said:" + aString, userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void propertyChange(PropertyChangeEvent evt) {
		String oldOutput = output;
		output = (String) evt.getNewValue();
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(this,
				"output", oldOutput, output));
	}
}
