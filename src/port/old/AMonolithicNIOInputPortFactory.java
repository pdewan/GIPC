package port.old;


import inputport.datacomm.simplex.buffer.nio.ASelectionManagerManager;

public class AMonolithicNIOInputPortFactory extends ASelectionManagerManager implements MonolithicInputPortFactory  {	
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
