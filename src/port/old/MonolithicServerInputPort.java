package port.old;
import inputport.ConnectionManager;
import extraip.DisconnectRegistrarAndNotifier;
public interface MonolithicServerInputPort extends ConnectionManager, PureConnectRegistrarAndNotifier, DisconnectRegistrarAndNotifier, MonolithicReceiptRegistrarAndNotifier {
//	public void makeConnectable ();
}
