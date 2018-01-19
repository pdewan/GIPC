package inputport.nio.manager.commands;
import java.nio.channels.SelectableChannel;
public interface Request {
	//SelectingRunnable getSelectingRunnable();
	SelectableChannel getChannel();
	public boolean initiate();
}
