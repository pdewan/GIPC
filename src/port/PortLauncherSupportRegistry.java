package port;

import inputport.datacomm.duplex.buffer.ADuplexBufferInputPortLauncherSupport;
import inputport.datacomm.duplex.object.ADuplexObjectInputPortLauncherSupport;
import inputport.datacomm.simplex.buffer.ASimplexBufferInputPortLauncherSupport;
import inputport.datacomm.simplex.object.ASimplexObjectInputPortLauncherSupport;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;

import java.util.HashMap;
import java.util.Map;

import sessionport.datacomm.group.object.direct.AnObjectGroupSessionPortDirectLauncherSupport;
import sessionport.datacomm.group.object.relayed.AnObjectGroupSessionPortRelayedLauncherSupport;
import sessionport.datacomm.group.object.relayed.latecomer.AnObjectGroupSessionPortLatecomerLauncherSupport;
import sessionport.rpc.duplex.direct.ADuplexRPCSessionPortDirectLauncherSupport;
import sessionport.rpc.duplex.relayed.ADuplexRPCSessionPortRelayedLauncherSupport;
import staticsessionport.rpc.group.AStaticSessionGroupRPCPortLauncherSupport;

public class PortLauncherSupportRegistry {
	static Map<String, PortLauncherSupport> stringToPortLauncherSupport = new HashMap();
	public static PortLauncherSupport put (PortDescription aPortDescription, PortLauncherSupport aPortLauncherSupport) {
		return stringToPortLauncherSupport.put(aPortDescription.toString(), aPortLauncherSupport);
	}
	public static PortLauncherSupport put (PortKind aPortKind, 
			PortAccessKind aPortAccessKind, 
			PortMessageKind aPortMessageKind, 
			PortLauncherSupport aPortLauncherSupport ){
		return put( new APortDescription(aPortKind,
				aPortAccessKind, aPortMessageKind),
				aPortLauncherSupport);
		
	}
	public static  PortLauncherSupport get (PortDescription aPortDescription) {
		return stringToPortLauncherSupport.get(aPortDescription.toString());
	}
	
	static {
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.SIMPLEX, PortMessageKind.BUFFER),
				new ASimplexBufferInputPortLauncherSupport());
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.SIMPLEX, PortMessageKind.BUFFER),
				new ASimplexBufferInputPortLauncherSupport());
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.SIMPLEX, PortMessageKind.OBJECT),
				new ASimplexObjectInputPortLauncherSupport());		
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.SIMPLEX, PortMessageKind.OBJECT),
				new ASimplexObjectInputPortLauncherSupport());
		
		
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.BUFFER),
				new ADuplexBufferInputPortLauncherSupport());			
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.BUFFER),
				new ADuplexBufferInputPortLauncherSupport());
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.OBJECT),
				new ADuplexObjectInputPortLauncherSupport());
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.OBJECT),
				new ADuplexObjectInputPortLauncherSupport());
		
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.BUFFER),
				new ADuplexBufferInputPortLauncherSupport());			
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.BUFFER),
				new ADuplexBufferInputPortLauncherSupport());
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.OBJECT),
				new ADuplexObjectInputPortLauncherSupport());
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.OBJECT),
				new ADuplexObjectInputPortLauncherSupport());
		put(new APortDescription(PortKind.CLIENT_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.RPC),
				new ADuplexRPCInputPortLauncherSupport());
		put(new APortDescription(PortKind.SERVER_INPUT_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.RPC),
				new ADuplexRPCInputPortLauncherSupport());
		put(new APortDescription(PortKind.SESSION_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.RPC,
				SessionChoice.P2P),
				new ADuplexRPCSessionPortDirectLauncherSupport());
		put(new APortDescription(PortKind.SESSION_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.RPC,
				SessionChoice.RELAYED),
				new ADuplexRPCSessionPortRelayedLauncherSupport());
		put(new APortDescription(PortKind.SESSION_PORT,
				PortAccessKind.DUPLEX, PortMessageKind.RPC,
				SessionChoice.LATECOMER_RELAYED),
				new ADuplexRPCSessionPortRelayedLauncherSupport());
		
		
		put(new APortDescription(PortKind.SESSION_PORT,
				PortAccessKind.GROUP, PortMessageKind.OBJECT,
				SessionChoice.P2P),
				new AnObjectGroupSessionPortDirectLauncherSupport());
		put(new APortDescription(PortKind.SESSION_PORT,
				PortAccessKind.GROUP, PortMessageKind.OBJECT,
				SessionChoice.RELAYED),
				new AnObjectGroupSessionPortRelayedLauncherSupport());
		put(new APortDescription(PortKind.SESSION_PORT,
				PortAccessKind.GROUP, PortMessageKind.OBJECT,
				SessionChoice.LATECOMER_RELAYED),
				new AnObjectGroupSessionPortRelayedLauncherSupport());
		// no distinction being made between rpc and object, I suppose
		// one needs rpc to session server
		put(new APortDescription(PortKind.SESSION_PORT,
				PortAccessKind.GROUP, PortMessageKind.RPC,
				SessionChoice.P2P),
				new AnObjectGroupSessionPortDirectLauncherSupport());
		put(new APortDescription(PortKind.SESSION_PORT,
				PortAccessKind.GROUP, PortMessageKind.RPC,
				SessionChoice.RELAYED),
				new AnObjectGroupSessionPortRelayedLauncherSupport());
		put(new APortDescription(PortKind.SESSION_PORT,
				PortAccessKind.GROUP, PortMessageKind.RPC,
				SessionChoice.LATECOMER_RELAYED),
				new AnObjectGroupSessionPortLatecomerLauncherSupport());
		
		put(new APortDescription(PortKind.SESSION_PORT,
				PortAccessKind.GROUP, PortMessageKind.RPC,
				SessionChoice.P2P,
				ParticipantBindTime.STATIC),
			   new AStaticSessionGroupRPCPortLauncherSupport());

		
		
	}


}
