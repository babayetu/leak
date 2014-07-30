package tomcatstudy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class ResponseCh2 implements ServletResponse {
	private OutputStream os = null;
	private RequestCh2 request = null;
	private PrintWriter writer;
	
	public ResponseCh2(OutputStream os) {
		this.os = os;
	}

	public void setRequest(RequestCh2 requestCh2) {
		this.request = requestCh2;
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
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}					
				} catch (IOException e) {
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

	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// autoflush is true, println() will flush,
	    // but print() will not.
	    writer = new PrintWriter(os, true);
	    return writer;
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentType(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub
		
	}
}