package inputport.rpc.duplex.example;

import java.rmi.Remote;


public interface AnotherCounter extends Remote {
	void increment(int val);
	int getValue();
}
