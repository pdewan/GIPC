package replicatedserverport.rpc.duplex.singleresponse;

public class AMessageWithId implements MessageWithId{
	Object message;
	int id;
	public AMessageWithId() {
		
	}
	public AMessageWithId(Object aMessage, int anId) {
		message = aMessage;
		id = anId;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Mesage " + message +  " id " + id;
	}	

}
