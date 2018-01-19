package port.old;


import inputport.nio.manager.factories.SelectionManagerFactory;

public class AMonolithicNIOInputPortFactory extends SelectionManagerFactory implements MonolithicInputPortFactory  {	
	@Override
	public MonolithicServerInputPort createServerInputPort(String theServerId,
			String theServerName) {
		return new AMonolithicNIOServerInputPort(selectionManager, theServerId, theServerName);
	}
	@Override
	public MonolithicClientInputPort createClientInputPort(String theServerHost,
			String theServerId, String theClientName) {
		return new AMonolithicNIOClientInputPort(selectionManager, theServerHost, theServerId, theClientName);
	}
}
