package com.dao;

import com.bean.FileI;

public interface UploadDao {
    public void add(FileI file);
    public boolean find(String filename);
    public String getFilenameByArrfileName(String arrfilename);
}
