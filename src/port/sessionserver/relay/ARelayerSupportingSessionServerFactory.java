package port.sessionserver.relay;

import java.util.List;

import port.sessionserver.ASessionServerFactory;
import port.sessionserver.LocalSessionsServer;
import port.sessionserver.SessionServerFactory;


public class ARelayerSupportingSessionServerFactory extends ASessionServerFactory
             implements SessionServerFactory{
	
	protected LocalSessionsServer createLocalSessionServerObject() {
		return new ARelayerSupportingSessionServer();
	}
	
	protected List<Class> getSessionServerInterfaces() {
		List<Class> retVal = super.getSessionServerInterfaces();
		retVal.add(ARelayerSupportingSessionServer.class);
		return retVal;
	}

	

}
