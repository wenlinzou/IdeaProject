package com.servlet;

import com.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Pet on 2015-12-16.
 */
public class LoadNoteServlet  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileService fileService = new FileService();
        String path =  this.getServletContext().getRealPath("/WEB-INF/node");
        String noteFileName = "node.txt";
        String notepath = path + System.getProperty("file.separator") + noteFileName;
        try {
            File noteFile = new File(path);
            if(!noteFile.exists()){
                noteFile.mkdir();
            }
            File note = new File(noteFile, noteFileName);
            if(!note.exists()){
                note.createNewFile();
            }
            String noteInfo = fileService.readNode(notepath);
            request.setAttribute("noteInfo", noteInfo);
            request.getRequestDispatcher("/otherInfo/note.jsp").forward(request, response);

        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("noteInfo", "读取笔记失败!");
            request.getRequestDispatcher("/otherInfo/note.jsp").forward(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
