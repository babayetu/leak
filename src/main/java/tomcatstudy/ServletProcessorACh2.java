package tomcatstudy;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletProcessorACh2 {
	public void process (RequestCh2 req, ResponseCh2 resp) {
		String oUri = req.getUri();
		int lastSlash = oUri.lastIndexOf("/");
		String className = oUri.substring(lastSlash+1);   // PrimaryServletCh2
		URL[] urls = new URL[1];
		URLClassLoader ucl = null;
		Class<?> myclass = null;
		
		try {
			File classPath = new File(Constant.WEB_ROOT);
			
			System.out.println(className);
			//结尾是/代表一个库目录，结尾不是/代表是一个jar包文件名
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
			System.out.println(repository);
			urls[0] = new URL(repository);
			ucl = new URLClassLoader(urls);
			myclass = ucl.loadClass("tomcatstudy." + className);
			
			ClassLoader cl = ucl;
			while(cl.getParent() != null) {
				System.out.println(cl.getClass().getName());
				cl = cl.getParent();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ucl !=null) {
				try {
					ucl.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try {
			Servlet aServlet = (Servlet)myclass.newInstance();
			aServlet.service((ServletRequest) req, ((ServletResponse)resp));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}	
}