package oldtypedip;

import java.io.Serializable;
import java.util.Set;

public interface TypedNamingGroupSend {
	void send(Set<String> clientNames, Serializable message);


}
