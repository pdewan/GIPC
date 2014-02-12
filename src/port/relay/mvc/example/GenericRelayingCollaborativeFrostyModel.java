package port.relay.mvc.example;

import inputport.datacomm.ReceiveListener;
import examples.mvc.local.duplex.DuplexFrostyModel;
import port.relay.Relayer;

public interface GenericRelayingCollaborativeFrostyModel extends DuplexFrostyModel, ReceiveListener {
	public void setRelayer (Relayer aRelayer);

}
