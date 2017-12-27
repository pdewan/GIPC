package inputport.nio;

public interface HeaderWriteCommand extends WriteCommand {

	public void makeHeaderFor(WriteCommand theBufferedWrite);

}