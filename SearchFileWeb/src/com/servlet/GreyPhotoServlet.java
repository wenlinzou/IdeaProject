package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.FileService;

public class GreyPhotoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imgpath = request.getParameter("imgPath");
		String webName = request.getContextPath();
		
		System.out.println("webName:"+webName);
        if (imgpath == null) {
            System.out.println("is null");
            return;
        }
        String method = request.getMethod();
        FileService fileService = new FileService();
        
        //get方式提交
        if ("GET".equals(method)) {
            byte[] bs = imgpath.getBytes("ISO8859-1");
            imgpath = new String(bs, "UTF-8");
            if(imgpath.endsWith("Copy.jpg")){
            	String filename = fileService.getFileName(imgpath);
            	filename = filename.substring(0,filename.lastIndexOf("C"));
            	request.setAttribute("filename", filename);
            	System.out.println(filename);
            	request.setAttribute("title", "显示彩色图片");
            	request.getRequestDispatcher("otherInfo/photoGrey.jsp").forward(request, response);
            	return;
            }
            boolean copyPhotoExist = fileService.checkPhotoCopyExist(imgpath, webName);
            if(copyPhotoExist){
            	String filename = fileService.getFileName(imgpath);
            	request.setAttribute("filename", filename);
            	request.setAttribute("title", "显示黑白图片");
            	request.setAttribute("existPhoto", "1");
            	request.getRequestDispatcher("otherInfo/photoGrey.jsp").forward(request, response);
            	return;
            }
            boolean pass = fileService.getUrlPhotoMakeItGrey(imgpath,webName);
            if (pass) {
            	String filename = fileService.getFileName(imgpath);
            	request.setAttribute("filename", filename);
            	request.setAttribute("title", "显示彩色图片");
                request.setAttribute("existPhoto", "1");
                request.getRequestDispatcher("otherInfo/photoGrey.jsp").forward(request, response);
            }
//            PhotoUtils.saveToFile(imgpath);
        }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
