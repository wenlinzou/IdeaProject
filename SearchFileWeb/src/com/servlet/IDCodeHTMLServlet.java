package com.servlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bean.ImageID;
import com.service.impl.UserServiceImpl;

public class IDCodeHTMLServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(IDCodeHTMLServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ImageID imageId = new ImageID();
		imageId.setHeight(30);
		imageId.setWidth(120);
		
		BufferedImage buffImage = new BufferedImage(imageId.getWidth(),imageId.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics graphics = buffImage.getGraphics();
		imageId.setG(graphics);
		
		UserServiceImpl us = new UserServiceImpl();
		String tempCode = us.getImageWordCheck(imageId);
		
System.out.println("TTTWordServlet:"+tempCode);
		logger.info("TTTWordServlet:"+tempCode);

		// 5图形写入浏览器
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		//获得session，并把字符串保存在session中，为后面的对比做基础
		HttpSession session = request.getSession();
		session.setAttribute("wordcheck", tempCode);
		
		OutputStream os=response.getOutputStream();
		ImageIO.write(buffImage, "jpg", os);
		
		response.flushBuffer();
		os.close();
		os=null;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
