package oldtypedip;
import java.io.Serializable;
public interface TypedImplicitGroupSend  extends TypedUniImplicitSend {
	public void sendAll(Serializable message);
	public void sendOthers(Serializable message);
//	public void reply(Serializable message);
}
