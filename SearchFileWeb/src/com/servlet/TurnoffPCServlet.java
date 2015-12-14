package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.service.PCService;

public class TurnoffPCServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(TurnoffPCServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String tempTime = request.getParameter("turnoffTime");
		tempTime = tempTime.trim();
System.out.println("tempTime:"+tempTime);
		logger.info("tempTime:"+tempTime);
		
		double time = Double.parseDouble(tempTime);
		
		
		PCService ps = new PCService();
		String info = "";
		if(time>0){
			ps.turnOffPC(time);
			info = "<p style='color:red;font-weight:blod;'>将在"+(int)(time)+"分钟后关机</p>";
		}else{
			ps.cancelOffPC();
			info = "<p style='color:green;font-weight:blod;'>取消关机成功!</p>";
		}
		request.setAttribute("turnOffSuccess", info);
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
