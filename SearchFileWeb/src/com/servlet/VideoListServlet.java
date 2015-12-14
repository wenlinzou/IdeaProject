package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FileI;
import com.service.UploadFileService;

public class VideoListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletContext().getRealPath("/resource/upload");
		String filetype = request.getParameter("filetype");
		UploadFileService uploadService = new UploadFileService();
		List<String> suffixLists = new ArrayList<String>();
		
		if(null == filetype || "".equals(filetype)){
			request.getRequestDispatcher("/WEB-INF/notfound.html").forward(request, response);
			return;
		}
		
System.out.println("filetype "+filetype);
		List<FileI> lists = new ArrayList<FileI>();
		if("img".equals(filetype)){
			lists = jumpImg(filetype, path, uploadService);
			if(null == lists || lists.size() < 1) {
				request.getRequestDispatcher("/notfound.html").forward(request,	response);
				return;
			}
		}else if("mp3".equals(filetype) || "mp4".equals(filetype)){
			lists = jumpAudioVideo(filetype, suffixLists, path, uploadService, request, response);
			if(null == lists || lists.size() < 1) {
				request.getRequestDispatcher("/notfound.html").forward(request,	response);
				return;
			}
		}else if("txt".equals(filetype)){
			lists = jumpTxt(filetype, path, uploadService);
			if(null == lists || lists.size() < 1) {
				request.getRequestDispatcher("/notfound.html").forward(request,	response);
				return;
			}
		}
		
		request.setAttribute("videolist", lists);
		String tempFileType = "";
		if("mp3".equals(filetype)){
			tempFileType = "audio";
		}else if("mp4".equals(filetype)){
			tempFileType = "video";
		}else if("img".equals(filetype)){
			tempFileType = "img";
		}else if("txt".equals(filetype)){
			tempFileType = "txt";
		}
		request.setAttribute("filetype", tempFileType);
		System.out.println(path);
		
		request.getRequestDispatcher("/WEB-INF/jsp/videolist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	public List<FileI> jumpAudioVideo(String filetype, List<String> suffixLists, String path, UploadFileService uploadService, HttpServletRequest request, HttpServletResponse response){
		File f = new File(path);
		File[] files = f.listFiles();
		List<FileI> lists = new ArrayList<FileI>();
		if(null!=files) {
			if (files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						FileI ifile = new FileI();
						String arrfilename = files[i].getName();
						int index = arrfilename.lastIndexOf(".");

						String suffix = "";
						if (index != -1)
							suffix = arrfilename.substring((index + 1));
						suffixLists.add(suffix);
						if (suffix.equals(filetype)) {
							String beforeName = uploadService.getUploadBeforeFilename(arrfilename.substring(0, index));
							System.out.println("beforename: " + beforeName);
							ifile.setFilename(beforeName);
							ifile.setArrfilename(arrfilename);
							lists.add(ifile);
						}
					}

				}
			}
			//check upload folder exist file suffix
			boolean hasFiletype = true;
			int noSuffix = 0;
			for (int i = 0; i < suffixLists.size(); i++) {
				if(!filetype.equals(suffixLists.get(i))){
					noSuffix++;
				}
				if(noSuffix == suffixLists.size()){
					hasFiletype = false;
				}
			}

			/*if(!hasFiletype){
				try {
					request.getRequestDispatcher("/notfound.html").forward(request,	response);
	//				response.sendRedirect("notfound.html");

				} catch (Exception e) {
				}
			}*/
		}

		return lists;
		
	}
	
	public List<FileI> jumpImg(String fileType, String path, UploadFileService uploadService){
		List<FileI> lists = new ArrayList<FileI>();
		if("img".equals(fileType)){
			File f = new File(path);
			File[] files = f.listFiles();
			if(null!=files) {
				if (files.length > 0) {
					for (int i = 0; i < files.length; i++) {
						if (files[i].isFile()) {
							FileI ifile = new FileI();
							String arrfilename = files[i].getName();
							int index = arrfilename.lastIndexOf(".");
							String suffix = "";
							if (index != -1)
								suffix = arrfilename.substring((index + 1));
							String temp_suffix = suffix.toLowerCase();
							if ("jpg".equals(temp_suffix) || "jpeg".equals(temp_suffix) || "png".equals(temp_suffix) || "bmp".equals(temp_suffix) || "gif".equals(temp_suffix)) {
								String beforeName = uploadService.getUploadBeforeFilename(arrfilename.substring(0, index));
								System.out.println("beforename: " + beforeName);
								ifile.setFilename(beforeName);
								ifile.setArrfilename(arrfilename);
								lists.add(ifile);
							}
						}
					}
				}
			}
		}
		return lists;
	}
	
	public List<FileI> jumpTxt(String filetype, String path, UploadFileService uploadService){
		List<FileI> lists = new ArrayList<FileI>();
		if("txt".equals(filetype)){
			File f = new File(path);
			File[] files = f.listFiles();
			if(null!=files) {
				if (files.length > 0) {
					for (int i = 0; i < files.length; i++) {
						if (files[i].isFile()) {
							FileI ifile = new FileI();
							String arrfilename = files[i].getName();
							int index = arrfilename.lastIndexOf(".");
							String suffix = "";
							if (index != -1)
								suffix = arrfilename.substring((index + 1));
							String temp_suffix = suffix.toLowerCase();
							if ("txt".equals(temp_suffix) || "sql".equals(temp_suffix)) {
								String beforeName = uploadService.getUploadBeforeFilename(arrfilename.substring(0, index));
								System.out.println("beforename: " + beforeName);
								ifile.setFilename(beforeName);
								ifile.setArrfilename(arrfilename);
								lists.add(ifile);
							}
						}
					}
				}
			}
		}
		return lists;
	}

}
