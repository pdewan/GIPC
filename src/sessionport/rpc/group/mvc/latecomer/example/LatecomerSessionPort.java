package sessionport.rpc.group.mvc.latecomer.example;

import port.SessionChoice;
import sessionport.rpc.group.mvc.direct.example.TrapperParameters;

public interface LatecomerSessionPort extends TrapperParameters {
	public static final SessionChoice SESSION_CHOICE = SessionChoice.LATECOMER_RELAYED;
}
