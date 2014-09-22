package tomcatstudy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnectorCh3 implements Runnable {
	private boolean stopped;
	private String schemeString = "http";
	
	
	public String getSchemeString() {
		return schemeString;
	}

	public void run() {
		ServerSocket ss = null;
		int port = 8080;
		
		try {
			ss = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		while (!stopped){
			Socket so = null;
			try {
				//blocked here
				so = ss.accept();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
			HttpProcessorCh3 hp = new HttpProcessorCh3(this);
			hp.process(so);
		}
	}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.run();
	}
}