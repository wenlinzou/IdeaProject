package com.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.util.Xls2K3Utils;
import com.util.Xls2K7Utils;

public class ReadExcelService {

    /**
     * 读取上传Excel 将文件名和内容转换为集合
     * @param folderpath
     * @return excel名称和对应的内容
     * @throws IOException
     */
    public Map<String,List<List<String>>> readExcelMap(String folderpath) throws IOException{
        Map<String,List<List<String>>> map = new HashMap<String, List<List<String>>>();

        List<Object> objLists = new ArrayList<Object>();
        if(null!=folderpath){
            File readFolder = new File(folderpath);
            List<String> excels = getFileAllAbsolutePath(readFolder);
            if(null!=excels){
                for (int i = 0; i < excels.size(); i++) {
                    String strfilename = excels.get(i);
                    String filesuffix = getFileSuffix(strfilename,".");

                    List<List<String>> listList = new ArrayList<List<String>>();
                    //2007
                    if (filesuffix.lastIndexOf("xlsx") != -1) {
                        List<String> temp1 = Xls2K7Utils.getAllInfo(strfilename);

                        Integer index = Xls2K7Utils.getXlsCellSize(strfilename);
                        listList = Xls2K7Utils.getRowList(temp1,index);
                        objLists.add(listList);
                        map.put(strfilename, listList);
                        //文件夹下所有excel文件
                    }else{
                        List<String> temp1 = Xls2K3Utils.getAllInfo(strfilename);

                        Integer index = Xls2K3Utils.getXlsCellSize(strfilename);
                        listList = Xls2K3Utils.getRowList(temp1,index);

                        objLists.add(listList);
                        map.put(strfilename, listList);
                    }
                }
            }
        }
        return map;
    }

    /**
     * 读取excel文件,并判断两者不同
     * @param folderpath
     * @return 拼接好的html代码(两个表格)
     * @throws IOException
     */
    public List<String> arrange2Excel(String folderpath) throws IOException{
        List<String> tableLists = new ArrayList<String>();
        Map<String,List<List<String>>> listMap = readExcelMap(folderpath);
        if(null != listMap){
            //将所有信息保存到map
            String[] excelnames = new String[2];
            List<List<List<String>>> tempListAB = new ArrayList<List<List<String>>>();
            int tempi = 0;
            for(Map.Entry<String,List<List<String>>> entry:listMap.entrySet()){
                String excelname = entry.getKey();
                excelnames[tempi] = excelname;
                tempi ++ ;
                List<List<String>> listList = entry.getValue();
                tempListAB.add(listList);
            }
            //
            if(null!=excelnames && null!=tempListAB){

                List<Map<String,Map<Integer,Boolean>>> listMapAB = judge2Excel2Excel(tempListAB.get(0), excelnames[0], tempListAB.get(1), excelnames[1]);
                List<List<Integer>> tempIndexList = new ArrayList<List<Integer>>();
                for (int i = 0; i < listMapAB.size(); i++) {
                    Map<String,Map<Integer,Boolean>> excelmapAB = listMapAB.get(i);
                    for(Entry<String,Map<Integer,Boolean>> entry:excelmapAB.entrySet()){
                        List<Integer> wrongList = new ArrayList<Integer>();

                        Map<Integer,Boolean> excelAB = entry.getValue();
                        for(Entry<Integer, Boolean> entryAB:excelAB.entrySet()){
                            Integer index = entryAB.getKey();

                            Boolean flag = entryAB.getValue();
                            if(!flag){
                                wrongList.add(index);
                            }
                            //	System.out.println(index + "-" + flag+" ");
                        }
                        tempIndexList.add(wrongList);
                        //System.out.println();
                    }
                    String tableStr = getTableString(tempListAB.get(i), tempIndexList.get(i), excelnames[i], i);
                    tableLists.add(tableStr);
                    System.out.println(tableStr);
                }
            }
        }

        return tableLists;
    }

    /**
     * 比较两个excel内容
     * @param listsA 内容
     * @param excelnameA excel名称
     * @param listsB 内容
     * @param excelnameB excel名称
     * @return excel名称下所有对应正确错误的下标集合
     */
    public List<Map<String,Map<Integer,Boolean>>> judge2Excel2Excel(List<List<String>> listsA, String excelnameA,List<List<String>> listsB, String excelnameB){
        List<Map<String,Map<Integer,Boolean>>> list = new ArrayList<Map<String,Map<Integer,Boolean>>>();

        //比较a和b两个excel内容
        if(null!=listsA && null!=listsB){
            int lengthA = listsA.size();
            int lengthB = listsB.size();
            //excelname wrong index
            if(lengthA>0 && lengthB>0){
                String[] strsA = new String[lengthA];
                for (int i = 0; i < lengthA; i++) {
                    List<String> temparrs = listsA.get(i);
                    strsA[i] = chageListString2StrBuilder(temparrs);
                }

                String[] strsB = new String[lengthB];
                for (int i = 0; i < lengthB; i++) {
                    List<String> temparrs = listsB.get(i);
                    strsB[i] = chageListString2StrBuilder(temparrs);
                }
                Map<String,Map<Integer,Boolean>> mapA = new HashMap<String,Map<Integer,Boolean>>();
                Map<String,Map<Integer,Boolean>> mapB = new HashMap<String,Map<Integer,Boolean>>();

                Map<Integer,Boolean> excelA = new HashMap<Integer,Boolean>();
                Map<Integer,Boolean> excelB = new HashMap<Integer,Boolean>();
                if(lengthA > lengthB){
                    excelA = initExcelMapAB(excelA, lengthA);
                    judge2ExcelMap(lengthA, lengthB, strsA, strsB, excelA, excelB);
                }
                if(lengthA < lengthB){
                    excelB = initExcelMapAB(excelB, lengthB);
                    judge2ExcelMap(lengthB, lengthA, strsB, strsA, excelB, excelA);
                }
                if(lengthA == lengthB){
                    excelA = initExcelMapAB(excelA, lengthA);
                    judge2ExcelMap(lengthA, lengthB, strsA, strsB, excelA, excelB);
                }
                mapA.put(excelnameA, excelA);
                mapB.put(excelnameB, excelB);
                list.add(mapA);
                list.add(mapB);
            }
        }

        return list;
    }

    /**
     * 给两组集合赋值
     * @param lengthA 内循环
     * @param lengthB 外循环
     * @param strsA 文件内容A
     * @param strsB 文件内容B
     * @param excelA 存放标示
     * @param excelB 存放标示
     */
    public void judge2ExcelMap(int lengthA, int lengthB, String[] strsA,String[] strsB, Map<Integer, Boolean> excelA,
                               Map<Integer, Boolean> excelB) {
        for (int i = 0; i < lengthB; i++) {
            String strB = strsB[i];
            for (int j = 0; j < lengthA; j++) {
                String strA = strsA[j];
                //判断两个值
                if(strB.equals(strA)){
                    //记录下excel名称,下标是否正确
                    excelB.put(i, true);
                    excelA.put(j, true);
                    break;
                }else{
                    if(null!=excelA.get(j) && excelA.get(j)){
                        excelA.put(j, true);
                        excelB.put(i, true);
                    }else{
                        excelB.put(i, false);
                        excelA.put(j, false);
                    }
                }
            }
        }
    }

    public Map<Integer,Boolean> initExcelMapAB(Map<Integer,Boolean> map, Integer size){
        for (int i = 0; i < size; i++) {
            map.put(i, false);
        }
        return map;
    }


    /**
     * 将传入的集合转换成字符串
     * @param list
     * @return 字符串
     */
    public String chageListString2StrBuilder(List<String> list){
        String str = null;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
        }
        str = sb.toString();
        return str;
    }

    /**
     * 获取文件下所有文件名称集合
     * @param filepath
     * @return 文件名称集合
     */
    public List<String> getFileAllAbsolutePath(File filepath){
        List<String> lists = new ArrayList<String>();
        File[] files = filepath.listFiles();
        if(null!=files){
            for (File f : files) {
                if(f.isDirectory()){
                    getFileAllAbsolutePath(f);
                }else{
                    lists.add(f.getAbsolutePath());
                }
            }
        }
        return lists;
    }

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
     * 获取文件后缀
     * @param filePath
     * @param startChar
     * @return 文件后缀
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

    public String getTableString(List<List<String>> list){
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        for (int i = 0; i < list.size(); i++) {
            sb.append("<tr>");
            sb.append("<td>").append(i).append("</td>");
            List<String> arrStrs = list.get(i);
            for (int j = 0; j < arrStrs.size(); j++) {
                sb.append("<td>");
                sb.append(arrStrs.get(j));
                sb.append("</td>");
            }
            //System.out.print(list.get(i)+"\n");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
    public String getTableString(List<List<String>> list, List<Integer> wrongList){
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        for (int i = 0; i < list.size(); i++) {
            if(null!=wrongList && wrongList.size()>0){
                for (int k = 0; k < wrongList.size(); k++) {
                    int wrongIndex = wrongList.get(k);
                    if(i == wrongIndex){
                        sb.append("<tr style=\"color:red;font-weight:bolder\">");
                    }else{
                        sb.append("<tr>");
                    }
                }
            }else{
                sb.append("<tr>");
            }
            sb.append("<td>").append(i).append("</td>");
            List<String> arrStrs = list.get(i);
            for (int j = 0; j < arrStrs.size(); j++) {

                sb.append("<td>");
                sb.append(arrStrs.get(j));
                sb.append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    /**
     * 将传入excel内容拼接成html代码(表格)
     * @param list
     * @param wrongList 错误下标集合
     * @param excelTitle excel名称
     * @return html代码
     */
    public String getTableString(List<List<String>> list, List<Integer> wrongList, String excelTitle, int tabid){
        StringBuilder sb = new StringBuilder();
        sb.append("<table id=\"tabid"+tabid+"\">");
        if(null!=list){
            sb.append("<tr>");
            sb.append("<td colspan=\""+list.get(0).size()+"\" style=\"font-weight:bolder\">").append(excelTitle).append("</td>");
            sb.append("</tr>");
        }
        for (int i = 0; i < list.size(); i++) {
            boolean isWrong = false;
            if(null!=wrongList && wrongList.size()>0){
                for (int k = 0; k < wrongList.size(); k++) {
                    int wrongIndex = wrongList.get(k);
                    if(i == wrongIndex){
                        isWrong = true;
                    }
                }
            }else{
                sb.append("<tr>");
            }
            if(isWrong){
                sb.append("<tr style=\"color:red;font-weight:bolder\">");
            }else{
                sb.append("<tr>");
            }
            sb.append("<td>").append(i).append("</td>");
            List<String> arrStrs = list.get(i);
            for (int j = 0; j < arrStrs.size(); j++) {

                sb.append("<td>");
                sb.append(arrStrs.get(j));
                sb.append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
    public void cleanOldFolder(String fileFolder){
        List<String> cleanFolders = getOldFileName(fileFolder);
        if(null!=cleanFolders){
            for (int i = 0; i < cleanFolders.size(); i++) {
                removeOldFile(cleanFolders.get(i));
            }
        }
    }
    public boolean removeOldFile(String fileFolder){
        boolean remove = false;
        File file = new File(fileFolder);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(null!=files){
                for (File f : files) {
                    if(f.isDirectory()){
                        removeOldFile(f.getAbsolutePath());
                    }else{
                        f.delete();
                    }
                }
            }
            file.delete();
        }
        return remove;
    }
    public String getCurrentTimeDay(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");// 可以方便地修改日期格式
        String currentNow = dateFormat.format(now);
        return currentNow;
    }
    public List<String> getOldFileName(String cleanFolder){
        List<String> folders = new ArrayList<String>();
        String nowDay  = getCurrentTimeDay();
//		Integer nowTime = Integer.parseInt(nowDay);
        File allFolder = new File(cleanFolder);
        if(allFolder.isDirectory()){
            File[] files = allFolder.listFiles();
            for (File f : files) {
                if(f.isDirectory()){
                    String tempfilename = f.getName();
                    if(!tempfilename.startsWith(nowDay)){
                        folders.add(f.getAbsolutePath());
                    }
                }
            }
        }

        return folders;
    }
    public static void main(String[] args) {
        String filepathstr = "D:\\Apache_Tomcat\\apache-tomcat-7.0.54\\webapps\\FileUpload\\WEB-INF\\upload\\20151123";
        String filepathstrs = "D:\\Apache_Tomcat\\apache-tomcat-7.0.54\\webapps\\FileUpload\\WEB-INF\\upload\\20151123\\strs.xls";
//		File f = new File(filepathstrs);
        String filename = getFilepath(filepathstrs,"/");
        System.out.println(filename);

        new ReadExcelService().getOldFileName("D:\\Apache_Tomcat\\apache-tomcat-7.0.54\\webapps\\FileUpload\\WEB-INF\\upload");
		/*List<String> lists = new ReadExcelService().getFileAllAbsolutePath(new File(filepathstr));
		for (String string : lists) {
			System.out.println(string);
		}*/
    }
}
