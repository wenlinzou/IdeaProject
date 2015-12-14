package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.FileService;

public class Html2PdfServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String htmlUrl = request.getParameter("htmlUrl");
		String pdfPath = request.getParameter("pdfPath");
		
		
		if(htmlUrl==null || htmlUrl.trim().equals("")){
			return;
		}
		FileService fs = new FileService();
		//html to local html
		boolean flag = fs.htmlURLTransLocal(htmlUrl, pdfPath);
		if(flag){
			request.setAttribute("ok", "1");
			request.setAttribute("title", "HTML2PDF成功");
			request.setAttribute("message", "HTML转换PDF成功!文件位于 "+pdfPath);
		}else{
			request.setAttribute("ok", "-1");
			request.setAttribute("title", "HTML2PDF失败");
			request.setAttribute("message", "HTML转换PDF失败!");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
