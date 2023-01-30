package testing.server;

import java.io.IOException;

import fiberous.network.cm.ConnectionManager;
import fiberous.network.cm.StreamBundle;

public class ServerThread implements Runnable {
	
	private StreamBundle sBundle;
	private ConnectionManager cMan;
	
	ServerThread(StreamBundle sb, ConnectionManager cm) {
		sBundle = sb;
		cMan = cm;
	}

	@Override
	public void run() {
		try {
			sBundle.writeUTFData("You connected!  Goodbye.");
			
			//Cleanup
			sBundle.closeStreams();
			cMan.closeSocket(sBundle.getSocket());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
