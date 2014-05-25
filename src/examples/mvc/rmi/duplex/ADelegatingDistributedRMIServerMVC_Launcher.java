package examples.mvc.rmi.duplex;


import java.rmi.Remote;

public class ADelegatingDistributedRMIServerMVC_Launcher extends  ADistributedRMIServerMVC_Launcher{
	
	protected Remote createUpperCaser() {
		try {
		return new ADistributedDelegatingRMICounter();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

	}
	public static void main (String[] args) {
		(new ADelegatingDistributedRMIServerMVC_Launcher()).launch();

	}
}
