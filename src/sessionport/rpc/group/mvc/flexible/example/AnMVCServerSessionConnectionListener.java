package sessionport.rpc.group.mvc.flexible.example;

import inputport.ConnectionListenerWithPort;
import inputport.InputPort;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCServerMVCLauncher;
import port.ATracingConnectionListener;
import port.relay.mvc.example.GenericRelayingCollaborativeFrostyModel;
import examples.mvc.local.duplex.DuplexUpperCaser;

public class AnMVCServerSessionConnectionListener extends ATracingConnectionListener implements ConnectionListenerWithPort{
	protected GenericRelayingCollaborativeFrostyModel model;	
	public AnMVCServerSessionConnectionListener (GenericRelayingCollaborativeFrostyModel aModel) {
		model = aModel;
	}	
	@Override
	public void initInputPort(InputPort anInputPort) {		
		inputPort = (DuplexRPCClientInputPort) anInputPort;
		// create and set proxies
		DuplexUpperCaser upperCaseProxy = (DuplexUpperCaser) DirectedRPCProxyGenerator.
				generateRPCProxy((DuplexRPCClientInputPort) inputPort,ADuplexRPCServerMVCLauncher.REGISTERED_DUPLEX_UPPER_CASER_CLASS);
		model.setUpperCaser(upperCaseProxy);
		// register remote object
		((DuplexRPCClientInputPort)  inputPort).register(model.getCounter());		
	}

}
