package tomcatstudy;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.apache.catalina.util.RequestUtil;
import org.apache.catalina.util.StringManager;

public class HttpProcessorCh3 {
	private HttpRequestCh3 request = null;
	private HttpResponseCh3 response = null;
	private HttpConnectorCh3 connector = null;
	private HttpRequestLine requestLine = new HttpRequestLine();
	private HttpHeader header = new HttpHeader();
	protected StringManager sm = StringManager.getManager("tomcatstudy");

	public HttpProcessorCh3(HttpConnectorCh3 connector) {
		this.connector = connector;
	}

	public void process(Socket so) {
		SocketInputStream sis = null;
		OutputStream os = null;

		try {
			sis = new SocketInputStream(so.getInputStream(), 2048); // 2k buffer
			os = so.getOutputStream();

			// create http request
			request = new HttpRequestCh3(sis);

			// create http response
			response = new HttpResponseCh3(os);
			response.setRequest(request);
			response.setHeader("Server", "Pyrmont Servlet Container");

			parseRequest(sis, os);
			parseHeaders(sis);

			if (request.getRequestURI().startsWith("/servlet/")) {
				ServletProcessorCh3 processor = new ServletProcessorCh3();
				processor.process(request, response);
			} else {
				StaticResourceProcessorCh3 processor = new StaticResourceProcessorCh3();
				processor.process(request, response);
			}

			so.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseHeaders(SocketInputStream input) throws IOException,
			ServletException {
		while (true) {
			HttpHeader header = new HttpHeader();

			// Read the next header
			input.readHeader(header);
			if (header.nameEnd == 0) {
				if (header.valueEnd == 0) {
					return;
				} else {
					throw new ServletException(
							sm.getString("httpProcessor.parseHeaders.colon"));
				}
			}

			String name = new String(header.name, 0, header.nameEnd);
			String value = new String(header.value, 0, header.valueEnd);
			request.addHeader(name, value);
			// do something for some headers, ignore others.
			if (name.equals("cookie")) {
				Cookie cookies[] = RequestUtil.parseCookieHeader(value);
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("jsessionid")) {
						// Override anything requested in the URL
						if (!request.isRequestedSessionIdFromCookie()) {
							// Accept only the first session id cookie
							request.setRequestedSessionId(cookies[i].getValue());
							request.setRequestedSessionCookie(true);
							request.setRequestedSessionURL(false);
						}
					}
					request.addCookie(cookies[i]);
				}
			} else if (name.equals("content-length")) {
				int n = -1;
				try {
					n = Integer.parseInt(value);
				} catch (Exception e) {
					throw new ServletException(
							sm.getString("httpProcessor.parseHeaders.contentLength"));
				}
				request.setContentLength(n);
			} else if (name.equals("content-type")) {
				request.setContentType(value);
			}
		} // end while
	}

	private void parseRequest(SocketInputStream sis, OutputStream os)
			throws IOException, ServletException {
		sis.readRequestLine(requestLine);
		String method = new String(requestLine.method, 0, requestLine.methodEnd);
		String uri = null;
		String protocol = new String(requestLine.protocol, 0,
				requestLine.protocolEnd);

		// Validate the incoming request line
		if (method.length() < 1) {
			throw new ServletException("Missing HTTP request method");
		}
		if (requestLine.uriEnd < 1) {
			throw new ServletException("Missing HTTP request URI");
		}

		int question = requestLine.indexOf("?");
		if (question >= 0) {
			request.setQueryString(new String(requestLine.uri, question + 1,
					requestLine.uriEnd - question - 1));
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
				if (pos == -1) {
					uri = "";
				} else {
					uri = uri.substring(pos);
				}
			}
		}

		// Parse any requested session ID out of the request URI
		String match = ";jsessionid=";
		int semicolon = uri.indexOf(match);
		if (semicolon != -1) {
			String rest = uri.substring(semicolon + match.length());
			int semicolon2 = rest.indexOf(";");
			if (semicolon2 != -1) {
				request.setRequestedSessionId(rest.substring(0, semicolon2));
				rest = rest.substring(semicolon2);
			} else {
				request.setRequestedSessionId(rest);
				rest = "";
			}
			request.setRequestedSessionURL(true);
			uri = uri.substring(0, semicolon) + rest;
		} else {
			request.setRequestedSessionId(null);
			request.setRequestedSessionURL(false);
		}

		// Normalize URI (using String operations at the moment)
		String normalizedUri = normalize(uri);

		// Set the corresponding request properties
		((HttpRequestCh3) request).setMethod(method);
		request.setProtocol(protocol);

		if (normalizedUri != null) {
			((HttpRequestCh3) request).setRequestURI(normalizedUri);
		} else {
			((HttpRequestCh3) request).setRequestURI(uri);
		}
		if (normalizedUri == null) {
			throw new ServletException("Invalid URI: " + uri + "'");
		}
	}

	protected String normalize(String path) {
		if (path == null)
			return null;
		// Create a place for the normalized path
		String normalized = path;

		// Normalize "/%7E" and "/%7e" at the beginning to "/~"
		if (normalized.startsWith("/%7E") || normalized.startsWith("/%7e"))
			normalized = "/~" + normalized.substring(4);

		// Prevent encoding '%', '/', '.' and '\', which are special reserved
		// characters
		if ((normalized.indexOf("%25") >= 0)
				|| (normalized.indexOf("%2F") >= 0)
				|| (normalized.indexOf("%2E") >= 0)
				|| (normalized.indexOf("%5C") >= 0)
				|| (normalized.indexOf("%2f") >= 0)
				|| (normalized.indexOf("%2e") >= 0)
				|| (normalized.indexOf("%5c") >= 0)) {
			return null;
		}

		if (normalized.equals("/."))
			return "/";

		// Normalize the slashes and add leading slash if necessary
		if (normalized.indexOf('\\') >= 0)
			normalized = normalized.replace('\\', '/');
		if (!normalized.startsWith("/"))
			normalized = "/" + normalized;

		// Resolve occurrences of "//" in the normalized path
		while (true) {
			int index = normalized.indexOf("//");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index)
					+ normalized.substring(index + 1);
		}

		// Resolve occurrences of "/./" in the normalized path
		while (true) {
			int index = normalized.indexOf("/./");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index)
					+ normalized.substring(index + 2);
		}

		// Resolve occurrences of "/../" in the normalized path
		while (true) {
			int index = normalized.indexOf("/../");
			if (index < 0)
				break;
			if (index == 0)
				return (null); // Trying to go outside our context
			int index2 = normalized.lastIndexOf('/', index - 1);
			normalized = normalized.substring(0, index2)
					+ normalized.substring(index + 3);
		}

		// Declare occurrences of "/..." (three or more dots) to be invalid
		// (on some Windows platforms this walks the directory tree!!!)
		if (normalized.indexOf("/...") >= 0)
			return (null);

		// Return the normalized path that we have completed
		return (normalized);

	}
}