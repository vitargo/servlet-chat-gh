package com.github.micro.orm;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface use for map single entity for my sql database.
 *
 * @param <T> your entity
 */
public interface CustomRowMapper<T> {
    T rowMap(ResultSet rs) throws SQLException;
}
