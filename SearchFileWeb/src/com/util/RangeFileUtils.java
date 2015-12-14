package com.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http range头 断点续传下载
 * @author Pet
 *
 */
public class RangeFileUtils {
	
	public void methodName(String urlName) throws IOException{
		URL url = new URL(urlName);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		//发送一个range头(tomcat5不支持,6支持)
		conn.setRequestProperty("Range", "byte=5-");
		
		//读取
		InputStream in = conn.getInputStream();
		
		int len = 0;
		byte[] buffer = new byte[1024];
		FileOutputStream out = new FileOutputStream("f:/del.txt", true);
		
		while((len=in.read(buffer))!=-1){
			out.write(buffer);
		}
		
		in.close();
		out.close();
		System.gc();
	}

}
