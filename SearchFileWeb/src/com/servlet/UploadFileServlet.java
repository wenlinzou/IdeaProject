package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;

import com.bean.FileI;
import com.service.UploadFileService;
import com.util.DateUtils;
import com.util.FileUtils;
import com.util.WebUtils;

public class UploadFileServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(UploadFileServlet.class);
	
	private String dir_name = "resource/upload";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UploadFileService upFileService = new UploadFileService();
		
		HashMap<String,String> param_hm = new HashMap<String,String>();
		try {
			RequestContext requestContext = new ServletRequestContext(request);
			if (FileUpload.isMultipartContent(requestContext)) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				File temp_file = new File(request.getRealPath("") + "//"+ getDir_name() + "//");
				if(!temp_file.exists())
					temp_file.mkdir();
				
				factory.setRepository(temp_file);
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("utf-8");
				upload.setSizeMax(-1);
				List<FileItem> items = new ArrayList<FileItem>();
				items = upload.parseRequest(request);
				
				
				Iterator<FileItem> it = items.iterator();
				
				ok:while (it.hasNext()) {
					FileItem fileItem = (FileItem) it.next();
					if (fileItem.isFormField()) {
						param_hm.put(fileItem.getFieldName(), new String(fileItem.getString().getBytes("iso8859-1"),"utf-8"));
					} else {
						if (fileItem.getName() != null && fileItem.getSize() != 0) {
							//判断文件是否已经存在
							boolean hasFile = FileUtils.hasFile(temp_file.getAbsolutePath(), fileItem.getName());
							//创建前 判断是否已经存在
							if(hasFile){
								System.out.println("文件已存在!");
								break ok;
							}
							
							File fullFile = new File(fileItem.getName());
							
							String arrFilename = DateUtils.getCurrentTimeAllNo();
							FileI filei = new FileI();
							filei.setArrfilename(arrFilename);
							filei.setId(WebUtils.generateID());
							String uploadFilename = fileItem.getName();
							int endIndex = uploadFilename.lastIndexOf(".");
							if(endIndex!=-1){
								filei.setFilename(uploadFilename.substring(0, endIndex));
								filei.setSuffix(uploadFilename.substring(endIndex+1));
							}
							File newFile = new File(request.getRealPath("")	+ "//" + getDir_name() + "//" + arrFilename+"." + filei.getSuffix());
							
							
							fileItem.write(newFile);
							upFileService.saveUploadFileInfo(filei);
							logger.info(filei.getFilename()+filei.getSuffix());
							
						} else {
							System.out.println("文件没有选择 或 文件内容为空");
						}
					}
				}
			}
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
//System.out.println(param_hm);
		//展现播放视频列表
//		request.getRequestDispatcher("/WEB-INF/jsp/videolist.jsp").forward(request, response);
		
		//跳回首页
		response.sendRedirect("jump.html");
//		response.sendRedirect("videolist.html");
//		return param_hm;
	}

	public void setDir_name(String dir_name) {
		this.dir_name = dir_name;
	}

	public String getDir_name() {
		return dir_name;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
