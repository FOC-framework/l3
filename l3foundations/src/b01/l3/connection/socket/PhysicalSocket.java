package b01.l3.connection.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.comm.SerialPortEventListener;

import b01.foc.Globals;
import b01.l3.connection.L3SerialPortReceptionCumulationBuffer;
import b01.sbs.BServer;

public class PhysicalSocket extends BServer {
	
	private Socket clientSocket = null;
	private ArrayList<L3SerialPortReceptionCumulationBuffer> listenerArray = null;
	
	public PhysicalSocket(int port){
		super(port);
		listenerArray = new ArrayList<L3SerialPortReceptionCumulationBuffer>();
	}
	
	public void disose(){
		super.dispose();
		if(listenerArray != null){
			listenerArray.clear();
			listenerArray = null;
		}
	}

	@Override
	public boolean openSocket() {
		boolean error = super.openSocket();
		if(!error){
			try{
				clientSocket = getSocket().accept();
			}catch(Exception e){
				error = true;
			}
			error = error || clientSocket == null;
		}
		return error;
	}
	
	@Override
	public void closeSocket(){
		if(clientSocket != null){
			try {
				clientSocket.close();
			} catch (IOException e) {
				Globals.logException(e);
			}
			clientSocket = null;
		}
		super.closeSocket();
	}
	
	public OutputStream getOutputStream() {
		OutputStream out = null;
		try {
			out = clientSocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	
	public void run() {
		while (true) {
			try {
				InputStreamReader streamReader = new InputStreamReader(clientSocket.getInputStream());
				char[] cArray = new char[1000];
		
				while(true){
					StringBuffer incrementalBuffer = new StringBuffer();
					
					int nbrOfCharacters = streamReader.read(cArray);
					for(int i=0; i<nbrOfCharacters; i++){
						incrementalBuffer.append(cArray[i]);
					}
					//String message = ASCII.convertNonCharactersToDescriptions(incrementalBuffer.toString());
					//Globals.logString("Buffer:"+message);
					
					notifyListenersOfReceivedMessage(incrementalBuffer);
					
//					OutputStream out = clientSocket.getOutputStream();
//					out.write(ASCII.ACK);
				}

			} catch (Exception e) {
				logString("At " + getPort() + " EXCEPTION");
				logException(e);
			}
		}
	}
	
	protected void notifyListenersOfReceivedMessage(StringBuffer incrementalBuffer){
		for(int i=0; i<listenerArray.size(); i++){
			L3SerialPortReceptionCumulationBuffer listener = listenerArray.get(i);
			listener.cumulateBufferAndAttemptToExtractFrame(incrementalBuffer);
		}
	}
	
	public void addEventListener(SerialPortEventListener cumulationListener) throws Exception {
		if(listenerArray != null && cumulationListener instanceof L3SerialPortReceptionCumulationBuffer){
			listenerArray.add((L3SerialPortReceptionCumulationBuffer) cumulationListener);
		}
	}

	public void removeEventListener() {
		if(listenerArray != null){
			listenerArray.clear();
		}
	}
}
