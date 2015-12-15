package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Created by Pet on 2015-07-02.
 */
public class Xls2K7Utils {




    /**
     * jar
     * poi3.12-20150511.jar
     * poi-ooxml-3.12-20150511.jar
     * poi-ooxml-schemas-3.12-20150511.jar
     * 读取xls的正文内容
     * @param xlsPath
     * @return
     * @throws IOException
     */
    public static List<String> getAllInfo(String xlsPath ) throws IOException{
        List<String> lists = new ArrayList<String>();
        InputStream in = new FileInputStream(xlsPath);
//		int startRowIndex = getStartRowIndex(xlsPath);
        int startRowIndex = 0;
        XSSFWorkbook XSSFWorkbook = new XSSFWorkbook(in);
        for (int i = 0; i < 1; i++) {
            XSSFSheet XSSFSheet = XSSFWorkbook.getSheetAt(i);
            if (null == XSSFSheet) {
                continue;
            }
            // 循环行row
            for (int j = startRowIndex+1; j <= XSSFSheet.getLastRowNum(); j++) {
                XSSFRow XSSFRow = XSSFSheet.getRow(j);
                if (null == XSSFRow) {
                    continue;
                }
                for (int k = 0; k < XSSFRow.getLastCellNum(); k++) {
                    XSSFCell temp = XSSFRow.getCell(k);
                    if(null!=temp){
                        String cellV = temp.toString();
                        //为空的值也存入到
                        if(cellV!=null && !"".equals(cellV)){
                            lists.add(cellV);
                        }
                    }
                }
            }
        }
        return lists;
    }





    /**
     * 将取得的所有数据转换成List<List>
     * @param inputList
     * @param len
     * @return
     */
    public static List<List<String>> getRowList(List<String> inputList, int len){
        List<List<String>> listList = new ArrayList<List<String>>();
        int index = 0;
        for (int i = 0; i < inputList.size(); i++) {
            if(i % len == 0){
                List<String> tempList = new ArrayList<String>();
                for (int j = 0; j < len; j++) {
                    String temp = inputList.get(index);
                    if(null!=temp.trim())
                        tempList.add(temp);
                    index ++;
                }
                if(null!=tempList && tempList.size()>0)
                    listList.add(tempList);
            }
        }
        return listList;
    }
    public static Integer getXlsCellSize(String readXls) throws IOException {
        Integer index = 1;
        InputStream in = new FileInputStream(readXls);
        XSSFWorkbook XSSFWorkbook = new XSSFWorkbook(in);
        // loop sheet
        // 此处只循环sheet1
        int startRowIndex = 0;
        int max = 0;
        for (int i = 0; i < 1; i++) {
            XSSFSheet XSSFSheet = XSSFWorkbook.getSheetAt(i);
            if (null == XSSFSheet) {
                continue;
            }
            // 循环行row
            for (int j = startRowIndex; j <= XSSFSheet.getLastRowNum(); j++) {
                XSSFRow XSSFRow = XSSFSheet.getRow(j);
                if (null == XSSFRow) {
                    continue;
                }
                index = (int) XSSFRow.getLastCellNum();
                if(index > max){
                    max = index;
                }
            }
        }
//		return list;
        return max;
    }

    public static void writeXls(String xlsPath,List<String[]> strs,String coursename) throws IOException{
        FileOutputStream output = new FileOutputStream(new File(xlsPath));  //读取的文件路径
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(String.valueOf(0));
        int len = strs.size();
        int totalLen = len+1;
        for (int i = 0; i < totalLen; i++) {
            XSSFRow row = sheet.createRow(i);
            if(i==0){
                for (int j = 0; j < 3; j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    if(j==0)cell.setCellValue("学号");
                    if(j==1)cell.setCellValue("姓名");
                    if(j==2)cell.setCellValue(coursename);
                }
            }else{
                String[] s = null;
                s = strs.get(i-1);
                for (int j = 0; j < s.length; j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(s[j]);
                }
            }
        }
        wb.write(output);
        output.close();
        System.out.println("over ok");
    }

}
