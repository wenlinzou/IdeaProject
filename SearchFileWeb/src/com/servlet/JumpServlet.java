package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;

public class JumpServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
//		HttpSession session = request.getSession();
//		IUser user = (IUser) session.getAttribute("user");
//System.out.println(user.getPassword()+"\t=="+user.getUsername());		
//		if(user!=null){
			request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
//			return;
//		}else
//		request.getRequestDispatcher("indexLogin.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
