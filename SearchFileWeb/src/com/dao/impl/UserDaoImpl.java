package com.dao.impl;

import java.text.SimpleDateFormat;

import com.util.SendMailUtils;
import com.util.XmlUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import com.bean.SEmail;
import com.bean.User;
import com.dao.UserDao;

public class UserDaoImpl implements UserDao {

    @SuppressWarnings("deprecation")
    @Override
    public void add(User user) {
        try {
            Document document = XmlUtils.getDocument();
            Element rootE = document.getRootElement();

            Element user_tag = rootE.addElement("user");
            user_tag.setAttributeValue("id",user.getId());
            user_tag.setAttributeValue("username",user.getUsername());
            user_tag.setAttributeValue("password",user.getPassword());
            user_tag.setAttributeValue("showpassword",user.getShowpassword());
            user_tag.setAttributeValue("birthday",user.getBirthday()==null?"":user.getBirthday().toLocaleString());

            XmlUtils.wirte2XML(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(String username, String password) {
        try {
            Document document = XmlUtils.getDocument();
            Element e = (Element)document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
            if(e==null){
                return null;
            }
            User user = new User();
            String date = e.attributeValue("birthday");
            if(date == null || date.equals("")){
                user.setBirthday(null);
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                user.setBirthday(sdf.parse(date));
            }
            user.setId(e.attributeValue("id"));
            user.setId(e.attributeValue("username"));
            user.setId(e.attributeValue("password"));
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean find(String username) {
        try {
            Document document = XmlUtils.getDocument();
            Element e = (Element)document.selectSingleNode("//user[@username='"+username+"']");
            if(e==null){
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean sendEmail(SEmail semail) {
        return SendMailUtils.sendEMail(semail);
    }


}
