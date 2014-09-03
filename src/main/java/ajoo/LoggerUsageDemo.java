package ajoo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import static ajoo.Loggers.*;


public class LoggerUsageDemo {
	public static void main(String[] args) {
		String errorLogpath = ".\\src\\main\\resources\\temp\\error.log";
		String infoLogpath = ".\\src\\main\\resources\\temp\\info.log";
		String warningLogpath = ".\\src\\main\\resources\\temp\\warning.log";
		
	    File errorFile = new File(errorLogpath);
	    File infoFile = new File(infoLogpath);
	    File warningFile = new File(warningLogpath);
	    
	    PrintWriter errorWriter = null;
	    PrintWriter infoWriter = null;
	    PrintWriter warningWriter = null;
	    
	    Logger err_log = null; 
	    Logger warning_log = null; 
	    Logger info_log = null;
	    
	    try {
	    	errorWriter = new PrintWriter(errorFile);	    	
		} catch (FileNotFoundException e) {
			try {
				errorFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	    try {
	    	infoWriter = new PrintWriter(infoFile);
	    	
		} catch (FileNotFoundException e) {
			try {
				infoFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	    
	    try {
	    	warningWriter = new PrintWriter(warningFile);
	    	
		} catch (FileNotFoundException e) {
			try {
				warningFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	    
	    
	    if(errorWriter !=null && infoWriter !=null && warningWriter !=null) {
	    	//不同的文件吗？好办啊。就是不同的PrintWriter对象了。 
			err_log = writer(errorWriter);
			info_log = writer(infoWriter);
			warning_log = writer(warningWriter);
			
//			err_log.println(LoggerConstant.ERROR.getValue(), "I want to go error.log");	    	
//	    	info_log.println(LoggerConstant.INFO.getValue(), "I want to go info.log");	    	
//	    	warning_log.println(LoggerConstant.WARNING.getValue(), "I want to go warning.log");
	    	
	    	//各个文件记录不同重要程度的信息是么？
	    	err_log = filter(LoggerConstant.ERROR.getValue(), err_log, nop());
	    	warning_log = filter(LoggerConstant.WARNING.getValue(), warning_log, nop()); 
	    	info_log = filter(LoggerConstant.INFO.getValue(), info_log, nop());
	    	
	    	err_log.println(LoggerConstant.WARNING.getValue(), "I will not goto error.log");
	    	err_log.println(LoggerConstant.ERROR.getValue(), "I do filtered to error.log");
	    	
	    	Logger logger = sequence(err_log, warning_log, info_log);
	    	logger.println(LoggerConstant.INFO.getValue(), "I finally will be sequenced to info.log");
	    	
	    	logger = timestamp(logger);
	    	logger.println(LoggerConstant.ERROR.getValue(), "I have timestamp to error.log");
	    	
	    	Logger std_logger = writer(new PrintWriter(System.out, true)); 
	    	std_logger = ignore(LoggerConstant.ERROR.getValue(), std_logger,logger); 
	    	std_logger.println(LoggerConstant.INFO.getValue(), "I have timestamp to info.log, console also print out");
	    	
	    	
		}
	    
	    



	    
	    // finally close 
	    if(errorWriter !=null) {
			errorWriter.close();
		}
	    if(infoWriter !=null) {
			infoWriter.close();
		}
	    if(warningWriter !=null) {
			warningWriter.close();
		}
	}
}