package com.util.filter;

import java.io.File;
import java.io.FilenameFilter;

public class ContainsWordFilter implements FilenameFilter{
	private String words;
	public ContainsWordFilter(String words){
		this.words = words;
	}
	@Override
	public boolean accept(File dir, String name) {
		
		return name.contains(words);
	}

}
