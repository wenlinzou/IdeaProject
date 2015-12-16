package com.servlet;

import com.service.FileService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Pet on 2015-12-16.
 */
public class SaveNoteServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(SaveNoteServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        FileService fileService = new FileService();
        String noteInfo = request.getParameter("content");
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
            fileService.writeNode(notepath, noteInfo);
            logger.info("saveNoteInfo:" + noteInfo);
            request.setAttribute("ok", "1");
            request.setAttribute("title", "保存笔记");
            request.setAttribute("message", "保存笔记成功!");
            System.gc();
            request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);

        } catch (IOException e) {
            request.setAttribute("ok", "-1");
            request.setAttribute("title", "保存笔记");
            request.setAttribute("message", "保存笔记失败!");
            System.gc();
            request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
