package port.old;

import inputport.datacomm.simplex.SimplexServerInputPort;
import oldgroupip.GroupSend;


public interface GroupDuplexServerInputPort extends SimplexServerInputPort, GroupSend, PeerAwareness{

}
