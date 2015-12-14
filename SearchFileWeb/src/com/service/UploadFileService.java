package com.service;


import com.bean.FileI;
import com.dao.UploadDao;
import com.dao.impl.UploadDaoImpl;
import com.exception.FileExistException;

public class UploadFileService {
    private UploadDao dao = new UploadDaoImpl();

    // 上传,将信息保存到xml中
    public void saveUploadFileInfo(FileI file) throws FileExistException {
        boolean flag = dao.find(file.getFilename());
        if (flag) {
            throw new FileExistException();
        } else {
            file.setArrfilename(file.getArrfilename());
            file.setSuffix(file.getSuffix());
            dao.add(file);
        }
    }

    //通过转变成数字的名字获取原本的文件名
    public String getUploadBeforeFilename(String arrfilename){
        return dao.getFilenameByArrfileName(arrfilename);
    }


}
