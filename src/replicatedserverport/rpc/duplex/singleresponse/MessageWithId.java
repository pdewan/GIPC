package replicatedserverport.rpc.duplex.singleresponse;

public interface MessageWithId {
	Object getMessage();
	void setMessage(Object message);
	int getId();
	void setId(int id);

}
