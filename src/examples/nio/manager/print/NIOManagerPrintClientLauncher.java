package examples.nio.manager.print;

import assignments.util.mainArgs.ClientArgsProcessor;

public class NIOManagerPrintClientLauncher {
	public static void main(String[] args) {
		new AnNIOManagerPrintClient(ClientArgsProcessor.getNIOServerPort(args)).processInput();
	}

}
