package p2psockets;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class AP2PSocketsIMLauncher {
	static final int  IM_PORT = 4444;
	static ServerSocket mySocket;
	static List<Socket> peerSockets;
	public static void main (String[] args) {
		peerSockets = toSockets(args);
		mySocket = toServerSocket(args[0]);			
		//Echoer echoer = new AnOutCoupledEchoer();		
	}
	
	static void acceptConnections() {
		while (true) {
			try {
				mySocket.accept();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static ServerSocket createMySocket() {
		try {
			return new ServerSocket(IM_PORT);
		} catch (Exception e) {
			return null;
		}
	}
	static List<Socket> toSockets(String[] args) {
		List<Socket> sockets = new ArrayList();
		int index = 1;
		while (true) {
			if (index > args.length - 1) 
				break;
			sockets.add(toSocket(args[index], args[index+1]));		
			index += 2;
		}
		return sockets;
	}
	static Socket toSocket(String hostName, String portString) {
		try {
			int portNumber = Integer.parseInt(portString);
			return toSocket(hostName, portNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	static Socket toSocket(String hostName, int portNumber) {
		try {			
			return new Socket(hostName, portNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	static ServerSocket toServerSocket(String portString) {
		try {
			int portNumber = Integer.parseInt(portString);
			return toServerSocket(portNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	static ServerSocket toServerSocket(int portNumber) {
		try {			
			return new ServerSocket(portNumber);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	 public static String getHostName() {
	    	try {
	    		return java.net.InetAddress.getLocalHost().getHostName();
	    	} catch (Exception e) {
	    		return null;
	    	}
	 }		
}
