package examples.mvc.rmi.muser;

import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.rmi.duplex.ADistributedRMIFrostyModel;
import examples.rmi.counter.DistributedCounter;


public class AMultiUserRMIFrostyModel extends ADistributedRMIFrostyModel implements DuplexFrostyModel {
	protected String userName;
	public AMultiUserRMIFrostyModel(MultiUserRMIUpperCaser anUpperCaser, 
											DistributedCounter aCounter,
											String aUserName) {
		super(anUpperCaser, aCounter);
		userName = aUserName;
	}
	protected String toCountedUpperCase(String anInput ) {
		try {
		  return ((MultiUserRMIUpperCaser)upperCaser).toCountedUpperCase(anInput, userName);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}		
	}
//	protected void printUpperCase(String anInput ) {
//		try {
//		  upperCaser.printUpperCase(anInput);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//	}
}
