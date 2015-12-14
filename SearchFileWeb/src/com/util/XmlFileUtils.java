package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlFileUtils {
	private static String filePath;
	static{
		filePath = XmlUtils.class.getClassLoader().getResource("uploadfile.xml").getPath();
	}
	
	public static Document getDocument() throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(filePath));
		return document;
	}
	
	public static void wirte2XML(Document document) throws IOException{
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		
		XMLWriter writer = new XMLWriter(new FileOutputStream(filePath), format);
		writer.write(document);
		writer.close();
	}
}
