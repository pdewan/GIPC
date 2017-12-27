package inputport.nio;
import java.nio.channels.SelectableChannel;
public interface RegisterRequest extends Request {
	public SelectableChannel getChannel();
	public RegisterListener getListener();
}