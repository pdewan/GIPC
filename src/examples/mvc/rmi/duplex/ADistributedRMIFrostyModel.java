package examples.mvc.rmi.duplex;

import java.rmi.RemoteException;

import examples.mvc.local.duplex.ADuplexFrostyModel;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.duplex.DuplexFrostyModel;


public class ADistributedRMIFrostyModel extends ADuplexFrostyModel implements DuplexFrostyModel {
	protected DistributedRMIUpperCaser upperCaser;
	public ADistributedRMIFrostyModel(DistributedRMIUpperCaser anUpperCaser,
											DistributedRMICounter aCounter) {
		super(null,  (Counter) aCounter);
		upperCaser = anUpperCaser;
	}
	protected String toCountedUpperCase(String anInput ) {
		try {
		return upperCaser.toCountedUpperCase(anInput);
		} catch (RemoteException e) {
			e.printStackTrace();
			return "";
		}		
	}
	protected void printUpperCase(String anInput ) {
		try {
		upperCaser.printUpperCase(anInput);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
