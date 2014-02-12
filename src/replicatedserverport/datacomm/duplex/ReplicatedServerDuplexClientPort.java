package replicatedserverport.datacomm.duplex;

import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.DuplexClientInputPort;

public interface ReplicatedServerDuplexClientPort<MessageType> extends DuplexClientInputPort<MessageType>, ReceiveListener<MessageType> {
   
}
