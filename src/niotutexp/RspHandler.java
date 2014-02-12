package niotutexp;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class RspHandler {
	private byte[] rsp = null;
	//private Object rsp = null;
	
	public synchronized boolean handleResponse(byte[] rsp) {
		this.rsp = rsp;
		this.notify();
		return true;
	}
	
	public synchronized void waitForResponse() {
		while(this.rsp == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		//ByteBuffer buf = ByteBuffer.wrap(rsp);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rsp);
		try {
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		Object retVal = objectInputStream.readObject();
		System.out.println(retVal);
		retVal = objectInputStream.readObject();
		System.out.println(retVal);
		} catch (Exception e) {
		
		//System.out.println(new String(this.rsp));
		System.out.println(rsp.toString());
		}
		
	}
}

