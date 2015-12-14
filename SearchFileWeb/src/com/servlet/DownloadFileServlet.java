package com.servlet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DownloadFileServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(DownloadFileServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String path = this.getServletContext().getRealPath("/resource/download/call_show.mp4");
		String path = request.getParameter("filepath");

		byte [] bs = path.getBytes("ISO8859-1");
		path = new String(bs,"UTF-8");
		
		path = path.replace("\\", "/");
System.out.println("Download path - "+path);
		logger.info("Download path - "+path);

		String filename = path.substring(path.lastIndexOf("/") + 1);
		//通知浏览器接收
		//如果下载文件时中文文件,则文件名需要经过url编码
		//e.g.URLEncoder.encode(filename, "UTF-8")
		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(filename, "UTF-8"));
		
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(path);
			int len = 0;
			byte buffer[] = new byte[1024];
			out = response.getOutputStream();
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
