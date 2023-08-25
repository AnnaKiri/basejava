package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.FillResume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    public final SqlHelper helper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.helper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        helper.execute("DELETE FROM resume", null, ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return helper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }

            getInfo(conn, resume, "contact", this::addContact);
            getInfo(conn, resume, "section", this::addSection);

            return resume;
        });
    }

    @Override
    public void update(Resume resume) {
        helper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteFromTable(resume, "contact");
            saveContacts(conn, resume);
            deleteFromTable(resume, "section");
            saveSections(conn, resume);
            return null;
        });

    }

    @Override
    public void save(Resume resume) {
        helper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            saveContacts(conn, resume);
            saveSections(conn, resume);
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        helper.execute("DELETE FROM resume WHERE uuid = ?", uuid, ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return helper.transactionalExecute(conn -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r ORDER BY full_name, uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    map.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }
            getTable(conn, map, "contact", this::addContact);
            getTable(conn, map, "section", this::addSection);
            return new ArrayList<>(map.values());
        });
    }

    @Override
    public int size() {
        return helper.execute("SELECT COUNT(*) AS total_records FROM resume", null, ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("total_records");
        });
    }

    private void addContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            resume.setContacts(type, value);
        }
    }

    private void addSection(ResultSet rs, Resume resume) throws SQLException {
        String content = rs.getString("content");
        if (content != null) {
            SectionType sectionType = SectionType.valueOf(rs.getString("type"));

            switch (sectionType) {
                case OBJECTIVE, PERSONAL -> resume.setSections(sectionType, new TextSection(content));
                case ACHIEVEMENT, QUALIFICATIONS -> {
                    String[] strings = content.split("\n");
                    List<String> list = Arrays.asList(strings);
                    resume.setSections(sectionType, new ListTextSection(list));
                }
            }
        }
    }

    private void deleteFromTable(Resume resume, String tableName) {
        helper.execute(String.format("DELETE FROM %s WHERE resume_uuid = ?", tableName), null, ps -> {
            ps.setString(1, resume.getUuid());
            ps.execute();
            return null;
        });
    }

    private void saveContacts(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void saveSections(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, content) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : resume.getSections().entrySet()) {
                ps.setString(1, resume.getUuid());
                SectionType sectionType = SectionType.valueOf(e.getKey().name());
                ps.setString(2, String.valueOf(sectionType));

                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> ps.setString(3, ((TextSection) e.getValue()).getDescription());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> list = ((ListTextSection) e.getValue()).getStrings();
                        String description = String.join("\n", list);
                        ps.setString(3, description);
                    }
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void getTable(Connection conn, Map<String, Resume> map, String tableName, FillResume fillResume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(String.format("SELECT * FROM %s", tableName))) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fillResume.action(rs, map.get(rs.getString("resume_uuid")));
            }
        }
    }

    private void getInfo(Connection conn, Resume resume, String tableName, FillResume fillResume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(String.format("SELECT * FROM %s WHERE resume_uuid = ?", tableName))) {
            ps.setString(1, resume.getUuid());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fillResume.action(rs, resume);
            }
        }
    }
}
