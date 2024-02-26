package com.sberbank.postgretest.embedded.zonky;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import io.zonky.test.db.postgres.junit.EmbeddedPostgresRules;
import io.zonky.test.db.postgres.junit.SingleInstancePostgresRule;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class SimpleSingleInstanceZonkyTest {

	/**
	 * Rule to create instance for test cases
	 */
	@Rule
	public SingleInstancePostgresRule postgresRule = EmbeddedPostgresRules.singleInstance();

	/**
	 * Simple test with select 3 numbers
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void simpleSelectTest() throws SQLException, IOException {
		try (EmbeddedPostgres embeddedPostgres = EmbeddedPostgres.builder().start();
			 Connection connection = embeddedPostgres.getPostgresDatabase().getConnection()) {

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT 1, 2, 3");

			//Получаем первую строку - ожидаем true
			Assertions.assertTrue(resultSet.next());
			//Проверяем данные в строке по колонкам
			Assertions.assertEquals(1, resultSet.getInt(1));
			Assertions.assertEquals(2, resultSet.getInt(2));
			Assertions.assertEquals(3, resultSet.getInt(3));
			//Больше строк не ожидаем
			Assertions.assertFalse(resultSet.next());
		}
	}

}
