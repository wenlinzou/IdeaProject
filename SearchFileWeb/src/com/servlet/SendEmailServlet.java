package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bean.SEmail;
import com.service.impl.UserServiceImpl;
import com.util.MD5Utils;
import com.util.FileUtils;

public class SendEmailServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(SendEmailServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		List<String> filelist = new ArrayList<String>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sendToEmailName = request.getParameter("sendToEmailName");
		String sendFromEmailName = username;
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String filename = request.getParameter("filename");
		if(filename!=null && !filename.trim().equals("")){
			filename = "f:/"+filename;
			filelist.add(filename);
		}
		/*int i = 0;
		String filetemp = "";
		do{
			i++;
			filetemp = request.getParameter("filename"+i);
System.out.println("filetemp:"+filetemp);			
			if(filetemp!=null && !filetemp.trim().equals("")){
				filelist.add("f:/"+filetemp);
			}else{
				break;
			}
		}while(filetemp==null);*/
		
		StringBuilder hostname = new StringBuilder();
		hostname.append("smtp");
//		String hostname = "smtp.sina.com";
		//截取
		String hostat = FileUtils.getAtName(username);
		hostname.append(".").append(hostat).append(".com");
		
System.out.println("emailname:"+username+"\tpassword:"+MD5Utils.md5(password)+"\thostname:"+hostname.toString()+"\nTO:"+sendToEmailName+"\nFROM:"+sendFromEmailName
		+"\nSUBJECT:"+subject+"\nCONTENT:"+content+"\nFILENAME:"+(filename==null||filename.equals("")?"无":filename));	
		logger.info("emailname:"+username+"\tpassword:"+MD5Utils.md5(password)+"\thostname:"+hostname.toString()+"\nTO:"+sendToEmailName+"\nFROM:"+sendFromEmailName
		+"\nSUBJECT:"+subject+"\nCONTENT:"+content+"\nFILENAME:"+(filename==null||filename.equals("")?"无":filename));

		SEmail semail = new SEmail();
		semail.setUsername(username);
		semail.setPassword(password);
		semail.setHostname(hostname.toString());
		semail.setSendFromEmailName(sendFromEmailName);
		semail.setSendToEmailName(sendToEmailName);
		semail.setSubject(subject);
		semail.setContent(content);
		
		
		
		semail.setFileList(filelist);
		UserServiceImpl us = new UserServiceImpl();
		boolean flag = us.sendEmail(semail);
System.out.println("发送邮件:"+(flag==true?"成功!":"失败!"+"\n"));		
		logger.info("发送邮件:"+(flag==true?"成功!":"失败!"+"\n"));
		
		if(flag){
			request.setAttribute("ok", "1");
			request.setAttribute("title", "邮件发送成功");
			request.setAttribute("message", sendToEmailName+"发送邮件成功!");
		}else{
			request.setAttribute("ok", "-1");
			request.setAttribute("title", "邮件发送失败");
			request.setAttribute("message", sendToEmailName+"发送邮件失败!");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
