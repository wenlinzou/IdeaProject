package com.util.filter;

import java.io.File;

public class MySuffixFilter implements java.io.FilenameFilter{

	private String suffix;
	public MySuffixFilter(String suffix){
		this.suffix = suffix;
	}
	@Override
	public boolean accept(File dir, String name) {
//		return name.endsWith(suffix);
		return name.contains(suffix);
	}

}
