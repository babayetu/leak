package tomcatstudy;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletException;

public class HttpProcessorCh3 {
	private HttpRequestCh3 request = null;
	private HttpResponseCh3 response = null;
	private HttpConnectorCh3 connector = null;
	private HttpRequestLine requestLine = null;
	
	
	public HttpProcessorCh3(HttpConnectorCh3 connector) {
		this.connector = connector;
	}

	protected StringManager sm = StringManager.getManager("tomcatstudy");
	
	public void process(Socket so) {
		SocketInputStream sis = null;
		OutputStream os = null;
		
		try {
			sis = new SocketInputStream(so.getInputStream(),2048);  //2k buffer
			os = so.getOutputStream();
			
			//create http request
			request = new HttpRequestCh3(sis);
			
			//create http response
			response = new HttpResponseCh3(os);
			response.setRequest(request);
			response.setHeader("Server", "Pyrmont Servlet Container");
			
			parseRequest(sis, os);
			parseHeaders(sis);
			
			if (request.getUri().startsWith("/servlet/")) {
				ServletProcessorACh2 processor = new ServletProcessorACh2();
				processor.process(request, response);
			} else {
				StaticResourceProcessor processor = new StaticResourceProcessor();
				processor.process(request, response);
			}
			
			so.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseHeaders(SocketInputStream sis) {
		// TODO Auto-generated method stub
		
	}

	private void parseRequest(SocketInputStream sis, OutputStream os) throws IOException, ServletException {
		sis.readRequestLine(requestLine);
		String method = new String(requestLine.method, 0, requestLine.methodEnd);
		String uri = null;
		String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);
		
		// Validate the incoming request line
		if (method.length()<1) {
			throw new ServletException("Missing HTTP request method");
		}
		if (requestLine.uriEnd <1) {
			throw new ServletException("Missing HTTP request URI");
		}
		
		int question = requestLine.indexOf("?");
		if (question >= 0) {
			request.setQueryString(new String(requestLine.uri, question +1, requestLine.uriEnd - question -1));
			uri = new String(requestLine.uri, 0, question);
		} else {
			request.setQueryString(null);
			uri = new String(requestLine.uri, 0, requestLine.uriEnd);
		}
		
		// 检查 HTTP绝对路径
		if (!uri.startsWith("/")) {
			int pos = uri.indexOf("://");
			if (pos != -1) {
				pos = uri.indexOf('/', pos + 3);
				if (pos != -1) {
					uri = "";
				} else {
					uri = uri.substring(pos);
				}
			}
		}
		
		
		
	}
	
	
}