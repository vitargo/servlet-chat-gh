package com.github.chat.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {

    private static HikariConfig config = new HikariConfig();

    private static HikariDataSource dataSource;

    static {
        config.setJdbcUrl( "jdbc:postgresql://localhost:5432/servlet_chat" );
        config.setUsername( "user" );
        config.setPassword( "password" );
        dataSource = new HikariDataSource( config );
    }

    public static HikariConfig getConfig() {
        return config;
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

}
