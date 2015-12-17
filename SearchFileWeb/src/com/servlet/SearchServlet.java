package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bean.FileI;
import com.bean.Page;
import com.service.FileService;
import com.util.PageUtils;

public class SearchServlet extends HttpServlet{
	private static Logger logger = Logger.getLogger(SearchServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		// 创建一个Cookie,包括(key,value).在服务器端创建
//	     Cookie cookie = new Cookie("cookieName", "cookieValue");
	     
	     // 设置Cookie的生命周期,如果设置为负值的话,关闭浏览器就失效.
//	     cookie.setMaxAge(60*60*24*365);
	     //单位是秒，默认情况下是不保存的
	     //如果是0，则意味着删除该cookie
	     //有的浏览器在关闭时会自动检查它所创建的cookie是否过期
	     //如果过期，则将它删除
//	  	 res.addCookie(myCookie);       ///添加到客户端
	     
	     // 设置Cookie路径,不设置的话为当前路径(对于Servlet来说为request.getContextPath() + web.xml里配置的该Servlet的url-pattern路径部分)
	     // cookie.setPath("/"); 
	   
	     // 输出Cookie
//	     response.addCookie(cookie);
	     
		
		String diskname = request.getParameter("diskname");
		String foldername = request.getParameter("foldername");
		String filename = request.getParameter("filename");
		String suffix = request.getParameter("suffix");
System.out.println(diskname+" fold:"+foldername+" file:"+filename+" suf:"+suffix);
		logger.info(diskname+" fold:"+foldername+" file:"+filename+" suf:"+suffix);
		
		FileI iFile = new FileI();
		FileService ss = new FileService();
		List<String> fileLists = new ArrayList<String>();
		
		Page page = new Page();
		String currentPage = request.getParameter("page");
		page.setCurrentPage(Integer.parseInt(currentPage==null||currentPage.equals("")?"1":currentPage));
		page.setPageSize(20);
		
		//当前页码
		int currPage = 0;
		//总页数
		int totalPage = 0;
		//显示条数
		int pageSize = 0;
		//总记录数
		int totalCount = 0;
		
		Object fileObj = session.getAttribute("iFile");
		
		FileI f = null;
		boolean hasFileISession = false;
		
		//当from无值 取session
		if(null == diskname && null==foldername && null==filename && null==suffix) {
			if(fileObj instanceof FileI){
				f = (FileI)fileObj;
				iFile = f;
			}
			hasFileISession = true;
		} else{
			hasFileISession = false;
		}
			
		//session not null and fileObje!=f
		if(hasFileISession) {
System.out.println("-- has session no create --");	
			logger.info("-- has session no create --");
			
			//取sessionFileLists
			List<String> inputSessionList = (List<String>) session.getAttribute("sessionFileLists");
			fileLists = ss.queryFileListBySession(inputSessionList, page);
			
			currPage = page.getCurrentPage();
			totalPage = page.getTotalPage();
			if(currPage <= 1) {
				currPage = 1;
			}else if(currPage >= totalPage) {
				currPage = totalPage;
			}
			pageSize = page.getPageSize();
			totalCount = page.getTotalCount();
				
		}else{
System.out.println("-- no session createing --");
			logger.info("-- no session createing --");
			
			//session is null by filefilter maybe this is first so u need input all filelist in session 
			//first remove allfilelist session
//			session.removeAttribute("sessionFileLists");
			
			iFile.setDiskname(diskname);
			iFile.setFilename(filename);
			iFile.setFoldername(foldername);
			iFile.setSuffix(suffix);
			
			//将filelist放入session中,page方式取自session-->alllists
			List<String> inputSessionList = new ArrayList<String>();
			inputSessionList = ss.queryFileLists(new FileI(iFile.getDiskname(), iFile.getFoldername(), iFile.getFilename(), iFile.getSuffix()));
			session.setAttribute("sessionFileLists", inputSessionList);
			
			fileLists = ss.queryFileListBySession(inputSessionList, page);

			currPage = page.getCurrentPage();
			totalPage = page.getTotalPage();
			if (currPage <= 1) {
				currPage = 1;
			}else if (currPage >= totalPage) {
				currPage = totalPage;
			}
			pageSize = page.getPageSize();
			totalCount = page.getTotalCount();
System.out.println("file is null");	
			logger.info("file is null");
			
		}
		
		//定义是否搜索到内容的标记
		int flag = -1;
		if(fileLists==null || fileLists.size()<1) {
			StringBuilder sb = new StringBuilder();
			sb.append("<center>没能搜索到");
			if(filename!=null && !filename.trim().equals("")){
				sb.append("文件名为:<p style='color:red;font-size:30px;'>").append(filename).append("</p>");
			}
			if(suffix!=null && !suffix.trim().equals("")){
				sb.append("后缀为:<p style='color:green;font-size:30px;'>")
				.append(suffix)
				.append("</p>");
			}
			sb.append("的文件!</center>");
			
			fileLists.add(sb.toString());
			flag = 1;//没有内容
		}
		String fullStr = fileLists.get(0).toString();
		if("搜索上限已到!".equals(fullStr)){
			request.setAttribute("fullStr", "<center><p style='color:red;font-size:30px;'>"+fullStr+"!</p></center>");
			request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
			return;
		}
//		if(session.getAttribute("fileLists")==null)
//			session.setAttribute("fileLists", fileLists);
		request.setAttribute("fileLists", fileLists);
		session.setAttribute("iFile", iFile);
		request.setAttribute("page", currPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("flag", flag);

		String osname = System.getProperty("os.name");
		int startIndex = osname.indexOf(" ");
		if(startIndex>0){
			osname = osname.substring(0,startIndex);
		}
		session.setAttribute("osname", osname);

		request.getRequestDispatcher("/WEB-INF/jsp/searchSuccess1.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
