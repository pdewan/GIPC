package examples.mvc.rmi.duplex;

import java.rmi.RemoteException;

import examples.mvc.local.duplex.ACounter;


public class ADistributedInheritingRMICounter extends ACounter implements DistributedRMICounter{
	@Override
	public boolean equals(Object otherObject) {
		if (!(otherObject instanceof DistributedRMICounter))
				return super.equals(otherObject);
		try {
			return getValue().equals(((DistributedRMICounter) otherObject).getValue());
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}	
}
