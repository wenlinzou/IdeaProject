package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bean.User;
import com.bean.form.RegisterForm;
import com.exception.UserExistException;
import com.service.impl.UserServiceImpl;
import com.util.WebUtils;

public class RegisterServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(RegisterServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1校验提交表单字段的合法性(把表单数据封装到formbean)
		RegisterForm form = WebUtils.request2Bean(request, RegisterForm.class);
		boolean b = form.validate();
		
		
		//2校验失败。调回表单页面，回显失败信息
		if(!b){
			request.setAttribute("form", form);
			request.getRequestDispatcher("/otherInfo/userRegister.jsp").forward(request, response);
			return;
		}
		
		//3如果校验成功，则调用servlet处理注册请求
		User user = new User();
		
		WebUtils.copyBean(form, user);
		user.setId(WebUtils.generateID());
		
		UserServiceImpl service = new UserServiceImpl();
		try {
			service.register(user);
System.out.println("注册成功 ===\n用户名"+user.getUsername()+"\t密码:"+user.getShowpassword());
			logger.info("注册成功 ===\n用户名"+user.getUsername()+"\t密码:"+user.getShowpassword());
		
			//6如果service成功，跳转到网站的全局消息显示页面，为用户注册成功的消息
			request.setAttribute("message", "注册成功！");
			request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
					return;
		} catch (UserExistException e) {
				
			//4如果service处理不成功：并且不成功的原因是注册用户已存在，则调回到注册页面，显示注册用户已存在
			form.getErrors().put("username", "注册用户名已存在");
			request.setAttribute("form", form);
			request.getRequestDispatcher("/otherInfo/userRegister.jsp").forward(request, response);
			
			return;
		}catch(Exception e){
			//5如果service处理不成功，并且不成功原因是其他问题的话，跳转到网站的全局消息显示页面，为用户显示友好错误消息
			request.setAttribute("message", "服务器出现位置错误");
			request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
			return;
		}
				
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
