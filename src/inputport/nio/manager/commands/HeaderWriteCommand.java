package inputport.nio.manager.commands;


public interface HeaderWriteCommand extends WriteCommand {

	public void makeHeaderFor(WriteCommand theBufferedWrite);

}