package com.voxeldev.eftstore.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Easter Egg!", value = "/about")
public class RandomServlet extends HttpServlet {
    
    private static final String html =
            "<html><head><title>Easter Egg!</title></head><body>" +
                    "<h1>This website is made by nVoxel!!</h1>" +
                    "<img src=\"http://www.notepad.org/mssucks.gif\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/notepad.gif\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/notepad4.gif\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/browser.gif\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/ani-notepad.gif\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/notepad12.gif\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/notepad6.gif\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/mshplogo.gif\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/notepad-logo3.gif\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/notepad-fulvio.gif\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/bar_notepad.png\">&nbsp;\n" +
                    "<img src=\"http://www.notepad.org/madewithnotepad.gif\"></body></html>";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(html);
    }
}
