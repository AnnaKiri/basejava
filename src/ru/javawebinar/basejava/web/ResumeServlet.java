package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        if (fullName.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "fullName can not be empty");
            return;
        }

        final boolean isCreate = (uuid == null || uuid.length() == 0);
        Resume r;
        if (isCreate) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.setContacts(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String[] values = request.getParameterValues(type.name());
            if (values.length == Arrays.stream(values).filter(a -> a.trim().equals("")).count()) {
                r.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE, PERSONAL -> r.setSections(type, new TextSection(values[0]));
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        String[] strings = values[0].replace("\r", "").split("\n");
                        List<String> list = Arrays.stream(strings).filter(a -> !a.equals("")).toList();
                        r.setSections(type, new ListTextSection(list));
                    }
                    case EDUCATION, EXPERIENCE -> {
                        List<Company> companies = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (name != null && name.trim().length() != 0) {
                                List<Period> periods = new ArrayList<>();
                                String pfx = type.name() + i;
                                String[] startDates = request.getParameterValues(pfx + "startDate");
                                String[] endDates = request.getParameterValues(pfx + "endDate");
                                String[] positions = request.getParameterValues(pfx + "position");
                                String[] descriptions = request.getParameterValues(pfx + "description");

                                for (int j = 0; j < positions.length; j++) {
                                    if (startDates[j].trim().length() != 0) {
                                        LocalDate endDate = endDates[j].equals("Сейчас") ? LocalDate.MIN :  LocalDate.parse(endDates[j]);
                                        periods.add(new Period(LocalDate.parse(startDates[j]), endDate, positions[j], descriptions[j]));
                                    }
                                }
                                companies.add(new Company(periods, name, urls[i]));
                            }
                        }
                        r.setSections(type, new CompanySection(companies));
                    }
                }
            }
        }
        if (isCreate) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "add" -> r = new Resume();
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "view", "edit" -> r = storage.get(uuid);
            default -> throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }
}

