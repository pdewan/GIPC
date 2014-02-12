package oldtypedip;
import inputport.ConnectionsQueryable;
import inputport.InputPort;
import port.old.MonolithicDuplexClientInputPort;
public interface TypedClientInputPort extends 

MonolithicDuplexClientInputPort, TypedDuplexSend,  TypedReceiptNotifier, InputPort, ConnectionsQueryable {

}
