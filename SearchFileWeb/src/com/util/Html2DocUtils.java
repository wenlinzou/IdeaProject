package com.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 读取文件到word
 * 
 * @author Pet
 * 
 */
public class Html2DocUtils {
	
	// 根据实际情况写路径
	public static boolean writeDocFile(String readpath, String writepath) throws Exception {

		boolean flag = false;
		ByteArrayInputStream bais = null;
		FileOutputStream fos = null;
		
		try {
			if (!"".equals(writepath)) {
				File fileDir = new File(writepath);
				if (fileDir.exists()) {
					String content = FileUtils.readFile(readpath);
					byte b[] = content.getBytes();
					bais = new ByteArrayInputStream(b);
					POIFSFileSystem poifs = new POIFSFileSystem();
					DirectoryEntry directory = poifs.getRoot();
					DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);

					//获取html的名字作为doc的名字
					String docname = FileUtils.getUNeedname(readpath, '/', '.');
System.out.println("docname:"+docname);					
					//写入路径 仅仅是路径writepath e.g f:/txt
System.out.println("wirtepathUtil: "+writepath+"/"+docname+".doc");					
					fos = new FileOutputStream(writepath + "/" + docname + ".doc");
					poifs.writeFilesystem(fos);
					bais.close();
					fos.close();
					flag = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (fos != null)
				fos.close();
			if (bais != null)
				bais.close();
		}
		return flag;

	}
	
	

	public static void main(String[] args) throws Exception {
		new Html2DocUtils().writeWordFile("f:/txt/Servlet获得Http请求,GETPOST.html");
	}
	
	//============暂未使用=====================

	/**
	 * 
	 * 读取html文件到字符串
	 */

	public String readFile(String filename) throws Exception {
		StringBuffer buffer = new StringBuffer("");
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filename));
			buffer = new StringBuffer();
			while (br.ready())
				buffer.append((char) br.read());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				br.close();
		}
		return buffer.toString();
	}
	public boolean writeWordFile(String filepath) throws Exception {

		boolean flag = false;

		ByteArrayInputStream bais = null;

		FileOutputStream fos = null;

		String path = "f:/txt"; // 根据实际情况写路径

		try {
			if (!"".equals(path)) {
				File fileDir = new File(path);
				if (fileDir.exists()) {
					String content = readFile(filepath);
					byte b[] = content.getBytes();
					bais = new ByteArrayInputStream(b);
					POIFSFileSystem poifs = new POIFSFileSystem();
					DirectoryEntry directory = poifs.getRoot();
					DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
					fos = new FileOutputStream(path + "/temp.doc");
					poifs.writeFilesystem(fos);
					bais.close();
					fos.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (fos != null)
				fos.close();
			if (bais != null)
				bais.close();
		}
		return flag;

	}

}
