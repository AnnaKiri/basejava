package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private static final String DUPLICATE_KEY_ERROR_STATE = "23505";
    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String query, String uuid, ABlockOfCode <T> aBlockOfCode) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return aBlockOfCode.execute(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals(DUPLICATE_KEY_ERROR_STATE)) {
                throw  new ExistStorageException(uuid);
            }
            throw new StorageException(e);
        }
    }
}
