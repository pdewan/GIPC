package port.old;
import extraip.DisconnectRegistrarAndNotifier;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;

public interface ConnectionSendNotifier extends PureConnectRegistrarAndNotifier, DisconnectRegistrarAndNotifier, SendRegistrarAndNotifier {

	
}
