package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.service.FileService;

public class PrintServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String filename = request.getParameter("filename");
		String diskname = request.getParameter("diskname");
		
		FileService fs = new FileService();
		
		File iPrintFile= new File(diskname, filename); 
		
		if(filename==null || filename.trim().equals("")){
			response.sendRedirect("jump.html");
			return;
		}
		filename+=".txt";
		request.setAttribute("localtion", diskname+":"+filename);
		String localFile = diskname + ":" + filename;
		
		HttpSession session = request.getSession();
		
		List<String> list = (List<String>) session.getAttribute("fileLists");
		
		boolean flag = fs.write2File(list, new File(diskname+":",filename));
		
		if(flag){
			request.setAttribute("ok", "1");
			request.setAttribute("title", "写入磁盘成功");
			request.setAttribute("message", "文件位置 " + localFile + "写入成功!");
		}else{
			request.setAttribute("ok", "-1");
			request.setAttribute("title", "写入磁盘失败");
			request.setAttribute("message", "文件位置 " + localFile + "写入失败!");
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
