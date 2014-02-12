package oldtypedip;
import java.io.Serializable;
public interface CopyOfTypedDuplexSend extends TypedUniImplicitSend, TypedUniNamingSend {
	public void reply (Serializable message);
}
