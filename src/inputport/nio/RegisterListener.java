package inputport.nio;

import java.nio.channels.spi.AbstractSelectableChannel;

public interface RegisterListener {
	public void socketChannelRegistered(AbstractSelectableChannel theRegisteredSocketChannel);	
}
