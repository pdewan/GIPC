package examples.nio.manager.print;

import assignments.util.mainArgs.ServerArgsProcessor;

public class NIOManagerPrintServerLauncher {
	public static void main(String[] args) {
		new AnNIOManagerPrintServer(ServerArgsProcessor.getNIOServerPort(args));
	}

}
