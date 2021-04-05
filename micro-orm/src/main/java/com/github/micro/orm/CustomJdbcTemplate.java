package com.github.micro.orm;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomJdbcTemplate {

    private final DataSource dataSource;

    public CustomJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * public List<Person> findAll() {
     *         List<Person> result = new ArrayList<>();
     *         Connection connection = this.pool.connection();
     *         String sql = "SELECT * FROM people";
     *         try (PreparedStatement statement = connection.prepareStatement(sql)) {
     *             ResultSet resultSet = statement.executeQuery();
     *             while (resultSet.next()) {
     *                 Person p = new Person(
     *                         resultSet.getLong("id"),
     *                         resultSet.getString("first_name"),
     *                         resultSet.getString("last_name"),
     *                         resultSet.getInt("age"),
     *                         resultSet.getString("city")
     *                 );
     *                 result.add(p);
     *             }
     *         } catch (SQLException e) {
     *             throw new CrudException(e.getMessage());
     *         } finally {
     *             this.pool.parking(connection);
     *         }
     *
     *         return result.stream()
     *                 .sorted(Comparator.comparingLong(Person::getId))
     *                 .collect(Collectors.toList());
     *     }
     */
    public <T> Collection<T> findAll(String query, CustomRowMapper<T> rm, Object... params) {
        List<T> result = new ArrayList<>();
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            if (params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rm.rowMap(rs));
            }
        } catch (SQLException e) {
            System.out.printf("Message %s \n", e.getMessage());
        }
        return result;
    }

    public <T> T findBy(String query, CustomRowMapper<T> rm, Object... params) {
        T result = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            if (params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = rm.rowMap(rs);
            }
        } catch (SQLException e) {
            System.out.printf("Message %s \n", e.getMessage());
        }
        return result;
    }

    public <T> T find(String query, CustomRowExtractor<T> re, Object... params) {
        T result = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            if (params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            result = re.extract(rs);
        } catch (SQLException e) {
            System.out.printf("Message %s \n", e.getMessage());
        }
        return result;
    }

    public <T> T insert(String query, CustomRowMapper<T> rm, Object... params) {
        T result = null;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            if (params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
            }
            int row = stmt.executeUpdate();
            if (row != 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                result = rm.rowMap(rs);
            }
        } catch (SQLException e) {
            System.out.printf("Message %s \n", e.getMessage());
        }
        return result;
    }
    /**
     * Connection connection = this.pool.connection();
     *         String sql = "UPDATE people SET first_name = ?, last_name = ?, age = ?, city = ? WHERE id = ?";
     *         try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
     *             statement.setString(1, p.getFirstName());
     *             statement.setString(2, p.getLastName());
     *             statement.setInt(3, p.getAge());
     *             statement.setString(4, p.getCity());
     *             statement.setLong(5, id);
     *             statement.executeUpdate();
     *         } catch (SQLException e) {
     *             throw new CrudException(e.getMessage());
     *         } finally {
     *             this.pool.parking(connection);
     *         }
     */
    public void update(String query, Object... params) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            if (params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
                ResultSet rs = stmt.executeQuery();

            }
        } catch (SQLException e) {
            System.out.printf("Message %s \n", e.getMessage());
        }
    }

    /**
     * Connection connection = this.pool.connection();
     *         String sql = "DELETE FROM people WHERE id = ?";
     *         try (PreparedStatement statement = connection.prepareStatement(sql)) {
     *             statement.setLong(1, id);
     *             statement.executeUpdate();
     *         } catch (SQLException e) {
     *             throw new CrudException(e.getMessage());
     *         } finally {
     *             this.pool.parking(connection);
     *         }
     */
    public <T> void delete(String query, CustomRowExtractor<T> re, Object... params) {
        try (Connection connection = this.dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query)){
            if (params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
                stmt.execute();
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Message %s \n", e.getMessage());
        }
    }

}
