package port.relay.mvc.example;

import examples.mvc.local.duplex.DuplexFrostyModel;
import inputport.datacomm.ReceiveListener;
import port.relay.Relayer;

public interface GenericRelayingCollaborativeFrostyModel extends DuplexFrostyModel, ReceiveListener {
	public void setRelayer (Relayer aRelayer);

}
