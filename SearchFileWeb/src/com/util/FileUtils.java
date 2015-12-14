package com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.SequenceInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.util.filter.ContainsWordFilter;
import com.util.filter.FilenameSuffixFilter;
import com.util.filter.MySuffixFilter;
import com.util.filter.PrefixFilter;

public class FileUtils {
	private static int SIZE = 1024*1024;
	
	/**
	 * 获取全路径下的文件名称
	 * @param filePath
	 * @param startChar
	 * @param endChar
	 * @return
	 */
	public static String getFilePathFileName(String filePath, String startChar, String endChar) {
        String fileName = "";
        filePath = filePath.replace("\\", "/");
        int startIndex = filePath.lastIndexOf(startChar);
        int endIndex = filePath.lastIndexOf(endChar);
        if (startIndex < endIndex && startIndex != -1) {
            fileName = filePath.substring(startIndex + 1, endIndex);
        }
        return fileName;
	
	}
	
	//folder has input file
	public static boolean hasFile(String filepath, String filename){
		File folder = new File(filepath);
		String [] children = folder.list();
		boolean hasFile = false;
		for (int i = 0; i < children.length; i++) {
			if(children[i].equals(filename)){
				hasFile = true;
			}
		}
		return hasFile;
	}
	public static void main(String[] args) {
		hasFile("f:/txt","");
	}
	 /**
     * 获取全路径的路径
     *
     * @param fileName
     * @param endChar
     * @return
     */
    public static String getFilepath(String fileName, String endChar) {
        String filePath = "";
        fileName = fileName.replace("\\", "/");
        int endIndex = fileName.lastIndexOf(endChar);
        if (endIndex != -1) {
            filePath = fileName.substring(0, endIndex + 1);
        }
        return filePath;
    }
    /**
     * 截取全路径下的文件后缀
     *
     * @param filePath
     * @param startChar
     * @return
     */
    public static String getFileSuffix(String filePath, String startChar) {
        String suffix = "";
        filePath = filePath.replace("\\", "/");
        int startIndex = filePath.lastIndexOf(startChar);
        if (startIndex != -1) {
            suffix = filePath.substring(startIndex + 1);
        }
        return suffix;
    }
	/*
	 * 查询文件名及后缀
	 */
	public void searchIngoreNameWithSuffix(File dir, FilenameSuffixFilter nameSuffixFilter, List<String> fileList) {
		myFilter(dir, nameSuffixFilter, fileList);
	}

	/*
	 * 模糊查询文件名
	 */
	public void searchIgnoreFilename(File dir, ContainsWordFilter filter, List<String> fileLists) {
		myFilter(dir, filter, fileLists);
	}
	
	/*
	 * 搜索该目录下包含此后缀名的文件
	 */
	public void accpetSuffix(File dir, MySuffixFilter filter, List<String> fileLists){
		myFilter(dir, filter, fileLists);
	}
	
	
	public List<String> accpetPrefix(File dir, PrefixFilter filter, List<String> fileLists) {
        myFilter(dir, filter, fileLists);
        return fileLists;
    }
	
	//fatherFilter=====================
	public void myFilter(File dir, FilenameFilter filter, List<String> lists){
		File[] files = dir.listFiles();
		
		if(files!=null){
			for(File f:files){
				if(f.isDirectory()){
					myFilter(f, filter, lists);
				}else{
					if(filter.accept(f, f.getName())){
						lists.add(f.getAbsolutePath());
					}
				}
			}
		}
	}




	/*
	 * 搜索该目录下的所有文件
	 */
	public void searchFolderFile(File dir, List<String> fileList) {
		File[] files = dir.listFiles();
		
		if(files!=null){
			for(File f:files){
				if(f.isDirectory()){
					searchFolderFile(f, fileList);
				}else{
					fileList.add(f.getAbsolutePath());
				}
			}
		}
	}
	
	
	/*
	 * 写入到txt
	 */
	public void write2File(List<String> list,File inputFile) throws IOException{
//		List<String> listTemp = new ArrayList<String>();
//		listTemp = lineNum(list, inputFile);
//		list = lineNum(list, inputFile);
		String inputFileName = inputFile.getAbsolutePath();
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(inputFileName,true),"UTF-8");
		BufferedWriter bw = new BufferedWriter(out);
		int i = 1;
		for (Iterator<String> it = list.iterator(); it.hasNext();i++) {
			bw.write(i+": "+it.next()+"\t"+getCurrentDate());
			bw.newLine();
			bw.flush();
		}
		bw.close();
			
	}
	public void write2File(List<String> list,String inputHtmlFile) throws IOException{
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File(inputHtmlFile)),"UTF-8");
		BufferedWriter bw = new BufferedWriter(out);
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			bw.write(it.next());
			bw.newLine();
			bw.flush();
		}
		bw.close();
			
	}
	
	//读取文件-->暂时用户读取html
	public static String readFile(String filename) throws Exception {
		StringBuffer buffer = new StringBuffer("");
//		InputStreamReader in = new InputStreamReader(new FileInputStream(new File(filename)),"UTF-8");
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

	//打印数据带行号 暂未使用
	public List<String> lineNum(List<String> list, File fileLine) throws IOException{
		LineNumberReader liner = new LineNumberReader(new FileReader(fileLine));
		String line = null;
		while((line = liner.readLine())!=null){
			list.add(liner.getLineNumber()+"\t"+line);
		}
		liner.close();
		return list;
	}
	
	//获取当当前系统时间
	public String getCurrentDate(){
		Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置显示格式
		String nowTime="";
		nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
		return nowTime;
	}
	//截取盘符
	public String getDisknameM(String anyname){
		String disknameTemp = anyname;
		int end = anyname.indexOf(":");
		if(end!=-1)
			disknameTemp = anyname.substring(0, end);
		return disknameTemp;
	}
	//截取文件夹名称
	public String getFoldernameM(String anyname){
		String foldernameTemp = anyname;
		int start = anyname.indexOf("\\");
		foldernameTemp = anyname.substring(start+1);
		return foldernameTemp;
	}

	
	//重命名文件
	public boolean renameFile(List<String> lists, String sameName) {
		File[] tempFiles = new File[lists.size()];
		boolean canRename = false;
		for (int i = 0; i < lists.size(); i++) {
			tempFiles[i] = new File(lists.get(i));
			String filename = tempFiles[i].getName();
			String oldname = filename;
			String newName = "";
			if(filename.startsWith(sameName)){
				newName = filename.substring(sameName.length());
			
				boolean rename = tempFiles[i].renameTo(new File(tempFiles[i].getParent(),newName));
System.out.println("ISRename:"+rename+"\toldname:"+oldname+"\tsameName:"+sameName+"\tnewName:"+newName);
				canRename = true;
			}else{
				System.out.println("文件名:" + oldname + " -- 前缀不符合:" + sameName);
				canRename = false;
			}
		}
		return canRename;

	}
	
	//拆分文件
	public void splitFile(File srcFile, File destDir,int split_size, String suffix) throws IOException{
		//1源
		FileInputStream fis = new FileInputStream(srcFile);
		//2目的
		FileOutputStream fos = null;
		byte[] buf = new byte[SIZE*split_size];
		int count = 1;
		int len = 0;
		//3创建一个Properties集合，用于存储文件信息
		Properties prop = new Properties();
		if(!destDir.exists()){
			destDir.mkdir();
		}
		
		//对指定的目录进行判断
		while((len=fis.read(buf))!=-1){
			fos = new FileOutputStream(new File(destDir,(count++)+suffix));
			fos.write(buf, 0, len);
			fos.close();
		}
		File confile = new File(destDir,count+".properties");
		
		//在Properties集合存储一些文件信息
		prop.setProperty("filename", srcFile.getName());
		prop.setProperty("count", count+"");
		prop.setProperty("splitSuffix", suffix);
		
		fos = new FileOutputStream(confile);
		prop.store(fos, "");
		
		fos.close();
		fis.close();
	}
	
	//读取配置文件 重新组合文件
	public void mergeFile(File srcDir, String suffixName) throws IOException {
		//健壮性判断
		if(!srcDir.exists()){
			throw new RuntimeException(srcDir.getName()+"不存在");
		}
		//1判断该目录下是否有配置文件
		String[] names = srcDir.list(new MySuffixFilter(suffixName));
		
		if(names.length!=1){
//			throw new RuntimeException("后缀名为"+suffixName+"的文件不错在或者有多个");
			System.out.println("后缀名为"+suffixName+"的文件不错在或者有多个");
		}
		
		File confile = new File(srcDir, names[0]);
		
		//用读取流和配置文件关联
		FileInputStream fis = new FileInputStream(confile);
		
		Properties prop = new Properties();
		
		prop.load(fis);
		
		//读取文件名称和碎片文件个数
		String filename = prop.getProperty("filename");
		int count = Integer.parseInt(prop.getProperty("count"))-1;
		String splitSuffix = prop.getProperty("splitSuffix");
		
		File[] partFiles = srcDir.listFiles(new MySuffixFilter(splitSuffix));
		if(partFiles.length!=count){
//			throw new RuntimeException("碎片个数错误，不是"+count+"个");
			System.out.println("碎片个数错误，不是"+count+"个");
		}
		for(int i = 0; i < count; i++) {
			File file = new File(srcDir,(i+1)+splitSuffix);
			if(!file.exists()){
//				throw new RuntimeException(file.getName()+"不存在");
				System.out.println(file.getName()+"不存在");
			}
		}
		//到这里基本的合并的目录文件判断完毕
		//合并
		merge(srcDir, filename, count, splitSuffix);
		
		
		
	}

	//合并文件操作
	public void merge(File srcDir, String filename, int count, String splitSuffix) throws IOException {
		List<FileInputStream> list = new ArrayList<FileInputStream>();
		//System.out.println("count:"+count+"\tfilename:"+filename+"\tsrcDir:"+srcDir);
		for(int i=0; i < count; i++){
			list.add(new FileInputStream(new File(srcDir,(i+1)+splitSuffix)));
		}
		
		//获取枚举接口对象
		Enumeration<FileInputStream> en = Collections.enumeration(list);
		
		SequenceInputStream sis =new SequenceInputStream(en);
		
		FileOutputStream fos = new FileOutputStream(new File(srcDir, filename));
		byte[] buf = new byte[1024];
		int len = 0;
		
		while((len=sis.read(buf))!=-1){
			fos.write(buf,0,len);
		}
		fos.close();
		sis.close();
		
	}
	
	//获取该目录下的所有文件名
	public List<String> getFileAllNames(File file){
		List<String> lists = new ArrayList<String>();
		File[] files = file.listFiles();
		for (File f : files) {
			if(file.isDirectory()){
				getFileAllNames(f);
			}else{
				lists.add(f.getName());
			}
		}
		return lists;
		
	}
	
	//替换非法字符
	public String changeIngellUrlName(String urlName){
		urlName = urlName.replace("?", "");
		urlName = urlName.replace("&", "");
		urlName = urlName.replace("=", "");
		return urlName;
	}
	
	//截取输入邮箱由谁发送的品牌 www@qq.com -->qq
	public static String getAtName(String emailname){
		String atname = "";
		int beginIndex = emailname.indexOf("@");
		int endIndex = emailname.lastIndexOf(".");
		atname = emailname.substring(beginIndex+1, endIndex);
		return atname;
	}
	
	//截取指定的两者间字符-->前包后不包
	public static String getUNeedname(String name, char startC, char endC){
		String needname = "";
		int beginIndex = name.lastIndexOf(startC);
		int endIndex = name.lastIndexOf(endC);
		needname = name.substring(beginIndex+1, endIndex);
		return needname;
	}
	
	
	
	
	
	
	/*=============================================================
	 * //列出文件和子目录
	public void showDicAndFile(){
		File dir = new File("f:/txt");  
		String[] children = dir.list();  
		if (children == null) {  
			// Either dir does not exist or is not a directory  
		} else {  
			for (int i=0; i < children.length; i++) {  
				// Get filename of file or directory  
				String filename = children[i];  
			}  
		}  
		
		// It is also possible to filter the list of returned files.  
		// This example does not return any files that start with `.'.  
		FilenameFilter filter = new FilenameFilter() {  
			public boolean accept(File dir, String name) {  
				return !name.startsWith(".");  
			}  
		};  
		children = dir.list(filter);  
		
		// The list of files can also be retrieved as File objects  
		File[] files = dir.listFiles();  
		
		// This filter only returns directories  
		FileFilter fileFilter = new FileFilter() {  
			public boolean accept(File file) {  
				return file.isDirectory();  
			}  
		};  
		files = dir.listFiles(fileFilter);
	}*/
}
