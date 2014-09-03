package ajoo;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

public interface Logger {
	public void println(int level, String msg);

	public void printException(Throwable e);
}

class NopLogger implements Logger {
	public void println(int level, String msg) {
	}

	public void printException(Throwable e) {
	}
}

class WriterLogger implements Logger {
	private final PrintWriter writer;

	public void println(int level, String msg) {
		writer.println(msg);
	}

	public void printException(Throwable e) {
		e.printStackTrace(writer);
	}

	WriterLogger(PrintWriter writer) {
		this.writer = writer;
	}
}

class SequenceLogger implements Logger {
	private final Logger[] loggers;

	SequenceLogger(Logger[] ls) {
		this.loggers = ls;
	}

	public void println(int level, String msg) {
		for (Logger l : loggers) {
			l.println(level, msg);
		}
	}

	public void printException(Throwable e) {
		for (Logger l : loggers) {
			l.printException(e);
		}
	}
}

class FilteredLogger implements Logger {
	private final Logger logger1;
	private final Logger logger2;
	private final int lvl;

	public FilteredLogger(Logger logger1, Logger logger2, int lvl) {
		this.logger1 = logger1;
		this.logger2 = logger2;
		this.lvl = lvl;
	}

	public void println(int level, String msg) {
		if (level == lvl)
			logger1.println(level, msg);
		else
			logger2.println(level, msg);
	}

	public void printException(Throwable e) {
		if (lvl == LoggerConstant.ERROR.getValue())
			logger1.printException(e);
		else
			logger2.printException(e);
	}
}

class IgnoringLogger implements Logger {
	private final Logger logger1;
	private final Logger logger2;
	private final int lvl;

	public IgnoringLogger(Logger logger1, Logger logger2, int lvl) {
		this.logger1 = logger1;
		this.logger2 = logger2;
		this.lvl = lvl;
	}

	public void println(int level, String msg) {
		if (level >= lvl)
			logger1.println(level, msg);
		else
			logger2.println(level, msg);
	}

	public void printException(Throwable e) {
		if (lvl <= LoggerConstant.ERROR.getValue())
			logger1.printException(e);
		else
			logger2.printException(e);
	}
}

class ErrorMessageLogger implements Logger {
	private final PrintWriter out;
	private final Logger logger;

	public ErrorMessageLogger(PrintWriter out, Logger logger) {
		this.out = out;
		this.logger = logger;
	}

	public void println(int level, String msg) {
		logger.println(level, msg);
	}

	public void printException(Throwable e) {
		out.println(e.getMessage());
	}
}

class TimestampLogger implements Logger {
	private final Logger logger;

	public TimestampLogger(Logger logger) {
		this.logger = logger;
	}

	public void println(int level, String msg) {
		logger.println(level, new Date().toString() + ": " + msg);
	}

	public void printException(Throwable e) {
		logger.println(LoggerConstant.ERROR.getValue(), new Date().toString() + ": ");
		logger.printException(e);
	}
}

class Loggers{ 
	  static Logger nop(){return new NopLogger();} 
	  static Logger writer(PrintWriter writer){ 
	    return new WriterLogger(writer); 
	  } 
	  static Logger writer(OutputStream out){ 
	    return writer(new PrintWriter(out, true)); 
	  } 
	  static Logger filter(int lvl, Logger l1, Logger l2){ 
	    return new FilteredLogger(l1, l2,lvl); 
	  } 
	  
	  static Logger ignore(int lvl, Logger l1, Logger l2){
		return new IgnoringLogger(l1,l2,lvl);
	  }
	  
	  static Logger timestamp(Logger l1){
		return new TimestampLogger(l1);  
	  }
	  
	  static SequenceLogger sequence(Logger... l1) {
		  return new SequenceLogger(l1);
	  }	  
	}