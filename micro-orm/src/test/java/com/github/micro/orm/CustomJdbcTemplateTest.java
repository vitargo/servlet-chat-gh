package com.github.micro.orm;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class CustomJdbcTemplateTest {

    CustomJdbcTemplate jdbcTemplate;
    CustomRowMapper rowMapper;

    @Test
    public void findAll() {
        Collection act = null;
        act = jdbcTemplate.findAll("SELECT * FROM ", rowMapper);
        System.out.println(act + "jj");
    }

}