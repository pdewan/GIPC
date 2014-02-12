package inputport.datacomm.simplex.buffer.nio;
import java.nio.channels.SelectableChannel;
public interface Request {
	//SelectingRunnable getSelectingRunnable();
	SelectableChannel getChannel();
	public boolean initiate();
}
