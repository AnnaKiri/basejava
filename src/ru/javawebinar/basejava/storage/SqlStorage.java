package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper helper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        this.helper = new SqlHelper( () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword) );
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
        return helper.execute("SELECT * FROM resume r WHERE r.uuid =?", uuid, ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void update(Resume resume) {
        helper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?", resume.getUuid(), ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        helper.execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", resume.getUuid(), ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
            return null;
        });

//        } catch (SQLException e) {
//            throw new ExistStorageException(resume.getUuid());
//        }
    }

    @Override
    public void delete(String uuid) {
        helper.execute("DELETE FROM resume WHERE uuid = ?", uuid, ps -> {
            ps.setString(1, uuid);
            ps.executeUpdate();
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return helper.execute("SELECT * FROM resume", null, ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (rs.next()) {
                Resume resume = new Resume(rs.getString("uuid"), rs.getString("full_name"));
                list.add(resume);
            }
            list.sort(Resume.RESUME_COMPARATOR);
            return list;
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
}
