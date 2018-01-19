package port.old;

import inputport.nio.manager.factories.SelectionManagerFactory;

public class AMonolithicNIODuplexInputPortFactory extends SelectionManagerFactory implements MonolithicDuplexInputPortFactory  {	
	@Override
	public MonolithicDuplexServerInputPort createDuplexServerInputPort(String theServerId,
			String theServerName) {
		return new AMonolithicNIODuplexServerInputPort(selectionManager, theServerId, theServerName);
	}
	@Override
	public MonolithicDuplexClientInputPort createDuplexClientInputPort(String theServerHost,
			String theServerId, String theClientName) {
		return new AMonolithicNIODuplexClientInputPort(selectionManager, theServerHost, theServerId, theClientName);
	}
}
