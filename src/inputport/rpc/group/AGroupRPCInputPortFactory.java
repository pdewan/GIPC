package inputport.rpc.group;

import inputport.datacomm.group.GroupServerInputPort;
import inputport.datacomm.group.object.GroupObjectInputPortSelector;
import inputport.rpc.duplex.ADuplexRPCInputPortFactory;

public class AGroupRPCInputPortFactory  extends ADuplexRPCInputPortFactory implements GroupRPCInputPortFactory {
    public AGroupRPCInputPortFactory() {

//    	setGroupTrapperFactories();
    }
//    public static void setGroupTrapperFactories() {
//    	GroupTrapperFactory<Object, Object> factory = new AGroupSerializableCallTrapperFactory();
//		GroupSerializableCallTrapperSelector.getTrapperSelector().
//			setGroupSendTrapperFactory(factory);
//    }


	@Override
	public GroupRPCServerInputPort createGroupRPCServerInputPort(String theServerId,
			String theServerName) {
		GroupServerInputPort<Object> typedServerInputPort = GroupObjectInputPortSelector.createGroupServerInputPort(theServerId, theServerName);
		GroupRPCServerInputPort rpcServerInputPort = new AGroupRPCServerInputPort(typedServerInputPort);
		return rpcServerInputPort;
	}

}
