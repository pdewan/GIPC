package examples.nio.manager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import javax.net.ServerSocketFactory;

import examples.nio.manager.server.AMeaningOfLifeNIOServer;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.nio.SocketChannelBound;
import inputport.datacomm.simplex.buffer.nio.AReadingAcceptCommandFactory;
import inputport.nio.manager.AcceptCommandSelector;
import inputport.nio.manager.AnNIOManager;
import inputport.nio.manager.NIOManager;
import inputport.nio.manager.NIOManagerFactory;
import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.SelectionManagerFactory;

public class MeaningOfLifeNIOServerLauncher  {

	public static void main(String[] args) {
		AMeaningOfLifeNIOServer.main(args);

	}
}
