package inputport.nio.manager;
import java.nio.channels.SelectableChannel;
public interface RegisterRequest extends Request {
	public SelectableChannel getChannel();
	public RegisterListener getListener();
}