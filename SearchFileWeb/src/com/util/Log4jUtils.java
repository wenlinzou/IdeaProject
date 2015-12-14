package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jUtils extends HttpServlet {
	private static final long serialVersionUID = 1l;
	private static Logger logger = Logger.getLogger(Log4jUtils.class);
	
	public void init(){
		String path = this.getServletContext().getRealPath("/");
		String file = this.getInitParameter("log4j_init_path");
		String logFile = this.getInitParameter("log4j_file_path");
System.out.println("path "+path);		
System.out.println("file "+path);		
System.out.println("logFile "+path);		
System.out.println("pathFile  "+path+file);
		if(null != file){
			Properties prop = new Properties();
			try {
				//加载log4j.properties
				prop.load(new FileInputStream(path+file));
				//设置日志文件的输出路径
				String logPath = path+logFile+prop.getProperty("log4j.appender.R.File").toString();
				logPath = logPath.replace("\\", "/");
//				prop.setProperty("log4j.appender.R.File", path+logFile+prop.getProperty("log4j.appender.R.File"));
				prop.setProperty("log4j.appender.R.File", logPath);
System.out.println("logPath "+logPath);				
				//加载配置项
				PropertyConfigurator.configure(prop);
			} catch (IOException e) {
				logger.info("初始化log4j日志输入路径异常，请检查web.xml参数配置是否正常，异常发生在"+this.getClass().getName()+"类的public void init()方法，异常的愿意是："+e.getMessage(), e.fillInStackTrace());
System.out.println("初始化log4j日志输入路径异常，请检查web.xml参数配置是否正常，异常发生在"+this.getClass().getName()+"类的public void init()方法，异常的愿意是："+e.getMessage()+ e.fillInStackTrace());
			}
		}
	}
	protected void service(HttpServletRequest request, HttpServletResponse response)    throws ServletException, IOException {  
    }  

}
