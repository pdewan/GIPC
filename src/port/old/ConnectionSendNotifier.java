package port.old;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;
import extraip.DisconnectRegistrarAndNotifier;

public interface ConnectionSendNotifier extends PureConnectRegistrarAndNotifier, DisconnectRegistrarAndNotifier, SendRegistrarAndNotifier {

	
}
