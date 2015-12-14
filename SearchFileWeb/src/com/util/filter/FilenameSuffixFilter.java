package com.util.filter;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameSuffixFilter implements FilenameFilter{
	private String filename;
	private String suffix;
	
	public FilenameSuffixFilter(String filename, String suffix){
		this.filename = filename;
		this.suffix = suffix;
	}
	@Override
	public boolean accept(File dir, String name) {
		
//		return name.contains(filename) && name.endsWith(suffix);
		return name.contains(filename) && name.contains(suffix);
	}

}
