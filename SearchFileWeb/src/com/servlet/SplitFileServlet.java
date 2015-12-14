package com.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.service.FileService;

public class SplitFileServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(SplitFileServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String fileSplitPath = request.getParameter("splitFilePath");
		String sizeStr = request.getParameter("sizename");
		String suffixname = request.getParameter("suffixname");
		String putPath = request.getParameter("dirname");
		
		int size = Integer.parseInt(sizeStr);
		
System.out.println("filesplitPath:"+fileSplitPath);	
		logger.info("filesplitPath:"+fileSplitPath);
		
		FileService fs = new FileService();
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileSplitPath);
			if((size*1000) > fis.available()){
				request.setAttribute("boundsSize", "输入分解大小超过文件大小!");
System.out.println(fis.available()+" "+"输入分解大小超过文件大小!");
			logger.info(fis.available()+" "+"输入分解大小超过文件大小!");
			
//				return;
			}
			
System.out.println(fis.available()/1024);
			logger.info(fis.available()/1024);
			
			boolean flag = fs.splitFile(new File(fileSplitPath), new File(putPath), size, suffixname);
			if(flag){
				request.setAttribute("ok", "1");
				request.setAttribute("title", "拆分文件成功");
				request.setAttribute("message", "拆分文件 "+ fileSplitPath +"成功!文件位于 " + putPath);
			}else{
				request.setAttribute("ok", "-1");
				request.setAttribute("title", "拆分文件失败");
				request.setAttribute("message", "拆分文件 "+ fileSplitPath +"失败!");
			}
			System.gc();
			request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("系统找不到指定的文件"+fileSplitPath);
			request.setAttribute("ok", "-1");
			request.setAttribute("title", "拆分文件失败");
			request.setAttribute("message", "拆分文件 "+ fileSplitPath +"失败!");
			System.gc();
			request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
