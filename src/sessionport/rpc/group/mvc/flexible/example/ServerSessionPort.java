package sessionport.rpc.group.mvc.flexible.example;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;

public interface ServerSessionPort {
	public static final boolean DO_DELAY = false;
	public static final PortLauncherSupport DELAYS_SUPPORT = null;
	public static final boolean DO_JITTER = false;
	public static final boolean DO_CAUSAL = false;
	public static final ParticipantChoice PARTICIPANT_CHOICE = ParticipantChoice.SERVER_ONLY;
}
