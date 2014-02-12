package sessionport.datacomm.duplex.object.relayed;

import inputport.InputPort;
import inputport.datacomm.AReceiveMessageForwarderFactory;
import inputport.datacomm.ASendMessageForwarderFactory;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapperFactory;
import inputport.datacomm.SendTrapperFactory;
import inputport.datacomm.simplex.object.ObjectSendTrapperFactory;

public class RelayingObjectDuplexTrapperSelector {
//	static ObjectReceiveTrapperFactory objectReceiveTrapperFactory = new AReceiveObjectForwarderFactory();
	static ReceiveTrapperFactory objectReceiveTrapperFactory = new AReceiveMessageForwarderFactory();

//	static ObjectSendTrapperFactory objectSendTrapperFactory = new ASendObjectForwarderFactory();
	static SendTrapperFactory objectSendTrapperFactory = new ASendMessageForwarderFactory();

	public static ReceiveTrapperFactory getObjectReceiveTrapperFactory() {
		return objectReceiveTrapperFactory;
	}
	public static void setObjectReceiveTrapperFactory(
			ReceiveTrapperFactory objectReceiveTrapperFactory) {
		RelayingObjectDuplexTrapperSelector.objectReceiveTrapperFactory = objectReceiveTrapperFactory;
	}
	public static SendTrapperFactory getObjectSendTrapperFactory() {
		return objectSendTrapperFactory;
	}
	public static void setObjectSendTrapperFactory(
			ObjectSendTrapperFactory SendTrapperFactory) {
		RelayingObjectDuplexTrapperSelector.objectSendTrapperFactory = objectSendTrapperFactory;
	}
	public static NamingSender<Object> createObjectSendTrapper(InputPort anInputPort, NamingSender<Object> destination) {
//		return objectSendTrapperFactory.createObjectSendTrapper(destination);
		return objectSendTrapperFactory.createSendTrapper(anInputPort, destination);

	}
	public static ReceiveNotifier<Object> createObjectReceiveTrapper(InputPort anInputPort, ReceiveNotifier<Object> destination) {
		return objectReceiveTrapperFactory.createReceiveTrapper(anInputPort, destination);
	}
}
