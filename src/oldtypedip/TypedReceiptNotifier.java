package oldtypedip;

import java.io.Serializable;
public interface TypedReceiptNotifier {
	void addTypedReceiveListener(TypedReceiveListener portReceiveListener);
	void removeTypedReceiveListener(TypedReceiveListener portReceiveListener);
	void notifyTypedPortReceive(String remoteEnd, Serializable message);
}
