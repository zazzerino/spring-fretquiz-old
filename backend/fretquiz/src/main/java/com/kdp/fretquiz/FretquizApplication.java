package com.kdp.fretquiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class FretquizApplication implements CommandLineRunner
{
    private static final Logger log = LoggerFactory.getLogger(FretquizApplication.class);
    private final JdbcTemplate jdbcTemplate;

    public FretquizApplication(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(FretquizApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        log.info("creating tables...");
        createTables();
    }

    private void createTables()
    {
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
        jdbcTemplate.execute("""
                CREATE TABLE users
                (id SERIAL, session_id TEXT, name TEXT, game_id INTEGER)""");
    }
}
