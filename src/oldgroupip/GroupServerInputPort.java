package oldgroupip;

import java.nio.ByteBuffer;

import port.old.MonolithicDuplexServerInputPort;




public interface GroupServerInputPort extends MonolithicDuplexServerInputPort, GroupSend<ByteBuffer>{
//	String getLastSender();

}
