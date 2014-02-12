package oldtypedip;
import java.io.Serializable;

public interface TypedDuplexSend extends TypedUniSend {
	void reply(Serializable message);
	
	
}
