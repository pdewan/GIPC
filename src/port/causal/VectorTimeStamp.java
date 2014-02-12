package port.causal;

import java.io.Serializable;
import java.util.Set;

public interface VectorTimeStamp extends Serializable, Comparable<VectorTimeStamp> {
	public void addUser(String user);
	public void removeUser(String user);
	public void addMessage(String user);
	public int size();
	Set<String> users();
	void addUsers(VectorTimeStamp other);
	public int get(String user);
	public boolean isSucceededBy(VectorTimeStamp other);
	public boolean isConcurrent(VectorTimeStamp other);
	public VectorTimeStamp clone();
}
