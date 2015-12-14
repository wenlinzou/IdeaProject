package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderURLUtils {
	 //发送代数据的HTTP 请求 -->网络爬虫
	
	public List<String> getDataByURL(String urlPath) throws IOException {
//		URL url = new URL("http://192.168.1.100:8080/myweb/mail.html");
		List<String> htmlInfo = new ArrayList<String>();
		
		URL url = null;
//		String mail_reg = "\\w+@\\w+(\\.\\w+)+";
//		String mail_reg = "\\S";
//		Pattern p = Pattern.compile(mail_reg);
		
		try {
			url = new URL(urlPath);
			BufferedReader bufr = new BufferedReader(new InputStreamReader(
					url.openStream(), "UTF-8"));
			String line = null;
			while ((line = bufr.readLine()) != null) {
				htmlInfo.add(line);
				/*htmlInfo.add(m.group());
					Matcher m = p.matcher(line);
					while (m.find()) {
						sb.append(m.group());
					}*/
			}
		} catch (IOException e) {
			System.out.println("找不到 " + urlPath);
		}
		return htmlInfo;
		//document.close();
	}
	public static void main(String[] args) throws Exception {
		SpiderURLUtils s = new SpiderURLUtils();
		List<String> temm = s.getDataByURL("http://www.open-open.com/lib/view/open1402448471822.html");

	}
}
