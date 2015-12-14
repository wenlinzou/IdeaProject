package com.util.filter;

import java.io.File;
import java.io.FilenameFilter;

public class PrefixFilter implements FilenameFilter{
	private String prefix;
	
	public PrefixFilter(String prefix){
		this.prefix = prefix;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.startsWith(prefix);
	}

}
