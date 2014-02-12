package inputport.datacomm.simplex.buffer.mvc.example;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.buffer.ASimplexBufferInputPortLauncherSupport;
import inputport.datacomm.simplex.buffer.SimplexBufferInputPortSelector;
import inputport.datacomm.simplex.object.mvc.example.ASimplexObjectServerMVCLauncher;
import inputport.datacomm.simplex.object.mvc.example.ASimplexObjectServerUpperCaseReceiveTrapper;

import java.nio.ByteBuffer;

import port.PortLauncherSupport;

import examples.mvc.local.simplex.ASimplexUpperCaser;
import examples.mvc.local.simplex.SimplexUpperCaser;



public class ASimplexBufferServerMVC_Launcher extends ASimplexObjectServerMVCLauncher   {
	public static final Class REGISTERED_UPPER_CASER_CLASS =  ASimplexUpperCaser.class;
	
	protected SimplexUpperCaser upperCaser;
	
	public ASimplexBufferServerMVC_Launcher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public ASimplexBufferServerMVC_Launcher() {
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASimplexBufferInputPortLauncherSupport();
	}
//	protected Class registereUpperCaserClass() {
//		return REGISTERED_UPPER_CASER_CLASS;
//	}
	protected InputPort getPort() {
		return SimplexBufferInputPortSelector.createServerSimplexInputPort(
				serverId, 
				serverName);
	}
	@Override
	protected ReceiveListener getReceiveListener (InputPort aServerInputPort) {
		return getBufferReceiveListener(getObjectReceiveListener(aServerInputPort));
	}
	protected ReceiveListener<ByteBuffer> getBufferReceiveListener (ReceiveListener anObjectReceiveListener) {
		return new ASimplexBufferServerUpperCaseReceiveTrapper(anObjectReceiveListener);
	}
	
//	protected ReceiveListener getObjectReceiveListener (InputPort aServerInputPort) {
//		return new ASimplexObjectServerUpperCaseReceiveTrapper(upperCaser);
//	}

//	@Override
//	public void createServerObjects() {
//		 upperCaser = new ASimplexUpperCaser();	
//
//
//	}	
	public static void main (String[] args) {
		(new ASimplexBufferServerMVC_Launcher()).launch();
	}
	



	
	
}
