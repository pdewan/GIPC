package sessionport.rpc.group.mvc.flexible.example;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;
import inputport.rpc.simplex.SimplexRPCClientInputPort;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortKind;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.group.GroupRPCSessionPort;
import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.local.duplex.DuplexUpperCaser;
import examples.mvc.local.simplex.SimplexUpperCaser;

public class AFlexibleSessionPortMVCClientLauncher extends ADuplexRPCClientMVCLauncher{
	public AFlexibleSessionPortMVCClientLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName,
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, ParticipantChoice aChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
	}
	public AFlexibleSessionPortMVCClientLauncher(SessionParticipantDescription[] aServerList, String aMyId, String aMyName,

			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		super(aServerList, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
	}	
	SessionSendingCollaborativeFrostyModel frostyModel;
	public PortKind getPortKind() {
		return PortKind.SESSION_PORT;
	}
	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.GROUP;
	}	
    protected void setStateBeforeAddingListeners() {
    	createMVC(mainPort);		
	}
    @Override
    protected Integer getNumberOfServerParticipantConnects() {
		return 1;
	}
    protected void createUI(InputPort anInputPort) {
    	processConsoleInput();
    }
    
    // why not make inherited method also take MVC SERVER name as argument
	protected  void createProxies() {
    	upperCaseProxy = (SimplexUpperCaser) DirectedRPCProxyGenerator.generateRPCProxy((SimplexRPCClientInputPort) mainPort, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME, registeredUpperCaserClass());
	}
	protected  ReceiveListener getReceiveListener (InputPort anInputPort) {
		return (ReceiveListener) getFrostyModel();
	}
	protected DuplexFrostyModel getFrostyModel() {
		if (frostyModel == null)
			frostyModel = new ASessionSendingCollaborativeFrostyModel((DuplexUpperCaser)upperCaseProxy, (GroupRPCSessionPort) mainPort, counter, clientName);		
		return frostyModel;
	}


}
