package tomcatstudy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class RequestCh1 {
	private InputStream is = null;
	private String uri = null;

	protected RequestCh1(InputStream is) {
		this.is = is;
	}
	
	public String parseRequest(){
		StringBuffer sb = new StringBuffer(2048);
		byte[] buffer = new byte[2048];
		String retVal = null;
		int readSize = 0;
		
		try {
			readSize = is.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (readSize > 0 ) {
			for (int i=0;i<readSize;i++) {
				sb.append((char)buffer[i]);
			}
			
			retVal = sb.toString();
			parseUri(retVal);
		}
		
		return retVal;
	}

	private void parseUri(String requestString) {
	    int index1, index2;
	    index1 = requestString.indexOf(' ');
	    if (index1 != -1) {
	      index2 = requestString.indexOf(' ', index1 + 1);
	      if (index2 > index1)
	        uri = requestString.substring(index1 + 1, index2);
	    }
	  }
	
	public String getUri() {
		return uri;
	}
}