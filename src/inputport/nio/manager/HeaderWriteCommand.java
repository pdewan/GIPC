package inputport.nio.manager;

public interface HeaderWriteCommand extends WriteCommand {

	public void makeHeaderFor(WriteCommand theBufferedWrite);

}