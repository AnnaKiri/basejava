package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = Config.get().getStorage();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + "!");

        List<Resume> list = storage.getAllSorted();
        response.getWriter().write("<table>\n" + "  <tr>\n");
        response.getWriter().write("<th>uuid</th>\n" + "<th>full_name</th>\n" + "</tr>\n");
        for (Resume resume : list) {
            response.getWriter().write("<tr>\n" + "<td>" + resume.getUuid() + "</td>\n");
            response.getWriter().write("<td>" + resume.getFullName() + "</td>\n" + "</tr>\n");
        }
        response.getWriter().write("</table>");
    }

}

