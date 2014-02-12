package port.jitter;

import inputport.datacomm.TrapperFactory;
import inputport.datacomm.TrapperSelector;
import inputport.datacomm.group.GroupTrapperFactory;
import inputport.datacomm.group.GroupTrapperSelector;
import inputport.datacomm.group.object.ServerObjectGroupTrapperSelector;
import inputport.datacomm.simplex.object.ClientObjectTrapperSelector;
import inputport.datacomm.simplex.object.ServerObjectTrapperSelector;
import multiserverport.datacomm.group.object.MultiServerObjectGroupTrapperSelector;
import sessionport.datacomm.duplex.object.relayed.ObjectDuplexSPRelayedTrapperSelector;
import sessionport.datacomm.group.object.relayed.latecomer.ObjectGroupSPRelayedTrapperSelector;

public class JitterUtility {
	public static void setSingleServerJitterFactories() {
    	TrapperFactory trapperFactory = new AJitterControllingTrapperFactory();
    	TrapperSelector clientTrapperSelector = ClientObjectTrapperSelector.getTrapperSelector();
    	clientTrapperSelector.addReceiveTrapperFactory(trapperFactory);
    	clientTrapperSelector.addSendTrapperFactory(trapperFactory);
    	TrapperSelector serverTrapperSelector = ServerObjectTrapperSelector.getTrapperSelector();
    	serverTrapperSelector.addReceiveTrapperFactory(trapperFactory);
    	serverTrapperSelector.addSendTrapperFactory(trapperFactory);
     }
	public static void setMultipleServerJitterFactories() {
    	GroupTrapperFactory groupTrapperFactory = new AJitterControllingGroupTrapperFactory();
    	GroupTrapperSelector trapperSelector = MultiServerObjectGroupTrapperSelector.getTrapperSelector();
    	trapperSelector.addReceiveTrapperFactory(groupTrapperFactory);
    	trapperSelector.addGroupSendTrapperFactory(groupTrapperFactory);
     }
	public static void setGroupServerJitterFactories() {
    	GroupTrapperFactory groupTrapperFactory = new AJitterControllingGroupTrapperFactory();
    	GroupTrapperSelector groupTrapperSelector = ServerObjectGroupTrapperSelector.getTrapperSelector();
    	groupTrapperSelector.addReceiveTrapperFactory(groupTrapperFactory);
    	groupTrapperSelector.addGroupSendTrapperFactory(groupTrapperFactory);
	}
	public static void setRelayerJitterFactories() {
    	TrapperFactory trapperFactory = new AJitterControllingTrapperFactory();
    	TrapperSelector duplexTrapperSelector = ObjectDuplexSPRelayedTrapperSelector.getTrapperSelector();
    	duplexTrapperSelector.addReceiveTrapperFactory(trapperFactory);
    	duplexTrapperSelector.addSendTrapperFactory(trapperFactory);
    	GroupTrapperFactory groupTrapperFactory = new AJitterControllingGroupTrapperFactory();

    	GroupTrapperSelector groupTrapperSelector = ObjectGroupSPRelayedTrapperSelector.getTrapperSelector();
    	groupTrapperSelector.addGroupSendTrapperFactory(groupTrapperFactory);
    	groupTrapperSelector.addReceiveTrapperFactory(groupTrapperFactory);


     }

}
