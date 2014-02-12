package inputport.datacomm.group.object.example;

import inputport.datacomm.ReceiveListener;
import inputport.datacomm.group.GroupInputPort;


public class AMulticastingObjectReceiveListener implements ReceiveListener<Object> {
	GroupInputPort<Object> inputPort;
	public AMulticastingObjectReceiveListener (GroupInputPort<Object> anInputPort) {
		inputPort = anInputPort;
	}
	@Override
	public void messageReceived(String aRemoteEnd, Object aMessage) {
		System.out.println(aRemoteEnd + "-->" + inputPort.getLocalName() + ":" + aMessage);
		inputPort.reply("You say:" + aMessage);
		inputPort.sendOthers(aRemoteEnd + " says:" + aMessage);
	}

}
