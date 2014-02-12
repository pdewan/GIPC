package inputport.rpc;

import inputport.InputPort;

public interface RPCInputPort extends InputPort {
	InputPort getDataInputPort();
	void setDataInputPort(InputPort newVal);
	Marshaller getMarshaller();
	void setMarshaller(Marshaller newVal);

}
