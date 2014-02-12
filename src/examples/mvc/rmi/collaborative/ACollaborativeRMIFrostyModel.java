package examples.mvc.rmi.collaborative;


import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.mvc.rmi.muser.AMultiUserRMIFrostyModel;


public class ACollaborativeRMIFrostyModel extends AMultiUserRMIFrostyModel {
	public ACollaborativeRMIFrostyModel(CollaborativeRMIUpperCaser anUpperCaser, 
											DistributedRMICounter aCounter,
											String aUserName) {
		super(anUpperCaser, aCounter, aUserName);
		try {
			anUpperCaser.connect(aUserName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
