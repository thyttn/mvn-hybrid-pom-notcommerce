package commons;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqLConnUtils {
	public static Connection getMySQLConnection() throws SQLException {
		String hostName = "localhost";
		String dbName = "automationfc";
		String userName = "root";
		String password = "123456";
		return getMySQLConnection(hostName, dbName, userName, password);
	}
	
	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException {
		Connection conn = null;
		try {
			String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			conn = DriverManager.getConnection(connectionUrl, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return conn;
	}
}
