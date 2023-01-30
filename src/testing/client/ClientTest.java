package testing.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import fiberous.network.cm.ConnectionManager;
import fiberous.network.cm.StreamBundle;

public class ClientTest {
	public static void main(String[]args) throws UnknownHostException, IOException {
		ConnectionManager cm = new ConnectionManager();
		
		StreamBundle sb = cm.initSessionNegotiation(InetAddress.getByName("127.0.0.1"));
		
		System.out.println(sb.readUTFData());
		
		//Cleanup
		sb.closeStreams();
		cm.closeSocket(sb.getSocket());
	}
}
