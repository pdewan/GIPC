package examples.rmi.counter;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

public class ARemoteRepository implements RemoteRepository {
	List<Remote> remotes = new ArrayList();
	public void deposit(Remote anObject) {
		remotes.add(anObject);
	}
	public List<Remote> getObjects() {
		return remotes;
	}
}
