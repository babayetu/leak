package tomcatstudy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketDemo {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("10.249.222.70", 8080);
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("GET /tomcatsample/index.jsp HTTP/1.1");
			pw.println("Host: localhost:8080");
			pw.println("Connection: Close");
			pw.println();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			boolean loop = true;
			StringBuffer sb = new StringBuffer(8096);
			while (loop) {
		          if ( in.ready() ) {
		            int i=0;
		            while (i!=-1) {
		              i = in.read();
		              sb.append((char) i);
		            }
		            loop = false;
		          }
		          Thread.currentThread().sleep(50);
		        }
		        System.out.println(sb.toString());
		        socket.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}