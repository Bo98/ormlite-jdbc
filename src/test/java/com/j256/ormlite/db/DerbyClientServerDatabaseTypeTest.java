package com.j256.ormlite.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import org.junit.Test;

import java.sql.SQLException;

public class DerbyClientServerDatabaseTypeTest extends DerbyEmbeddedDatabaseTypeTest {

	@Override
	protected void setDatabaseParams() throws SQLException {
		databaseUrl = "jdbc:derby://localhost:1527/MyDbTest;create=true";
		connectionSource = new JdbcConnectionSource(DEFAULT_DATABASE_URL);
		databaseType = new DerbyClientServerDatabaseType();
	}

	@Test
	public void testGetClientServerDriverClassName() {
		assertEquals("org.apache.derby.jdbc.ClientDriver", new DerbyClientServerDatabaseType().getDriverClassName());
	}

	@Test
	public void testIsDatabaseUrlThisType() {
		assertTrue(new DerbyClientServerDatabaseType()
				.isDatabaseUrlThisType("jdbc:derby://localhost:1527/MyDbTest;create=true", "derby"));
		assertFalse(new DerbyClientServerDatabaseType().isDatabaseUrlThisType("jdbc:derby:database", "derby"));
	}

	@Test
	public void testIsNestedSavePointsSupported() {
		assertFalse(databaseType.isNestedSavePointsSupported());
	}
}
