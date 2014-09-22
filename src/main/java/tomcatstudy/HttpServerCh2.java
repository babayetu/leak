package tomcatstudy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerCh2 {

	public static void main(String[] args) {
		HttpServerCh2 hs = new HttpServerCh2();
		ServerSocket serverSo = null;
		try {
			serverSo = new ServerSocket(2222, 1,
					InetAddress.getByName("127.0.0.1"));
			System.out.println("http server started. File home: "
					+ Constant.WEB_ROOT);
			boolean shutDown = false;
			Socket so = null;
			InputStream is = null;
			OutputStream os = null;
			RequestCh2 req = null;
			ResponseCh2 resp = null;

			while (!shutDown) {
				so = serverSo.accept();
				// chrome get accept twice while firefox only get once
				System.out.println("accept get one request");
				is = so.getInputStream();
				os = so.getOutputStream();

				req = new RequestCh2(is);
				resp = new ResponseCh2(os);
				resp.setRequest(req);

				System.out.println(req.parseRequest());
				if (req.getUri() != null
						&& req.getUri().equalsIgnoreCase(Constant.SHUTDOWN)) {
					shutDown = true;
				}

				if (req.getUri().startsWith("/servlet/")) {
					ServletProcessorACh2 processor = new ServletProcessorACh2();
					processor.process(req, resp);
				} else {
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(req, resp);
				}

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