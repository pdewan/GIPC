package p2psockets;

import java.net.Socket;

public interface ReadListener {
	public void bytesRead(Socket socket, byte[] bytes, int theNumRead);
}
