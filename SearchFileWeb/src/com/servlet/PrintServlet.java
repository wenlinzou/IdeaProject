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
		String OSNAME = System.getProperty("os.name");
		String filename = request.getParameter("filename");
		String diskname = request.getParameter("diskname");
		
		FileService fs = new FileService();
		
		File iPrintFile= new File(diskname, filename); 
		
		if(filename==null || filename.trim().equals("")){
			response.sendRedirect("jump.html");
			return;
		}
		filename+=".txt";
		String localFile = "";
		if("Windows".startsWith(OSNAME)) {
			request.setAttribute("localtion", diskname + ":" + filename);
			localFile = diskname + ":" + filename;
		}else{
			request.setAttribute("localtion", diskname + System.getProperty("file.separator") + filename);
			localFile = diskname + System.getProperty("file.separator") + filename;
		}
		
		HttpSession session = request.getSession();
		
		List<String> list = (List<String>) session.getAttribute("sessionFileLists");

		boolean flag = false;
		if(null!=list) {

			if(OSNAME.startsWith("Windows")) {
				String tempFilepath = "";
				int lastIndex = diskname.lastIndexOf(":");
				int startIndex = diskname.indexOf(":");
				if(startIndex!=lastIndex){
					char[] disknames = diskname.toCharArray();
					StringBuilder sb = new StringBuilder();

					for(int i=0;i<disknames.length;i++){
						if(i!=lastIndex){
							sb.append(disknames[i]);
						}
					}

				}
				tempFilepath = diskname + System.getProperty("file.separator") + filename;

				File writeFile = new File(tempFilepath);
				if(!writeFile.exists()){
					writeFile.mkdir();
					writeFile.createNewFile();
				}
				flag = fs.write2File(list, writeFile);
			}else{
				File writeFile = new File(diskname + System.getProperty("file.separator")+filename);
				if(!writeFile.exists()){
					writeFile.mkdir();
					writeFile.createNewFile();
				}
				flag = fs.write2File(list, writeFile);
			}
		}
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
