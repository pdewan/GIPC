package examples.mvc.rmi.collaborative;

import examples.mvc.rmi.muser.AMultiUserRMIUpperCaser;

public class ACollaborativeRMIUpperCaser extends AMultiUserRMIUpperCaser 
             implements CollaborativeRMIUpperCaser {	
	public void connect(String aClientName) {
		getCounterProxy(aClientName); // need to register the counter if not registered already
	}
	protected Object[] getAllCounters()  {		
		Object[] retVal = new Object[nameToCounter.keySet().size()];
		int index =0;
		for (String aClient:nameToCounter.keySet()) {
			try {
				retVal[index] = nameToCounter.get(aClient).getValue();
				index++;
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return retVal;		
	}	
    protected String toStringCounterValue(Object aCounterValue) {
    	return DisplayLibrary.toString( (Object[])aCounterValue);
    }
//	protected  String computeOthersLabelledUpperCase(String aCallerName, String anInput) {
//		return aCallerName + " Said:" + anInput.toUpperCase();
//	}
	@Override
	public String toCountedUpperCase(String aString, String aCallerName) {
		return  toUpperCase(aString)+
				 counterFeedback(getAllCounters());		
	}	
	
}
