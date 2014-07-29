package tomcatstudy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerCh1 {
	public static final String WEB_ROOT = System.getProperty("user.dir")
			+ File.separator + "webroot";
	private static final String SHUTDOWN = "/shutdown";

	public static void main(String[] args) {
		HttpServerCh1 hs = new HttpServerCh1();
		ServerSocket serverSo = null;
		try {
			serverSo = new ServerSocket(1234, 1,
					InetAddress.getByName("127.0.0.1"));
			System.out.println("http server started. File home: " + WEB_ROOT);
			boolean shutDown = false;
			Socket so = null;
			InputStream is = null;
			OutputStream os = null;
			RequestCh1 req = null;
			ResponseCh1 resp = null;

			while (!shutDown) {
				so = serverSo.accept();
				// chrome get accept twice while firefox only get once
				System.out.println("accept get one request");
				is = so.getInputStream();
				os = so.getOutputStream();
				
				req = new RequestCh1(is);
				System.out.println(req.parseRequest());
				if (req.getUri() != null && req.getUri().equalsIgnoreCase(SHUTDOWN)) {
					shutDown = true;
				}
				
				resp = new ResponseCh1(os);
				resp.setRequestCh1(req);
				resp.sendStaticResponse();
				
				// Close the socket
				// socket必须要关闭，不然outputstream就不会关闭，浏览器client端就会一直等待，显示为图标一直转动
		        so.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (serverSo != null) {
					serverSo.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}