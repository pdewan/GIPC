package oldtypedip;
import java.io.Serializable;
public interface TypedReceiveListener {	
	public void  messageReceived(String remoteClientName, Serializable message);
}
