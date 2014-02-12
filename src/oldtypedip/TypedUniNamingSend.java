package oldtypedip;
import java.io.Serializable;
public interface TypedUniNamingSend {
	void send(String remoteName, Serializable message);
}
