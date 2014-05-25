package port.relay.mvc.example;

import inputport.datacomm.ReceiveListener;
import port.relay.Relayer;
import examples.mvc.local.duplex.DuplexFrostyModel;

public interface GenericRelayingCollaborativeFrostyModel extends DuplexFrostyModel, ReceiveListener {
	public void setRelayer (Relayer aRelayer);

}
