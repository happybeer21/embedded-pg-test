package com.sberbank.postgretest.embedded.zonky;

import io.zonky.test.db.postgres.embedded.DatabaseConnectionPreparer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SimplePreparer implements DatabaseConnectionPreparer {

    private final String name;

    public SimplePreparer(String name) {
        this.name = name;
    }

    @Override
    public void prepare(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(
                "CREATE TABLE %s (" +
                        "id integer, " +
                        "name varchar(40), " +
                        "email varchar(40)" +
                        ")", name))) {
            preparedStatement.execute();
        }
    }
}
