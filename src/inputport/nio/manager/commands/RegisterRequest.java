package inputport.nio.manager.commands;
import inputport.nio.manager.listeners.RegisterListener;

import java.nio.channels.SelectableChannel;
public interface RegisterRequest extends Request {
	public SelectableChannel getChannel();
	public RegisterListener getListener();
}