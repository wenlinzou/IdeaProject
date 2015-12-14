package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class IPUtils {
	public static String getAddressByIP(String ipAddr) {
		try {
			// String strIP = "0.0.0.0";
			//新浪接口：http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=
			//淘宝接口：http://ip.taobao.com/service/getIpInfo.php?ip=[ip地址字串]
			URL url = new URL("http://ip.qq.com/cgi-bin/searchip?searchip1=" + ipAddr);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
			String line = null;
			StringBuffer result = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			reader.close();
			ipAddr = result.substring(result.indexOf("该IP所在地为："));
			ipAddr = ipAddr.substring(ipAddr.indexOf("：") + 1);
			String province = ipAddr.substring(6, ipAddr.indexOf("省"));
			String city = ipAddr.substring(ipAddr.indexOf("省") + 1,	ipAddr.indexOf("市"));
			return (province+city).indexOf(",")==-1 ? (province+city) : "目前暂时没有您的IP信息";
		} catch (IOException e) {
			return "读取失败";
		}
	}
}
