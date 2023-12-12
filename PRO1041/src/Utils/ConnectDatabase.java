package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabase {
    public static Connection getConnection() {
	    Connection conn = null;
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        conn = DriverManager.getConnection("jdbc:sqlserver://DoanThanhBut\\DOANTHANHBUT:1433;user=NHOM4;password=123456;databaseName=SeafrontHotel;encrypt=false");
//	        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=12345;databaseName=SeafrontHotel;encrypt=false");
	    } catch (Exception ex) {
	        System.out.println("connect failure!");
	        ex.printStackTrace();
	    }
	    return conn;
	}
}
