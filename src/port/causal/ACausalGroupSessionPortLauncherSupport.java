package port.causal;

import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupServerInputPort;
import inputport.datacomm.group.GroupTrapperFactory;
import inputport.datacomm.group.GroupTrapperSelector;
import port.PortLauncherSupport;
import sessionport.datacomm.group.object.SessionObjectGroupTrapperSelector;
import util.trace.Tracer;

public class ACausalGroupSessionPortLauncherSupport implements PortLauncherSupport{
	
	public void init() {
		setCausalBroadcastFactories();
	}

	public static void doCausalBroadcast(GroupServerInputPort aGroupInputPort ) {
		Tracer.info("Doing causal broadcast for " + aGroupInputPort);
	
		ReceiveTrapper oldReceiveTrapper = aGroupInputPort.getReceiveTrapper();
		GroupSendTrapper oldSendTrapper = aGroupInputPort.getGroupSendTrapper();
		GroupTrapperFactory<Object, Object> aCausalFactory = new ACausalClientObjectMessageFactory();
	
		ReceiveTrapper receiveTrapper = aCausalFactory.createReceiveTrapper(aGroupInputPort, oldReceiveTrapper);
		GroupSendTrapper sendTrapper = aCausalFactory.createGroupSendTrapper(aGroupInputPort, oldSendTrapper);		
		aGroupInputPort.setGroupSendTrapper(sendTrapper);
		aGroupInputPort.setReceiveTrapper(receiveTrapper);
	}
	public static void setCausalBroadcastFactories() {
		GroupTrapperFactory<Object, Object> causalFactory = new ACausalClientObjectMessageFactory();
		GroupTrapperSelector<Object, Object> trapperSelector = SessionObjectGroupTrapperSelector.getTrapperSelector();
		trapperSelector.setReceiveTrapperFactory(causalFactory);
		trapperSelector.setGroupSendTrapperFactory(causalFactory);
		
	}

	@Override
	public void tracePrints() {
		// TODO Auto-generated method stub
		
	}
}
