package inputport.nio.manager.commands;
import java.nio.channels.SelectableChannel;

import inputport.nio.manager.listeners.RegisterListener;
public interface RegisterRequest extends Request {
	public SelectableChannel getChannel();
	public RegisterListener getListener();
}