package oldtypedip;

import oldgroupip.GroupServerInputPort;
import port.old.ByteBufferReceiveListener;
import port.old.ConnectionSendReceiptNotifier;

public interface TypedGroupServerInputPort extends GroupServerInputPort, TypedGroupSend, TypedDuplexSend, TypedReceiptNotifier, ConnectionSendReceiptNotifier, ByteBufferReceiveListener{

}
