package com.dao.impl;

import com.util.XmlFileUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import com.bean.FileI;
import com.dao.UploadDao;

public class UploadDaoImpl implements UploadDao{

    public boolean find(String filename) {
        try {
            Document document = XmlFileUtils.getDocument();
            Element e = (Element)document.selectSingleNode("//file[@filename='"+filename+"']");
            if(e==null){
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getFilenameByArrfileName(String arrfilename){
        try {
            String filename = "";
            Document document = XmlFileUtils.getDocument();
            Element e = (Element)document.selectSingleNode("//file[@arrfilename='"+arrfilename+"']");
            if(e==null)
                return filename;
            filename = e.attributeValue("filename");
            return filename;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(FileI file) {
        try {
            Document document = XmlFileUtils.getDocument();
            Element rootE = document.getRootElement();

            Element file_tag = rootE.addElement("file");
            file_tag.setAttributeValue("id",file.getId());
            file_tag.setAttributeValue("filename",file.getFilename());
            file_tag.setAttributeValue("arrfilename",file.getArrfilename());
            file_tag.setAttributeValue("suffix",file.getSuffix());

            XmlFileUtils.wirte2XML(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
