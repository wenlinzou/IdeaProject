package com.bean;

public class FileI {
    private String id;
    private String diskname;
    private String foldername;
    private String filename;
    private String suffix;

    private String arrfilename;//处理后的了filename,用于播放中文不支持

    public FileI() {
        super();

    }
    public FileI(String diskname, String foldername, String filename,
                 String suffix) {
        super();
        this.diskname = diskname;
        this.foldername = foldername;
        this.filename = filename;
        this.suffix = suffix;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getArrfilename() {
        return arrfilename;
    }
    public void setArrfilename(String arrfilename) {
        this.arrfilename = arrfilename;
    }
    public String getDiskname() {
        return diskname;
    }
    public void setDiskname(String diskname) {
        this.diskname = diskname;
    }
    public String getFoldername() {
        return foldername;
    }
    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
