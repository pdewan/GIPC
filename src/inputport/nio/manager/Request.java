package inputport.nio.manager;
import java.nio.channels.SelectableChannel;
public interface Request {
	//SelectingRunnable getSelectingRunnable();
	SelectableChannel getChannel();
	public boolean initiate();
}
