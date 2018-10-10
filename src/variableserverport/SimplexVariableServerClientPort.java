package variableserverport;

import inputport.datacomm.simplex.SimplexClientInputPort;
import port.ParticipantChoice;
// variable server and multiserver packages should be merged except that it is the abstraction of session and
// and multisever port
// but multiserver is the abstraction between replicated and session and static session
public interface SimplexVariableServerClientPort<MessageType> extends SimplexClientInputPort<MessageType>
//BasicSendingPort<MessageType>,
//	Sender<MessageType>

{
	public ParticipantChoice getParticipantChoice();


}
