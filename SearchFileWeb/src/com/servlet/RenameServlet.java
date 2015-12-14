package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.FileService;

public class RenameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		FileService fs = new FileService();
		
		String checkedName = request.getParameter("rename");
		String sameName = request.getParameter("samename");
		if(sameName==null || sameName.equals("")){
			response.sendRedirect("search.action");
			return;
		}
		
		String[] temp = checkedName.split(",");
		for (int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].replace("\\", "/");
			
		}
		List<String> lists = new ArrayList<String>();
		lists = Arrays.asList(temp);
		boolean canRename = fs.renameFile(lists, sameName);
		if(canRename){
			request.setAttribute("ok", "1");
			request.setAttribute("title", "修改文件名成功");
			request.setAttribute("message", "修改相同文件名\"" + sameName + "\"成功!");
		}else{
			request.setAttribute("ok", "-1");
			request.setAttribute("title", "修改文件名失败");
			request.setAttribute("message", "修改相同文件名\"" + sameName + "\"失败!");
		}
		System.gc();
		request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
