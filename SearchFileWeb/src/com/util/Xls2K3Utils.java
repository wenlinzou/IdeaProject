package com.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



/**
 * Created by Pet on 2015-06-30.
 */
public class Xls2K3Utils {


    /**
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
        HSSFWorkbook hSSFWorkbook = new HSSFWorkbook(in);
        for (int i = 0; i < 1; i++) {
            HSSFSheet hSSFSheet = hSSFWorkbook.getSheetAt(i);
            if (null == hSSFSheet) {
                continue;
            }
            // 循环行row
            for (int j = startRowIndex+1; j <= hSSFSheet.getLastRowNum(); j++) {
                HSSFRow hSSFRow = hSSFSheet.getRow(j);
                if (null == hSSFRow) {
                    continue;
                }
                for (int k = 0; k < hSSFRow.getLastCellNum(); k++) {
                    HSSFCell temp = hSSFRow.getCell(k);
                    String cellV = temp.toString();

                    if(cellV!=null && !"".equals(cellV)){
                        lists.add(cellV);
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
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
        // loop sheet
        // 此处只循环sheet1
        int startRowIndex = 0;
        int max = 0;
        for (int i = 0; i < 1; i++) {
            HSSFSheet hSSFSheet = hssfWorkbook.getSheetAt(i);
            if (null == hSSFSheet) {
                continue;
            }
            // 循环行row
            for (int j = startRowIndex; j <= hSSFSheet.getLastRowNum(); j++) {
                HSSFRow hSSFRow = hSSFSheet.getRow(j);
                if (null == hSSFRow) {
                    continue;
                }
                index = (int) hSSFRow.getLastCellNum();
                if(index > max){
                    max = index;
                }
            }
        }
//		return list;
        return max;
    }
}
