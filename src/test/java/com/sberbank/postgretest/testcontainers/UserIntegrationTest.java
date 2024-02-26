package com.sberbank.postgretest.testcontainers;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

///todo: Разобраться с performQuery
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserIntegrationTest {

    /*@Test
    public void simpleTest() throws SQLException {
        try (PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:9.6.12"))) {
            postgres.start();

            ResultSet resultSet = performQuery(postgres, "SELECT 1");
            int resultSetInt = resultSet.getInt(1);
            assertThat(resultSetInt).as("A basic SELECT query succeeds").isEqualTo(1);
            assertHasCorrectExposedAndLivenessCheckPorts(postgres);
        }
    }

    private void assertHasCorrectExposedAndLivenessCheckPorts(PostgreSQLContainer<?> postgres) {
        assertThat(postgres.getExposedPorts()).containsExactly(PostgreSQLContainer.POSTGRESQL_PORT);
        assertThat(postgres.getLivenessCheckPortNumbers())
                .containsExactly(postgres.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT));
    }*/
}
