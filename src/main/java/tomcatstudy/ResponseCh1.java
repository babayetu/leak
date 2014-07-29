package tomcatstudy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class ResponseCh1 {
	private OutputStream os = null;
	private RequestCh1 request = null;
	
	public ResponseCh1(OutputStream os) {
		this.os = os;
	}

	public void setRequestCh1(RequestCh1 requestCh1) {
		this.request = requestCh1;
	}

	public void sendStaticResponse() {
		if (request.getUri() == null) {
			return;
		}
		File file = new File(HttpServerCh1.WEB_ROOT, request.getUri());
		FileInputStream fis = null;
		
		if (file !=null && file.exists()) {
			try {
				fis = new FileInputStream(file);
				if (fis != null) {
					byte[] buffer = new byte[2048];
					int offset = 0;
					int readReturn = 0;
					
					os.write("HTTP/1.1 200 File Found\r\nContent-Type: text/html\r\n\r\n".getBytes());
					
					readReturn = fis.read(buffer, offset, buffer.length);
					
					while (readReturn != -1) {
						os.write(buffer,0,readReturn);				
						readReturn = fis.read(buffer, 0, buffer.length);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} else {
			String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
			          "Content-Type: text/html\r\n" +
			          "Content-Length: 23\r\n" +
			          "\r\n" +
			          "<h1>File Not Found</h1>";
			try {
				os.write(errorMessage.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}