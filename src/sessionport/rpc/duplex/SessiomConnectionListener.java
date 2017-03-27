package sessionport.rpc.duplex;

import java.util.Set;

import inputport.ConnectionListener;

public interface SessiomConnectionListener extends ConnectionListener {
	Set<String> getMmebers();

}
