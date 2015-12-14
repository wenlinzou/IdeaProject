package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtils {
	public static <T> T request2Bean(HttpServletRequest request,
			Class<T> beanClazz){
		try {
			// 创建要封装的bean
			T bean = beanClazz.newInstance();

			// 把数据加入bean中
			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement(); // username
				String value = request.getParameter(name); // aaa
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void copyBean(Object src, Object dest) {
		//注册日期转换
		ConvertUtils.register(new Converter() {

			public Object convert(Class type, Object value) {
				if (value == null) {
					return null;
				}
				String str = (String) value;
				if (str.trim().equals("")) {
					return null;
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return sdf.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}

			}
		}, Date.class);

		try {
			BeanUtils.copyProperties(dest, src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//产生全球唯一ID
	public static String generateID(){
		
		return UUID.randomUUID().toString();
	}
}
