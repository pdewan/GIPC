package examples.mvc.rmi.collaborative;


import examples.mvc.rmi.muser.AMultiUserRMIFrostyModel;
import examples.rmi.counter.DistributedCounter;


public class ACollaborativeRMIFrostyModel extends AMultiUserRMIFrostyModel {
	public ACollaborativeRMIFrostyModel(CollaborativeRMIUpperCaser anUpperCaser, 
											DistributedCounter aCounter,
											String aUserName) {
		super(anUpperCaser, aCounter, aUserName);
		try {
			anUpperCaser.connect(aUserName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
