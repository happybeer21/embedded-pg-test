package com.sberbank.postgretest.embedded.opentable;

import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class SimpleSingleInstanceOpenTableTest {
    @Rule
    public SingleInstancePostgresRule epg = EmbeddedPostgresRules.singleInstance();

    @Test
    public void testRule() throws Exception {
        try (Connection c = epg.getEmbeddedPostgres().getPostgresDatabase().getConnection()) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT 1");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt(1));
            assertFalse(rs.next());
        }
    }
}
