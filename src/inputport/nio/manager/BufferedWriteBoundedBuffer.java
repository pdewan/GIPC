package inputport.nio.manager;
import inputport.nio.manager.commands.WriteCommand;

import java.util.concurrent.BlockingQueue;
public interface BufferedWriteBoundedBuffer extends BlockingQueue<WriteCommand>{

}
