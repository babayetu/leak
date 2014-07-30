package tomcatstudy;

import java.io.File;

public class Constant {
	public static final String WEB_ROOT = System.getProperty("user.dir")
			+ File.separator + "webroot";
	public static final String SHUTDOWN = "/shutdown";
}