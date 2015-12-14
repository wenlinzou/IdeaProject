package com.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestHistoryInputFile {
	private void printHistoryInFile(List<String> lists, File file) throws IOException{
		lists = lineNum(file);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for (Iterator<String> it = lists.iterator(); it.hasNext();) {
				bw.write(it.next());
				bw.newLine();
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//打印数据带行号
	public List<String> lineNum(File fileLine) throws IOException{
		LineNumberReader liner = new LineNumberReader(new FileReader(fileLine));
		List<String> lists = new ArrayList<String>();
		String line = null;
		while((line = liner.readLine())!=null){
			lists.add(liner.getLineNumber()+"\t"+line);
		}
		liner.close();
		return lists;
	}
}
