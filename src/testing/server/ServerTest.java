package testing.server;

import java.io.IOException;

import fiberous.network.ConnectionManager;
import fiberous.network.StreamBundle;

public class ServerTest {

	public static void main(String[] args) throws IOException {
		ConnectionManager cm = new ConnectionManager();
		
		StreamBundle sb;
	
		while(true) {
			sb = cm.waitForSessionNegotiation();
			
			ServerThread st = new ServerThread(sb, cm);
			
			st.run();
		}
	}
}
