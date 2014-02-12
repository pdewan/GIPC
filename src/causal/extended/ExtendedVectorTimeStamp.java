package causal.extended;

import java.io.Serializable;
import java.util.Set;

public interface ExtendedVectorTimeStamp extends Serializable, Comparable<ExtendedVectorTimeStamp> {
	public void addUser(String user);
	public void removeUser(String user);
	public void addMessage(String user);
	public int size();
	Set<String> users();
	void addUsers(ExtendedVectorTimeStamp other);
	public int get(String user);
	public boolean isSucceededBy(ExtendedVectorTimeStamp other);
	public boolean isSucceededBy(ExtendedVectorTimeStamp other, String ignoreKey);
	public boolean isConcurrent(ExtendedVectorTimeStamp other);
	public ExtendedVectorTimeStamp clone();
}
