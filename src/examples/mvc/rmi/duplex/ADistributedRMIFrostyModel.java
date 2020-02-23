package examples.mvc.rmi.duplex;

import java.rmi.RemoteException;

import examples.counter.Counter;
import examples.mvc.local.duplex.ADuplexFrostyModel;
import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.rmi.counter.DistributedCounter;


public class ADistributedRMIFrostyModel extends ADuplexFrostyModel implements DuplexFrostyModel {
	protected DistributedRMIUpperCaser upperCaser;
	public ADistributedRMIFrostyModel(DistributedRMIUpperCaser anUpperCaser,
											DistributedCounter aCounter) {
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
