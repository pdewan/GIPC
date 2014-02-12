package extraip;

import inputport.rpc.SerializableCall;

public interface SequencedSerializableCall extends SerializableCall{
	int getID();

}
