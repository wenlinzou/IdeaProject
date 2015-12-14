package com.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.service.FileService;

public class MergeFileServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(MergeFileServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String mergeFilePath = request.getParameter("splitFilePath");
System.out.println("MergeFile-servlet:"+mergeFilePath);	
		logger.info("MergeFile-servlet:"+mergeFilePath);
		
		FileService fs = new FileService();
		boolean flag = fs.mergeFile(new File(mergeFilePath));
System.out.println("MergeFile:"+flag);		
		logger.info("MergeFile:"+flag);
		
		if(flag){
			request.setAttribute("ok", "1");
			request.setAttribute("title", "合并文件成功");
			request.setAttribute("message", "合并文件成功!文件位于 " + mergeFilePath);
		}else{
			request.setAttribute("ok", "-1");
			request.setAttribute("title", "合并文件成功");
			request.setAttribute("message", "合并文件 "+ mergeFilePath +"失败!");
		}
		System.gc();
		request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
