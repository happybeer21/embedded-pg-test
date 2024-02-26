package com.sberbank.postgretest.embedded;

import com.sberbank.postgretest.embedded.SimplePreparer;
import io.zonky.test.db.postgres.embedded.DatabasePreparer;
import io.zonky.test.db.postgres.junit5.EmbeddedPostgresExtension;
import io.zonky.test.db.postgres.junit5.PreparedDbExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreparerTest {

    private static final String tableName = "peoples";

    //Таблица
    private static final DatabasePreparer peoplesPreparer = new SimplePreparer(tableName);
    @RegisterExtension
    private static final PreparedDbExtension peopleExtension = EmbeddedPostgresExtension.preparedDatabase(peoplesPreparer);


    @Test
    public void insertSelectTest() throws SQLException {
        try (Connection connection = peopleExtension.getTestDatabase().getConnection();
             Statement statement = connection.createStatement()){

            //insert to DB
            insertToDB(statement);
            checkInsertion(statement);
        }
    }

    public void insertToDB(final Statement statement) {
        //Statement statement = connection.createStatement();
        String[] names = {"Alex", "Tom", "Cat", "Alice", "Kate"};
        String[] emails = {"a1@spasibosb.ru", "t1@spasibosb.ru", "c1@spasibosb.ru", "a2@spasibosb.ru", "k1@spasibosb.ru"};

        for (int i = 0; i < names.length; i++) {
            try {
                statement.execute(String.format("INSERT INTO %s VALUES (%d, '%s', '%s')", tableName, i + 1, names[i], emails[i]));
            } catch (SQLException throwables) {
                System.out.println("Insert exception: " + throwables);
            }
        }
    }

    public void checkInsertion(final Statement statement) {
        //Statement statement = connection.createStatement();
        int result = 0;
        try {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT COUNT(1) FROM %s", tableName));
            Assertions.assertTrue(resultSet.next());

            result = resultSet.getInt(1);
            Assertions.assertEquals(5, result);

        } catch (SQLException throwables) {
            System.out.println("Check insert exception: " + throwables);
        }

        System.out.println("Expected: 5, Result: " + result);
    }
}
