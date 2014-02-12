package oldtypedip;

import java.io.Serializable;

public interface TypedUniSend {
	void send(String remoteName, Serializable message);
	void send (Serializable message);

}
