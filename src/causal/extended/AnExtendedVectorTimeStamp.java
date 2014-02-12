package causal.extended;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import util.trace.Tracer;



public class AnExtendedVectorTimeStamp implements ExtendedVectorTimeStamp {
	Map<String, Integer> contents = new HashMap();

	@Override
	public void addMessage(String user) {		
		contents.put(user, get(user) + 1);
	}

	@Override
	public void addUser(String user) {
		contents.put(user, 0);
	}
	@Override
	public void removeUser(String user) {
		contents.remove(user);
	}

	@Override
	public int compareTo(ExtendedVectorTimeStamp other) {
//		if (other.size() != size()) {
//			throw new RuntimeException("Incomparable vector time stamps" + ". Others.size:" + other.size() );
//		}
		Set<String> keys = contents.keySet();
		
		for (String key: keys) {
			int myValue = get(key);
			int otherValue = other.get(key);
			int diff = myValue - otherValue;
			if (diff < 0)
				return -1;
			else if (diff > 0)
				return 1;			
		}
		return 0;		
	}
	public void addUsers(ExtendedVectorTimeStamp other) {
		Set<String> otherUsers = other.users();
		for (String user:otherUsers) {
			if (contents.get(user) == null)
				addUser(user);
		}		
	}

	public boolean isConcurrent(ExtendedVectorTimeStamp other) {
//		if (other.size() != size()) {
//			throw new RuntimeException("Incomparable vector time stamps: this:" + this + " other:" + other );
//		}
		Set<String> keys = contents.keySet();
//		Set<String> otherKeys = other.users();
//		keys.addAll(otherKeys);
		boolean otherGreater = false;
		boolean thisGreater = false;
		for (String key: keys) {
			int myValue = get(key);
			int otherValue = other.get(key);
			int diff = otherValue - myValue;
			if (diff == 0)
				continue;
			else if (diff < 0 && !thisGreater) {
				thisGreater = true;
				if (otherGreater)
					return true;
			}
			else if (diff > 0 && !otherGreater) {
				otherGreater = true;
				if (thisGreater)
					return true;
					
			}
		}
		return false;		
		
	}
	@Override
	public boolean isSucceededBy(ExtendedVectorTimeStamp other, String ignoreKey) {
//		if (other.size() != size()) {
//			throw new RuntimeException("Incomparable vector time stamps" );
//		}
		Tracer.info(this, "checking if this " + this + " is succeded by  " + other);
		Set<String> keys = contents.keySet();
		boolean foundOneSuccessor= false;
		for (String key: keys) {
			if (key.equals(ignoreKey)) {
				Tracer.info(this, "Ignoring messages received from " + key);
				continue;
			}
			int myValue = get(key);
			int otherValue = other.get(key);
			int diff = otherValue - myValue;
			if (diff == 0) {
				Tracer.info(this, "Have received same number of messages from "  + key);
				continue;
			} else if (diff != 1) {
				Tracer.info(this, "Have not received " + diff + " messages from " + key);
				return false;
			} else if (foundOneSuccessor) {
				Tracer.info(this, "Have not received 1 message from a second user " + key);
				return false;
			} else {
				Tracer.info(this, "Have not received 1 message from  " + key);
				foundOneSuccessor = true;	
			}
			
				
//			else if (diff < 0)
//				return false;
//			else if (diff == 1) {
//				if (foundOneSuccessor)
//					return false;
//				else
//					foundOneSuccessor = true;	
//			} else 
//				return false;			
		}
		return foundOneSuccessor;		
	}
	@Override
	public boolean isSucceededBy(ExtendedVectorTimeStamp other) {
		return isSucceededBy(other, null);
	}
//	@Override
//	public boolean isSucceededBy(ExtendedVectorTimeStamp other) {
////		if (other.size() != size()) {
////			throw new RuntimeException("Incomparable vector time stamps" );
////		}
//		Message.info(this, "checking if t " + this + " is succeded by time stamp " + other);
//		Set<String> keys = contents.keySet();
//		
//		boolean foundOneSuccessor= false;
//		for (String key: keys) {
//			int myValue = get(key);
//			int otherValue = other.get(key);
//			int diff = otherValue - myValue;
//			if (diff == 0)
//				continue;
//			else if (diff != 1)
//				return false;
//			else if (foundOneSuccessor)
//				return false;
//			else
//				foundOneSuccessor = true;				
//			
//				
////			else if (diff < 0)
////				return false;
////			else if (diff == 1) {
////				if (foundOneSuccessor)
////					return false;
////				else
////					foundOneSuccessor = true;	
////			} else 
////				return false;			
//		}
//		return foundOneSuccessor;		
//	}
	

	@Override
	public int get(String user) {
		Integer val = contents.get(user);
		if (val == null)
			val = 0;
		return val;
	}

	@Override
	public int size() {
		return contents.size();
	}
	
	@Override
	public ExtendedVectorTimeStamp clone() {
		AnExtendedVectorTimeStamp retVal = new AnExtendedVectorTimeStamp();
		Set<String> keys = contents.keySet();
		for (String key: keys)
			retVal.contents.put(key, get(key));
		return retVal;
	}
	@Override
	public String toString() {
		return contents.toString();
	}

	@Override
	public Set<String> users() {
		return contents.keySet();
	}

}
