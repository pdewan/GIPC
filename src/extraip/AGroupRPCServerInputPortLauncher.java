package extraip;


import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.old.Adder;
import port.old.AnAdder;
import port.old.PrintingReplyingObjectReceiver;
import util.trace.Tracer;




public class AGroupRPCServerInputPortLauncher  {

	
	public static void main (String[] args) {
		Tracer.showInfo(true);
		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort("9090", "test server");

		serverInputPort.connect();
		PrintingReplyingObjectReceiver messageReceiver = new PrintingReplyingObjectReceiver(serverInputPort);
		serverInputPort.addConnectionListener(messageReceiver);

		Adder adder = new AnAdder();
		serverInputPort.register(Adder.class, adder);
		AGroupAdder groupAdder = new AGroupAdder(serverInputPort);
		serverInputPort.register(GroupAdder.class, groupAdder);
		serverInputPort.addSendListener(messageReceiver);
	}
	
	
}
