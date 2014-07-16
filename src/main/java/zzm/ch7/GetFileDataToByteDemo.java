package zzm.ch7;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class GetFileDataToByteDemo{
	public byte[] readFileFromFilePath(String name) throws FileNotFoundException, IOException {
		File file = new File(name);
		FileInputStream fos;
		
		fos = new FileInputStream(file);

		byte[] fileContent = new byte[(int)file.length()];
		fos.read(fileContent);
		fos.close();
		return fileContent;
	}
	
	public byte[] readFileFromURL(String rootURL, String className) throws FileNotFoundException, IOException {
		URL url = new URL(classNameToPath(rootURL,className));
		
		InputStream is = url.openStream();
		
		byte[] b = new byte[1024 * 10];
		int len = is.read(b);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (len != -1) {
			baos.write(b, 0, len);
		}
		return baos.toByteArray();		
	}

	private String classNameToPath(String rootURL, String className) {
		return rootURL + "/" + className.replace(".", "/") + ".class";
	}
	
	public static void main(String[] args) throws IOException {
		String fileName="C:\\1.txt";
		GetFileDataToByteDemo gfdtbd = new GetFileDataToByteDemo();
		
		try {
			byte[] content = gfdtbd.readFileFromFilePath(fileName);
			System.out.println(new String(content,"UTF-8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String netAddress = "http://localhost:8080/karl";
		byte[] aa = gfdtbd.readFileFromURL(netAddress,"NetClassLoaderSimple");
		
		int i = 0;
		StringBuffer sb = new StringBuffer();
		while (i<aa.length) {
			if (i%10 == 0) {
				System.out.println(sb.toString() + "\n");
				sb.delete(0,sb.length());
			} else {
				sb.append(aa[i]).append(" ");
			}
			i++;
		}
	}
}