package com.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.bean.SEmail;

public class SendMailUtils {
	public static boolean sendMail(SEmail semail){
		boolean flag = false;
		SimpleEmail email = new SimpleEmail();
//		email.setHostName("smtp.sina.cn");
		email.setHostName(semail.getHostname());
		email.setAuthentication(semail.getUsername(), semail.getPassword());
		email.setCharset("UTF-8");
		try {
			email.addTo(semail.getSendToEmailName());//发送的对象
			email.setFrom(semail.getSendFromEmailName());//必须和Authentication使用的用户相同，否则失败
			email.setSubject(semail.getSubject());//标题
			email.setMsg(semail.getContent());
			email.send();
			flag = true;
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	//添加抄送和附件
	public static boolean sendEMail(SEmail semail){
		// 构造mail session
		Properties props = new Properties() ;
		props.put("mail.smtp.host", semail.getHostname());
		props.put("mail.smtp.auth", "true");
		final String username = semail.getUsername();
		final String password = semail.getContent();
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// 构造MimeMessage 并设定基本的值
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(semail.getSendFromEmailName()));
		 
			
			//msg.addRecipients(Message.RecipientType.TO, address); //这个只能是给一个人发送email
			msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(semail.getSendToEmailName())) ;
			String subject = transferChinese(semail.getSubject());
			msg.setSubject(subject);

			// 构造Multipart
			Multipart mp = new MimeMultipart();

			// 向Multipart添加正文
			MimeBodyPart mbpContent = new MimeBodyPart();
			mbpContent.setContent(semail.getContent(), "text/html;charset=utf-8");
			
			// 向MimeMessage添加（Multipart代表正文）
			mp.addBodyPart(mbpContent);

			// 向Multipart添加附件
			Enumeration<String> efiles = Collections.enumeration(semail.getFileList());
			while (efiles.hasMoreElements()) {

				MimeBodyPart mbpFile = new MimeBodyPart();
//				filename = efiles.nextElement().toString();
				semail.setFilename(efiles.nextElement().toString());
				
				FileDataSource fds = new FileDataSource(semail.getFilename());
				mbpFile.setDataHandler(new DataHandler(fds));
				/*<span style="color: #ff0000;">//这个方法可以解决附件乱码问题。</span>	
*/				
//						String filename= new String(fds.getName().getBytes(),"ISO-8859-1");
				String filename= new String(fds.getName().getBytes(),"ISO-8859-1");
				mbpFile.setFileName(filename);
				// 向MimeMessage添加（Multipart代表附件）
				mp.addBodyPart(mbpFile);

			}

			semail.getFileList().removeAll(semail.getFileList());
			//.removeAllElements()
			
			// 向Multipart添加MimeMessage
			msg.setContent(mp);
			msg.setSentDate(new Date());
			//保存邮件
			msg.saveChanges() ;

			// 发送邮件
			Transport transport = session.getTransport("smtp");
			//连接服务器的邮箱
			transport.connect(semail.getHostname(), semail.getUsername(), semail.getPassword());
			//把邮件发送出去
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (Exception mex) {
			mex.printStackTrace();
System.out.println("发送邮件--连接异常>>>");			
			return false;
		}
		return true;
	}
	
	/**
	 * <br>
	 * 方法说明：往附件组合中添加附件 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	/*public void attachfile(String fname) {
		file.addElement(fname);
	}*/
	public List<String> attachfile(String fname) {
		List<String> filelist = new ArrayList<String>();
		filelist.add(fname);
		return filelist;
	}
	/**
	 * 方法说明：把主题转换为中文 <br>
	 * 输入参数：String strText <br>
	 * 返回类型：
	 */
	public static String transferChinese(String strText) {
		try {
			strText = MimeUtility.encodeText(new String(strText.getBytes(),
//					"UTF-8"), "UTF-8", "B");使用GB2312解决中文乱码
					"GB2312"), "GB2312", "B");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strText;
	}
}
