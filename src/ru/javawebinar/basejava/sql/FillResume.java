package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.model.Resume;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface FillResume {
    void action(ResultSet rs, Resume resume) throws SQLException;
}
