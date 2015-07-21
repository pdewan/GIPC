package extraip;


import java.rmi.Remote;

import sessionport.rpc.duplex.relayed.example.Adder;


public interface RemoteAdder extends Remote, Adder {
	

}
