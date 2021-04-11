package com.github.micro.orm;

import com.github.chat.config.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomJdbcTemplate {

    private final DatabaseConfig dataSource;

    public CustomJdbcTemplate(DatabaseConfig dataSource) {
        this.dataSource = dataSource;
    }

    public <T> Collection<T> findAll(String query, CustomRowMapper<T> rm, Object... params) throws SQLException {
        List<T> result = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rm.rowMap(rs));
            }
        }
        return result;
    }

    public <T> T findBy(String query, CustomRowMapper<T> rm, Object... params) throws SQLException {
        T result = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = rm.rowMap(rs);
            }
        }
        return result;
    }

    public <T> T find(String query, CustomRowExtractor<T> re, Object... params) throws SQLException {
        T result = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            result = re.extract(rs);
        }
        return result;
    }

    public <T> T insert(String query, CustomRowMapper<T> rm, Object... params) throws SQLException {
        T result = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            int row = stmt.executeUpdate();
            if (row != 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                result = rm.rowMap(rs);
            }
        }
        return result;
    }

    public void update(String query, Object... params) throws SQLException {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            if (params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
                stmt.execute();
            }
        }
    }

    public <T> void delete(String query, Object... params) throws SQLException {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)){
            if (params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
                stmt.execute();
            }
            stmt.executeUpdate();
        }
    }

}
