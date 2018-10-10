package port.old;
import extraip.DisconnectRegistrarAndNotifier;
import inputport.ConnectionManager;
public interface MonolithicServerInputPort extends ConnectionManager, PureConnectRegistrarAndNotifier, DisconnectRegistrarAndNotifier, MonolithicReceiptRegistrarAndNotifier {
//	public void makeConnectable ();
}
