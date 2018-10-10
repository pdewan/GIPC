package inputport.nio.manager;
import java.util.concurrent.BlockingQueue;

import inputport.nio.manager.commands.WriteCommand;
public interface BufferedWriteBoundedBuffer extends BlockingQueue<WriteCommand>{

}
