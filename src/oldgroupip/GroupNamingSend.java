package oldgroupip;


import java.nio.ByteBuffer;
import java.util.Set;


public interface GroupNamingSend  {
	void send(Set<String> clientNames, ByteBuffer message);

}
