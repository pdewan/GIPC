package inputport.datacomm.simplex.buffer.nio;

public interface HeaderWriteCommand extends WriteCommand {

	public void makeHeaderFor(WriteCommand theBufferedWrite);

}