package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class PlayServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(PlayServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String myUrl = request.getParameter("myUrl");
		if(null==myUrl || "".equals(myUrl)){
			request.getRequestDispatcher("/WEB-INF/notfound.html").forward(request, response);
			return;
		}
		byte [] bs = myUrl.getBytes("ISO8859-1");
		myUrl = new String(bs,"UTF-8");
		
		myUrl = myUrl.replace("\\", "/");
System.out.println("PlayServlet myUrl:"+myUrl);	
		logger.info("PlayServlet myUrl:"+myUrl);
//myUrl = "//f:/w/d"; 
		request.setAttribute("playUrl", myUrl);
		String suffixStr = myUrl;
		int endIndex = suffixStr.lastIndexOf(".");
		//.properties
		if(endIndex!=-1)
			suffixStr = suffixStr.substring(endIndex);
		
		HttpSession sessionPlay = request.getSession();
		sessionPlay.setAttribute("iPlay", myUrl);
		request.setAttribute("suffixStr", suffixStr);
		request.getRequestDispatcher("/WEB-INF/jsp/play.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
