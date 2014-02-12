package inputport.datacomm.simplex.buffer;

public interface InputPortDriver<MessageChannelType> {
    void close(MessageChannelType aChannel);
}
