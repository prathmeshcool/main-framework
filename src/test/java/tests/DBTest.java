package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest {

    @BeforeClass
    public void setupDB() {
        DBUtils.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT, email TEXT)");
        DBUtils.executeUpdate("DELETE FROM users");
        DBUtils.executeUpdate("INSERT INTO users (name, email) VALUES ('Alice', 'alice@test.com')");
        DBUtils.executeUpdate("INSERT INTO users (name, email) VALUES ('Bob', 'bob@test.com')");
    }

    @Test(groups = {"regression","db"})
    public void verifyUserCount() throws SQLException {
        ResultSet rs = DBUtils.executeQuery("SELECT COUNT(*) AS total FROM users");
        rs.next();
        int count = rs.getInt("total");
        Assert.assertEquals(count, 2, "User count should be 2");
    }

    @Test(groups = {"regression","db"})
    public void verifyAliceExists() throws SQLException {
        ResultSet rs = DBUtils.executeQuery("SELECT email FROM users WHERE name='Alice'");
        rs.next();
        String email = rs.getString("email");
        Assert.assertEquals(email, "alice@test.com");
    }
}
